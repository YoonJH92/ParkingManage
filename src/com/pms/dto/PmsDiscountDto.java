package com.pms.dto;

public class PmsDiscountDto {
	private int COM_NUM;
	private String COMPANY;
	private String PURPOSE;
	private int USE_TIME;

	public int getCOM_NUM() {
		return COM_NUM;
	}

	public void setCOM_NUM(int cOM_NUM) {
		COM_NUM = cOM_NUM;
	}

	public String getCOMPANY() {
		return COMPANY;
	}

	public void setCOMPANY(String cOMPANY) {
		COMPANY = cOMPANY;
	}

	public String getPURPOSE() {
		return PURPOSE;
	}

	public void setPURPOSE(String pURPOSE) {
		PURPOSE = pURPOSE;
	}

	public int getUSE_TIME() {
		return USE_TIME;
	}

	public void setUSE_TIME(int uSE_TIME) {
		USE_TIME = uSE_TIME;
	}
}
