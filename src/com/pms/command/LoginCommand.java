package com.pms.command;

import javax.servlet.ServletRequest;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.pms.dao.ManagerDAO;
import com.pms.dto.ManagerBean;

public class LoginCommand implements Command{

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		request.setCharacterEncoding("UTF-8");
		
		ManagerBean mbean = new ManagerBean();
		
		
		ManagerDAO mdao = new ManagerDAO();
		String id = request.getParameter("id");
		String pass = request.getParameter("pass");
		int re = mdao.loginManager(id,pass);
		HttpSession session = request.getSession();
		
		session.setAttribute("sessid", id);
		
		request.setAttribute("re", re);

				
		String save = request.getParameter("save");
				
		String cid = request.getParameter("id");
				
				
				if(save != null){
		
					Cookie cookie = new Cookie("cid",cid);

					cookie.setMaxAge(60*60*24*7);

					response.addCookie(cookie);
					
					 
				}
				if(save == null){
					
					
					Cookie cookie = new Cookie("cid",cid);

					cookie.setMaxAge(0);
					
					response.addCookie(cookie);
					 
				}
		
		return "login/login";
	}
	
	
	
}
