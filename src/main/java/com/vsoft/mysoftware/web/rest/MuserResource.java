package com.vsoft.mysoftware.web.rest;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vsoft.mysoftware.domain.Muser;
import com.vsoft.mysoftware.repository.MuserRepository;

@RestController
@RequestMapping("/api")
public class MuserResource {

	
	private final MuserRepository muserRepository;
	
	public MuserResource(MuserRepository muserRepository) {
		this.muserRepository = muserRepository;
	}
	
   @GetMapping("/musers")
   public ResponseEntity<List<Muser>> getAllMusers(Pageable pageable) {
       Page<Muser> page = muserRepository.findAll(pageable);
       return new ResponseEntity<>(page.getContent(), HttpStatus.OK);
   }
}
