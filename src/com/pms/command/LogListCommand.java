package com.pms.command;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.record.pivottable.PageItemRecord;

import com.pms.dao.PmsLogDao;
import com.pms.dto.PmsLogDto;
import com.pms.dto.PmsPageDto;
import com.pms.paging.Pagination;
import com.pms.paging.Pagination2;

public  class LogListCommand implements Command {
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		 PmsLogDao dao=PmsLogDao.getInstance(); 
		
		 
		 	int count=dao.getlistCount();
		 	int dispalyRow=20;
		 	int curPage = (request.getParameter("p") == null || request.getParameter("p") == "") ? 1 : Integer.parseInt(request.getParameter("p")); // 현재 페이지
		 	
		 	if(request.getParameter("dRs")!=null){
		 	dispalyRow=Integer.parseInt(request.getParameter("dRs"));
		 	}
<<<<<<< Updated upstream
		 
		 
		 ArrayList<PmsLogDto> arr=dao.viewList(pagination);		 	
		 request.setAttribute("displayRow", dispalyRow);
		 request.setAttribute("pagination", pagination);		 
		 HashMap<String, Integer> result=dao.logTotalResult();		 
		 ArrayList<String>fare=dao.Curentfare();	
		 request.setAttribute("list", arr);
		 request.setAttribute("total", result);
		 request.setAttribute("farelist", fare);
		 request.setAttribute("pagination", pagination); 
		 request.setAttribute("listCnt", count); 
		 request.setAttribute("p", curPage); 
=======
		 	 
		 	Pagination2 pagination = new Pagination2(count, curPage, dispalyRow);	
		 	ArrayList<PmsLogDto> arr=dao.viewList(pagination);		 	
		 	request.setAttribute("displayRow", dispalyRow);
		 	request.setAttribute("pagination", pagination);		 
		 	HashMap<String, Integer> result=dao.logTotalResult();		 
		 	ArrayList<String>fare=dao.Curentfare();		 		 
		 	request.setAttribute("list", arr);
		 	request.setAttribute("total", result);
		 	request.setAttribute("farelist", fare);
		 	request.setAttribute("pagination", pagination); 
		 	request.setAttribute("listCnt", count); 
		 	request.setAttribute("p", curPage); 
>>>>>>> Stashed changes
		 
		 
		 
	     return "list";		
	}
	
}
