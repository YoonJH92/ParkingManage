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
		ManagerDAO mdao = new ManagerDAO();
		mbean.setId(request.getParameter("id"));
		mbean.setPass(request.getParameter("pass"));
		mbean.setName(request.getParameter("name"));
		mbean.setEmail(request.getParameter("email"));
		mbean.setTel(request.getParameter("tel"));
		
		mdao.insertManager(mbean);	
		
			
			int re;
			
			
			
			if(mbean.getPass().equals(mbean.getPass2())){
				
				re = -1;
				
			}else{
				re = 1;
			}
		request.setAttribute("re", re);
		
			
		
		
		
		
		
		return "join";
	}

}
