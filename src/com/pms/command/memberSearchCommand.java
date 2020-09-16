package com.pms.command;

import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.pms.dao.MemberManageDAO;
import com.pms.dto.memberManageDTO;
import com.pms.paging.Pagination2;

public class memberSearchCommand implements Command {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		ArrayList<String> date = new ArrayList<String>();
		HashMap<String, String> map = new HashMap<String, String>();
		MemberManageDAO dao = MemberManageDAO.getInstance();
		
		String dateSearch = request.getParameter("dateSearch");
		String startForm = request.getParameter("startForm") == "" ? "" : request.getParameter("startForm")+" 00:00:00";
		String endForm = request.getParameter("endForm") == "" ? "" : request.getParameter("endForm")+" 23:59:59"; 
		String search = request.getParameter("search");
		String searchForm = request.getParameter("searchForm");
		int limit = (request.getParameter("limit") == null || request.getParameter("limit") == "") ? 10 : Integer.parseInt(request.getParameter("limit"));
		
		map.put("dateSearch",dateSearch);
		map.put("startForm",startForm);
		map.put("endForm",endForm);
		map.put("search",search);
		map.put("searchForm",searchForm);
		
		int listCnt = dao.ListMemberCount(map); //전체 리스트 수
		int curPage = (request.getParameter("p") == null || request.getParameter("p") == "") ? 1 : Integer.parseInt(request.getParameter("p")); // 현재 페이지
		Pagination2 pagination = new Pagination2(listCnt, curPage, limit); //페이징 객체 생성
		ArrayList<memberManageDTO> arr = dao.ListMember(map,pagination); // 리스트 데이터 가져옴		

		request.setAttribute("arr", arr);
		request.setAttribute("dateSearch", request.getParameter("dateSearch"));
		request.setAttribute("search", request.getParameter("search"));
		request.setAttribute("startForm", request.getParameter("startForm"));
		request.setAttribute("endForm", request.getParameter("endForm"));
		request.setAttribute("searchForm", request.getParameter("searchForm"));
		request.setAttribute("pagination", pagination); // 페이징 설정 
		request.setAttribute("listCnt", listCnt); // 전체 개수
		request.setAttribute("p", curPage); // 현재 페이지

		return "member/manage";
	}

}


