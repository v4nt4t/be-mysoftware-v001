package com.vsoft.mysoftware.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.GenericGenerator;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name="mmenuuser")
public class Mmenuuser implements Serializable {
	
	private static final long serialVersionUID = 1L;

//	@Id
//  UUID id;
	
	@Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    @Column(length = 36, nullable = false)
	private String id;
	
	@NotNull
	private boolean allowedf;
	
	@JsonIgnore
	@ManyToOne(fetch=FetchType.LAZY)
	@NotNull
	private Muser muser;
	
	@JsonIgnore
	@ManyToOne(fetch=FetchType.LAZY)
	@NotNull
	private Mmenu mmenu;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public boolean isAllowedf() {
		return allowedf;
	}

	public void setAllowedf(boolean allowedf) {
		this.allowedf = allowedf;
	}

	public Muser getMuser() {
		return muser;
	}

	public void setMuser(Muser muser) {
		this.muser = muser;
	}

	public Mmenu getMmenu() {
		return mmenu;
	}

	public void setMmenu(Mmenu mmenu) {
		this.mmenu = mmenu;
	}

	@Override
	public String toString() {
		return "Menuuser [id=" + id + ", allowedf=" + allowedf + ", muser=" + muser + ", mmenu=" + mmenu + "]";
	}
	
}
