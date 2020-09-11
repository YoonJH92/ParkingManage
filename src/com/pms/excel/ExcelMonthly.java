package com.pms.excel;

import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import com.pms.dao.StatDailyDAO;
import com.pms.dto.StatisticsDTO;

public class ExcelMonthly implements ExcelCommand {

	@Override
	public HSSFWorkbook execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String startForm = request.getParameter("HidStartForm") == "" ? "" : request.getParameter("HidStartForm");
		String endForm = request.getParameter("HidEndForm") == "" ? "" : request.getParameter("HidEndForm"); 
		
		ArrayList<StatisticsDTO> arr = new ArrayList<StatisticsDTO>();
		StatDailyDAO dao = StatDailyDAO.getInstance();
		
		if((startForm !="" && startForm != null) || (endForm !="" && endForm !=null)) {
			HashMap<String, String> map = new HashMap<String, String>();
			map.put("startForm",startForm);
			map.put("endForm",endForm);
			arr = dao.ListView(map);
		}else {
			arr = dao.ListView();
		}
		
		HSSFWorkbook workbook = new HSSFWorkbook();
        HSSFSheet sheet = workbook.createSheet();
        HSSFRow row = null;
        
        HSSFCell cell = null;
        
        row = sheet.createRow(0);
        String[] headerKey = {"시간", "입차일반", "입차월정액", "입차합계","출차일반","출차월정액","출차합계","출차 사용요금","월정액 등록수","월정액 사용요금","합계"};
        
        for(int i=0; i<headerKey.length; i++) {
            cell = row.createCell(i);
            cell.setCellValue(headerKey[i]);
        }
        
        for(int i=0; i<arr.size(); i++) {
            row = sheet.createRow(i + 1);
            StatisticsDTO vo = arr.get(i);
            
            cell = row.createCell(0);
            cell.setCellValue(vo.getTime());
            
            cell = row.createCell(1);
            cell.setCellValue(vo.getInNomal());
            
            cell = row.createCell(2);
            cell.setCellValue(vo.getInMonth());
            
            cell = row.createCell(3);
            cell.setCellValue(vo.getInNomal()+vo.getInMonth());
            
            cell = row.createCell(4);
            cell.setCellValue(vo.getOutNomal());
            
            cell = row.createCell(5);
            cell.setCellValue(vo.getOutMonth());
            
            cell = row.createCell(6);
            cell.setCellValue(vo.getOutNomal()+vo.getOutMonth());
            
            cell = row.createCell(7);
            cell.setCellValue(vo.getPay());
            
            cell = row.createCell(8);
            cell.setCellValue(vo.getMonthCount());
            
            cell = row.createCell(9);
            cell.setCellValue(vo.getMonthPay());
            
            cell = row.createCell(10);
            cell.setCellValue(vo.getPay() + vo.getMonthPay());
            
        }
		return workbook;
	}

}
