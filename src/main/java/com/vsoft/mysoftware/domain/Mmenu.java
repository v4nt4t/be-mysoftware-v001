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
@Table(name="mmenu")
public class Mmenu implements Serializable {

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
	@Column(name = "menu", length = 50, nullable = false)
	private String menu;

	@NotNull
	@Column(name = "allowedf", nullable = false)
	private boolean allowedf;

	@NotNull
	@Column(name = "urutan", nullable = false)
	private Integer urutan;

	@ManyToOne
	@NotNull
	private Mgroupmenu mgroupmenu;

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

	public String getMenu() {
		return menu;
	}

	public void setMenu(String menu) {
		this.menu = menu;
	}

	public boolean isAllowedf() {
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

	public Mgroupmenu getMgroupmenu() {
		return mgroupmenu;
	}

	public void setMgroupmenu(Mgroupmenu mgroupmenu) {
		this.mgroupmenu = mgroupmenu;
	}

	@Override
	public String toString() {
		return "Mmenu [id=" + id + ", kode=" + kode + ", menu=" + menu + ", allowedf=" + allowedf + ", urutan=" + urutan
				+ "]";
	}

}
