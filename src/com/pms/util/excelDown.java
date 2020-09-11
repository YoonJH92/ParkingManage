package com.pms.util;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class excelDown {

	public static void main(String[] args) {

	    // Workbook 생성
	    Workbook xlsWb = new HSSFWorkbook(); // Excel 2007 이전 버전
	    Workbook xlsxWb = new XSSFWorkbook(); // Excel 2007 이상

	    // *** Sheet-------------------------------------------------
	    // Sheet 생성
	    Sheet sheet1 = xlsWb.createSheet("firstSheet");

	    // 컬럼 너비 설정
	    sheet1.setColumnWidth(0, 10000);
	    sheet1.setColumnWidth(9, 10000);
	    // ----------------------------------------------------------
	     
	    // *** Style--------------------------------------------------
	    // Cell 스타일 생성
	    CellStyle cellStyle = xlsWb.createCellStyle();
	     
	    // 줄 바꿈
	    cellStyle.setWrapText(true);
	     
	    Row row = null;
	    Cell cell = null;
	    //----------------------------------------------------------
	     
	    // 첫 번째 줄
	    row = sheet1.createRow(0);
	     
	    // 첫 번째 줄에 Cell 설정하기-------------
	    cell = row.createCell(0);
	    cell.setCellValue("1-1");
	    cell.setCellStyle(cellStyle); // 셀 스타일 적용
	     
	    cell = row.createCell(1);
	    cell.setCellValue("1-2");
	     
	    cell = row.createCell(2);
	    cell.setCellValue("1-3 abccdefghijklmnopqrstuvwxyz");
	    cell.setCellStyle(cellStyle); // 셀 스타일 적용
	    //---------------------------------
	     
	    // 두 번째 줄
	    row = sheet1.createRow(1);
	     
	    // 두 번째 줄에 Cell 설정하기-------------
	    cell = row.createCell(0);
	    cell.setCellValue("2-1");
	     
	    cell = row.createCell(1);
	    cell.setCellValue("2-2");
	     
	    cell = row.createCell(2);
	    cell.setCellValue("2-3");
	    cell.setCellStyle(cellStyle); // 셀 스타일 적용
	    //---------------------------------

	    // excel 파일 저장
	    try {
	        File xlsFile = new File("C://Download/testExcel.xls");
	        FileOutputStream fileOut = new FileOutputStream(xlsFile);
	        xlsWb.write(fileOut);
	    } catch (FileNotFoundException e) {
	        e.printStackTrace();
	    } catch (IOException e) {
	        e.printStackTrace();
	    }
	}

}
