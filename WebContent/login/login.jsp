<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>


<meta charset="utf-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">
<meta name="description" content="">
<meta name="author"
	content="Mark Otto, Jacob Thornton, and Bootstrap contributors">
<meta name="generator" content="Jekyll v4.0.1">
<title>Login</title>

<link rel="canonical"
	href="https://getbootstrap.com/docs/4.5/examples/floating-labels/">

<!-- Bootstrap core CSS -->
<link href="../resources/bootstrap.css" rel="stylesheet">

 
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
	background: #DAE2F8; /* fallback for old browsers */
	background: -webkit-linear-gradient(to bottom, #D6A4A4, #DAE2F8);
	/* Chrome 10-25, Safari 5.1-6 */
	background: linear-gradient(to bottom, #D6A4A4, #DAE2F8);
	/* W3C, IE 10+/ Edge, Firefox 16+, Chrome 26+, Opera 12+, Safari 7+ */
	font-family: 'Yeon Sung', cursive;
}
</style>
<!-- Custom styles for this template -->
<link href="../resources/floating-labels.css" rel="stylesheet">

<body>
 <!-- header -->
<%@ include file="../WEB-INF/views/include/header.jsp" %> 

<main>

	<div class="container" id="contain">
		<div class="jumbotron " id="jumbo" style="width: 50%">
			<form action="login.do" class="form-signin" name="login"
				method="post">
				<div class="text-center mb-4">

					<h1 class="h3 mb-3 font-weight-normal">관리자 로그인</h1>
					<p>주차 관리 프로그램 입니다.</p>

				</div>



				<div class="form-label-group">
					<input name="id" type="text" id="loginID" class="form-control"
						required autofocus> <label for="loginID">ID</label>
				</div>



				<div class="form-label-group">

					<input name="pass" type="password" id="loginPassword"
						class="form-control" required> <label for="loginPassword">Password</label>
				</div>

				<div class="checkbox mb-3">
					<label> <input type="checkbox" checked name="save">
						아이디 저장
					</label>
				</div>
				<button class="btn btn-lg btn-primary btn-block" type="submit">로그인</button>

				<a href="join.jsp">가입이 안되어 있으신가요?</a><br> <a
					href="searchId.jsp">아이디</a> <a href="searchPass.jsp">비밀번호찾기</a> <br>
				<br>


				<p class="mt-5 mb-3 text-muted text-center">&copy; 2020~</p>
			</form>
		</div>
	</div>


	<c:if test="${re == -1 }">
		<script type="text/javascript">
			alert("아이디가 존제하지 않습니다");
			history.go(-1);
		</script>
	</c:if>

	<c:if test="${re == 0 }">
		<script type="text/javascript">
			alert("패스워드가 일치하지 않습니다");
			history.go(-1);
		</script>
	</c:if>

	<c:if test="${re == 1 }">
		<script type="text/javascript">
			document.location.href = "join.jsp";
		</script>
	</c:if>

</main>
 <!-- footer -->
<%@ include file="../WEB-INF/views/include/footer.jsp" %> 
</body>
</html>