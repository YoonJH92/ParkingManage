package com.pms.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Search_Log_Command implements Command {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
	    return "coupon/search_log_Proc";
	}

}
