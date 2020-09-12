package com.pms.dto;

public class Pms_Coupon_Log_Dto {
	int IDX;
	int CPNUM;
	String CPCODE;
	String VALIDITY;
	String USED;
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
	public String getVALIDITY() {
		return VALIDITY;
	}
	public void setVALIDITY(String date) {
		VALIDITY = date;
	}
	public String isUSED() {
		return USED;
	}
	public void setUSED(String uSED) {
		USED = uSED;
	}
	public String getCNUM() {
		return CNUM;
	}
	public void setCNUM(String cNUM) {
		CNUM = cNUM;
	}
}
