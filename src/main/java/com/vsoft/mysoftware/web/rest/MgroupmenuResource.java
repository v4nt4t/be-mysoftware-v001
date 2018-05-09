package com.vsoft.mysoftware.web.rest;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vsoft.mysoftware.domain.Mgroupmenu;
import com.vsoft.mysoftware.repository.MgroupmenuRepository;
import com.vsoft.mysoftware.service.projection.MgroupmenuListProj;
import com.vsoft.mysoftware.service.projection.MgroupmenuProj;
import com.vsoft.mysoftware.web.rest.error.BadRequestAlertException;
import com.vsoft.mysoftware.web.rest.util.HeaderUtil;
import com.vsoft.mysoftware.web.rest.util.PaginationUtil;

@RestController
@RequestMapping("/api")
public class MgroupmenuResource {

	private static final String ENTITY_NAME = "mgroupmenus";
	
	private final MgroupmenuRepository mgroupmenuRepository;
	
	public MgroupmenuResource(
			MgroupmenuRepository mgroupmenuRepository
	) {
		this.mgroupmenuRepository = mgroupmenuRepository;
	}
	
	@PostMapping("/mgroupmenus")
	public ResponseEntity<Mgroupmenu> create(@Valid @RequestBody Mgroupmenu mgroupmenu)
		throws URISyntaxException{
		
		if(mgroupmenu.getId() != null){
			throw new BadRequestAlertException("ID sudah ada data mgroupmenu tidak bisa di input", ENTITY_NAME, "idexists");
		}
		
		Mgroupmenu result = mgroupmenuRepository.save(mgroupmenu);
		return ResponseEntity.created(new URI("/api/mgroupmenus/" + result.getId()))
				.headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
	            .body(result);
	}
	
	@PutMapping("/mgroupmenus")
	public ResponseEntity<Mgroupmenu> update(@Valid @RequestBody Mgroupmenu mgroupmenu)
		throws URISyntaxException{
		
		if(mgroupmenu.getId() == null){
			return create(mgroupmenu);
		}
		
		Mgroupmenu result = mgroupmenuRepository.save(mgroupmenu);
		return ResponseEntity.ok()
				.headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
	            .body(result);
	}
	
	@DeleteMapping("/mgroupmenus/{id}")
	public ResponseEntity<Mgroupmenu> deleteById(@PathVariable String id){
		
		mgroupmenuRepository.deleteById(id);
		return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id)).build();
		
	}
	
	@GetMapping("/mgroupmenus")
	public ResponseEntity<List<MgroupmenuListProj>> findAll(Pageable pageable) {
		
		Page<MgroupmenuListProj> page = this.mgroupmenuRepository.findPagedProjectedBy(pageable, MgroupmenuListProj.class);
		HttpHeaders httpHeaders = PaginationUtil.generatePaginationHttpHeaders(page, "api/mgroupmenus");
		return new ResponseEntity<>(page.getContent(), httpHeaders, HttpStatus.OK);
	}
	
	@GetMapping("/mgroupmenus/all")
	public ResponseEntity<Collection<MgroupmenuProj>> findAll() {
		Collection<MgroupmenuProj> result = this.mgroupmenuRepository.findAllProjectedBy(MgroupmenuProj.class);
		return new ResponseEntity<>(result, HttpStatus.OK);
	}	
	
	
	@GetMapping("/mgroupmenus/{id}")
	public ResponseEntity<Mgroupmenu> findById(@PathVariable String id){
		
		Optional<Mgroupmenu> mgroupmenu = this.mgroupmenuRepository.findById(id);
		return mgroupmenu.map(
				result ->new ResponseEntity<>(result, HttpStatus.OK))
				.orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
	}
	
	@GetMapping("/mgroupmenus/kode/like/{kode}")
	public ResponseEntity<List<Mgroupmenu>> findLikeKode(Pageable pageable, @PathVariable String kode) {
		
		kode = kode +"%";
		
		Page<Mgroupmenu> page = this.mgroupmenuRepository.findByKodeLike(pageable, kode);
		HttpHeaders httpHeaders = PaginationUtil.generatePaginationHttpHeaders(page, "api/mgroupmenus/kode/like");
		return new ResponseEntity<>(page.getContent(), httpHeaders, HttpStatus.OK);
	}	
	
	@GetMapping("/mgroupmenus/uraian/like/{uraian}")
	public ResponseEntity<List<Mgroupmenu>> findLikeUraian(Pageable pageable, @PathVariable String uraian) {
		
		uraian = uraian +"%";
		
		Page<Mgroupmenu> page = this.mgroupmenuRepository.findByGroupmenuLike(pageable, uraian);
		HttpHeaders httpHeaders = PaginationUtil.generatePaginationHttpHeaders(page, "api/mgroupmenus/uraian/like");
		return new ResponseEntity<>(page.getContent(), httpHeaders, HttpStatus.OK);
	}	
}
