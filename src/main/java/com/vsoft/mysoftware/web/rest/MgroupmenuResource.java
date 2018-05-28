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
import com.vsoft.mysoftware.service.MgroupmenuService;
import com.vsoft.mysoftware.service.dto.MgroupmenuDTO;
import com.vsoft.mysoftware.service.projection.MgroupmenuProj;
import com.vsoft.mysoftware.web.rest.error.BadRequestAlertException;
import com.vsoft.mysoftware.web.rest.util.HeaderUtil;
import com.vsoft.mysoftware.web.rest.util.PaginationUtil;

@RestController
@RequestMapping("/api")
public class MgroupmenuResource {

	private static final String ENTITY_NAME = "mgroupmenus";
	
	private final MgroupmenuService mgroupmenuService;
	
	public MgroupmenuResource(
			MgroupmenuService mgroupmenuService
	) {
		this.mgroupmenuService = mgroupmenuService;
	}
	
	@PostMapping("/mgroupmenus")
	public ResponseEntity<Mgroupmenu> create(@Valid @RequestBody MgroupmenuDTO mgroupmenuDTO)
		throws URISyntaxException{
		
		if(mgroupmenuDTO.getId() != null){
			throw new BadRequestAlertException("ID sudah ada data mgroupmenu tidak bisa di input", ENTITY_NAME, "idexists");
		}
		
		Mgroupmenu result = mgroupmenuService.create(mgroupmenuDTO);
		return ResponseEntity.created(new URI("/api/mgroupmenus/" + result.getId()))
				.headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
	            .body(result);
	}
	
	@PutMapping("/mgroupmenus")
	public ResponseEntity<Mgroupmenu> update(@Valid @RequestBody MgroupmenuDTO mgroupmenuDTO)
		throws URISyntaxException{
		
		if(mgroupmenuDTO.getId() == null){
			return create(mgroupmenuDTO);
		}
		
		Mgroupmenu result = mgroupmenuService.update(mgroupmenuDTO);
		return ResponseEntity.ok()
				.headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
	            .body(result);
	}
	
	@DeleteMapping("/mgroupmenus/{id}")
	public ResponseEntity<Mgroupmenu> deleteById(@PathVariable String id){
		
		mgroupmenuService.deleteById(id);
		return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id)).build();
		
	}
	
	@GetMapping("/mgroupmenus")
	public ResponseEntity<List<MgroupmenuProj>> findAll(Pageable pageable) {
		
		Page<MgroupmenuProj> page = mgroupmenuService.findAll(pageable);
		HttpHeaders httpHeaders = PaginationUtil.generatePaginationHttpHeaders(page, "api/mgroupmenus");
		return new ResponseEntity<>(page.getContent(), httpHeaders, HttpStatus.OK);
	}
	
	@GetMapping("/mgroupmenus/all")
	public ResponseEntity<Collection<Mgroupmenu>> findAll() {
		Collection<Mgroupmenu> result = mgroupmenuService.findAll();
		return new ResponseEntity<>(result, HttpStatus.OK);
	}	
	
	@GetMapping("/mgroupmenus/{id}")
	public ResponseEntity<MgroupmenuProj> findById(@PathVariable String id){
		
		Optional<MgroupmenuProj> mgroupmenu = mgroupmenuService.findById(id);
		return mgroupmenu.map(
				result ->new ResponseEntity<>(result, HttpStatus.OK))
				.orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
	}
	
	@GetMapping("/mgroupmenus/kode/like/{kode}")
	public ResponseEntity<List<MgroupmenuProj>> findLikeKode(Pageable pageable, @PathVariable String kode) {
		
		Page<MgroupmenuProj> page = mgroupmenuService.findByKodeLike(pageable, kode);
		HttpHeaders httpHeaders = PaginationUtil.generatePaginationHttpHeaders(page, "api/mgroupmenus/kode/like");
		return new ResponseEntity<>(page.getContent(), httpHeaders, HttpStatus.OK);
	}	
	
	@GetMapping("/mgroupmenus/uraian/like/{uraian}")
	public ResponseEntity<List<MgroupmenuProj>> findLikeUraian(Pageable pageable, @PathVariable String uraian) {
		
		Page<MgroupmenuProj> page = mgroupmenuService.findByGroupmenuLike(pageable, uraian);
		HttpHeaders httpHeaders = PaginationUtil.generatePaginationHttpHeaders(page, "api/mgroupmenus/uraian/like");
		return new ResponseEntity<>(page.getContent(), httpHeaders, HttpStatus.OK);
	}	
}
