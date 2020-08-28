package com.pms.dto;

public class PmsCouponDto {
	private int CPNUM;
	private int USE_DATE;
	private String CPNAME;
	private String PURPOSE;
	private int DISCOUNT;

	public int getCPNUM() {
		return CPNUM;
	}

	public void setCPNUM(int cPNUM) {
		CPNUM = cPNUM;
	}

	public int getUSE_DATE() {
		return USE_DATE;
	}

	public void setUSE_DATE(int uSE_DATE) {
		USE_DATE = uSE_DATE;
	}

	public String getCPNAME() {
		return CPNAME;
	}

	public void setCPNAME(String cPNAME) {
		CPNAME = cPNAME;
	}

	public String getPURPOSE() {
		return PURPOSE;
	}

	public void setPURPOSE(String pURPOSE) {
		PURPOSE = pURPOSE;
	}

	public int getDISCOUNT() {
		return DISCOUNT;
	}

	public void setDISCOUNT(int dISCOUNT) {
		DISCOUNT = dISCOUNT;
	}

}
