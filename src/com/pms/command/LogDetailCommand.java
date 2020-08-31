package com.pms.command;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.pms.dao.PmsLogDao;
import com.pms.dto.PmsDto;

public class LogDetailCommand implements Command {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		String cnum=request.getParameter("cnum");
		String fDate=request.getParameter("FDate");
		String LDate=request.getParameter("LDate");		
		PmsLogDao dao=PmsLogDao.getInstance();
		dao.imgUpdate(request);  
		dao.fare();
		if(fDate==null&&cnum==null) {
			System.out.println("null");			
		}
		else {
		ArrayList<PmsDto> arr=dao.viewDetail(fDate, LDate, cnum);
		 request.setAttribute("detail", arr);
		}
		
		
		
		return "list/logdetails";
	}

}
