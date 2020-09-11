package com.pms.command;

import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.pms.dao.MemberManageDAO;
import com.pms.dto.memberManageDTO;

public class memberSearchCommand implements Command {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		ArrayList<String> date = new ArrayList<String>();
		HashMap<String, String> map = new HashMap<String, String>();
		
		String dateSearch = request.getParameter("dateSearch");
		String startForm = request.getParameter("startForm") == "" ? "" : request.getParameter("startForm")+" 00:00:00";
		String endForm = request.getParameter("endForm") == "" ? "" : request.getParameter("endForm")+" 23:59:59"; 
		String search = request.getParameter("search");
		String searchForm = request.getParameter("searchForm");
		
		map.put("dateSearch",dateSearch);
		map.put("startForm",startForm);
		map.put("endForm",endForm);
		map.put("search",search);
		map.put("searchForm",searchForm);
		
		MemberManageDAO dao = MemberManageDAO.getInstance();
		ArrayList<memberManageDTO> arr = dao.ListMember(map);
		request.setAttribute("arr", arr);
		
		request.setAttribute("startForm", request.getParameter("startForm"));
		request.setAttribute("endForm", request.getParameter("endForm"));
		request.setAttribute("searchForm", request.getParameter("searchForm"));
		return "member/manage";
	}

}
