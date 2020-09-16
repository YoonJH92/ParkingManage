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




<style>


#contain {
	display: flex;
	justify-content: center;
}

body {
	background: #0F2027;
	background: -webkit-linear-gradient(to right, #2C5364, #203A43, #0F2027);
	background: linear-gradient(to right, #2C5364, #203A43, #0F2027);
	font-family: 'Yeon Sung', cursive;
}
</style>
<!-- Bootstrap core CSS -->
<link href="resources/bootstrap.css" rel="stylesheet">
<!-- Custom styles for this template -->
<link href="resources/floating-labels.css" rel="stylesheet">

<body>


	<div class="container" id="contain">
		<div class="jumbotron " id="jumbo" style="width: 50%">
			<form action="login.do" class="form-signin" name="login"
				method="post">
				<div class="text-center mb-4">

					<h1 class="h3 mb-3 font-weight-normal">관리자 로그인</h1>
					<p>주차 관리 프로그램 입니다.</p>

				</div>


				<c:if test="${cookie.cid.value==null}">
					<div class="form-label-group">
						<input name="id" type="text" id="loginID" class="form-control"
							required autofocus> <label for="loginID">ID</label>
					</div>
				</c:if>


				<c:if test="${cookie.cid.value!=null}">
					<div class="form-label-group">
						<input name="id" type="text" id="loginID" class="form-control"
							value="${cookie.cid.value }"> <label for="loginID">ID</label>
					</div>
				</c:if>





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

				<a href="joinac.do">가입이 안되어 있으신가요?</a><br> <a
					href="searchIdac.do">아이디</a> <a href="searchPassac.do">비밀번호찾기</a> <br>
				<br>


				<p class="mt-5 mb-3 text-muted text-center">&copy; PMS 2020</p>
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
			document.location.href = "loglist.do";
		</script>
	</c:if>


</body>
</html>