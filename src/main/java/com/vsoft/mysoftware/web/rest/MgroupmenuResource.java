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

import com.vsoft.mysoftware.domain.Mgroupmenu;
import com.vsoft.mysoftware.repository.MgroupmenuRepository;
import com.vsoft.mysoftware.web.rest.util.PaginationUtil;

@RestController
@RequestMapping("/api")
public class MgroupmenuResource {

	private final MgroupmenuRepository mgroupmenuRepository;
	
	public MgroupmenuResource(MgroupmenuRepository mgroupmenuRepository) {
		this.mgroupmenuRepository = mgroupmenuRepository;
	}
	
	@GetMapping("/mgroupmenus")
	public ResponseEntity<List<Mgroupmenu>> getMgroupmenus(Pageable pageable) {
		
		Page<Mgroupmenu> page = this.mgroupmenuRepository.findAll(pageable);
		HttpHeaders httpHeaders = PaginationUtil.generatePaginationHttpHeaders(page, "api/mgroupmenus");
		return new ResponseEntity<>(page.getContent(), httpHeaders, HttpStatus.OK);
	}	
}
