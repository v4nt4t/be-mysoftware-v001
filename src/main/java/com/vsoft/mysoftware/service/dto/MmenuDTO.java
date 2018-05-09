package com.vsoft.mysoftware.service.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.vsoft.mysoftware.domain.Mgroupmenu;
import com.vsoft.mysoftware.domain.Mmenu;

public class MmenuDTO {


	private String id;
	private String kode;
	private String menu;
	private String objek;
	private boolean allowedf;
	private Integer urutan;
	
	@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
	private Mgroupmenu mgroupmenu;
	
	public MmenuDTO(){
		
	}
	
	public MmenuDTO(Mmenu mmenu) {
		this.id = mmenu.getId();
		this.kode = mmenu.getKode();
		this.menu = mmenu.getMenu();
		this.objek = mmenu.getObjek();
		this.allowedf = mmenu.isAllowedf();
		this.urutan = mmenu.getUrutan();
		this.mgroupmenu = mmenu.getMgroupmenu();
	}


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

	public String getObjek() {
		return objek;
	}

	public void setObjek(String objek) {
		this.objek = objek;
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
		return "MmenuDTO [id=" + id + ", kode=" + kode + ", menu=" + menu + ", objek=" + objek + ", allowedf="
				+ allowedf + ", urutan=" + urutan + ", mgroupmenu=" + mgroupmenu + "]";
	}
	
}
