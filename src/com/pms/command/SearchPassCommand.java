package com.pms.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.pms.dao.ManagerDAO;
import com.pms.dto.ManagerBean;

public class SearchPassCommand implements Command {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		
		request.setCharacterEncoding("UTF-8");
		ManagerDAO mdao = new ManagerDAO();
		ManagerBean passbean = new ManagerBean();
		String passsearch =mdao.MdSearchPass(passbean);
		
		request.setAttribute("passsearch", passsearch);
		
		
		
		
		return "login/searchPass";
	}

}
