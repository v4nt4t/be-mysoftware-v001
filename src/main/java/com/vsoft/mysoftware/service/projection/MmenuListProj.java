package com.vsoft.mysoftware.service.projection;

public interface MmenuListProj {

	String getId();
	String getKode();
	String getMenu();
	String getObjek();
	String getAllowedf();
	String getUrutan();
	G getMgroupmenu();
	
	interface G{
		String getGroupmenu();
	}
	
}
