package com.pms.command;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Enumeration;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.pms.dao.PmsC_D_Dao;
import com.pms.dto.Pms_Coupon_Log_Dto;
import com.pms.util.PMSRandom2;

public class Send_Coupon_Command implements Command {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		PmsC_D_Dao dao = PmsC_D_Dao.getInstance();
		// 요청된 값들에 대한 인코딩
		request.setCharacterEncoding("UTF-8");
		String[] num = request.getParameterValues("num");
		String[] name = request.getParameterValues("name");
		String[] cnum = request.getParameterValues("cnum");
		String[] cpnum = request.getParameterValues("cpnum");
		String[] cpname = request.getParameterValues("cpname");
		String[] date = request.getParameterValues("date");
		String[] discount = request.getParameterValues("discount");
		String[] purpose = request.getParameterValues("purpose");
		
		Pms_Coupon_Log_Dto dto = new Pms_Coupon_Log_Dto();
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Calendar cal = Calendar.getInstance();
		
		for(int e = 0; e<cpname.length; e++) {
			for(int i = 0; i < name.length; i++) {
				cal.setTime(new java.util.Date());
				num[i] = num[i].replaceAll("-", "");
				date[e] = date[e].replaceAll("일","");
				StringBuffer cpcode = PMSRandom2.randomCouponCode(15);
				
				dto.setCNUM(cnum[e]);
				dto.setCPNUM(Integer.parseInt(cpnum[e]));
				dto.setUSED("0");
				cal.add(Calendar.DAY_OF_MONTH, Integer.parseInt(date[e]));
				dto.setVALIDITY(format.format(cal.getTime()));
				dto.setCPCODE(cpcode.toString());
				dao.NewCoupon_Log(dto);
				dao.SendSms(name[i],num[i],cpname[e],format.format(cal.getTime()),discount[e],purpose[e], cpcode.toString());
			}
		}
		return null;
	}

}
