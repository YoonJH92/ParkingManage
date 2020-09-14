package com.pms.command;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.pms.dao.PmsLogDao;
import com.pms.dto.PmsLogDto;
import com.pms.dto.PmsPageDto;

public class pagingCommand implements Command{
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		String cnum=request.getParameter("cnum"); 
		String fDate=request.getParameter("FDate"); 		
		String LDate=request.getParameter("LDate");			
		PmsLogDao dao=PmsLogDao.getInstance();		
		PmsPageDto paging=new PmsPageDto();			
		 int page = 1;	 
		 if(request.getParameter("page")!=null) {
			 page=Integer.parseInt(request.getParameter("page"));
			}
		paging.setPage(page);	
		int displayRow=20; //기본 값 
		if(fDate==null&&cnum==null) {	
			System.out.println("null");
			fDate="-1";		
			paging.setDisplayRow(displayRow);		
			int count=dao.datailCount(fDate, LDate, cnum); 
			paging.setTotalCount(count);		
			ArrayList<PmsLogDto> arr=dao.viewDetail (paging,fDate, LDate, cnum);			
			request.setAttribute("detail", arr);
		    request.setAttribute("paging", paging);						
			}	
		else {	
			int count=dao.datailCount(fDate, LDate, cnum);			
			 if(request.getParameter("dRs")!=null){		 
				displayRow=Integer.parseInt(request.getParameter("dRs"));
			 }			
				paging.setDisplayRow(displayRow); 
				paging.setTotalCount(count);	 
				ArrayList<PmsLogDto> arr=dao.viewDetail (paging,fDate, LDate, cnum); 
				request.setAttribute("displayRow", displayRow);
				request.setAttribute("detail", arr);
				request.setAttribute("paging", paging);
				request.setAttribute("cnum", cnum);
				request.setAttribute("FDate", fDate);
				request.setAttribute("LDate", LDate);
			}	
		
		Map tempJ = new HashMap<String, PmsPageDto>();        
			tempJ.put("page", paging.getPage());
			tempJ.put("displayPage",paging.getdisplayPage());
			tempJ.put("totalCount", paging.getTotalCount());
			tempJ.put("endNum", paging.getEndNum());
			tempJ.put("startNum" ,paging.getStartNum());
			tempJ.put("displayRow",paging.getDisplayRow());
			tempJ.put("begingPage", paging.getBeginPage());
			tempJ.put("endpage", paging.getEndPage());
		
			
		return "list/detailtest";
	}

	
	
	
	
	
	
	
}
