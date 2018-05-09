package com.vsoft.mysoftware.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.hibernate.Hibernate;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.vsoft.mysoftware.domain.Mgroupmenu;
import com.vsoft.mysoftware.repository.MgroupmenuRepository;
import com.vsoft.mysoftware.service.dto.MgroupmenuDTO;

@Service
@Transactional
public class MgroupmenuService {
	
	private final MgroupmenuRepository mgroupmenuRepository;
	private final ModelMapper modelMapper;

	public MgroupmenuService(
			MgroupmenuRepository mgroupmenuRepository,
			ModelMapper modelMapper){
		this.mgroupmenuRepository = mgroupmenuRepository;
		this.modelMapper = modelMapper;
	}
	
	@Transactional
	public void deleteById(String id){
		mgroupmenuRepository.deleteById(id);
	}
	
	@Transactional(readOnly = true)
	public Page<MgroupmenuDTO> findAll(Pageable pageable) {
		
		Page<Mgroupmenu> mgroupmenus = mgroupmenuRepository.findAll(pageable);
		
//		for (Mgroupmenu mgroupmenu: mgroupmenus){
//			Hibernate.initialize(mgroupmenu.getMheadermenu());
//		}
		
		return mgroupmenus.map(x->this.modelMapper.map(x, MgroupmenuDTO.class));
	}
	
	@Transactional(readOnly = true)
	public List<MgroupmenuDTO> findAll(){
		
		List<Mgroupmenu> mgroupmenus = mgroupmenuRepository.findAll();
		List<MgroupmenuDTO> mgroupmenuDTOs = new ArrayList<MgroupmenuDTO>();
		
		for(Mgroupmenu mgroupmenu: mgroupmenus){
			MgroupmenuDTO mgroupmenuDTO = this.modelMapper.map(mgroupmenu, MgroupmenuDTO.class);
			mgroupmenuDTOs.add(mgroupmenuDTO);
		}
		
		return mgroupmenuDTOs;
		
	}
	
	@Transactional(readOnly = true)
	public Optional<MgroupmenuDTO> findById(String id) {
		return mgroupmenuRepository.findById(id).map(x-> this.modelMapper.map(x, MgroupmenuDTO.class));
	}
	
	@Transactional(readOnly = true)
	public Page<MgroupmenuDTO> findLikeKode(Pageable pageable, String kode) {
		kode = kode +"%";
		return mgroupmenuRepository.findByKodeLike(pageable, kode).map(x-> this.modelMapper.map(x, MgroupmenuDTO.class));
	}
	
	@Transactional(readOnly = true)
	public Page<MgroupmenuDTO> findLikeUraian(Pageable pageable, String uraian) {
		uraian = uraian +"%";
		return mgroupmenuRepository.findByGroupmenuLike(pageable, uraian).map(x-> this.modelMapper.map(x, MgroupmenuDTO.class));
	}
	
}
