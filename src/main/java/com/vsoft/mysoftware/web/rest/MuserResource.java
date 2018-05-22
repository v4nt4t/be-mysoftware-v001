package com.vsoft.mysoftware.web.rest;

import java.net.URI;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.vsoft.mysoftware.domain.Muser;
import com.vsoft.mysoftware.repository.MuserRepository;
import com.vsoft.mysoftware.service.MuserService;
import com.vsoft.mysoftware.service.dto.MuserDTO;
import com.vsoft.mysoftware.service.projection.MuserProj;
import com.vsoft.mysoftware.service.util.StorageUtil;
import com.vsoft.mysoftware.web.rest.util.HeaderUtil;
import com.vsoft.mysoftware.web.rest.util.PaginationUtil;
import com.vsoft.mysoftware.web.rest.vm.MuserVM;

@RestController
@RequestMapping("/api")
public class MuserResource {

	private static final String ENTITY_NAME = "musers";
	
	
	private final MuserRepository muserRepository;
	
	private final MuserService muserService;
	
	private final StorageUtil storageUtil;
	
	public MuserResource(
			MuserRepository muserRepository,
			MuserService muserService,
			StorageUtil storageUtil
			) {
		this.muserRepository = muserRepository;
		this.muserService = muserService;
		this.storageUtil = storageUtil;
	}
	
	@PostMapping("/musers/createUserAndFile")
	public ResponseEntity<MuserDTO> createUserAndFile(@RequestParam(value="file", required=false) MultipartFile file, @RequestParam(value="muser") String data)
			throws Exception{
		
		//simpen data user dan file photo
		MuserDTO result = muserService.createUserAndFile(data, file);
		
		return ResponseEntity.created(new URI("/api/musers/" + result.getId()))
				.headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
				.body(result);
		
	}
	
	@DeleteMapping("/musers/{id}")
	public ResponseEntity<MuserProj> deleteById(@PathVariable String id){
		
		muserService.deleteUserWithFile(id);
		return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id)).build();
		
	}
	
   @GetMapping("/musers")
   public ResponseEntity<List<MuserProj>> findAll(Pageable pageable) {
       Page<MuserProj> page = muserRepository.findPagedProjectedBy(pageable, MuserProj.class);
       HttpHeaders httpHeaders = PaginationUtil.generatePaginationHttpHeaders(page, "/api/musers");
       return new ResponseEntity<>(page.getContent(), httpHeaders, HttpStatus.OK);
   }
  
	@GetMapping("/musers/i/{id}")
	public ResponseEntity<MuserProj> findById(@PathVariable String id){
		
		Optional<MuserProj> muserProj = muserRepository.findOneById(id, MuserProj.class);
			
		return muserProj.map(result -> new ResponseEntity<>(result, HttpStatus.OK))
				.orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
	}
   
	@GetMapping("/musers/lo/{login}")
	public ResponseEntity<MuserProj> findByLogin(@PathVariable String login){
		
		Optional<MuserProj> muserProj = muserRepository.findOneByLogin(login, MuserProj.class);
		
		return muserProj.map(result -> new ResponseEntity<>(result, HttpStatus.OK))
				.orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
	}
   
   // pencarian berdasarkan login
   @GetMapping("/musers/login/like/{login}")
   public ResponseEntity<List<MuserProj>> findByLoginLike(Pageable pageable, @PathVariable String login) {
	   
	   login = login+"%";
       Page<MuserProj> page = muserRepository.findByLoginLike(pageable, login, MuserProj.class);
       HttpHeaders httpHeaders = PaginationUtil.generatePaginationHttpHeaders(page, "/api/musers/login/like");
       return new ResponseEntity<>(page.getContent(), httpHeaders, HttpStatus.OK);
   }
   
   // pencarian berdasarkan nama pertama
   @GetMapping("/musers/fn/like/{fn}")
   public ResponseEntity<List<MuserProj>> findByFirstNameLike(Pageable pageable, @PathVariable String fn) {
	   
	   fn = fn+"%";
       Page<MuserProj> page = muserRepository.findByFirstNameLike(pageable, fn, MuserProj.class);
       HttpHeaders httpHeaders = PaginationUtil.generatePaginationHttpHeaders(page, "/api/musers/fn/like");
       return new ResponseEntity<>(page.getContent(), httpHeaders, HttpStatus.OK);
   }
   
//	@GetMapping("/musers/getfile/{fileName}")
//	public ResponseEntity<String> getListFiles(@PathVariable String fileName) {
//		String fn = MvcUriComponentsBuilder
//						.fromMethodName(MuserResource.class, "getFile", fileName).build().toString();
//
//		return ResponseEntity.ok().body(fn);
//	}
//   
//	@GetMapping("/musers/file/{fileName}")
//	@ResponseBody
//	public ResponseEntity<Resource> getFile(@PathVariable String fileName) {
//		
//		Resource file = storageUtil.loadFile(fileName, FOLDER_PATH);
//		return ResponseEntity.ok()
//				.header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + file.getFilename() + "\"")
//				.body(file);
//	}
	
	@GetMapping("/musers/fileBase64/{id}")
	@ResponseBody
	public ResponseEntity<Map<String, String>> getFileBase64(@PathVariable String id) {
		
		Muser muser = muserRepository.getOne(id);
		
//		String encodeImage = storageUtil.loadFileBase64(fileName, FOLDER_PATH);
		String encodeImage = storageUtil.loadFileBase64(muser.getImageUrl());
		Map<String, String> jsonMap = new HashMap<>();
		jsonMap.put("content", encodeImage);
		
		return ResponseEntity.ok().body(jsonMap);
	}

	@PostMapping("/musers/admResetPass")
	public ResponseEntity<MuserProj> admResetPassword(@RequestBody MuserVM muserVM){
		
		muserService.adminResetPassword(muserVM.getId(), muserVM.getPassword());
		return ResponseEntity.ok().headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, muserVM.getId())).build();

	}
}
