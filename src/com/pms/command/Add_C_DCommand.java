package com.pms.command;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.pms.dao.PmsC_D_Dao;
import com.pms.dto.PmsCouponDto;
import com.pms.dto.PmsDiscountDto;

public class Add_C_DCommand implements Command{

	private String removeCommas(String x) {
		return x.replace(",", "");
	}

	private String choice(HttpServletRequest request, String val) {
		String[] arr = request.getParameterValues(val);
		for (String i : arr) {
			if (i.equals("직접 입력")) {
				return arr[1];
			}
		}
		return arr[0];
	}

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		PmsC_D_Dao dao = PmsC_D_Dao.getInstance();
		// 요청된 값들에 대한 인코딩
		request.setCharacterEncoding("UTF-8");
		String c_d = request.getParameter("a_c_d");
		HttpSession session = request.getSession();

		if (c_d.equals("a_coupon")) {
			PmsCouponDto dto = new PmsCouponDto();
			dto.setCPNAME(request.getParameter("name"));
			dto.setUSE_DATE(Integer.parseInt(removeCommas(choice(request, "date"))));
			dto.setDISCOUNT(Integer.parseInt(removeCommas(choice(request, "price"))));
			dto.setPURPOSE(request.getParameter("cpurpose"));
			session.setAttribute("c_d", c_d);
			dao.NewCoupon(dto);
		} else if (c_d.equals("a_discount")) {
			PmsDiscountDto dto = new PmsDiscountDto();
			dto.setCOMPANY(request.getParameter("company"));
			dto.setPURPOSE(request.getParameter("dpurpose"));
			dto.setUSE_TIME(Integer.parseInt(removeCommas(choice(request, "time"))));
			dao.NewDiscount(dto);
			session.setAttribute("c_d", c_d);
		}
		return "redirect:search_cp_dc.do";
	}
}