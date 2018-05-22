package com.vsoft.mysoftware.service.dto;

import com.vsoft.mysoftware.domain.Muser;

public class MuserDTO {
	
	private String id;
	private String login;
	private String email;
	private String firstName;
	private String lastName;
	private String imageUrl;
	private boolean activated;
	
	public MuserDTO(){
		
	}
	
	public MuserDTO(String id, String login, String email, String firstName, String lastName,
			String imageUrl, boolean activated) {
		super();
		this.id = id;
		this.login = login;
		this.email = email;
		this.firstName = firstName;
		this.lastName = lastName;
		this.imageUrl = imageUrl;
		this.activated = activated;
	}

	public MuserDTO(Muser muser){
		this.id = muser.getId();
		this.login = muser.getLogin();
		this.email = muser.getEmail();
		this.firstName = muser.getFirstName();
		this.lastName = muser.getLastName();
		this.imageUrl = muser.getImageUrl();
		this.activated = muser.getActivated();
	}
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

	public boolean isActivated() {
		return activated;
	}

	public void setActivated(boolean activated) {
		this.activated = activated;
	}

	@Override
	public String toString() {
		return "MuserDTO [id=" + id + ", login=" + login + ", email=" + email + ", firstName=" + firstName
				+ ", lastName=" + lastName + ", imageUrl=" + imageUrl + ", activated=" + activated + "]";
	}

	

}