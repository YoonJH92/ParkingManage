package com.pms.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.pms.command.Action;
import com.pms.command.Command;

@WebServlet(urlPatterns = "*.do",loadOnStartup = 1)
public class Controller extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
    public Controller() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		String getCommand = getCommand(request); // 키워드 추출 ex) /stat/daily
		Command command = null; // 인터페이스 활용
		String strView = null; // 리턴할때 사용
		
		Action action =  Action.getInstance(); // 싱글톤 패턴으로 생성 , 생성자에 map 저장
		command = action.getAction(getCommand); // key값을 보내줘서 map 체크

		// command 객체가 있으면 실행
		try {
			strView = command.execute(request, response);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		if(strView == null) { //없으면 리턴
			return;
		}

		//페이지 전송
		if(strView.startsWith("redirect:")) {
			// 리다이렉트 이동
			String redirectPath = strView.substring("redirect:".length());
			response.sendRedirect(redirectPath); 
		} else {
			// 값포함 페이지 이동
			String prefix = "/WEB-INF/views/";
			String suffix = ".jsp";
			String jspPath = prefix + strView + suffix;
			RequestDispatcher dispatcher = request.getRequestDispatcher(jspPath);
			dispatcher.forward(request, response);
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
		command = command.substring(0, command.indexOf(".do"));
		return command;
	}
}
