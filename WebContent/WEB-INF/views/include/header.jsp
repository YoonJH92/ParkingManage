<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<head>
<meta charset="UTF-8" />
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<title>PMS</title>

   <script src="<%=request.getContextPath()%>/resources/jquery-3.5.1.min.js"></script>
       <script src="<%=request.getContextPath()%>/resources/bootstrap.min.js"></script>
       <link rel="stylesheet" href="<%=request.getContextPath()%>/resources/bootstrap.css" />
   <script src="<%=request.getContextPath()%>/resources/bootstrap.min.js"></script>
   <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.13.0/css/all.min.css" rel="stylesheet">

</head>
<style>

@import url(http://fonts.googleapis.com/earlyaccess/nanumgothic.css);

@import url(https://fonts.googleapis.com/css?family=Gothic+A1:400,700);

@media screen and (max-width: 1999px) {
	#user {
		display: block;
	}
}



@media screen and (max-width: 1200px) {
	.fnav {
		display: none;
	}
}

#hnavbar {
	padding-top:10px;
	padding-bottom:10px;
	padding-left: 30px;
	padding-rigt: 30px;
}

.nav-item {
	padding-right: 4rem;
}

#navdrop {
	padding: 0px;
	font-style: normal;
}


#mainnav {
background-color: #f8f9fa;
border-bottom: 1px solid #ebecf1;
}

.nav-item {
	text-decoration: none;
	color: black;
}

.nav-link {
	text-decoration: none;
	color: black;
}

.navbar-brand {
	text-decoration: none;
	color: black;
}

#logoimg {
	width: auto;
	height: auto;
	max-width: 100px;
	max-height: 100px;
}
 

.fnav {
	padding-top:15px;
	padding-bottom:15px;
	background-color: white;
	border-bottom: 1px solid #ebecf1;}

@media screen and (min-width: 1200px) {
	#user {
		display: none;
	}
	
	#mainnavlogo{
		display: none;
		
	}
	
	
#mainnav ul li.active-link .underline {
	width: 100%;
	background-color: #463f3a;
}

#mainnav ul li {
	display: inline-block;
	font-size: 16px;
}

#mainnav  ul li a {
	 font-family: 'Gothic A1', sans-serif;

	color: black;
	text-decoration: none;
	display: inline-block;	
	transition: color 0.5s;
}

#mainnav ul li .underline {
	height: 3px;
	background-color: transparent;
	width: 0%;
	transition: width 0.2s, background-color 0.5s;
	margin: 0 auto;
}

#mainnav ul li.active-link .underline {
	width: 100%;
	background-color: #463f3a;
}

#mainnav ul li:hover .underline {
	background-color: #463f3a;
	width: 100%;
}

 #mainnav ul li:hover .nav-link {
	font-weight: bold;
		color: #ee964b;
}




#mainnav ul li:active .nav-link {
	font-weight: bold;
	transition: none;
	color: #ee964b;
}

#mainnav ul li:active .underline {
	transition: none;
	background-color: #ee964b;
	font-weight: bold;
	
}

.dropdown-item:hover {
	background-color: #ee964b;

}

#hitem1,#hitem2{
  position: relative;

 font-family: 'Gothic A1', sans-serif;
	padding:2px;
	text-align:center;
}


#hitem1{
	padding:10px;
}

#hitem1 a{
	color:#46494c;
}

#hitem1:hover{
	text-decoration:none;
	border-bottom: solid 1px black;
	border-top: solid 1px black;
}
#hitem1 a:hover{
font-weight: bold;
		
}

}
.navbar-brand
{   position: absolute;
    width: 100%;
    left: 0;
    top: 0;
    text-align: center;
    margin: auto;
}



.fa-2x {

	align-items:center;
  color: black;
}

#line {
	align-items:center;
	padding-top:0.5rem;
    width: 1px;
    height: 20px;
    background-color: #e4e8eb;

}



.divider{

	display: block;
	padding-top: 6px; 
}




}

</style>

<body>
	<header>
	<!-- haeadernav -->
		<div class="fnav">
		
			<nav class="navbar navbar-expand-xl" id="hnavbar">
				<a href="#" class="navbar-brand"><img alt="" src="logo.png"
					id="logoimg"></a>
					
				<div class="navbar-nav ml-auto">
					<div id="hitem1">
						<a href="#" class="hlinks" >회원정보수정</a>
					</div>
					<span class="divider">|</span>
					<div id="hitem1"><a href="#"><a href="#">
				<i class="fas fa-sign-out-alt fa-lg"></i></a>					
					</div>
				</div>
			</nav>
		</div>
		<!--  mainnav -->
		<nav class="navbar navbar-expand-xl justify-content-md-center"
			id="mainnav">
			<div class="container-fluid">
					<div class="btn mr-auto" id="mainnavlogo">
			<img alt="" src="logo.png"
					id="logoimg">
					</div>
				<div class="btnbox ml-auto">
						<button type="button" class="navbar-toggler" id="headerbtn"
							data-toggle="collapse" data-target="#navbarCollapse">
							<i class="fas fa-user-alt"></i>
						</button>					
					<button id="mainbtn" type="button" class="navbar-toggler"
						data-toggle="collapse" data-target="#toggle">
						<i class="fas fa-bars"></i>
					</button>
				</div>
				<div class="collapse navbar-collapse" id="navbarCollapse">
					<div class="navbar-nav" id="user">
						<a href="#" class="nav-item nav-link">회원정보수정</a>
						<a href="#" class="nav-item nav-link">Logout</a>
					</div>
				</div>

				<div id="toggle" class="navbar-collapse collapse">
					<ul class="navbar-nav">
						<li class="nav-item active-link"><a href="#" class="nav-link">실시간
								주차 현황</a><div class="underline"></div>
								</li>
						<li class="nav-item"><a href="#" class="nav-link">차량조회</a>
									<div class="underline"></div>
						</li>
						<li class="nav-item"><a href="#" class="nav-link ">월정액
								회원관리</a>
								<div class="underline"></div></li>
						<li class="nav-item dropdown"><a href="#"
							class="nav-link dropdown-toggle"
							data-toggle="dropdown">
							 <i class="nav-item" id="navdrop">쿠폰관리
							</i>
						</a>
							<div class="dropdown-menu">
								<a href="#" class="dropdown-item"> <i class="dropnav-item"></i>쿠폰
									생성
								</a> <a href="#" class="dropdown-item"> <i class="dropnav-item"></i>쿠폰
									삭제
								</a> <a href="#" class="dropdown-item"> <i class="dropnav-item"></i>쿠폰
									조회
								</a>
							</div></li>
						<li class="nav-item "><a href="#" class="nav-link">월별통계</a><div class="underline"></div>
						</li>
						<li class="nav-item "><a href="#" class="nav-link">일별통계</a><div class="underline"></div>
						</li>
						<li class="nav-item "><a href="#" class="nav-link">설정</a><div class="underline"></div>
						</li>
					</ul>
				</div>
			</div>
		</nav>
	</header>

	<script type="text/javascript">
    $(function(){
 
    	 $('#headerbtn').click(function() {	
 	    	if($('#toggle').hasClass('show')){
 	    		$('#navbarCollapse').removeClass('show');
 				$('#toggle').removeClass('show');
 			 }	
 	    });
    	 $('#mainbtn').click(function() {	
  	    	if($('#navbarCollapse').hasClass('show')){
  	    		$('#toggle').removeClass('show');
  				$('#navbarCollapse').removeClass('show');
  			 }	
  	    });
    });
   
    $('.nav-item').on('click', function() {
    	$('.active-link').removeClass('active-link');
    	$(this).addClass('active-link');
    }); 
	  

    </script>


	<!-- navbar 끝 -->
	<!-- header 끝 -->
	<!-- JS, Popper.js, and jQuery -->




