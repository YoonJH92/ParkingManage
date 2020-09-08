package com.pms.command;
	
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
	
public class StatTimeCommand implements Command {
	
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		request.setCharacterEncoding("UTF-8");
		String startForm = request.getParameter("startForm");
		
		
		return "stat/time";
	}
	
}
