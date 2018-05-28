package com.vsoft.mysoftware.service;

import java.io.IOException;
import java.time.Instant;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.vsoft.mysoftware.domain.Mauthority;
import com.vsoft.mysoftware.domain.Muser;
import com.vsoft.mysoftware.repository.MauthoritiesRepository;
import com.vsoft.mysoftware.repository.MuserRepository;
import com.vsoft.mysoftware.security.AuthoritiesConstants;
import com.vsoft.mysoftware.security.SecurityUtils;
import com.vsoft.mysoftware.service.dto.MuserDTO;
import com.vsoft.mysoftware.service.util.RandomUtil;
import com.vsoft.mysoftware.service.util.StorageUtil;
import com.vsoft.mysoftware.web.rest.error.BadRequestAlertException;
import com.vsoft.mysoftware.web.rest.error.EmailAlreadyUsedException;
import com.vsoft.mysoftware.web.rest.error.LoginAlreadyUsedException;

/**
 * Service class for managing users.
 */
@Service
@Transactional
public class MuserService {
	
	private final Logger log = LoggerFactory.getLogger(MuserService.class);
	
	private static final String FOLDER_PATH = "profile-dir";
	
	private final PasswordEncoder passwordEncoder;
	
	private final MuserRepository muserRepository;
	
	private final MauthoritiesRepository mauthoritiesRepository;
	
	private final StorageUtil storageUtil;
	
	public MuserService(
			MuserRepository muserRepository, 
			PasswordEncoder passwordEncoder,
			StorageUtil storageUtil,
			MauthoritiesRepository mauthoritiesRepository){
		this.muserRepository = muserRepository;
		this.passwordEncoder = passwordEncoder;
		this.storageUtil= storageUtil;
		this.mauthoritiesRepository = mauthoritiesRepository;
	}

	public MuserDTO createUser(MuserDTO muserDTO){
		
		Muser muser = new Muser();
		
		muser.setLogin(muserDTO.getLogin());
		muser.setFirstName(muserDTO.getFirstName());
		muser.setLastName(muserDTO.getLastName());
		muser.setEmail(muserDTO.getEmail());
		
		String encryptedPassword = passwordEncoder.encode(RandomUtil.generatePassword());
		muser.setPassword(encryptedPassword);
		muser.setActivated(muserDTO.isActivated());
		muser.setResetKey(RandomUtil.generateResetKey());
		muser.setResetDate(Instant.now());
		
		//set Authorities jika ada
		if (muserDTO.getMauthorities() != null){
			Set<Mauthority> mauthorities = muserDTO.getMauthorities().stream()
					.map(mauthoritiesRepository::getOne)
					.collect(Collectors.toSet());
	
			muser.setMauthorities(mauthorities);
		//set Authorities jika tidak dipilih ada
		}else{
			Mauthority mauthority = mauthoritiesRepository.getOne(AuthoritiesConstants.USER);
			Set<Mauthority> mauthorities = new HashSet<>();
			mauthorities.add(mauthority);
			muser.setMauthorities(mauthorities);
		}
		
		muserRepository.save(muser);
		log.debug("Created Information for User: {}", muser);
		
        return new MuserDTO(muser);
	}
	
	public MuserDTO updateUser(MuserDTO muserDTO){
		return muserRepository
				.findById(muserDTO.getId())
				.map(user -> {
					user.setLogin(muserDTO.getLogin());
					user.setEmail(muserDTO.getEmail());
					user.setFirstName(muserDTO.getFirstName());
					user.setLastName(muserDTO.getLastName());
					user.setActivated(muserDTO.isActivated());
					user.setMauthorities(muserDTO.getMauthorities().stream()
							.map(mauthoritiesRepository::getOne)
							.collect(Collectors.toSet()));
					log.debug("Update Information for User: {}", user);
					return user;
				}).map(MuserDTO::new).get();	
	}
	
	public MuserDTO createUserAndFile(String data, MultipartFile file) throws IOException, NullPointerException{
		
		//create ObjectMapper instance
		ObjectMapper objectMapper = new ObjectMapper();
		
		//convert json string to object
		MuserDTO muserDTO = objectMapper.readValue(data, MuserDTO.class);

		System.out.println(data);
		
		MuserDTO muser = new MuserDTO();

		//jika sudah ada id update jika belum buat baru
		if(muserDTO.getId()!=null){
			muser = updateUser(muserDTO);
		// Lowercase the user login before comparing with database
		}else if(muserRepository.findOneByLogin(muserDTO.getLogin().toLowerCase()).isPresent()){
			throw new LoginAlreadyUsedException();
		}else if(muserRepository.findOneByEmailIgnoreCase(muserDTO.getEmail()).isPresent()){
			throw new EmailAlreadyUsedException();
		}else{
			muser = createUser(muserDTO);
		}
		
		MuserDTO result = new MuserDTO();
		if (muser.getId() != null){
		
			//simpen photo profile
			if (file == null){
				result = muser;
			}else{
				
				//simpan file
				this.storageUtil.store(file, muser.getId(), FOLDER_PATH);
				
				//set image url
				String tipeFile = file.getOriginalFilename().split("\\.")[1];
				String imageUrl = FOLDER_PATH + "/" + muser.getId() + "." + tipeFile;
				
				Muser m = muserRepository.getOne(muser.getId());
				m.setImageUrl(imageUrl);
				result = new MuserDTO(muserRepository.save(m));
			}
		}
		
		log.debug("Created/update Information for User: {}", result);		
		return result;
		
	}

	public void deleteUserWithFile(String id){
		
		Muser muser = muserRepository.getOne(id);
		
		muserRepository.deleteById(id);
		this.storageUtil.deleteFile(muser.getImageUrl());
		
	}
	
	public void adminResetPassword(String muserId, String password){
		
		muserRepository.findById(muserId).ifPresent(muser -> {
			String encryptedPassword = passwordEncoder.encode(password);
			muser.setPassword(encryptedPassword);
			log.debug("admin reset password", muser);
		});
		
	}
	
    @Transactional(readOnly = true)
    public Optional<Muser> getUserWithAuthorities() {
        return SecurityUtils.getCurrentUserLogin().flatMap(muserRepository::findOneWithMauthoritiesByLogin);
    }
    
    @Transactional(readOnly = true)
    public List<String> getAuthorities(){
    	return mauthoritiesRepository.findAll().stream().map(Mauthority::getName).collect(Collectors.toList());
    }
}
