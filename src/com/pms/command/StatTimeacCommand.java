package com.pms.command;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.pms.dao.StatTime2DAO;
import com.pms.dao.StatTimeDAO;
import com.pms.dto.StatisticsDTO;

public class StatTimeacCommand implements Command {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		request.setCharacterEncoding("UTF-8");
		StatisticsDTO stdto = new StatisticsDTO();
		StatTimeDAO stdao = new StatTimeDAO();
		
		SimpleDateFormat mSimpleDateFormat = new SimpleDateFormat ( "yyyy-MM-dd", Locale.KOREA );
		Date currentTime = new Date ();
		String startForm = mSimpleDateFormat.format ( currentTime );
		System.out.println ( startForm );
		
		
		ArrayList<StatisticsDTO> arr = stdao.timeList(startForm);
		request.setAttribute("arr", arr);
		request.setAttribute("startForm", startForm);
		
		
		return "stat/time";
	}

}
