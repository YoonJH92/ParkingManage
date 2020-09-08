package com.pms.command;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.pms.dao.PmsLogDao;
import com.pms.dto.PmsDto;
import com.pms.dto.PmsPageDto;

public class LogDetailCommand implements Command {

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
		if(fDate==null&&cnum==null) {
			System.out.println("null");
			fDate="-1";			
			int count=dao.datailCount(fDate, LDate, cnum);
			paging.setTotalCount(count);
			
			ArrayList<PmsDto> arr=dao.viewDetail (paging,fDate, LDate, cnum);
			request.setAttribute("detail", arr);
		    request.setAttribute("paging", paging);

		// dao.fare();
		// dao.totalfare();
			
	}
		else {
			ArrayList<PmsDto> arr=dao.viewDetail (paging,fDate, LDate, cnum); 
			int count=dao.datailCount(fDate, LDate, cnum);
		 	paging.setTotalCount(count);	 	
			request.setAttribute("detail", arr);
			request.setAttribute("paging", paging);
			request.setAttribute("cnum", cnum);
			request.setAttribute("FDate", fDate);
			request.setAttribute("LDate", LDate);
		}
				
		return "list/logdetails";
	}
	
}
