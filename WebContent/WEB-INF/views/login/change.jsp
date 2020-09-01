<%-- <%@page import="com.pms.dto.ManagerBean"%>
<%@page import="com.pms.dao.ManagerDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">
<meta name="description" content="">
<meta name="author"
	content="Mark Otto, Jacob Thornton, and Bootstrap contributors">
<meta name="generator" content="Jekyll v4.0.1">
<title>회원가입</title>

<link rel="canonical"
	href="https://getbootstrap.com/docs/4.5/examples/floating-labels/">



<style>
@import
	url('https://fonts.googleapis.com/css2?family=Nanum+Pen+Script&family=Yeon+Sung&display=swap')
	;

.bd-placeholder-img {
	font-size: 1.125rem;
	text-anchor: middle;
	-webkit-user-select: none;
	-moz-user-select: none;
	-ms-user-select: none;
	user-select: none;
}

@media ( min-width : 768px) {
	.bd-placeholder-img-lg {
		font-size: 3.5rem;
	}
}

#contain {
	display: flex;
	justify-content: center;
}

body {
	font-family: 'Yeon Sung', cursive;
}
</style>
<!-- Bootstrap core CSS -->
<link href="resources/bootstrap.css" rel="stylesheet">
<!-- Custom styles for this template -->
<link href="resources/floating-labels.css" rel="stylesheet">
</head>

<body>
 --%>



<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="f" %>
<%@ include file="/WEB-INF/views/include/header.jsp" %> 




	<main>
		<!-- header -->
		<%-- <%@ include file="/WEB-INF/views/include/header.jsp"%> --%>
		<div class="container" id="contain">
			<div class="jumbotron " style="width: 60%">
				<center>
					<h1>회원정보 수정</h1>
				</center>
				<br>
				<form action="change.do" method="post">

					<div class="mb-3">
						<label for="address"><h3>ID : ${id }</h3></label>


					</div>

					<div class="mb-3">
						<label for="email">Name </label> <input type="text" name="name"
							class="form-control" value="${name }">

					</div>


					<div class="mb-3">
						<label for="email">Email </label> <input type="email" name="email"
							class="form-control" value="${email }">

					</div>

					<div class="mb-3">
						<label for="address">Tel</label> <input type="tel" name="tel"
							class="form-control" value="${tel }">

					</div>

					<div class="mb-3">
						<label for="address">Password</label> <input type="password"
							name="pass" class="form-control" placeholder="비밀번호를 입력해 주세요" required>

					</div>

					<br>
					<div style="display: flex; justify-content: flex-end;">
						<input style="margin-right: 5px;" class="btn btn-primary btn-lg"
							type="submit" value="수정">
						<button type="button" class="btn btn-primary btn-lg"
							onclick="location.href = 'loglistac.do' ">취소</button>
					</div>



				</form>
			</div>
		</div>

	</main>
	</div>
	 <%@ include file="/WEB-INF/views/include/footer.jsp" %>
