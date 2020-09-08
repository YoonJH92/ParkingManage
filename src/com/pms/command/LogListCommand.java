package com.pms.command;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.record.pivottable.PageItemRecord;

import com.pms.dao.PmsLogDao;
import com.pms.dto.PmsDto;
import com.pms.dto.PmsPageDto;

public  class LogListCommand implements Command {
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		 PmsLogDao dao=PmsLogDao.getInstance(); 
		 int page = 1;	 
		 if(request.getParameter("page")!=null) {
			page=Integer.parseInt(request.getParameter("page"));
		 	}
		 	PmsPageDto paging =new PmsPageDto();		 
		 	int dispalyRow=20;
		 	if(request.getParameter("dRs")!=null){
		 	dispalyRow=Integer.parseInt(request.getParameter("dRs"));
		 	System.out.println(dispalyRow);
		 	paging.setDisplayRow(dispalyRow);
		 	}
		 	int count=dao.getlistCount();
		 	paging.setTotalCount(count);
		 	paging.setPage(page);
		 
		 HashMap<String, Integer> result=dao.logTotalResult();
		 ArrayList<String>fare=dao.Curentfare();		 
		 ArrayList<PmsDto> arr=dao.viewList(paging);		 	
		 request.setAttribute("list", arr);
		 request.setAttribute("displayRow", dispalyRow);
		 request.setAttribute("paging", paging);
		 request.setAttribute("total", result);
		 request.setAttribute("farelist", fare);
	     return "list";		
	}
	
}
