package com.pms.command;

import java.util.ArrayList;
import java.util.Enumeration;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.pms.dao.PmsC_D_Dao;

public class Send_Coupon_Command implements Command {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		PmsC_D_Dao dao = PmsC_D_Dao.getInstance();
		// 요청된 값들에 대한 인코딩
		request.setCharacterEncoding("UTF-8");
		String[] num = request.getParameterValues("num");
		String[] name = request.getParameterValues("name");
		String[] cpname = request.getParameterValues("cpname");
		String[] date = request.getParameterValues("date");
		String[] discount = request.getParameterValues("discount");
		String[] purpose = request.getParameterValues("purpose");
		
		for(int e = 0; e<cpname.length; e++) {
			for(int i = 0; i < name.length; i++) {
				num[i] = num[i].replaceAll("-", "");
				dao.SendSms(name[i],num[i],cpname[e],date[e],discount[e],purpose[e]);
			}
		}
		return null;
	}

}
