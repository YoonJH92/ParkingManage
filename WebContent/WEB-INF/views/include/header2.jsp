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
       
<style>  
  #page-wrapper {padding-left: 250px;}
  #sidebar-wrapper {
    position: fixed;
    width: 250px;
    height: 100%;
    margin-left: -250px;
    background: #f9f7f7;
    overflow-x: hidden;
    overflow-y: auto;
  }
  #page-content-wrapper {
    width: 100%;
    padding: 20px;}
    
  .sidebar-nav {
    width: 250px;
    margin: 0;
    padding: 0;
    list-style: none;
    color:black;
    background-color: #f6f6f6;
    }
  .navbar { background-color: #112d4e;}
.navbar-nav a{
text-decoration: none;
     display: block;
	  color: #999;}
  .sidebar-nav li {
    text-indent: 1.5em;
    line-height: 2.8em; }
  
  .sidebar-nav li a {
    display: block;
    text-decoration: none;
    color: #999;
    border-radius: 5px;
  } 
/*   .slidebar-nav li a:active{
    color: #fff;
    background: #d6e4f0;
} */
/*   .sidebar-nav li a:hover {
    color: #fff;
    background: #d6e4f0; } */
.sidebar-brand{
    background: #3f72af;
    padding-left: 20px;}  
  .active-link{
       background: #d6e4f0; }
 
</style>
</head>
<body>

<div class="header">
    <nav class="navbar navbar-expand-md">
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


<div id="page-wrapper">
  <!-- 사이드바 -->
  <div id="sidebar-wrapper">
    <ul class="sidebar-nav">
   <li class="sidebar-brand">
        <a href="#">주차관리시스템</a> </li>
      <li class="nav-link active-link"><a class="nav-item" href="#">실시간주차현황</a></li>
      <li class="nav-link"><a class="nav-item" href="#">차량 조회</a></li>
      <li class="nav-link"><a class="nav-item" href="#">월정액 회원관리</a></li>
      <li class="nav-link"><a class="nav-item" href="#">쿠폰관리</a></li>
      <li class="nav-link"><a class="nav-item"  href="#">일별통계</a></li>
      <li class="nav-link"><a class="nav-item" href="#">월별통계</a></li>
      <li class="nav-link"><a class="nav-item"  href="#">설정</a></li>
  
    </ul>
  </div>
  </div>

<script>
$('.nav-link').on('click', function() {
	$('.active-link').removeClass('active-link');
	$(this).addClass('active-link');
});

</script>        
   
   <script src="<%=request.getContextPath()%>/resources/bootstrap.min.js"></script>
