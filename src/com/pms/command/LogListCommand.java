package com.pms.command;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.pms.dao.PmsLogDao;
import com.pms.dto.PmsDto;



public  class LogListCommand implements Command {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		 PmsLogDao dao=PmsLogDao.getInstance(); 
		 ArrayList<PmsDto> arr=dao.viewList();
		 HashMap<String, Integer> result=dao.logTotalResult();
		 ArrayList<Integer>fare=dao.Curentfare();
		 
		 request.setAttribute("list", arr);
		 request.setAttribute("total", result);
		 request.setAttribute("farelist", fare);
	     return "list";		
	}

	

	
}
