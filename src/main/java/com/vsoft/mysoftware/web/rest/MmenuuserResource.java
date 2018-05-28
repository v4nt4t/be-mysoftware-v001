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

import com.vsoft.mysoftware.domain.Mmenuuser;
import com.vsoft.mysoftware.repository.MmenuuserRepository;
import com.vsoft.mysoftware.service.MmenuuserService;
import com.vsoft.mysoftware.service.dto.MmenuuserDTO;
import com.vsoft.mysoftware.service.projection.MmenuuserForSideBar;
import com.vsoft.mysoftware.service.projection.MmenuuserOnyWithMmenuProj;
import com.vsoft.mysoftware.service.projection.MmenuuserProj;
import com.vsoft.mysoftware.web.rest.error.BadRequestAlertException;
import com.vsoft.mysoftware.web.rest.util.HeaderUtil;
import com.vsoft.mysoftware.web.rest.util.PaginationUtil;

@RestController
@RequestMapping("/api")
public class MmenuuserResource {
	
	private static final String ENTITY_NAME = "mmenuuser";
	
	private final MmenuuserRepository mmenuuserRepository;
	private final MmenuuserService mmenuuserService;
	
	public MmenuuserResource(
			MmenuuserRepository mmenuuserRepository,
			MmenuuserService mmenuuserService) {
		this.mmenuuserRepository = mmenuuserRepository;
		this.mmenuuserService = mmenuuserService;
	}
	
	@PostMapping("/mmenuusers")
	public ResponseEntity<Mmenuuser> create(@Valid @RequestBody MmenuuserDTO mmenuuserDTO) 
			throws URISyntaxException{
		System.out.println("=======================================================");
		System.out.println(mmenuuserDTO);
		System.out.println("=======================================================");
		
		if(mmenuuserDTO.getId() != null){
			throw new BadRequestAlertException("ID sudah ada, data mmenuusers tidak bisa di input", ENTITY_NAME, "idexists");
		}
		
		Mmenuuser result = mmenuuserService.create(mmenuuserDTO);
		return ResponseEntity.created(new URI("/api/mmenuusers/" + result.getId()))
				.headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
				.body(result);
	}
	
	@PutMapping("/mmenuusers")
	public ResponseEntity<Mmenuuser> update(@Valid @RequestBody MmenuuserDTO mmenuuserDTO) 
			throws URISyntaxException{
	
		if(mmenuuserDTO.getId() == null){
			create(mmenuuserDTO);
		}
		
		Mmenuuser result = mmenuuserService.update(mmenuuserDTO);
		return ResponseEntity.ok()
				.headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, result.getId().toString()))
				.body(result);
	}
	
	@DeleteMapping("/mmenuusers/{id}")
	public ResponseEntity<Mmenuuser> delete(@PathVariable String id){
		
		mmenuuserService.deleteById(id);
		return ResponseEntity.ok()
				.headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString()))
				.build();
	}
	
	@GetMapping("/mmenuusers/muser/id/{id}")
	public ResponseEntity<List<MmenuuserOnyWithMmenuProj>> findByMuserId(Pageable pageable, @PathVariable String id){
		
		Page<MmenuuserOnyWithMmenuProj> page =  mmenuuserService.findByMuserId(pageable, id);
		HttpHeaders httpHeaders = PaginationUtil.generatePaginationHttpHeaders(page, "api/mmenuusers/muser/id");
		return new ResponseEntity<>(page.getContent(), httpHeaders, HttpStatus.OK);
		
	}
	
	@GetMapping("/mmenuusers/menuSideBar/muser/id/{id}")
	public ResponseEntity<Collection<MmenuuserForSideBar>> findForMenuSideBarByMuserId(@PathVariable String id){
		
		Collection<MmenuuserForSideBar> result =  mmenuuserRepository.FindForSideBarByMuserId(id);
		return new ResponseEntity<>(result, HttpStatus.OK);
	}
	
	
	@GetMapping("/mmenuusers/id/{id}")
	public ResponseEntity<MmenuuserProj> findById(@PathVariable String id){
		
		Optional<MmenuuserProj> mmenuuser = mmenuuserService.findById(id);
		return mmenuuser.map(result -> new ResponseEntity<>(result, HttpStatus.OK))
				.orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
	}
	
}
