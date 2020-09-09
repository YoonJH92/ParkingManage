package com.pms.dto;

import java.sql.Date;

public class Pms_Coupon_Log_Dto {
	int IDX;
	int CPNUM;
	String CPCODE;
	Date VALIDITY;
	boolean USED;
	String CNUM;
	
	public int getIDX() {
		return IDX;
	}
	public void setIDX(int iDX) {
		IDX = iDX;
	}
	public int getCPNUM() {
		return CPNUM;
	}
	public void setCPNUM(int cPNUM) {
		CPNUM = cPNUM;
	}
	public String getCPCODE() {
		return CPCODE;
	}
	public void setCPCODE(String cPCODE) {
		CPCODE = cPCODE;
	}
	public Date getVALIDITY() {
		return VALIDITY;
	}
	public void setVALIDITY(Date vALIDITY) {
		VALIDITY = vALIDITY;
	}
	public boolean isUSED() {
		return USED;
	}
	public void setUSED(boolean uSED) {
		USED = uSED;
	}
	public String getCNUM() {
		return CNUM;
	}
	public void setCNUM(String cNUM) {
		CNUM = cNUM;
	}
}
