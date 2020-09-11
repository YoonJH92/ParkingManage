package com.pms.controller;

import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.text.SimpleDateFormat;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import com.pms.excel.ExcelCommand;
import com.pms.excel.ExcelAction;

@WebServlet(urlPatterns = "*.ex",loadOnStartup = 1)
public class ExcelController extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public ExcelController() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String getCommand = getCommand(request); // 키워드 추출 ex) /stat/daily
		ExcelCommand command = null; // 인터페이스 활용
		ExcelAction action =  ExcelAction.getInstance(); // 싱글톤 패턴으로 생성 , 생성자에 map 저장
		command = action.getAction(getCommand); // key값을 보내줘서 map 체크
		
		OutputStream out = null;
		try {
            HSSFWorkbook workbook = command.execute(request, response);
            
			response.reset();
            response.setHeader("Content-Disposition", "attachment;filename="+reName(getCommand)+".xls");
            response.setContentType("application/vnd.ms-excel");
            
            OutputStream fileOut = response.getOutputStream();
            out = new BufferedOutputStream(fileOut);
            workbook.write(out);
            out.close();
            fileOut.flush();
            fileOut.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
	//url 값 추출
	private String getCommand(HttpServletRequest request) {
		String RequestURI = request.getRequestURI();
		String ContextPath = request.getContextPath();
		String command = RequestURI.substring(ContextPath.length());
		command = command.substring(0, command.indexOf(".ex"));
		return command;
	}
	private String reName(String name) {
		SimpleDateFormat format = new SimpleDateFormat("yyMMddHHmmss");
		String today = format.format(System.currentTimeMillis());
		return name.replace("/", "") + today;
	}
}
