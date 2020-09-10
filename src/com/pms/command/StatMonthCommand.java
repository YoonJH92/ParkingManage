package com.pms.command;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.pms.dao.StatMonthDAO;
import com.pms.dto.StatisticsDTO;

public class StatMonthCommand implements Command {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		StatMonthDAO dao = StatMonthDAO.getInstance();
		ArrayList<StatisticsDTO> arr = dao.ListView();
		request.setAttribute("arr", arr);
		return "stat/monthly";
	}

}
