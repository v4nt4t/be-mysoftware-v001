package com.vsoft.mysoftware.web.rest;

import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vsoft.mysoftware.domain.Mheadermenu;
import com.vsoft.mysoftware.repository.MheadermenuRepository;



@RestController
@RequestMapping("/api")
public class MheadermenuResource {

	private final MheadermenuRepository mheadermenuRepository;

	public MheadermenuResource(MheadermenuRepository mheadermenuRepository) {
		this.mheadermenuRepository = mheadermenuRepository;
	}
	    
	   /**
     * GET  /mheadermenus : get all the mheadermenus.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of mheadermenus in body
     */
    @GetMapping("/mheadermenus")
    public ResponseEntity<List<Mheadermenu>> getAllMheadermenus(Pageable pageable) {
        Page<Mheadermenu> page = mheadermenuRepository.findAll(pageable);        
        return new ResponseEntity<>(page.getContent(), HttpStatus.OK);
    }
}
