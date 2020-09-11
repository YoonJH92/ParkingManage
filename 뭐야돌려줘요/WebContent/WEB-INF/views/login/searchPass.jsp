<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
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
</head>
<body>
	<c:if test="${passsearch == null }">
		<div class="container" id="contain">
			<div class="jumbotron " style="width: 50%">

				<form action="searchPass.do" method="post" class="form-signin"
					name="login">
					<div class="text-center mb-4">

						<h1 class="h3 mb-3 font-weight-normal">Password찾기</h1>
						<p>가입하신 계정의 정보가 일치할 경우 이메일로 비밀번호를 보내드립니다.</p>

					</div>

					<div class="mb-3">
						<label for="address">Name</label> <input type="text" name="name"
							class="form-control" placeholder="이름을 입력해 주세요" required>

					</div>

					<div class="mb-3">
						<label for="address">ID</label> <input type="text" name="id"
							class="form-control" placeholder="아이디를 입력해 주세요" required>

					</div>

					<div class="mb-3">
						<label for="email">Email </label> <input type="email" name="email"
							class="form-control" placeholder="이메일을 입력해주세요">

					</div>
					<div class="mb-3">
						<label for="address">Tel</label> <input type="tel" name="tel"
							class="form-control" placeholder="전화번호를 입력해 주세요" required>

					</div>
					<br>
					<div style="display: flex; justify-content: flex-end;">
						<input style="margin-right: 5px;" class="btn btn-primary btn-lg"
							type="submit" value="확인">
						<button type="button" class="btn btn-primary btn-lg"
							onclick="location.href = 'loginac.do' ">취소</button>
					</div>


				</form>

			</div>
		</div>
	</c:if>
	<c:set var="gggg" value="없음" />
	<c:if test="${passsearch==gggg}">
		<script type="text/javascript">
			alert("찾으시는 계정의 정보가 일치하지 않습니다");
			history.go(-1);
		</script>
	</c:if>

	<c:if test="${passsearch != null&&passsearch!=gggg}">
		<script>
			alert('이메일 에서 패스워드를 확인해 주세요!');
			document.location.href = "loginac.do";
		</script>
	</c:if>



</body>
</html>






