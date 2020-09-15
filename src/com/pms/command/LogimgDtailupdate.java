package com.pms.command;

import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.pms.dao.PmsLogDao;
import com.pms.dto.PmsLogDto;
import com.pms.dto.PmsPageDto;

public class LogimgDtailupdate implements Command{

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {	
		request.setCharacterEncoding("UTF-8");  	
		PmsLogDao dao=PmsLogDao.getInstance();


		System.out.println(request.getParameter("cnum"));
		ArrayList<String> url= dao.imgDetailUpdate(request);			

		String encodedParam = URLEncoder.encode(url.get(2), "UTF-8");		
		return "redirect:logdetail.do?Search=&"+url.get(0)+url.get(1)+encodedParam+url.get(3);
		
			
	
	}

}
