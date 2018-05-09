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

import com.vsoft.mysoftware.domain.Mmenu;
import com.vsoft.mysoftware.repository.MmenuRepository;
import com.vsoft.mysoftware.service.dto.MmenuDTO;

@Service
@Transactional
public class MmenuService {

	private final MmenuRepository mmenuRepository;
	private final ModelMapper modelMapper;
	
	public MmenuService(
			MmenuRepository mmenuRepository,
			ModelMapper modelMapper
			){
		this.mmenuRepository = mmenuRepository;
		this.modelMapper = modelMapper;
	}
	
	@Transactional
	public MmenuDTO save(MmenuDTO mmenuDTO) {
		Mmenu mmenu = this.modelMapper.map(mmenuDTO, Mmenu.class);
		return this.modelMapper.map(mmenuRepository.save(mmenu), MmenuDTO.class);
	} 
	
	@Transactional
	public void deleteById(String id){
		mmenuRepository.deleteById(id);
	}
	
	@Transactional(readOnly = true)
	public Page<MmenuDTO> findAll(Pageable pageable){
		
//		Page<Mmenu> result = 	mmenuRepository.findAll(pageable).map(MmenuDTO::new);
//		for(Mmenu mmenu:result){
//			Hibernate.initialize(mmenu.getMgroupmenu().getMheadermenu());
//		}
		
//		return result.map(x->this.modelMapper.map(x, MmenuDTO.class));
		return mmenuRepository.findAll(pageable).map(MmenuDTO::new);
	}
	
	
	@Transactional(readOnly = true)
	public List<MmenuDTO> findAll(){
		List<Mmenu> mmenus = mmenuRepository.findAll();
		List<MmenuDTO> mmenuDTOs = new ArrayList<MmenuDTO>();
		
		for(Mmenu mmenu:mmenus){
			MmenuDTO mmenuDTO = this.modelMapper.map(mmenu, MmenuDTO.class);
			mmenuDTOs.add(mmenuDTO);
		}
		
		return mmenuDTOs;
	}
	
	@Transactional(readOnly = true)
	public Optional<MmenuDTO> findById(String id) {
		return mmenuRepository.findById(id).map(x->this.modelMapper.map(x, MmenuDTO.class));
	}
	
	@Transactional(readOnly = true)
	public Page<MmenuDTO> findLikeKode(Pageable pageable, String kode) {
		kode = kode +"%";
		return mmenuRepository.findByKodeLike(pageable, kode).map(x->this.modelMapper.map(x, MmenuDTO.class));
	}
	
	@Transactional(readOnly = true)
	public Page<MmenuDTO> findLikeUraian(Pageable pageable, String uraian) {
		uraian = uraian +"%";
		return mmenuRepository.findByMenuLike(pageable, uraian).map(x->this.modelMapper.map(x, MmenuDTO.class));
	}
}
