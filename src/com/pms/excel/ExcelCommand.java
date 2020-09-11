package com.pms.excel;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;

public interface ExcelCommand {
	public HSSFWorkbook execute(HttpServletRequest request, HttpServletResponse response) throws Exception;	
}
