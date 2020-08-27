
<%@page import="com.pms.dao.*"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<body>


<%
	request.setCharacterEncoding("UTF-8");
	
%>
	<!-- useBean을 이용하여 한꺼번에 데이터를 받아옴 -->
	<jsp:useBean id="mbean" class="com.pms.dto.ManagerBean">
		<jsp:setProperty property="*" name="mbean"/>
	</jsp:useBean>
	
<%
	
	
	//데이터베이스 클래스 객체 생성
	ManagerDAO mdao = new ManagerDAO();
	mdao.insertManager(mbean);
	if(mbean.getPass().equals(mbean.getPass2())){
		%>
		<script type="text/javascript">
			alert("회원가입이 완료되었습니다");
			document.location.href="login.jsp";
		</script>
		<% 
		
		
	}else{
		%>
		<script type="text/javascript">
			alert("비밀번호가 일치하지 않습니다");
			history.go(-1);
		</script>
		<% 
	}
	
	
	
%>	
</body>
</html>