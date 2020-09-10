package com.pms.command;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.pms.dao.StatDailyDAO;
import com.pms.dto.StatisticsDTO;



public class StatDailyCommand implements Command{

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		StatDailyDAO dao = StatDailyDAO.getInstance();
		ArrayList<StatisticsDTO> arr = dao.ListView();
		request.setAttribute("arr", arr);
		return "stat/daily";
	}
}
