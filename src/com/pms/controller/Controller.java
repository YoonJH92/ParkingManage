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
		String getCommand = getCommand(request); // 서블릿에 사용할 값 추출 ex)/stat/daily
		Command command = null;
		String strView = null; 	
		Action action =  Action.getInstance();
		command = action.getAction(getCommand); 
		try {
			strView = command.execute(request, response);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		if(strView == null) {
			return;
		}
		if(strView.startsWith("redirect:")) {
			String redirectPath = strView.substring("redirect:".length());
			response.sendRedirect(redirectPath); 
		} else {
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

	private String getCommand(HttpServletRequest request) {
		String RequestURI = request.getRequestURI();
		String ContextPath = request.getContextPath();
		String command = RequestURI.substring(ContextPath.length());
		command = command.substring(0, command.indexOf(".do"));
		return command;
	}
}
