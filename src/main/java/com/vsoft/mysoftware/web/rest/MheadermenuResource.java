package com.vsoft.mysoftware.web.rest;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

import com.vsoft.mysoftware.domain.Mheadermenu;
import com.vsoft.mysoftware.repository.MheadermenuRepository;
import com.vsoft.mysoftware.web.rest.error.BadRequestAlertException;
import com.vsoft.mysoftware.web.rest.util.HeaderUtil;
import com.vsoft.mysoftware.web.rest.util.PaginationUtil;



@RestController
@RequestMapping("/api")
public class MheadermenuResource {

	private final Logger log = LoggerFactory.getLogger(MheadermenuResource.class);
	
	private static final String ENTITY_NAME = "mheadermenu";
	
	private final MheadermenuRepository mheadermenuRepository;

	public MheadermenuResource(MheadermenuRepository mheadermenuRepository) {
		this.mheadermenuRepository = mheadermenuRepository;
	}
	    
	
	@PostMapping("/mheadermenus")
	public ResponseEntity<Mheadermenu> createMheadermenu(@Valid @RequestBody Mheadermenu mheadermenu)
			throws URISyntaxException {
		
		if(mheadermenu.getId() != null){
			throw new BadRequestAlertException("ID sudah ada data mheadermenu tidak bisa di input", ENTITY_NAME, "idexists");
		}
		
		Mheadermenu result = mheadermenuRepository.save(mheadermenu);
		 return ResponseEntity.created(new URI("/api/mheadermenus/" + result.getId()))
		            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
		            .body(result);
	}
	
    @PutMapping("/mheadermenus")
    public ResponseEntity<Mheadermenu> updateMheadermenu(@Valid @RequestBody Mheadermenu mheadermenu) 
    		throws URISyntaxException {
        log.debug("REST request to update Mheadermenu : {}", mheadermenu);
        if (mheadermenu.getId() == null) {
            return createMheadermenu(mheadermenu);
        }
        Mheadermenu result = mheadermenuRepository.save(mheadermenu);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, mheadermenu.getId().toString()))
            .body(result);
    }
	
    @DeleteMapping("/mheadermenus/{id}")
    public ResponseEntity<Void> deleteMheadermenu(@PathVariable String id){
    	
    	mheadermenuRepository.deleteById(id);
    	return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();    	
    }
	
    @GetMapping("/mheadermenus")
    public ResponseEntity<List<Mheadermenu>> getAllMheadermenus(Pageable pageable) {
        Page<Mheadermenu> page = mheadermenuRepository.findAll(pageable);
        HttpHeaders httpHeaders = PaginationUtil.generatePaginationHttpHeaders(page, "/api/mheadermenus");
        return new ResponseEntity<>(page.getContent(), httpHeaders, HttpStatus.OK);
    }
    
    @GetMapping("/mheadermenus/{id}")
    public ResponseEntity<Mheadermenu> getMheadermenus(@PathVariable String id) {
    	
        Optional<Mheadermenu> mheadermenu = mheadermenuRepository.findById(id);
        return mheadermenu.map(result ->new ResponseEntity<>(
                result,
                HttpStatus.OK))
        		.orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
    
    @GetMapping("/mheadermenus/kode/like/{kode}")
    public ResponseEntity<List<Mheadermenu>> getMheadermenusLikeKode(Pageable pageable, @PathVariable String kode) {
    	
    	kode = kode +"%";
        Page<Mheadermenu> page = mheadermenuRepository.findByKodeLike(pageable, kode);
        HttpHeaders httpHeaders = PaginationUtil.generatePaginationHttpHeaders(page, "/api/mheadermenus/kode/like");
        return new ResponseEntity<>(page.getContent(), httpHeaders, HttpStatus.OK);
    }
    
    @GetMapping("/mheadermenus/uraian/like/{uraian}")
    public ResponseEntity<List<Mheadermenu>> getMheadermenusLikeUraian(Pageable pageable, @PathVariable String uraian) {
    	
    	uraian = uraian +"%";
        Page<Mheadermenu> page = mheadermenuRepository.findByHeadermenuLike(pageable, uraian);
        HttpHeaders httpHeaders = PaginationUtil.generatePaginationHttpHeaders(page, "/api/mheadermenus/uraian/like");
        return new ResponseEntity<>(page.getContent(), httpHeaders, HttpStatus.OK);
    }
}
