package com.vsoft.mysoftware.service;


import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.vsoft.mysoftware.domain.Mmenuuser;
import com.vsoft.mysoftware.repository.MmenuuserRepository;
import com.vsoft.mysoftware.service.dto.MmenuuserDTO;
import com.vsoft.mysoftware.service.projection.MmenuuserOnyWithMmenuProj;
import com.vsoft.mysoftware.service.projection.MmenuuserProj;

@Service
@Transactional
public class MmenuuserService {
	
	private final MmenuuserRepository mmenuuserRepository;
	
	public MmenuuserService(
			MmenuuserRepository mmenuuserRepository
			) {
		this.mmenuuserRepository = mmenuuserRepository;
	}
	
	public Mmenuuser create(MmenuuserDTO mmenuuserDTO){
		
		Mmenuuser mmenuuser = new Mmenuuser();
		mmenuuser.setAllowedf(mmenuuserDTO.isAllowedf());
		mmenuuser.setMuser(mmenuuserDTO.getMuser());
		mmenuuser.setMmenu(mmenuuserDTO.getMmenu());
		mmenuuserRepository.save(mmenuuser);
		return mmenuuser;
	}
	
	public Mmenuuser update(MmenuuserDTO mmenuuserDTO) {
		return mmenuuserRepository.findById(mmenuuserDTO.getId())
		.map(mmenuuser ->{
			
			mmenuuser.setAllowedf(mmenuuserDTO.isAllowedf());
			mmenuuser.setMuser(mmenuuserDTO.getMuser());
			mmenuuser.setMmenu(mmenuuserDTO.getMmenu());
			return mmenuuser;
		}).get();
	}
	
	public void deleteById(String id) {
		mmenuuserRepository.deleteById(id);
	}
	
	@Transactional(readOnly = true)
	public Page<MmenuuserOnyWithMmenuProj> findByMuserId(Pageable pageable, String muserId){
		return mmenuuserRepository.findByMuserId(pageable, muserId, MmenuuserOnyWithMmenuProj.class);
	}
	
	@Transactional(readOnly = true)
	public Optional<MmenuuserProj> findById(String id){
		return mmenuuserRepository.findOneById(id, MmenuuserProj.class);
	}
	
	
}
