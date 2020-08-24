package com.pms.dto;

import java.sql.Timestamp;

public class PMSDto {
	private int idx;
	private String cnum;
	private Timestamp in_time;
	private Timestamp out_time;
	private int pay;
	private int cp_num;
	private int sale_num;
	private int total_pay;
	

	public Timestamp getOut_time() {
		return out_time;
	}

	public void setOut_time(Timestamp out_time) {
		this.out_time = out_time;
	}

	public int getPay() {
		return pay;
	}

	public void setPay(int pay) {
		this.pay = pay;
	}

	private int month_num;
	private String c_img;

	public int getIdx() {
		return idx;
	}

	public void setIdx(int idx) {
		this.idx = idx;
	}

	public String getCnum() {
		return cnum;
	}

	public void setCnum(String cnum) {
		this.cnum = cnum;
	}


	public int getCp_num() {
		return cp_num;
	}

	public void setCp_num(int cp_num) {
		this.cp_num = cp_num;
	}

	public int getSale_num() {
		return sale_num;
	}

	public void setSale_num(int sale_num) {
		this.sale_num = sale_num;
	}

	public int getTotal_pay() {
		return total_pay;
	}

	public void setTotal_pay(int total_pay) {
		this.total_pay = total_pay;
	}

	public int getMonth_num() {
		return month_num;
	}

	public void setMonth_num(int month_num) {
		this.month_num = month_num;
	}

	public String getC_img() {
		return c_img;
	}

	public void setC_img(String c_img) {
		this.c_img = c_img;
	}

	public Timestamp getIn_time() {
		return in_time;
	}

	public void setIn_time(Timestamp in_time) {
		this.in_time = in_time;
	}


}
