package com.vsoft.mysoftware.service.projection;

public interface MgroupmenuListProj {

	String getId();
	String getKode();
	String getGroupmenu();
	boolean getAllowedf();
	Integer getUrutan();
	MheadermenuSum getMheadermenu();
	
	interface MheadermenuSum{
		String getHeadermenu();
	}
	
}
