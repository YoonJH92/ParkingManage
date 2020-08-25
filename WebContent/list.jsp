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
<%--    <button type="button" data-toggle="modal" data-target="#myModal">${arr.cImg}Open Modal</button>
 --%>
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








<!--  <div class="modal fade" id="myModal" role="dialog">
    <div class="modal-dialog">    
      Modal content
      <div class="modal-content">
        <div class="modal-header">
          <h4 class="modal-title">차량이미지</h4>
        </div>
        <div class="modal-body">
          <form action="ImgModify.do" enctype="multipart/form-data" method="post" accept=".jpg, .jpeg, .png">
               </table>
                  <thead class="thead-dark">
                  <tr class="active">
                  <th class="text-center"> 
               <td><input type="file" name="fileName"></td>
				<table>

    
        </div>
        <div class="modal-footer">
          <input type="submit" class="btn btn-default" >수정</button>
          <button type="button" class="btn btn-default" data-dismiss="modal">닫기</button>
          </form> -->
      
        </div>
      </div>
      
    </div>
  </div>









  