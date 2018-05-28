package com.vsoft.mysoftware.web.rest;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vsoft.mysoftware.service.MuserService;
import com.vsoft.mysoftware.service.dto.MuserDTO;
import com.vsoft.mysoftware.web.rest.error.InternalServerErrorException;


@RestController
@RequestMapping("/api")
public class AccountResource {
	
	private final MuserService muserService;
	
	public AccountResource(MuserService muserService) {
		this.muserService = muserService;
	}

    @GetMapping("/account")
    public MuserDTO getAccount() {
        return muserService.getUserWithAuthorities()
            .map(MuserDTO::new)
            .orElseThrow(() -> new InternalServerErrorException("User could not be found"));
    }
	
}
