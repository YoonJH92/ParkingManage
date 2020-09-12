package com.pms.command;

import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.pms.dao.PmsLogDao;
import com.pms.dto.PmsDto;

import javafx.application.Application;

public class LogExcelDownCommand implements Command {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		PmsLogDao dao= PmsLogDao.getInstance();
		ArrayList<PmsDto> arr =new ArrayList<PmsDto>();
		dao.writeLogExcel(arr);
		dao.ExcelDownload(request, response);

		
		return "loglist.do";
	}
	
	
	

}