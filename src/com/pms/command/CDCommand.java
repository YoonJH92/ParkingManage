package com.pms.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CDCommand implements Command {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		return "/coupon/new_cp_dc";
	}
//	http://localhost/ParkingManage/coupon/new_cp_dc.do
}
