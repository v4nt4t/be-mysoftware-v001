package com.vsoft.mysoftware.service.dto;

import com.vsoft.mysoftware.domain.Mmenu;
import com.vsoft.mysoftware.domain.Mmenuuser;
import com.vsoft.mysoftware.domain.Muser;

public class MmenuuserDTO {

	private String id;
	private boolean allowedf;
	private Muser muser;
	private Mmenu mmenu;
	
	public MmenuuserDTO() {

	}

	public MmenuuserDTO(Mmenuuser mmenuuser) {
		
		this.id = mmenuuser.getId();
		this.allowedf = mmenuuser.isAllowedf();
		this.muser = mmenuuser.getMuser();
		this.mmenu = mmenuuser.getMmenu();
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public boolean isAllowedf() {
		return allowedf;
	}

	public void setAllowedf(boolean allowedf) {
		this.allowedf = allowedf;
	}

	public Muser getMuser() {
		return muser;
	}

	public void setMuser(Muser muser) {
		this.muser = muser;
	}

	public Mmenu getMmenu() {
		return mmenu;
	}

	public void setMmenu(Mmenu mmenu) {
		this.mmenu = mmenu;
	}

	@Override
	public String toString() {
		return "MmenuuserDTO [id=" + id + ", allowedf=" + allowedf + ", muser=" + muser + ", mmenu=" + mmenu + "]";
	}

}
