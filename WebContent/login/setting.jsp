<%@page import="com.pms.dto.SettingDTO"%>
<%@page import="com.pms.dao.SettingDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">
<title>Login</title>

<link rel="canonical"
	href="https://getbootstrap.com/docs/4.5/examples/floating-labels/">

<!-- Bootstrap core CSS -->
<link href="../resources/bootstrap.css" rel="stylesheet">


<style>
@import
	url('https://fonts.googleapis.com/css2?family=Nanum+Pen+Script&family=Yeon+Sung&display=swap')
	;

#contain {
	display: flex;
	justify-content: center;
}

body {
	font-family: 'Yeon Sung', cursive;
}


</style>

<link href="../resources/floating-labels.css" rel="stylesheet">
</head>
<body>
	<main>
		<!-- header -->
<%@ include file="/WEB-INF/views/include/header.jsp" %> 
	


	<%
		request.setCharacterEncoding("UTF-8");
	%>
		
	<%
		SettingDAO sdao = SettingDAO.getInstance();
		SettingDTO sdto = sdao.settItem();
		
		
	%>
	




	<div class="container">
		<h1>설정</h1>
		<br> <br>
		<table class="table table-hover">
			<form action="settingProc.jsp" method="post">
				<tbody>
					<tr>
						<td align="center" width="30%">주차개수</td>
						<td><input class="form-control" type="text" name="count"
							value="<%=sdto.getCount() %>"></td>
					</tr>
					<tr>
						<td align="center" width="30%">기본시간</td>
						<td><input class="form-control" type="text" name="dtime"
							value="<%=sdto.getDtime() %>"></td>
					</tr>
					<tr>
						<td align="center" width="30%">기본 요금</td>
						<td><input class="form-control" type="text" name="fare"
							value="<%=sdto.getFare() %>"></td>
					</tr>
					<tr>
						<td align="center" width="30%">오버시 시간</td>
						<td><input class="form-control" type="text" name="otime"
							value="<%=sdto.getOtime() %>"></td>
					</tr>
					<tr>
						<td align="center" width="30%">오버시 추가요금</td>
						<td><input class="form-control" type="text" name="ofare"
							value="<%=sdto.getOfare() %>"></td>
					</tr>
					<tr>
						<td align="center" width="30%">월정액 요금</td>
						<td><input class="form-control" type="text" name="month_fare"
							value="<%=sdto.getMonth_fare() %>"></td>
					</tr>
					<tr>
						<td colspan="2">
							<div style="display: flex; justify-content: flex-end;">
								<input style="margin-right: 5px;" class="btn btn-primary btn-lg"
									type="submit" value="변경">
								<button type="button" class="btn btn-primary btn-lg"
									onclick="location.href = 'index.jsp' ">취소</button>
							</div>
						</td>
					</tr>
				</tbody>
			</form>
		</table>
	</div>


<!-- footer -->
<%@ include file="/WEB-INF/views/include/footer.jsp" %>
</main>
</body>
</html>