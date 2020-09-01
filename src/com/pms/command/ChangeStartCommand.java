package com.pms.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.websocket.Session;

import com.pms.dao.ManagerDAO;
import com.pms.dto.ManagerBean;

public class ChangeStartCommand implements Command {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		request.setCharacterEncoding("UTF-8");
		
		ManagerBean csbean = new ManagerBean();
		ManagerDAO mdao = new ManagerDAO();
		HttpSession session = request.getSession();
		
		String sessid = (String) session.getAttribute("sessid");
		
		csbean = mdao.searchM(sessid);
		
		request.setAttribute("id", csbean.getId());
		request.setAttribute("name", csbean.getName());
		request.setAttribute("email", csbean.getEmail());
		request.setAttribute("tel", csbean.getTel());
		
		return "login/change";
	}

}
