package com.pms.command;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.pms.dao.PmsLogDao;
import com.pms.dto.PmsDto;
import com.pms.dto.PmsPageDto;

public  class LogListCommand implements Command {
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		 PmsLogDao dao=PmsLogDao.getInstance(); 
		 int page = 1;//처음에는 1 	 
		 if(request.getParameter("page")!=null) {
			 page=Integer.parseInt(request.getParameter("page"));
		 }
		 	PmsPageDto paging =new PmsPageDto();
		 	paging.setPage(page);
		 	int count=dao.getlistCount();
		 	paging.setTotalCount(count);
		 
		 ArrayList<PmsDto> arr=dao.viewList(page);
		 HashMap<String, Integer> result=dao.logTotalResult();
		 ArrayList<String>fare=dao.Curentfare();		 
		 request.setAttribute("list", arr);
		 request.setAttribute("paging", paging);
		 request.setAttribute("total", result);
		 request.setAttribute("farelist", fare);
	     return "list";		
	}

	

	
}
