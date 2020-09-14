package com.pms.excel;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import com.pms.dao.PmsLogDao;
import com.pms.dto.PmsLogDto;

public class ExcelDatailLog implements ExcelCommand{

	@Override
	public HSSFWorkbook execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		PmsLogDao dao=PmsLogDao.getInstance();
	    String cnum=request.getParameter("cnum"); 
		String fDate=request.getParameter("FDate"); 
		String LDate=request.getParameter("LDate");		
		
		if(fDate==""&&LDate==""&&cnum=="") {
			fDate="-1";
		}
			
		ArrayList<PmsLogDto> arr=dao.viewDetail(fDate, LDate, cnum);
		HSSFWorkbook workbook = new HSSFWorkbook();
        HSSFSheet sheet = workbook.createSheet();
        HSSFRow row = null;    
        HSSFCell cell = null;        
        row = sheet.createRow(0);
        String[] headerKey = {"No.","차량 번호","입차 시간","출차시간","사용금액","쿠폰 여부","월정액 여부","할인 적용여부","총 사용 금액"};
        
        for(int i=0; i<headerKey.length; i++) {
            cell = row.createCell(i);
            cell.setCellValue(headerKey[i]);
        }
        
        for(int i=0; i<arr.size(); i++) {
            row = sheet.createRow(i + 1);
            PmsLogDto dto = arr.get(i);
            
            cell = row.createCell(0);
            cell.setCellValue(dto.getIdx());
            
            cell = row.createCell(1);
            cell.setCellValue(dto.getCnum());
            
            cell = row.createCell(2);
            cell.setCellValue(dto.getInTime());
           
            cell = row.createCell(3);
            cell.setCellValue(dto.getOutTime());
            
            cell = row.createCell(4);
            cell.setCellValue(dto.getPay());
            
            cell = row.createCell(5);
            cell.setCellValue(dto.getCpNum());
            
            cell = row.createCell(6);
            cell.setCellValue(dto.getMonthNum());
            
            cell = row.createCell(7);
            cell.setCellValue(dto.getSaleNum());
           
            cell = row.createCell(8);
            cell.setCellValue(dto.getTotalPay());
                                                
        }
		return workbook;

	}

	
}
