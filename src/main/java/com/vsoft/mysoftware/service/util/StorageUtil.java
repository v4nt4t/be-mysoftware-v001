package com.vsoft.mysoftware.service.util;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Base64;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class StorageUtil {

	Logger log = LoggerFactory.getLogger(StorageUtil.class);
	Path rootLocation = Paths.get("D://upload-dir//");
	
	private void init(Path rootPath){
		
		try{
			
			if(Files.notExists(rootPath)){
				Files.createDirectory(rootPath);
			};
			
		}catch (Exception e) {
			throw new RuntimeException("Gagal Membuat RootDirectory");
		}
		
	}
	
	public void store(MultipartFile file){
		
		try{
			
			init(this.rootLocation);
			
			Files.copy(file.getInputStream(), rootLocation.resolve(file.getOriginalFilename()));
			
		}catch(Exception e){
			throw new RuntimeException("File Gagal Simpan !");
		}
	}
	
	public void store(MultipartFile file, String fileName){
		
		String tipeFile = file.getOriginalFilename().split("\\.")[1];
		fileName = fileName +"."+ tipeFile;
		
		try{
			
			init(this.rootLocation);
			
			Files.copy(file.getInputStream(), rootLocation.resolve(fileName));
			
		}catch(Exception e){
			throw new RuntimeException("File Gagal Simpan !");
		}
	}
	
	public void store(MultipartFile file, String fileName, String folderPath){
		
		String tipeFile = file.getOriginalFilename().split("\\.")[1];
		fileName = fileName +"."+ tipeFile;
		
		try{
			
			String url = "D://upload-dir//"+folderPath+"//";
			
			Path customLocation = Paths.get(url);
			init(customLocation);
			
			Files.copy(file.getInputStream(), customLocation.resolve(fileName));
			
		}catch(Exception e){
			throw new RuntimeException("File Gagal Simpan !");
		}
	}
	
	public Resource loadFile(String fileName){
		
		try {
		
			Path file = this.rootLocation.resolve(fileName);
			Resource resource = new UrlResource(file.toUri());
			
			if(resource.exists() || resource.isReadable()){
				return resource;
			}else {
				throw new RuntimeException("FAIL!");
			}
		
		}catch (MalformedURLException e) {
			throw new RuntimeException("FAIL!");
		}
	}
	
	public Resource loadFile(String fileName, String folderPath){
		
		String url = "D://upload-dir//"+folderPath+"//";
		
		Path customLocation = Paths.get(url);
		
		try {
		
			Path file = customLocation.resolve(fileName);
			Resource resource = new UrlResource(file.toUri());
			
			if(resource.exists() || resource.isReadable()){
				return resource;
			}else {
				throw new RuntimeException("FAIL!");
			}
			
		} catch (MalformedURLException e) {
			throw new RuntimeException("FAIL!");
		}
		
	}
	
	public String loadFileBase64(String fileName, String folderPath){
		String url = "D://upload-dir//"+folderPath+"//";
		Path customLocation = Paths.get(url);
		
		try{
		
			Path path = customLocation.resolve(fileName);
			Resource resource = new UrlResource(path.toUri());
			File file = resource.getFile();
			
			String encodeImage = Base64.getEncoder().withoutPadding().encodeToString(Files.readAllBytes(file.toPath()));
			
			return encodeImage;
			
		}catch (IOException e){
			throw new RuntimeException("Fail!");
		}
	}

}
