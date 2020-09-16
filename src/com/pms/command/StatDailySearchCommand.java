package com.pms.command;

import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.pms.dao.StatDailyDAO;
import com.pms.dto.StatisticsDTO;

public class StatDailySearchCommand implements Command {
	/**일별 통계 검색 커맨드 **/
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ArrayList<String> date = new ArrayList<String>();
		HashMap<String, String> map = new HashMap<String, String>();
		
		/**파라미터값 체크 **/
		String startForm = request.getParameter("startForm") == "" ? "" : request.getParameter("startForm");
		String endForm = request.getParameter("endForm") == "" ? "" : request.getParameter("endForm"); 
		
		map.put("startForm",startForm);
		map.put("endForm",endForm);
		
		StatDailyDAO dao = StatDailyDAO.getInstance();
		ArrayList<StatisticsDTO> arr = dao.ListView(map);
		request.setAttribute("arr", arr);
		request.setAttribute("startForm", request.getParameter("startForm"));
		request.setAttribute("endForm", request.getParameter("endForm"));
		return "stat/daily";
	}

}
