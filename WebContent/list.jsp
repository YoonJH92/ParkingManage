<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!-- header -->
<%@ include file="/WEB-INF/views/include/header.jsp" %> 
<!-- footer -->
<%@ include file="/WEB-INF/views/include/footer.jsp" %>


  
<style>

.table{
	margin-top: 40px;
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
	
	
	.info1{
	
    width: 100%;
    left: 0;
    top: 0;
    text-align: center;
    margin: auto;
	}
	
	.btn{
	
	}
	

</style>

<div id="bodycontainer" class="container">
	<div class="infomation">
	<div class="info1 ">
		<p> 주차수 :  / 일반 :  / 월정액 : /</p>
	</div>	
	
	<div class="btn">
	
		<button> 10개씩 보기 </button>		
		<button> 엑셀다운 </button>		
	
	</div>
	
	</div>

<table class="table table-bordered text-center">

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

<c:forEach items="${arr}" var="item">
<tr>
<%--   <td> ${arr.carnum} </td>  
 --%> <td>${item.parking}  </td>
<td class="text-center"> ${item.admin} </td>
<td class="text-center"> ${item.carnum} </td>
<td class="text-center"> ${item.discount} </td> 
<td class="text-center"> ${item.amount} </td>
<td class="text-center"> ${item.payment} </td> 
<td> <img src="car1.png" id="eximg"> </td>
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

