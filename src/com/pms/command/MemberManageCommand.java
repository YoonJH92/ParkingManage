package com.pms.command;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.pms.dao.MemberManageDAO;
import com.pms.dto.memberManageDTO;
import com.pms.paging.Pagination;
import com.pms.paging.Pagination2;

public class MemberManageCommand implements Command {
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		MemberManageDAO dao = MemberManageDAO.getInstance();
		int listCnt = dao.ListMemberCount();
		int curPage = (request.getParameter("p") == null || request.getParameter("p") == "") ? 1 : Integer.parseInt(request.getParameter("p"));
		Pagination2 pagination = new Pagination2(listCnt, curPage);
		ArrayList<memberManageDTO> arr = dao.ListMember(pagination);
		request.setAttribute("arr", arr);
		request.setAttribute("pagination", pagination);
		request.setAttribute("listCnt", listCnt);
		request.setAttribute("p", curPage);
		return "member/manage";
	}
}