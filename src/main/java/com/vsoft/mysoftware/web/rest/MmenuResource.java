package com.vsoft.mysoftware.web.rest;


import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vsoft.mysoftware.domain.Mmenu;
import com.vsoft.mysoftware.repository.MmenuRepository;
import com.vsoft.mysoftware.web.rest.util.PaginationUtil;


@RestController
@RequestMapping("/api")
public class MmenuResource {

	private final MmenuRepository mmenuRepository;
	
	private MmenuResource(MmenuRepository mmenuRepository) {
		this.mmenuRepository = mmenuRepository;
	}
	
	@GetMapping("/mmenus")
	public ResponseEntity<List<Mmenu>> getMmenus(Pageable pageable){
		
		Page<Mmenu> page = mmenuRepository.findAll(pageable);
		HttpHeaders httpHeaders = PaginationUtil.generatePaginationHttpHeaders(page, "/api/mmenus");
		
		return new ResponseEntity<>(page.getContent(), httpHeaders, HttpStatus.OK);
		
	}
}
