package com.pms.dto;

public class StatisticsDTO {
	private String time;
	private int inMonth;
	private int inNomal;
	private int outMonth;
	private int outNomal;
	private int pay;
	private int totalPay;
	private int monthCount;
	private int monthPay;
	
	public int getMonthPay() {
		return monthPay;
	}
	public void setMonthPay(int monthPay) {
		this.monthPay = monthPay;
	}
	public int getMonthCount() {
		return monthCount;
	}
	public void setMonthCount(int monthCount) {
		this.monthCount = monthCount;
	}
	public int getPay() {
		return pay;
	}
	public void setPay(int pay) {
		this.pay = pay;
	}
	public int getTotalPay() {
		return totalPay;
	}
	public void setTotalPay(int totalPay) {
		this.totalPay = totalPay;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	public int getInMonth() {
		return inMonth;
	}
	public void setInMonth(int inMonth) {
		this.inMonth = inMonth;
	}
	public int getInNomal() {
		return inNomal;
	}
	public void setInNomal(int inNomal) {
		this.inNomal = inNomal;
	}
	public int getOutMonth() {
		return outMonth;
	}
	public void setOutMonth(int outMonth) {
		this.outMonth = outMonth;
	}
	public int getOutNomal() {
		return outNomal;
	}
	public void setOutNomal(int outNomal) {
		this.outNomal = outNomal;
	}
	

	
}
