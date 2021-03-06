package com.pms.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.pms.dao.ManagerDAO;
import com.pms.dto.ManagerBean;

public class JoinCommand implements Command {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

		request.setCharacterEncoding("UTF-8");

		ManagerBean mbean = new ManagerBean();
		ManagerBean mbean2 = new ManagerBean();
		ManagerDAO mdao = new ManagerDAO();
		ManagerDAO mdao2 = new ManagerDAO();
		String id = request.getParameter("id");
		String pass = request.getParameter("pass");
		String pass2 = request.getParameter("pass2");
		
		if(!pass.equals(pass2)) {
			return "login/joinFail2";
		}

		mbean.setId(id);
		mbean.setPass(pass);
		mbean.setName(request.getParameter("name"));
		mbean.setEmail(request.getParameter("email"));
		mbean.setTel(request.getParameter("tel"));
		
		mbean2 = mdao2.searchM(id);
		if (id.equals(mbean2.getId())) {

			return "login/joinFail";

		} else {
		
			mdao.insertManager(mbean);


			
			return "login/joinComp";
		}

	}

}











