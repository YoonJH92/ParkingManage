package com.pms.action;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.pms.dao.PmsLogDao;
import com.pms.dto.PMSDto;

/**
 * Servlet implementation class LogListAction
 */
@WebServlet({ "/test/list.do" })
public class LogListAction extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public LogListAction() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		request.setCharacterEncoding("utf-8");	
		PmsLogDao logdao =PmsLogDao.getInstance();
		ArrayList<PMSDto>logdto=logdao.viewList();
		request.setAttribute("arr", logdto);
		RequestDispatcher rd= request.getRequestDispatcher("list.jsp");
		rd.forward(request, response);
		
	
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
