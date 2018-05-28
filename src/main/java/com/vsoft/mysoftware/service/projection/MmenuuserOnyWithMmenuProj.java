package com.vsoft.mysoftware.service.projection;

public interface MmenuuserOnyWithMmenuProj {

	String getId();
	boolean isAllowedf();
	Mmenu getMmenu();

	interface Mmenu{
		String getId();
		String getMenu();
	}
}
