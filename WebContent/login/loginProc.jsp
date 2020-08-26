
<%@page import="com.pms.dao.ManagerDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<body>

<%
	request.setCharacterEncoding("UTF-8");
	
%>
	<!-- useBean을 이용하여 한꺼번에 데이터를 받아옴 -->
	<jsp:useBean id="mbean" class="com.pms.dto.ManagerBean">
		<jsp:setProperty property="*" name="mbean"/>
	</jsp:useBean>
	
<%
	//1이 패스워드 맞는거 0이 패스워드 틀린거 -1이 아이디 안맞는거
	
	//데이터베이스 클래스 객체 생성
	ManagerDAO mdao = new ManagerDAO();
	int re = mdao.loginManager(mbean.getId(),mbean.getPass());
	
	if(re == -1){
		%>
		<script type="text/javascript">
			alert("아이디가 존제하지 않습니다");
			history.go(-1);
		</script>
		<% 
	}else if(re == 0){
		%>
		<script type="text/javascript">
			alert("패스워드가 일치하지 않습니다");
			history.go(-1);
		</script>
		<% 
		
		
	}else if(re == 1){
		
		session.setAttribute("sessid", mbean.getId());
		%>
		<script type="text/javascript">
			
			document.location.href="join.jsp";
		</script>
		
		<% 
	}
	
	
			String save = request.getParameter("save");
			
			String id = request.getParameter("id");
			
			if(save != null){
	
				Cookie cookie = new Cookie("id",id);

				cookie.setMaxAge(60*60*24*7);

				response.addCookie(cookie);
				
				 
			}
			
	
	
	
%>

</body>
</html>