package com.vsoft.mysoftware.web.rest.vm;

import javax.validation.constraints.Size;

import com.vsoft.mysoftware.service.dto.MuserDTO;

public class MuserVM extends MuserDTO {

    public static final int PASSWORD_MIN_LENGTH = 4;

    public static final int PASSWORD_MAX_LENGTH = 100;
	
	private String password;

	@Size(min = PASSWORD_MIN_LENGTH, max = PASSWORD_MAX_LENGTH)
	public MuserVM() {
		
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	@Override
	public String toString() {
		 return "MUserVM{" +
		            "} " + super.toString();
	}
	
}
