package com.pms.command;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.pms.dao.PmsLogDao;
import com.pms.dto.PmsLogDto;
import com.pms.dto.PmsPageDto;

public class imgDtailupdate implements Command{

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		

		
		PmsLogDao dao=PmsLogDao.getInstance();
    	//dao.imgUpdate(request);           
		dao.datailImgUpdate(request, response);
    	
		return "redirect:logdetail.do";
	}

}
