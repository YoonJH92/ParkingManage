package com.pms.command;

import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import com.pms.dao.PmsC_D_Dao;
import com.pms.dto.PmsCouponDto;

public class Modify_C_DCommand implements Command {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// 요청된 값들에 대한 인코딩
		request.setCharacterEncoding("UTF-8");
		PmsC_D_Dao dao = PmsC_D_Dao.getInstance();
		String c_d = request.getParameter("c_d");
		int num = Integer.parseInt(request.getParameter("num"));
		
		if (c_d.equals("coupon")) {
			String cpname = request.getParameter("name");
			int date = Integer.parseInt(request.getParameter("date"));
			String c_purpose = request.getParameter("c_purpose");
			int discount = Integer.parseInt(removeCommas(request.getParameter("price")));

			dao.modifyCMember(num, c_d, cpname, date, c_purpose, discount);
		} else if (c_d.equals("discount")) {
			String company= request.getParameter("company");
			int time = Integer.parseInt(request.getParameter("time"));
			String d_purpose = request.getParameter("d_purpose");
			
			dao.modifyDMember(num, c_d, d_purpose, time, company);
		}
		return "null";
	}
	
	private String removeCommas(String x) {
		return x.replace(",", "");
	}

}
