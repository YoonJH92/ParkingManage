package com.pms.command;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.pms.dao.MemberManageDAO;
import com.pms.dto.memberManageDTO;

public class MemberManageCommand implements Command {
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		MemberManageDAO dao = MemberManageDAO.getInstance();
		ArrayList<memberManageDTO> arr = dao.ListMember();
		request.setAttribute("arr", arr);
		return "member/manage";
	}
}
