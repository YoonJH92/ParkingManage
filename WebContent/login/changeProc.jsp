<%@page import="com.pms.dao.ManagerDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>



<%
	request.setCharacterEncoding("UTF-8");
	
%>
	<!-- useBean을 이용하여 한꺼번에 데이터를 받아옴 -->
	<jsp:useBean id="mbean" class="com.pms.dto.ManagerBean">
		<jsp:setProperty property="*" name="mbean"/>
	</jsp:useBean>
	
<%
	String id = (String)session.getAttribute("sessid");
	
	//데이터베이스 클래스 객체 생성
	ManagerDAO mdao = new ManagerDAO();
	mdao.searchM(id);
	
	
	
%>

</body>
</html>