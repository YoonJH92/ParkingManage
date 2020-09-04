<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>

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
		<div class="jumbotron " style="width: 60%">
			<center>
				<h1>회원가입</h1>
			</center>
			<form action="join.do" method="post">

				<div class="mb-3">
					<label for="address">Name</label> <input type="text" name="name"
						class="form-control" placeholder="이름을 입력해 주세요" required>

				</div>

				<div class="mb-3">
					<label for="address">ID</label> <input type="text" name="id"
						class="form-control" placeholder="아이디를 입력해 주세요" required>

				</div>
				<div class="mb-3">
					<label for="address">Password</label> <input type="password"
						name="pass" id="pw" onchange="check_pw()" class="form-control"
						placeholder="비밀번호는 6글자 이상 16글자 이하 입니다" required>

				</div>
				<div class="mb-3">
					<label for="address">Password 확인 &nbsp;<span id="check"></span></label>
					<input type="password" name="pass2" id="pw2" onchange="check_pw()"
						class="form-control" placeholder="비밀번호를 확인해 주세요" required>

				</div>
				<div class="mb-3">
					<label for="email">Email </label> <input type="email" name="email"
						class="form-control" placeholder="이메일을 입력해주세요" required>

				</div>
				<div class="mb-3">
					<label for="address">Tel</label> <input type="tel" name="tel"
						class="form-control" placeholder="전화번호를 입력해 주세요" required>

				</div>
				<br>
				<div style="display: flex; justify-content: flex-end;">
					<input style="margin-right: 5px;" class="btn btn-primary btn-lg"
						href="joinProc.jsp" type="submit" value="확인">
					<button type="button" class="btn btn-primary btn-lg"
						onclick="location.href = 'loginac.do' ">취소</button>
				</div>

			</form>
		</div>
	</div>



	<script>
        function check_pw(){
 
            var pw = document.getElementById('pw').value;
           
            var check_SC = 0;
 
            if(pw.length < 6 || pw.length>16){
                window.alert('비밀번호는 6글자 이상, 16글자 이하만 이용 가능합니다.');
                document.getElementById('pw').value='';
            }
            
            if(document.getElementById('pw').value !='' && document.getElementById('pw2').value!=''){
                if(document.getElementById('pw').value==document.getElementById('pw2').value){
                    document.getElementById('check').innerHTML='비밀번호가 일치합니다.'
                    document.getElementById('check').style.color='blue';
                }
                else{
                    document.getElementById('check').innerHTML='비밀번호가 일치하지 않습니다.';
                    document.getElementById('check').style.color='red';
                }
            }
        }
    </script>




</body>
</html>