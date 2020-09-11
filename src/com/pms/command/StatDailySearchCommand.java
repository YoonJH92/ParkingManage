package com.pms.command;

import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.pms.dao.StatDailyDAD;
import com.pms.dto.StatisticsDTO;

public class StatDailySearchCommand implements Command {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ArrayList<String> date = new ArrayList<String>();
		HashMap<String, String> map = new HashMap<String, String>();
		
		String startForm = request.getParameter("startForm") == "" ? "" : request.getParameter("startForm");
		String endForm = request.getParameter("endForm") == "" ? "" : request.getParameter("endForm"); 
		
		map.put("startForm",startForm);
		map.put("endForm",endForm);
		
		StatDailyDAD dao = StatDailyDAD.getInstance();
		ArrayList<StatisticsDTO> arr = dao.ListView(map);
		request.setAttribute("arr", arr);
		request.setAttribute("startForm", request.getParameter("startForm"));
		request.setAttribute("endForm", request.getParameter("endForm"));
		return "stat/daily";
	}

}