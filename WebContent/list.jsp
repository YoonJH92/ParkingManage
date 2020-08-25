<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!-- header -->
<%@ include file="/WEB-INF/views/include/header.jsp" %> 
<!-- footer -->
<%@ include file="/WEB-INF/views/include/footer.jsp" %>


  
<style>
	
.table{
	margin-top: 10px;
	margin-bottom: 20px;
}


	.page-link {
	
		text-decoration: none;
		color: black;
	}
	
	
	#eximg{
	width: auto;
	height: auto;
	max-width: 200px;
	max-height: 100px;
	
	}
	
	.infomation{
		margin-top:18px;
		padding-left: 20px;
		padding-right: 20px;
	}
		.info1{
			padding-top:10px;
			font-weight: bold;
		}
		
		.info2 {
		
			padding-top:10px;
		font-weight: bold;
		}
	
		#eximg{
			width: auto;
	height: auto;
	max-width: 200px;
	max-height: 100px;
		
		
		}

</style>

<div id="bodycontainer" class="container ">
	<div class="infomation row justify-content-between ">
	<div class="info1 col-xs-4">
		<p> 2020-08-24</p>
	
	</div>
	
	<div class="info2 col-xs-4">
		<p> 주차수 :100대 / 일반 :20대  / 월정액 :30대 </p>
	</div>	
	
	<div class="btns col-xs-4">
	
		<button class="tenbtn btn btn-light"> 10개씩 보기 </button>		
		<button class="downbtn btn btn-warning"> 엑셀다운 </button>		
	
	</div>
	
	</div>

<table class="table table-bordered text-center ">

    <thead class="thead-dark">
        <tr class="active">
<th  class="text-center"> 차량번호 </th>
<th class="text-center"> 입차시간 </th>
<th class="text-center"> 사용시간 </th>
<th class="text-center"> 사용금액</th>
<th class="text-center"> 월정액여부 </th>
<th class="text-center"> 구분</th>
<th class="text-center"> 차량이미지</th>
</tr>
</thead>
<tbody>

<tr>
<th  class="text-center"> 가XX1234</th>
<th class="text-center"> 2020-08-24 14:00:22 </th>
<th class="text-center"> 01:30:22 </th>
<th class="text-center"> 4000 </th>
<th class="text-center"> X </th>
<th class="text-center"> 관리자 </th>
<th class="text-center"> <img  id="eximg" src="car1.png"></th>

</tr>
</tbody>
</table>

<ul class="pagination justify-content-center">
	<li class="page-item"><a class="page-link" href="#">Previous</a></li>
	<li class="page-item"><a class="page-link" href="#">1</a></li>
	<li class="page-item"><a class="page-link" href="#">2</a></li>
	<li class="page-item"><a class="page-link" href="#">3</a></li>
	<li class="page-item"><a class="page-link" href="#">Next</a></li>
</ul>
</div>
  