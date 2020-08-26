<%@page import="com.pms.dao.SettingDAO"%>
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
	<jsp:useBean id="sdto" class="com.pms.dto.SettingDTO">
		<jsp:setProperty property="*" name="sdto"/>
	</jsp:useBean>
	<%
		SettingDAO sdao = SettingDAO.getInstance();
		
		sdao.updateSetting(sdto);
		response.sendRedirect("index.jsp");
	%>
</body>
</html>