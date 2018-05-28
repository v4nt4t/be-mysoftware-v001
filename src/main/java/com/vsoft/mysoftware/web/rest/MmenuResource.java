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

import com.vsoft.mysoftware.domain.Mmenu;
import com.vsoft.mysoftware.service.MmenuService;
import com.vsoft.mysoftware.service.dto.MmenuDTO;
import com.vsoft.mysoftware.service.projection.MmenuProj;
import com.vsoft.mysoftware.web.rest.error.BadRequestAlertException;
import com.vsoft.mysoftware.web.rest.util.HeaderUtil;
import com.vsoft.mysoftware.web.rest.util.PaginationUtil;


@RestController
@RequestMapping("/api")
public class MmenuResource {

	private static final String ENTITY_NAME = "mmenu";
	private final MmenuService mmenuService;
	
	private MmenuResource(
			MmenuService mmenuService
			) {
		this.mmenuService = mmenuService;
	}
	
	@PostMapping("/mmenus")
	public ResponseEntity<Mmenu> create(@Valid @RequestBody MmenuDTO mmenuDTO) 
			throws URISyntaxException{
		
		if(mmenuDTO.getId() != null){
			throw new BadRequestAlertException("ID sudah ada, data mmenu tidak bisa di input", ENTITY_NAME, "idexists");
		}
		
		Mmenu result = mmenuService.create(mmenuDTO);
		return ResponseEntity.created(new URI("/api/mmenus/" + result.getId()))
				.headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
				.body(result);
	}
	
	@PutMapping("/mmenus")
	public ResponseEntity<Mmenu> update(@Valid @RequestBody MmenuDTO mmenuDTO) 
			throws URISyntaxException{
	
		if(mmenuDTO.getId() == null){
			create(mmenuDTO);
		}
		
		Mmenu result = mmenuService.update(mmenuDTO);
		return ResponseEntity.ok()
				.headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, result.getId().toString()))
				.body(result);
	}
	
	@DeleteMapping("/mmenus/{id}")
	public ResponseEntity<Mmenu> delete(@PathVariable String id){
		
		mmenuService.deleteById(id);
		return ResponseEntity.ok()
				.headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString()))
				.build();
	}
	
	@GetMapping("/mmenus")
	public ResponseEntity<List<MmenuProj>> findAll(Pageable pageable){
		
		Page<MmenuProj> page = mmenuService.findAll(pageable);
		HttpHeaders httpHeaders = PaginationUtil.generatePaginationHttpHeaders(page, "/api/mmenus");
		
		return new ResponseEntity<>(page.getContent(), httpHeaders, HttpStatus.OK);
	}
	
	@GetMapping("/mmenus/all")
	public ResponseEntity<Collection<Mmenu>> findAll() {
		Collection<Mmenu> result = mmenuService.findAll();
		return new ResponseEntity<>(result, HttpStatus.OK);
	}
	
	@GetMapping("/mmenus/{id}")
	public ResponseEntity<MmenuProj> findById(@PathVariable String id){
		
		Optional<MmenuProj> mmenu = mmenuService.findById(id);
		return mmenu.map(result -> new ResponseEntity<>(result, HttpStatus.OK))
				.orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
	}
	
	@GetMapping("/mmenus/kode/like/{kode}")
	public ResponseEntity<List<MmenuProj>> findLikeKode(Pageable pageable, @PathVariable String kode){
		
		Page<MmenuProj> page = mmenuService.findByKodeLike(pageable, kode);
		HttpHeaders httpHeaders = PaginationUtil.generatePaginationHttpHeaders(page, "/api/mmenus/kode/like");
		return new ResponseEntity<>(page.getContent(), httpHeaders, HttpStatus.OK);
	}
	
	@GetMapping("/mmenus/uraian/like/{uraian}")
	public ResponseEntity<List<MmenuProj>> findLikeUraian(Pageable pageable, @PathVariable String uraian){
		
		Page<MmenuProj> page = mmenuService.findByMenuLike(pageable, uraian);
		HttpHeaders httpHeaders = PaginationUtil.generatePaginationHttpHeaders(page, "/api/mmenus/kode/like");
		return new ResponseEntity<>(page.getContent(), httpHeaders, HttpStatus.OK);
	}
	
}
