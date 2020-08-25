<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
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
		
#bodycontainer{
overflow:auto;
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
<th  class="text-center"> No. </th>
<th  class="text-center"> 차량번호 </th>
<th class="text-center"> 입차시간 </th>
<th class="text-center"> 출차시간 </th>
<th class="text-center"> 사용금액</th>
<th class="text-center"> 쿠폰적용여부 </th>
<th class="text-center"> 월정액여부 </th>
<th class="text-center"> 총금액</th>
<th class="text-center"> 월정액 여부</th>
<th class="text-center"> 차량이미지</th>
</tr>
</thead>
<tbody>

<c:forEach var="arr" items="${list}">
<tr>
	<td>${arr.idx}</td>
	<td>${arr.cnum}</td>
	<td>${arr.inTime}</td>
	<td>${arr.outTime}</td>   
    <td>${arr.pay}</td>
   <td>${arr.cpNum}</td>
   <td>${arr.saleNum}</td>
   <td>${arr.totalPay}</td>
   <td>${arr.monthNum}</td>
  <td><button type="button" class="btn btn-primary" data-toggle="modal" data-target="#myModal"> ${arr.cImg} Modal </button></td>
 
</tr>	
	</c:forEach>

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

<!-- 모달창  -->

<div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
  <div class="modal-dialog" role="document">
    <div class="modal-content">
      <div class="modal-header">
        <h4 class="modal-title" id="myModalLabel">Modal 제목</h4>
      </div>
      <div class="modal-body">     
 <form action="imgupdate.do" enctype="multipart/form-data" method="post">
 	<p></p>

    <td><input type="file" name="fileName"></td>
      </div>
      <div class="modal-footer">
      	<input type="submit" >
        <button type="button" class="btn btn-default" data-dismiss="modal">닫기</button>
        </form>
      </div>
    </div>
  </div></div>


<script>

	var LOGIDX;
	var CIMG;

	$(document).ready(function() {
		${'#myModal'}.on('show.bs.modal'),fuction(event){
			LOGIDX=$(event.relatedTarget).data('');
			CIMG=$(event.relatedTarget).data('');
			
		});
		
	}




</script>








  