package com.vsoft.mysoftware.service.projection;

public interface MmenuProj {

	String getId();
	String getKode();
	String getMenu();
	String getObjek();
	String getAllowedf();
	String getUrutan();
	Mgroupmenu getMgroupmenu();
	
	interface Mgroupmenu{
		String getId();
		String getGroupmenu();
	}
	
}
