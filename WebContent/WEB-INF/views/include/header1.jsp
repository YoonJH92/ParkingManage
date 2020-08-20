<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
    <script src="<%=request.getContextPath()%>/resources/jquery-3.5.1.min.js"></script>
    <script src="<%=request.getContextPath()%>/resources/bootstrap.min.js"></script>
<link rel="stylesheet" href="<%=request.getContextPath()%>/resources/bootstrap.css" />








<style type="text/css">

 .nav { overflow: hidden;}
 .nav a {  float: left;
  display: block;
  color: white;
  text-align: center;
  padding: 14px 16px;
  text-decoration: none; 
  font-size: 17px;
  transtion:all 0.3s ease 0s;
  border-bottom: 3px solid transparent;}
 #navbar{ background: #000c40;} 
#headernav{
padding-left: 30px;
padding-right: 30px}
 

.navbar-brand{
	padding-right: 20px;
	padding-left: 20px;

}
	.navbar-container ul li a {
	color: #ffffff;
	text-decoration: none;
	display: inline-block;	
	padding: 10px;
	transition: color 0.5s;
}

.container ul li .underline {
	height: 3px;
	background-color: transparent;
	width: 0%;
	transition: width 0.2s, background-color 0.5s;
	margin: 0 auto;
}

.container ul li.active-link .underline {
	width: 100%;
	background-color: white;
}

.container ul li:hover .underline {
	background-color: white;
	width: 100%;
}
	
   li.nav-item{ padding-right:50px;
   	transition: none;
   }
   
   

   .container ul li:hover a {
}

.container ul li:active a {
	transition: none;
	color: rgba(255,255,255,0.76);
}

.container ul li:active .underline {
	transition: none;
	background-color: rgba(255,255,255,0.76);
}
   
</style>

</head>
<body>
<body>
<div class="header">
    <nav class="navbar navbar-expand-md navbar-light bg-light" id="headernav">
        <a href="#" class="navbar-brand">주차관리리스템</a>
        <button type="button" class="navbar-toggler" data-toggle="collapse" data-target="#navbarCollapse">
            <span class="navbar-toggler-icon"></span> </button>
        <div class="collapse navbar-collapse" id="navbarCollapse">
            <div class="navbar-nav ml-auto">
                <a href="#" class="nav-item nav-link">회원정보수정</a>
            <a href="#" class="nav-item nav-link">logout</a>
            </div>
        </div>
    </nav>
    </div>

  <nav class="navbar navbar-expand-sm" id="navbar">
    <div class="container ">
      <div id="toggle" class="navbar-collapse justify-content-md-center">
        <ul class="nav navbar-nav">
          <li class="nav-item active-link">
            <a href="#" class="nav-link">실시간 주차 현황</a>
            			<div class="underline"></div></li>
          <li class="nav-item"><a href="ccc.jsp" class="nav-link">차량조회</a> 
          			<div class="underline"></div>
         
          </li>
          <li class="nav-item"><a href="#" class="nav-link">월정액 회원관리</a>
          			<div class="underline"></div>
          </li>
          <li class="nav-item"><a href="#" class="nav-link">쿠폰관리</a>
          			<div class="underline"></div>
          </li>
          <li class="nav-item"><a href="#" class="nav-link">일별통계</a>
          			<div class="underline"></div>
          </li>
          <li class="nav-item"><a href="#" class="nav-link">월별통계</a>
          			<div class="underline"></div>
          </li>
          <li class="nav-item"><a href="#" class="nav-link">설정</a>
          			<div class="underline"></div>
          
          </li>
        </ul>
      </div>
      </div>
    </nav>
    
 

<script>
$('.nav-item').on('click', function() {
	$('.active-link').removeClass('active-link');
	$(this).addClass('active-link');
});
</script>