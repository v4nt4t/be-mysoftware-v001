package com.vsoft.mysoftware.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "mgroupmenu")
public class Mgroupmenu implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(generator = "uuid")
	@GenericGenerator(name = "uuid", strategy = "uuid2")
	@Column(length = 36, nullable = false)
	private String id;

	@NotNull
	@NotEmpty
	@Size(min = 1, max = 5)
	@Column(name = "kode", length = 5, nullable = false)
	private String kode;

	@NotNull
	@NotEmpty
	@Size(min = 1, max = 50)
	@Column(name = "groupmenu", length = 50, nullable = false)
	private String groupmenu;

	@NotNull
	@Column(name = "allowedf", nullable = false)
	private boolean allowedf;

	@NotNull
	@Column(name = "urutan")
	private Integer urutan;

	@ManyToOne
	@NotNull
	private Mheadermenu mheadermenu;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getKode() {
		return kode;
	}

	public void setKode(String kode) {
		this.kode = kode;
	}

	public String getGroupmenu() {
		return groupmenu;
	}

	public void setGroupmenu(String groupmenu) {
		this.groupmenu = groupmenu;
	}

	public boolean getAllowedf() {
		return allowedf;
	}

	public void setAllowedf(boolean allowedf) {
		this.allowedf = allowedf;
	}

	public Integer getUrutan() {
		return urutan;
	}

	public void setUrutan(Integer urutan) {
		this.urutan = urutan;
	}

	public Mheadermenu getMheadermenu() {
		return mheadermenu;
	}

	public void setMheadermenu(Mheadermenu mheadermenu) {
		this.mheadermenu = mheadermenu;
	}

	@Override
	public String toString() {
		return "Mgroupmenu [id=" + id + ", kode=" + kode + ", groupmenu=" + groupmenu + ", allowedf=" + allowedf
				+ ", urutan=" + urutan + "]";
	}



}
