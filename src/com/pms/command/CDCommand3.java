package com.pms.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CDCommand3 implements Command {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		return "coupon/search_log";
	}
//	http://localhost/ParkingManage/search_cp_dc.do
}
