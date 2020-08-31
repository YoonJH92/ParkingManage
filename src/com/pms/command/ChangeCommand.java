package com.pms.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.pms.dao.ManagerDAO;
import com.pms.dto.ManagerBean;

public class ChangeCommand implements Command {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		request.setCharacterEncoding("UTF-8");
		
		ManagerDAO spdao = new ManagerDAO();
		ManagerDAO mdao = new ManagerDAO();
		ManagerBean cbean = new ManagerBean();
		
		HttpSession session = request.getSession();
		
		String sessid = (String) session.getAttribute("sessid");
		
		String spass = spdao.searchM(sessid).getPass();
		String pass = request.getParameter("pass");
		
		cbean.setName(request.getParameter("name"));
		cbean.setEmail(request.getParameter("email"));
		cbean.setTel(request.getParameter("tel"));
		cbean.setPass(request.getParameter("pass"));
		
		if(spass.equals(pass)) {
			
			mdao.updateM(cbean);
			return "login/changeComp";
			
		}else {
			
			return "login/changeFail";
			
		}
		
		
		
	}

}
