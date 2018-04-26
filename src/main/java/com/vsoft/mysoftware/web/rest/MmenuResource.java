package com.vsoft.mysoftware.web.rest;


import java.net.URI;
import java.net.URISyntaxException;
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
import com.vsoft.mysoftware.domain.Mmenu;
import com.vsoft.mysoftware.repository.MmenuRepository;
import com.vsoft.mysoftware.web.rest.error.BadRequestAlertException;
import com.vsoft.mysoftware.web.rest.util.HeaderUtil;
import com.vsoft.mysoftware.web.rest.util.PaginationUtil;


@RestController
@RequestMapping("/api")
public class MmenuResource {

	private static final String ENTITY_NAME = "mmenu";
	private final MmenuRepository mmenuRepository;
	
	private MmenuResource(MmenuRepository mmenuRepository) {
		this.mmenuRepository = mmenuRepository;
	}
	
	@PostMapping("/mmenus")
	public ResponseEntity<Mmenu> createMenu(@Valid @RequestBody Mmenu mmenu) 
			throws URISyntaxException{
		
		if(mmenu.getId() != null){
			throw new BadRequestAlertException("ID sudah ada, data mmenu tidak bisa di input", ENTITY_NAME, "idexists");
		}
		
		Mmenu result = mmenuRepository.save(mmenu);
		return ResponseEntity.created(new URI("/api/mmenus/" + result.getId()))
				.headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
				.body(result);
	}
	
	@PutMapping("/mmenus")
	public ResponseEntity<Mmenu> updateMenu(@Valid @RequestBody Mmenu mmenu) 
			throws URISyntaxException{
		
		if(mmenu.getId() == null){
			createMenu(mmenu);
		}
		
		Mmenu result = mmenuRepository.save(mmenu);
		return ResponseEntity.ok()
				.headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, result.getId().toString()))
				.body(result);
	}
	
	@DeleteMapping("/mmenus/{id}")
	public ResponseEntity<Mmenu> DeleteMenu(@PathVariable String id){
		
		mmenuRepository.deleteById(id);
		return ResponseEntity.ok()
				.headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString()))
				.build();
	}
	
	@GetMapping("/mmenus")
	public ResponseEntity<List<Mmenu>> getMmenus(Pageable pageable){
		
		Page<Mmenu> page = mmenuRepository.findAll(pageable);
		HttpHeaders httpHeaders = PaginationUtil.generatePaginationHttpHeaders(page, "/api/mmenus");
		
		return new ResponseEntity<>(page.getContent(), httpHeaders, HttpStatus.OK);
	}

	@GetMapping("/mmenus/all")
	public ResponseEntity<List<Mmenu>> getMmenus() {
		List<Mmenu> result = this.mmenuRepository.findAll();
		return new ResponseEntity<>(result, HttpStatus.OK);
	}
	
	@GetMapping("/mmenus/{id}")
	public ResponseEntity<Mmenu> getMmenusById(@PathVariable String id){
		
		Optional<Mmenu> mmenu = mmenuRepository.findById(id);
		return mmenu.map(result -> new ResponseEntity<>(result, HttpStatus.OK))
				.orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
	}
	
	@GetMapping("/mmenus/kode/like/{kode}")
	public ResponseEntity<List<Mmenu>> getMmenusByKodeLike(Pageable pageable, @PathVariable String kode){
		
		kode = kode+"%";
		Page<Mmenu> page = mmenuRepository.findByKodeLike(pageable, kode);
		HttpHeaders httpHeaders = PaginationUtil.generatePaginationHttpHeaders(page, "/api/mmenus/kode/like");
		return new ResponseEntity<>(page.getContent(), httpHeaders, HttpStatus.OK);
	}
	
	@GetMapping("/mmenus/uraian/like/{uraian}")
	public ResponseEntity<List<Mmenu>> getMmenusByUraianLike(Pageable pageable, @PathVariable String uraian){
		
		uraian = uraian+"%";
		Page<Mmenu> page = mmenuRepository.findByMenuLike(pageable, uraian);
		HttpHeaders httpHeaders = PaginationUtil.generatePaginationHttpHeaders(page, "/api/mmenus/kode/like");
		return new ResponseEntity<>(page.getContent(), httpHeaders, HttpStatus.OK);
	}
	
}
