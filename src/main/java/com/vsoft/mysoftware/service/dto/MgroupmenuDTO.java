package com.vsoft.mysoftware.service.dto;

public class MgroupmenuDTO{
	
	private String id;
	private String kode;
	private String groupmenu;
	private boolean allowedf;
	private int urutan;
	
	public MgroupmenuDTO(){
		
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

	@Override
	public String toString() {
		return "MgroupmenuDTO [id=" + id + ", kode=" + kode + ", groupmenu=" + groupmenu + ", allowedf=" + allowedf
				+ ", urutan=" + urutan + "]";
	}



	


	

	


	
}
