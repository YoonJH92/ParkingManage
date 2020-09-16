<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="en">

<c:if test="${sessid==null || sessid == '' }">
			<script type="text/javascript">
			alert("로그인이 필요한 창입니다");
			document.location.href = "loginac.do";
		</script>
</c:if>
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">
<meta name="description" content="">
<meta name="author" content="">

<title>PMS</title>

<!-- Custom fonts for this template-->
<link href="resources/vendor/fontawesome-free/css/all.min.css"
	rel="stylesheet" type="text/css">
<link
	href="https://fonts.googleapis.com/css?family=Nunito:200,200i,300,300i,400,400i,600,600i,700,700i,800,800i,900,900i"
	rel="stylesheet">
<link
	href="https://fonts.googleapis.com/css?family=Nanum+Gothic:400,700,800"
	rel="stylesheet">
<!-- Custom styles for this template-->
<link href="resources/css/sb-admin-2.css" rel="stylesheet">
<script src="resources/jquery-3.5.1.min.js"></script>
<link href="https://fonts.googleapis.com/css?family=Jua:400"
	rel="stylesheet">
<link href="resources/jquery.datetimepicker.css" rel="stylesheet">
<script src="resources/vendor/jquery/jquery.min.js"></script>
<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
<link rel="stylesheet"
	href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
<script src="resources/js/sb-admin-2.min.js"></script>

<head>
  <meta charset="utf-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
  <meta name="description" content="">
  <meta name="author" content="">

  <title>PMS</title>

  <!-- Custom fonts for this template-->
  <link href="resources/vendor/fontawesome-free/css/all.min.css" rel="stylesheet" type="text/css">
  <link href="https://fonts.googleapis.com/css?family=Nunito:200,200i,300,300i,400,400i,600,600i,700,700i,800,800i,900,900i" rel="stylesheet">
  <link href="https://fonts.googleapis.com/css?family=Nanum+Gothic:400,700,800" rel="stylesheet">
  <!-- Custom styles for this template-->
  <link href="resources/css/sb-admin-2.css" rel="stylesheet">
  <script src="resources/jquery-3.5.1.min.js"></script>
  <link href="https://fonts.googleapis.com/css?family=Jua:400" rel="stylesheet">
  <link href="resources/jquery.datetimepicker.css" rel="stylesheet">
  <script src="resources/vendor/jquery/jquery.min.js"></script>
  <script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script> 
  <link rel="stylesheet" href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
  <script src="resources/js/sb-admin-2.min.js"></script>
  
  <style>
@import url(http://fonts.googleapis.com/earlyaccess/jejugothic.css);

@font-face {
	font-family: 'NEXON Lv1 Gothic OTF';
	src:
		url('https://cdn.jsdelivr.net/gh/projectnoonnu/noonfonts_20-04@2.1/NEXON Lv1 Gothic OTF.woff')
		format('woff');
	font-weight: normal;
	font-style: normal;
}

@font-face {
	font-family: 'MapoDPPA';
	src:
		url('https://cdn.jsdelivr.net/gh/projectnoonnu/noonfonts_2001@1.1/MapoDPPA.woff')
		format('woff');
	font-weight: normal;
	font-style: normal;
}

h1 {
	font-family: 'MapoDPPA';
}

td, th {
	text-align: center;
	color: black;
	font-family: 'NEXON Lv1 Gothic OTF';
}

body{
	color: black;
	font-family: 'NEXON Lv1 Gothic OTF';
}
</style>
</head>

<body id="page-top">

	<!-- Page Wrapper -->
	<div id="wrapper">

		<!-- Sidebar -->
		<ul
			class="navbar-nav bg-gradient-primary sidebar sidebar-dark accordion"
			id="accordionSidebar">

			<!-- Sidebar - Brand -->
			<a
				class="sidebar-brand d-flex align-items-center justify-content-center"
				href="loglist.do">
				<div class="sidebar-brand-icon rotate-n-15"></div>


				<div class="sidebar-brand-text mx-3">parking management system</div>
			</a>
			<!-- Divider -->
			<hr class="sidebar-divider my-0">

			<li class="nav-item"><a class="nav-link" href="loglist.do">
					<i class="far fa-clock"></i> <span>실시간 주차 현황</span>
			</a></li>
			<li class="nav-item"><a class="nav-link" href="logdetail.do">
					<i class="fas fa-car"></i> <span>차량조회</span>
			</a></li>

			<li class="nav-item "><a class="nav-link" href="member.do">
					<i class="far fa-calendar-check"></i> <span>월정액 회원관리</span>
			</a></li>
			<!-- Nav Item - Pages Collapse Menu -->
			<li class="nav-item"><a id="arrow" class="nav-link collapsed"
				href="#" data-toggle="collapse" data-target="#collapsePages"
				aria-expanded="true" aria-controls="collapsePages"> <i
					class="fas fa-ticket-alt"></i> <span>쿠폰할인관리</span>
			</a>
				<div id="collapsePages" class="collapse">
					<div class="bg-white py-2 collapse-inner rounded">
						<h5 class="collapse-header">쿠폰</h5>
						<a class="collapse-item" href="search_cp_dc.do">쿠폰할인 생성 및 조회</a> <a
							class="collapse-item" href="search_log.do">쿠폰 사용 내역</a>
					</div>
				</div></li>

			<!-- Nav Item - Charts -->
			<li class="nav-item"><a class="nav-link" href="statTimeac.do">
					<i class="fas fa-fw fa-chart-area"></i> <span>1일 시간별 통계</span>
			</a></li>
			<li class="nav-item"><a class="nav-link" href="daily.do"> <i
					class="fas fa-fw fa-chart-area"></i> <span>일별통계</span></a></li>

			<!-- Nav Item - Tables -->
			<li class="nav-item"><a class="nav-link" href="monthly.do">
					<i class="fas fa-fw fa-table"></i> <span>월별통계</span>
			</a></li>
			<li class="nav-item"><a class="nav-link collapsed"
				href="settingStart.do"> <i class="fas fa-fw fa-wrench"></i> <span>설정</span>
			</a></li>

			<!-- Divider -->
			<hr class="sidebar-divider d-none d-md-block">

			<!-- Sidebar Toggler (Sidebar) -->
			<div class="text-center d-none d-md-inline">
				<button class="rounded-circle border-0" id="sidebarToggle"></button>
			</div>

		</ul>
		<!-- End of Sidebar -->

		<!-- Content Wrapper -->
		<div id="content-wrapper" class="d-flex flex-column">

			<!-- Main Content -->
			<div id="content">

				<!-- Topbar -->
				<nav
					class="navbar navbar-expand navbar-light bg-white topbar mb-4 static-top shadow">

					<!-- Sidebar Toggle (Topbar) -->
					<button id="sidebarToggleTop"
						class="btn btn-link d-md-none rounded-circle mr-3">
						<i class="fa fa-bars"></i>
					</button>

					<!-- Topbar Search -->


					<!-- Topbar Navbar -->
					<ul class="navbar-nav ml-auto">



						<!-- Nav Item - User Information -->
						<li class="nav-item dropdown no-arrow"><a
							class="nav-link dropdown-toggle" href="#" id="userDropdown"
							role="button" data-toggle="dropdown" aria-haspopup="true"
							aria-expanded="false"> <span
								class="mr-2 d-none d-lg-inline text-gray-600 small">${sessid }님</span>
								<i class="fas fa-user-circle fa-2x"></i>

						</a> <!-- Dropdown - User Information -->
							<div
								class="dropdown-menu dropdown-menu-right shadow animated--grow-in"
								aria-labelledby="userDropdown">

								<a class="dropdown-item" href="changeStart.do"> <i
									class="fas fa-cogs fa-sm fa-fw mr-2 text-gray-400"></i> 관리자
									정보/수정
								</a>

								<div class="dropdown-divider"></div>
								<a class="dropdown-item" href="logout.do" data-toggle="modal"
									data-target="#logoutModal"> <i
									class="fas fa-sign-out-alt fa-sm fa-fw mr-2 text-gray-400"></i>
									로그아웃
								</a>
							</div></li>

					</ul>

				</nav>
				<!-- End of Topbar -->