package com.pms.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.pms.dao.MemberManageDAO;
import com.pms.dto.memberManageDTO;

public class MemberUpdateCommand implements Command {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// 객체 생성 후 값 저장
		memberManageDTO mem = new memberManageDTO();
		mem.setIdx(Integer.parseInt(request.getParameter("idx")));
		mem.setEmail(request.getParameter("email"));
		mem.setCNUM(request.getParameter("CNUM"));
		mem.setName(request.getParameter("name"));
		mem.setPay(Integer.parseInt(request.getParameter("pay")));
		mem.setPhone(request.getParameter("phone"));
		mem.setStartDate(request.getParameter("startDate"));
		mem.setStopDate(request.getParameter("stopDate"));
		mem.setType(request.getParameter("type"));

		MemberManageDAO dao = MemberManageDAO.getInstance();
		dao.updateMember(mem);
		
		return "redirect:member.do";
	}

}
