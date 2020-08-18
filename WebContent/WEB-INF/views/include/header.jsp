<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
    <script src="../resources/jquery-3.5.1.min.js"></script>
<link rel="stylesheet" href="../resources/bootstrap.min.css" />

<title>Insert title here</title>
</head>
<body>
	<style type="text/css">	
a:link {
	color: who;
	text-decoration: none;
}
a:visited {
	color: black;
	text-decoration: none;
}
a:hover {
	color: white;
	text-decoration: underline;
}

.dropdown-item {
	padding: 20px;
}
     
     
.navbar .navbar-header,
.navbar-collapse {
    float:none;
    display:inline-block;
    vertical-align: top;
    padding-left: 0;
    padding-right: 0;
}

@media (max-width: 768px) {
    .navbar-collapse  {
        display: block;
    }
}

}
</style>

	<nav class="navbar navbar-defalut">
		<div class="container">
			<ul class="nav navbar-nav  navbar-right ">
				<li><a href="#"><span class="glyphicon glyphicon-user"></span>
						Sign Up</a></li>
				<li class="nav-item dropdown"><a
					class="nav-link dropdown-toggle" href="#" id="navbarDropdown"
					role="button" data-toggle="dropdown" aria-haspopup="true"
					aria-expanded="false"> <span class="glyphicon glyphicon-log-in"></span>
						Login
				</a>
					<div class="dropdown-menu dropdown-menu-right"
						aria-labelledby="navbarDropdown">
						<a class="dropdown-item" href="#"> <span
							class="glyphicon glyphicon-log-out"></span> Logout
						</a>
					</div></li>
			</ul>
		</div>
		<div class="jumbotron text-center">
			<p>주차관리 시스템</p> </div>
	
    <div class="container text-center">
        <div id="navbar" class="navbar-collapse">
            <ul class="nav navbar-nav">
                <li><a href="#">실시간 주차 현황</a></li>
                <li><a href="#">차량 조회</a></li>
                <li><a href="#">월정액 회원관리</a></li>
                <li><a href="#">쿠폰 관리</a></li>
                <li><a href="#">일별 통계</a></li>
                <li><a href="#">월별 통계</a></li>
                <li><a href="#">설정</a></li>
            </ul>
        </div>
    </div>
</nav>
    <script src="../resources/bootstrap.min.js"></script>



</body>
</html>