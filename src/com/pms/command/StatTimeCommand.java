package com.pms.command;
	
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.pms.dao.StatTimeDAO;
import com.pms.dto.StatisticsDTO;
	
public class StatTimeCommand implements Command {
	
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		request.setCharacterEncoding("UTF-8");
		String startForm = request.getParameter("startForm");
		
		StatisticsDTO stdto = new StatisticsDTO();
		StatTimeDAO stdao = new StatTimeDAO();
		stdao.timeList(startForm);
		
		ArrayList<StatisticsDTO> arr = stdao.timeList(startForm);
		
		request.setAttribute("arr", arr);
		
		
		return "stat/time";
	}
	
}
