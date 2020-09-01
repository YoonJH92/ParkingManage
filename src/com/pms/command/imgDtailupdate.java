package com.pms.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.pms.dao.PmsLogDao;

public class imgDtailupdate implements Command{

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		PmsLogDao dao=PmsLogDao.getInstance();
    	dao.imgUpdate(request);           
		
		
		return "redirect:logdetail.do";
	}

}
