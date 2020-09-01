package com.pms.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.pms.dao.ManagerDAO;
import com.pms.dto.ManagerBean;

public class SearchIdCommand implements Command {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		
		request.setCharacterEncoding("UTF-8");
		
		ManagerDAO mdao = new ManagerDAO();
		ManagerBean idbean = new ManagerBean();
		idbean.setName(request.getParameter("name"));
		idbean.setEmail(request.getParameter("email"));
		idbean.setTel(request.getParameter("tel"));
		String idsearch =mdao.MdSearchId(idbean);
		
		request.setAttribute("idsearch", idsearch);
		
		
		
		
		return "login/searchId";
	}

}
