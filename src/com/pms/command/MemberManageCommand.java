package com.pms.command;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.pms.dao.MemberManageDAO;
import com.pms.dto.memberManageDTO;
import com.pms.paging.Pagination;
import com.pms.paging.Pagination2;

public class MemberManageCommand implements Command {
	/**월정액 회원 페이지 커맨드**/
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		MemberManageDAO dao = MemberManageDAO.getInstance();
		
		int listCnt = dao.ListMemberCount(); // 전체 리스트 개수
		int curPage = (request.getParameter("p") == null || request.getParameter("p") == "") ? 1 : Integer.parseInt(request.getParameter("p")); // 현재 페이지
		int limit = (request.getParameter("limit") == null || request.getParameter("limit") == "") ? 10 : Integer.parseInt(request.getParameter("limit")); //리스트 개수
		Pagination2 pagination = new Pagination2(listCnt, curPage, limit); // 페이징 설정
		ArrayList<memberManageDTO> arr = dao.ListMember(pagination); // 리스트에 rownum 설정을 위한 Pagination2 객체 전달
		request.setAttribute("arr", arr); // 데이터 리스트
		request.setAttribute("pagination", pagination); // 페이징
		request.setAttribute("listCnt", listCnt); // 전체 개수
		request.setAttribute("p", curPage); // 현재 페이지
		return "member/manage";
	}
}