<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
<<<<<<< HEAD
	
	String sessid = (String)session.getAttribute("sessid");
	
	if(sessid == null){
		response.sendRedirect("index.do");
	}else{
		response.sendRedirect("loglist.do");
	}
	
	
	
=======
	response.sendRedirect("loglist.do");
>>>>>>> 7ffd14ba6b93750c3ad150c07e0e4fd7adc09301
%>
