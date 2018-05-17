package com.vsoft.mysoftware.service;

import java.time.Instant;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.Resource;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.vsoft.mysoftware.domain.Muser;
import com.vsoft.mysoftware.repository.MuserRepository;
import com.vsoft.mysoftware.service.dto.MuserDTO;
import com.vsoft.mysoftware.service.util.RandomUtil;
import com.vsoft.mysoftware.service.util.StorageUtil;

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
	
	private final StorageUtil storageUtil;
	
	public MuserService(
			MuserRepository muserRepository, 
			PasswordEncoder passwordEncoder,
			StorageUtil storageUtil){
		this.muserRepository = muserRepository;
		this.passwordEncoder = passwordEncoder;
		this.storageUtil= storageUtil;
	}

	public Muser createUser(MuserDTO muserDTO){
		
		Muser muser = new Muser();
		
		muser.setLogin(muserDTO.getLogin());
		muser.setFirstName(muserDTO.getFirstName());
		muser.setLastName(muserDTO.getLastName());
		muser.setEmail(muserDTO.getEmail());
		
		String encryptedPassword = passwordEncoder.encode(muserDTO.getPassword());
		muser.setPassword(encryptedPassword);
		muser.setActivated(muserDTO.isActivated());
		muser.setResetKey(RandomUtil.generateResetKey());
		muser.setResetDate(Instant.now());
		muserRepository.save(muser);
		log.debug("Created Information for User: {}", muser);
        return muser;
	}
	
	public Muser createUserAndFile(String data, MultipartFile file) 
			throws Exception{
		
		//create ObjectMapper instance
		ObjectMapper objectMapper = new ObjectMapper();
		
		//convert json string to object
		MuserDTO muserDTO = objectMapper.readValue(data, MuserDTO.class);
		
		//simpen data user
		Muser muser = createUser(muserDTO);
		
		//simpen photo profile
		if(!file.isEmpty() && muser.getId() != null){
			this.storageUtil.store(file, muser.getId(), FOLDER_PATH);
		}

		
		String tipeFile = file.getOriginalFilename().split("\\.")[1];
		String imageUrl = muser.getId()+"."+tipeFile;
		
		muser.setImageUrl(imageUrl);
		
		Muser result = muserRepository.save(muser);
		
		return result;
	}

}
