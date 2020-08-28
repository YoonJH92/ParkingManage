package com.pms.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CDCommand2 implements Command {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		return "coupon/search_cp_dc";
	}
//	http://localhost/ParkingManage/search_cp_dc.do
}
