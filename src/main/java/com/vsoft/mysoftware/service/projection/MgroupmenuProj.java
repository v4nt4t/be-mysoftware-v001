package com.vsoft.mysoftware.service.projection;

public interface MgroupmenuProj {

	String getId();
	String getKode();
	String getGroupmenu();
	boolean getAllowedf();
	Integer getUrutan();
	Mheadermenu getMheadermenu();
	
	interface Mheadermenu{
		String getId();
		String getHeadermenu();
	}
	
}
