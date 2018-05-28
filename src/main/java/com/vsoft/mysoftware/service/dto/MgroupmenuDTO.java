package com.vsoft.mysoftware.service.dto;

import com.vsoft.mysoftware.domain.Mgroupmenu;
import com.vsoft.mysoftware.domain.Mheadermenu;

public class MgroupmenuDTO{
	
	private String id;
	private String kode;
	private String groupmenu;
	private boolean allowedf;
	private int urutan;

	private Mheadermenu mheadermenu;
	
	public MgroupmenuDTO(){
		
	}

	public MgroupmenuDTO(Mgroupmenu mgroupmenu) {
		this.id = mgroupmenu.getId();
		this.kode = mgroupmenu.getKode();
		this.groupmenu = mgroupmenu.getGroupmenu();
		this.allowedf = mgroupmenu.isAllowedf();
		this.urutan = mgroupmenu.getUrutan();
		this.mheadermenu = mgroupmenu.getMheadermenu();
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

	public int getUrutan() {
		return urutan;
	}

	public void setUrutan(int urutan) {
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
		return "MgroupmenuDTO [id=" + id + ", kode=" + kode + ", groupmenu=" + groupmenu + ", allowedf=" + allowedf
				+ ", urutan=" + urutan + ", mheadermenu=" + mheadermenu + "]";
	}



	


	

	


	
}
