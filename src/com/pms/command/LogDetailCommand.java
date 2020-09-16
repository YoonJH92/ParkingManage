package com.pms.command;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;

import javax.mail.Session;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.pms.dao.PmsLogDao;
import com.pms.dto.PmsLogDto;
import com.pms.dto.PmsPageDto;
import com.pms.paging.Pagination2;

import oracle.net.aso.f;

public class LogDetailCommand implements Command{
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String cnum=request.getParameter("cnum"); 
		String fDate=request.getParameter("FDate"); 		
		String LDate=request.getParameter("LDate");		
		PmsLogDao dao=PmsLogDao.getInstance();		
		
		int displayRow=20; 
	 	int curPage = (request.getParameter("p") == null || request.getParameter("p") == "") ? 1 : Integer.parseInt(request.getParameter("p")); 
	 	if(fDate==null||fDate.equals("")&&LDate.equals("")&&cnum.equals("")) {				 		
	 		fDate=dao.Yesterday();		
	 		if(request.getParameter("dRs")!=null){		 
	 		displayRow=Integer.parseInt(request.getParameter("dRs"));	 		
	 	}			
	 	int count=dao.datailCount(fDate, LDate, cnum); 
	 	Pagination2 pagination = new Pagination2(count, curPage,displayRow);
		ArrayList<PmsLogDto> arr=dao.viewDetail (pagination,fDate, LDate, cnum);			
			request.setAttribute("detail", arr);
			request.setAttribute("displayRow", displayRow);
		    request.setAttribute("pagination", pagination);		
			request.setAttribute("Scnum", cnum);
			request.setAttribute("FDate", fDate);
			request.setAttribute("LDate", LDate);
			request.setAttribute("p", curPage); 
			request.setAttribute("listCnt", count);

			}
	 	
	 	
	 	else {	
			int count=dao.datailCount(fDate, LDate, cnum);			
			 if(request.getParameter("dRs")!=null){		 
				displayRow=Integer.parseInt(request.getParameter("dRs"));
			 }			
			Pagination2 pagination = new Pagination2(count, curPage,displayRow);
			ArrayList<PmsLogDto> arr=dao.viewDetail (pagination,fDate, LDate, cnum); 
			request.setAttribute("displayRow", displayRow);
			request.setAttribute("detail", arr);
			request.setAttribute("pagination", pagination);						
			request.setAttribute("Scnum", cnum);
			request.setAttribute("FDate", fDate);
			request.setAttribute("LDate", LDate);
			request.setAttribute("p", curPage); 
			request.setAttribute("listCnt", count);


			}	
		return "list/logdetails";
	}

}
