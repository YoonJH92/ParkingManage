package com.pms.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class StatDailyCommand implements Command{

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		return "stat/daily";
	}
	
}
