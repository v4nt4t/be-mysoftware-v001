package com.vsoft.mysoftware.service;

import java.util.Collection;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.vsoft.mysoftware.domain.Mgroupmenu;
import com.vsoft.mysoftware.repository.MgroupmenuRepository;
import com.vsoft.mysoftware.service.dto.MgroupmenuDTO;
import com.vsoft.mysoftware.service.projection.MgroupmenuProj;

@Service
@Transactional
public class MgroupmenuService {
	
	private final MgroupmenuRepository mgroupmenuRepository;

	public MgroupmenuService(
			MgroupmenuRepository mgroupmenuRepository){
		this.mgroupmenuRepository = mgroupmenuRepository;
	}
	
	public Mgroupmenu create(MgroupmenuDTO mgroupmenuDTO){
		Mgroupmenu mgroupmenu = new Mgroupmenu();

		mgroupmenu.setKode(mgroupmenuDTO.getKode());
		mgroupmenu.setGroupmenu(mgroupmenuDTO.getGroupmenu());
		mgroupmenu.setAllowedf(mgroupmenuDTO.getAllowedf());
		mgroupmenu.setUrutan(mgroupmenuDTO.getUrutan());
		mgroupmenu.setMheadermenu(mgroupmenuDTO.getMheadermenu());
		
		return mgroupmenuRepository.save(mgroupmenu);
	}
	
	public Mgroupmenu update(MgroupmenuDTO mgroupmenuDTO){
		return mgroupmenuRepository.findById(mgroupmenuDTO.getId())
			.map(mgroupmenu-> {
			
			mgroupmenu.setKode(mgroupmenuDTO.getKode());
			mgroupmenu.setGroupmenu(mgroupmenuDTO.getGroupmenu());
			mgroupmenu.setAllowedf(mgroupmenuDTO.getAllowedf());
			mgroupmenu.setUrutan(mgroupmenuDTO.getUrutan());
			mgroupmenu.setMheadermenu(mgroupmenuDTO.getMheadermenu());
			
			return mgroupmenu;
		}).get();

	}	
	
	public void deleteById(String id){
		mgroupmenuRepository.deleteById(id);
	}
	
	@Transactional(readOnly = true)
	public Page<MgroupmenuProj> findAll(Pageable pageable) {
		return mgroupmenuRepository.findPagedProjectedBy(pageable, MgroupmenuProj.class);
	}
	
	@Transactional(readOnly = true)
	public Collection<Mgroupmenu> findAll(){
		return mgroupmenuRepository.findAll();
	}
	
	@Transactional(readOnly = true)
	public Optional<MgroupmenuProj> findById(String id) {
		return mgroupmenuRepository.findOneById(id, MgroupmenuProj.class);
	}
	
	@Transactional(readOnly = true)
	public Page<MgroupmenuProj> findByKodeLike(Pageable pageable, String kode) {
		kode = kode +"%";
		return mgroupmenuRepository.findByKodeLike(pageable, kode, MgroupmenuProj.class);
	}
	
	@Transactional(readOnly = true)
	public Page<MgroupmenuProj> findByGroupmenuLike(Pageable pageable, String uraian) {
		uraian = uraian +"%";
		return mgroupmenuRepository.findByGroupmenuLike(pageable, uraian, MgroupmenuProj.class);
	}
	
}
