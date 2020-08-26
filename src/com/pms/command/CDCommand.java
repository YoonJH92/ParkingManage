package com.pms.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CDCommand implements Command {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		return "/coupon&discount/new_cp&dc";
	}
//	http://localhost/ParkingManage/coupon&discount/new_cp&dc.do
}
