package com.vsoft.mysoftware.domain;


import java.io.Serializable;
import java.time.Instant;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.annotations.BatchSize;
import org.hibernate.annotations.GenericGenerator;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "muser")
public class Muser extends AbstractAuditingEntity implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(generator = "uuid")
	@GenericGenerator(name = "uuid", strategy = "uuid2")
	@Column(length = 36, nullable = false)
	private String id;
	
	@NotNull
	@Size(min = 1, max = 50)
	@Column(name = "login", length = 50, nullable = false, unique = true )
	private String login;
	
	@JsonIgnore
	@NotNull
	@Size(min = 1, max = 60)
	@Column(name = "password", length = 60, nullable = false)
	private String password;
	
	@Size(min = 1, max = 50)
	@NotNull
	@Column(name = "first_name", length = 50)
	private String firstName;
	
	@Size(min = 1, max = 50)
	@Column(name = "last_name", length = 50)
	private String lastName;
	
	@Size(min = 5, max = 100)
	@Email
	@NotNull
	@Column(name = "email", length = 100, nullable = false, unique = true )
	private String email;
	
	@Size(max = 256)
	@Column(name = "image_url", length = 256)
	private String imageUrl;
	
	@Column(name = "activated", nullable = false)
	private Boolean activated;
	
	@Size(min = 2, max = 5)
	@Column(name = "lang_key", length = 5)
	private String langKey;
	
	@JsonIgnore
	@Size(max = 20)
	@Column(name = "activation_key", length = 20)
	private String activationKey;
	
	@JsonIgnore
	@Size(max = 20)
	@Column(name = "reset_key", length = 20)
	private String resetKey;
	
	@Column(name = "reset_date")
	private Instant resetDate;
	
	@JsonIgnore
	@ManyToMany
	@JoinTable(name = "userauthority", 
			joinColumns = {@JoinColumn(name = "muser_id", referencedColumnName = "id")},
	        inverseJoinColumns = {@JoinColumn(name = "authority_name", referencedColumnName = "name")})
	@BatchSize(size = 20)
	private Set<Mauthority> mauthorities = new HashSet<>();


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


	public String getPassword() {
		return password;
	}


	public void setPassword(String password) {
		this.password = password;
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


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	public String getImageUrl() {
		return imageUrl;
	}


	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}


	public Boolean getActivated() {
		return activated;
	}


	public void setActivated(Boolean activated) {
		this.activated = activated;
	}

	public String getLangKey() {
		return langKey;
	}


	public void setLangKey(String langKey) {
		this.langKey = langKey;
	}


	public String getActivationKey() {
		return activationKey;
	}


	public void setActivationKey(String activationKey) {
		this.activationKey = activationKey;
	}


	public String getResetKey() {
		return resetKey;
	}


	public void setResetKey(String resetKey) {
		this.resetKey = resetKey;
	}


	public Instant getResetDate() {
		return resetDate;
	}


	public void setResetDate(Instant resetDate) {
		this.resetDate = resetDate;
	}

	public Set<Mauthority> getMauthorities() {
		return mauthorities;
	}


	public void setMauthorities(Set<Mauthority> mauthorities) {
		this.mauthorities = mauthorities;
	}

	@Override
	public String toString() {
		return "Muser [login=" + login + 
				", firstName=" + firstName + 
				", lastName=" + lastName + 
				", email=" + email + 
				", imageUrl=" + imageUrl + 
				", activated=" + activated + 
				", langKey=" + langKey + 
				", activationKey=" + activationKey + "]";
	}
	
}
