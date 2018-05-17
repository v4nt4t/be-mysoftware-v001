package com.vsoft.mysoftware.service.dto;

public class MuserDTO {
	
	private String id;
	private String login;
	private String email;
	private String firstName;
	private String lastName;
	private String password;
	private String imageUrl;
	private boolean activated;
	
	public MuserDTO(){
		
	}
	
	public MuserDTO(String id, String login, String email, String firstName, String lastName, String password,
			String imageUrl, boolean activated) {
		super();
		this.id = id;
		this.login = login;
		this.email = email;
		this.firstName = firstName;
		this.lastName = lastName;
		this.password = password;
		this.imageUrl = imageUrl;
		this.activated = activated;
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

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
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
				+ ", lastName=" + lastName + ", password=" + password + ", imageUrl=" + imageUrl + ", activated="
				+ activated + "]";
	}


	
	

}
