package com.pms.command;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.pms.dto.memberManageDTO;

public class MemberInsertCommand implements Command {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		Calendar cal = Calendar.getInstance();
		String startDate = request.getParameter("startDate");
		SimpleDateFormat fm = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date toDate = fm.parse(startDate);

		memberManageDTO mem = new memberManageDTO();
		mem.setEmail(request.getParameter("email"));
		mem.setCNUM(request.getParameter("CNUM"));
		mem.setName(request.getParameter("name"));
		mem.setPay(Integer.parseInt(request.getParameter("pay")));
		mem.setPhone(request.getParameter("phone"));
		mem.setStartDate(toDate);
		mem.setType(request.getParameter("type"));

		return "member/manage";
	}

}
