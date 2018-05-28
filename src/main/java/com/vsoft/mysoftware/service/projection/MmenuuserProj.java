package com.vsoft.mysoftware.service.projection;

public interface MmenuuserProj {

	String getId();
	boolean isAllowedf();
	Muser getMuser();
	Mmenu getMmenu();
	
	interface Muser{
		String getId();
		String getFirstName();
	}
	
	interface Mmenu{
		String getId();
		String getMenu();
	}
}
