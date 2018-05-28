package com.vsoft.mysoftware.service;


import java.util.Collection;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.vsoft.mysoftware.domain.Mmenu;
import com.vsoft.mysoftware.repository.MmenuRepository;
import com.vsoft.mysoftware.service.dto.MmenuDTO;
import com.vsoft.mysoftware.service.projection.MmenuProj;

@Service
@Transactional
public class MmenuService {

	private final MmenuRepository mmenuRepository;
	
	public MmenuService(
			MmenuRepository mmenuRepository
			){
		this.mmenuRepository = mmenuRepository;
	}
	
	public Mmenu create(MmenuDTO mmenuDTO){
		Mmenu mmenu = new Mmenu();
		
		mmenu.setKode(mmenuDTO.getKode());
		mmenu.setMenu(mmenuDTO.getMenu());
		mmenu.setObjek(mmenuDTO.getObjek());
		mmenu.setAllowedf(mmenuDTO.isAllowedf());
		mmenu.setUrutan(mmenuDTO.getUrutan());
		mmenu.setMgroupmenu(mmenuDTO.getMgroupmenu());
		return mmenuRepository.save(mmenu);
	}
	
	public Mmenu update(MmenuDTO mmenuDTO){
		
		return mmenuRepository.findById(mmenuDTO.getId())
		.map(mmenu ->{
			mmenu.setKode(mmenuDTO.getKode());
			mmenu.setMenu(mmenuDTO.getMenu());
			mmenu.setObjek(mmenuDTO.getObjek());
			mmenu.setAllowedf(mmenuDTO.isAllowedf());
			mmenu.setUrutan(mmenuDTO.getUrutan());
			mmenu.setMgroupmenu(mmenuDTO.getMgroupmenu());
			return mmenu;
		}).get();
		
	}
	
	
	public void deleteById(String id){
		mmenuRepository.deleteById(id);
	}
	
	@Transactional(readOnly = true)
	public Page<MmenuProj> findAll(Pageable pageable){
		return mmenuRepository.findPagedProjectedBy(pageable, MmenuProj.class);
	}
	
	@Transactional(readOnly = true)
	public Collection<Mmenu> findAll(){
		return mmenuRepository.findAll();
	}
	
	@Transactional(readOnly = true)
	public Optional<MmenuProj> findById(String id) {
		return mmenuRepository.findOneById(id);
	}
	
	@Transactional(readOnly = true)
	public Page<MmenuProj> findByKodeLike(Pageable pageable, String kode) {
		kode = kode +"%";
		return mmenuRepository.findByKodeLike(pageable, kode, MmenuProj.class);
	}
	
	@Transactional(readOnly = true)
	public Page<MmenuProj> findByMenuLike(Pageable pageable, String uraian) {
		uraian = uraian +"%";
		return mmenuRepository.findByMenuLike(pageable, uraian, MmenuProj.class);
	}
}
