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
	
	<jsp:useBean id="idbean" class="com.pms.dto.ManagerBean">
		<jsp:setProperty property="*" name="idbean"/>
	</jsp:useBean>
	
<%
	
	ManagerDAO mdao = new ManagerDAO();
	String idsearch =mdao.MdSearchId(idbean);
	
	
	if(idsearch == "없음"){
		%>
		<script type="text/javascript">
		alert("찾으시는 아이디가 존제하지 않습니다");
		history.go(-1);
		</script>
		<% 
	}else{
		%>
		
		<h2>찾으시는 아이디는 <%=idsearch %> 입니다.</h2>
		<button type="button" class="btn btn-primary btn-lg" onclick = "location.href = 'login.jsp' " >돌아가기</button>
		<% 
	}
%>
	
	
	
	
	
	
	
	
	
	
	
</body>
</html>