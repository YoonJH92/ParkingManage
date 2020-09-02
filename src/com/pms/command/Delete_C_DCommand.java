package com.pms.command;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.pms.dao.PmsC_D_Dao;
import com.pms.dto.PmsCouponDto;
import com.pms.dto.PmsDiscountDto;

public class Delete_C_DCommand implements Command{

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// 요청된 값들에 대한 인코딩
		request.setCharacterEncoding("UTF-8");
		PmsC_D_Dao dao = PmsC_D_Dao.getInstance();
		String c_d = request.getParameter("c_d");
		int num = Integer.parseInt(request.getParameter("num"));
		
		if (c_d.equals("coupon")) {
			dao.Delete(num, c_d);
		} else if (c_d.equals("discount")) {
			dao.Delete(num, c_d);
		}
		return "redirect:delete_C_D.do";
	}
}