package com.vsoft.mysoftware.domain;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.annotations.GenericGenerator;


@Entity
@Table(name = "mheadermenu")
public class Mheadermenu implements Serializable {

    private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(generator = "uuid")
	@GenericGenerator(name = "uuid", strategy = "uuid2")
	@Column(length = 36, nullable = false)
	private String id;

	@NotNull
	@Size(min = 1, max = 5)
	@Column(name = "kode", length = 5, nullable = false)
	private String kode;

	@NotNull
	@Size(min = 1, max = 50)
	@Column(name = "headermenu", length = 50, nullable = false)
	private String headermenu;

	@NotNull
	@Column(name = "allowedf", nullable = false)
	private boolean allowedf;

	@NotNull
	@Size(min = 1, max = 5)
	@Column(name = "urutan")
	private Integer urutan;

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

	public String getHeadermenu() {
		return headermenu;
	}

	public void setHeadermenu(String headermenu) {
		this.headermenu = headermenu;
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
	
	 @Override
	    public boolean equals(Object o) {
	        if (this == o) {
	            return true;
	        }
	        if (o == null || getClass() != o.getClass()) {
	            return false;
	        }
	        Mheadermenu mheadermenu = (Mheadermenu) o;
	        if(mheadermenu.id == null || id == null) {
	            return false;
	        }
	        return Objects.equals(id, mheadermenu.id);
	    }

	    @Override
	    public int hashCode() {
	        return Objects.hashCode(id);
	    }
	
	@Override
	public String toString() {
		return "mheadermenu [id=" + id + ", kode=" + kode + ", headermenu=" + headermenu + ", allowedf=" + allowedf
				+ ", urutan=" + urutan + "]";
	}
    
    

}