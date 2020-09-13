package com.pms.command;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.pms.dao.PmsLogDao;
import com.pms.dto.PmsLogDto;
import com.pms.dto.PmsPageDto;

public class LogDetailTextCommand implements Command{

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
		return "list/logdetails";
	}

}
