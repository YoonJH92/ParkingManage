<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="f" %>
<%@ include file="/WEB-INF/views/include/header.jsp" %> 
<style>
.fr{float:right!important;}
.fl{float:left!important;}
.py10{padding: 10px 0;}
.al-center{align-items: center;}
.text-right{text-align: right;}
.text-center{text-align: center;}
.form-control1{
    height: calc(1.5em + .75rem + 2px);
    padding: .375rem .75rem;
    font-size: 1rem;
    font-weight: 400;
    line-height: 1.5;
    color: #6e707e;
    background-color: #fff;
    background-clip: padding-box;
    border: 1px solid #d1d3e2;
    border-radius: .35rem;
    transition: border-color .15s
}

.form-control1:focus {
	color: #6e707e;
	background-color: #fff;
	border-color: #bac8f3;
	outline: 0;
	box-shadow: 0 0 0 .2rem rgba(78, 115, 223, .25)
}

.mb4{margin-bottom: 4px;}
</style>
<!-- Begin Page Content -->
<div class="container-fluid">

  <!-- Page Heading -->
  <h1 class="h3 mb-5 text-gray-800">월정액 회원관리</h1>

  <!-- DataTales Example -->
  <div class="card shadow mb-4">
    <div class="card-header py-3">
      <form action="memberSearch.do" method="post" id="frm" name="frm">
      <div class="py10">
     	<span>날짜 검색</span>
     	<select name="dateSearch" id="dateSearch" class="form-control1">
       		<option value="JDATE">등록 기간</option>
       		<option value="SDATE">시작 시간</option>
       		<option value="EDATE">종료 시간</option>
       	</select>
      	<input type="text" class="form-control1" id="startForm" name="startForm" value="${startForm}">
      	~
      	<input type="text" class="form-control1" id="endForm" name="endForm" value="${endForm}">
      </div>
      <div>
      	<div>
      	
      	</div>
        <span>조건 검색</span>
        <select name="search" id="search" class="form-control1">
       		<option value="NAME">이름</option>
       		<option value="CARN">차량 번호</option>
       	</select>
      	<input type="text" class="form-control1" id="searchForm" name="searchForm" value="${searchForm}">
      	<a href="#" id="searchBtn" class="d-none d-sm-inline-block btn btn-warning shadow-sm mb4">
	      	<i class="fas fa-search fa-sm text-white-50"></i> 검색하기
      	</a>
    	<a href="member.do" class="d-none d-sm-inline-block btn btn-danger shadow-sm mb4">
	      	<i class="fas fa-search fa-sm text-white-50"></i> 초기화
      	</a>
	    <a href="#" class="d-none d-sm-inline-block btn btn-primary shadow-sm fr" data-toggle="modal" data-target="#AddModal">
	      	<i class="fas fa-user-plus fa-sm text-white-50"></i> 추가하기
      	</a>
      </div>
      </form>
    </div>
    <div class="card-body">
      <div class="table-responsive text-center">
        <table class="table table-bordered" id="dataTable" width="100%" cellspacing="0">
          <thead>
            <tr>
              <th>등록 기간</th>
              <th>회원 이름</th>
              <th>차량 번호</th>
              <th>시작 시간</th>
              <th>종료 시간</th>
              <th>사용 금액(원)</th>
              <th>이메일</th>
              <th>Phone</th>
              <th>구분</th>
              <th></th>
            </tr>
          </thead>
          <tbody>
          	<c:forEach var="list" items="${arr}">
          		<tr>
	              <td>${list.regDate}</td>
	              <td>${list.name}</td>
	              <td>${list.CNUM}</td>
	              <td>${list.startDate}</td>
	              <td>${list.stopDate}</td>
	              <td class="text-right"><f:formatNumber type="number" maxFractionDigits="3" value="${list.pay}" /></td>
	              <td>${list.email}</td>
	              <td>${list.phone}</td>
	              <td>${list.type}</td>
	              <td>	    
		              <a href="#" data-val="${list.idx }" class="d-none d-sm-inline-block btn btn-success shadow-sm btn-edit" data-toggle="modal" data-target="#EditModal">
		      			<i class="fas fa-user fa-sm text-white-50"></i> 수정
	      			  </a>
      			  </td>
	            </tr>
          	</c:forEach>
          </tbody>
        </table>
      </div>
    </div>
  </div>
</div>
<!-- /.container-fluid -->
</div>
<!-- End of Main Content -->

 <!-- Logout Modal-->
 <div class="modal" id="AddModal" tabindex="-1" role="dialog" aria-hidden="true">
 <form class="user" action="memberInsert.do" method="post">
   <div class="modal-dialog" role="document">
     <div class="modal-content">
       <div class="modal-header">
         <h5 class="modal-title">월정액 회원 추가</h5>
         <button class="close" type="button" data-dismiss="modal" aria-label="Close">
           <span aria-hidden="true">×</span>
         </button>
       </div>
       <div class="modal-body">
		
			<div class="row py10 al-center">
			  <div class="col-md-4" style="text-align: center;">회원 이름</div>
			  <div class="col-md-8"><input type="text" class="form-control" name="name"></div>
			</div>
			<div class="row py10 al-center">
			  <div class="col-md-4" style="text-align: center;">차량 번호</div>
			  <div class="col-md-8"><input type="text" class="form-control" name="CNUM"></div>
			</div>
			<div class="row py10 al-center">
			  <div class="col-md-4" style="text-align: center;">시작 시간</div>
			  <div class="col-md-8"><input type="text" class="form-control" name="startDate"></div>
			</div>
			<div class="row py10 al-center">
			  <div class="col-md-4" style="text-align: center;">이메일</div>
			  <div class="col-md-8"><input type="text" class="form-control" name="email"></div>
			</div>
			<div class="row py10 al-center">
			  <div class="col-md-4" style="text-align: center;">Phone</div>
			  <div class="col-md-8"><input type="text" class="form-control" name="phone"></div>
			</div>
			<div class="row py10 al-center">
			  <div class="col-md-4" style="text-align: center;">구 분</div>
			  <div class="col-md-8"><input type="text" class="form-control" name="type"></div>
			</div>
       </div>
       <div class="modal-footer">
         <input type="submit" value="확인" class="btn btn-primary">
         <button class="btn btn-secondary" type="button" data-dismiss="modal">취소</button>
       </div>
     </div>
   </div>
   </form>
 </div>
 
  <!-- Logout Modal-->
 <div class="modal" id="EditModal" tabindex="-1" role="dialog" aria-hidden="true">
 <form class="user" method="post" action="memberUpdate.do" id="editFrm" name="editFrm">
   <div class="modal-dialog" role="document">
     <div class="modal-content">
       <div class="modal-header">
         <h5 class="modal-title">월정액 회원 수정</h5>
         <button class="close" type="button" data-dismiss="modal" aria-label="Close">
           <span aria-hidden="true">×</span>
         </button>
       </div>
       <div class="modal-body">
			<input type="hidden" id="idx" name="idx" value="">
			<div class="row py10 al-center">
			  <div class="col-md-4" style="text-align: center;">회원 이름</div>
			  <div class="col-md-8"><input type="text" class="form-control" id="name" name="name"></div>
			</div>
			<div class="row py10 al-center">
			  <div class="col-md-4" style="text-align: center;">차량 번호</div>
			  <div class="col-md-8"><input type="text" class="form-control" id="CNUM" name="CNUM"></div>
			</div>
			<div class="row py10 al-center">
			  <div class="col-md-4" style="text-align: center;">시작 시간</div>
			  <div class="col-md-8"><input type="text" class="form-control" id="startDate" name="startDate"></div>
			</div>
			<div class="row py10 al-center">
			  <div class="col-md-4" style="text-align: center;">종료 시간</div>
			  <div class="col-md-8"><input type="text" class="form-control" id="stopDate" name="stopDate"></div>
			</div>
			<div class="row py10 al-center">
			  <div class="col-md-4" style="text-align: center;">사용 금액</div>
			  <div class="col-md-8"><input type="text" class="form-control" id="pay" name="pay"></div>
			</div>
			<div class="row py10 al-center">
			  <div class="col-md-4" style="text-align: center;">이메일</div>
			  <div class="col-md-8"><input type="text" class="form-control" id="email" name="email"></div>
			</div>
			<div class="row py10 al-center">
			  <div class="col-md-4" style="text-align: center;">Phone</div>
			  <div class="col-md-8"><input type="text" class="form-control" id="phone" name="phone"></div>
			</div>
			<div class="row py10 al-center">
			  <div class="col-md-4" style="text-align: center;">구 분</div>
			  <div class="col-md-8"><input type="text" class="form-control" id="type" name="type"></div>
			</div>
       </div>
       <div class="modal-footer">
         <input type="button" onclick="deleteConfirm();" value="삭제" class="btn btn-danger fl">
         <input type="button" onclick="updateConfirm();" value="확인" class="btn btn-primary">
         <button class="btn btn-secondary" type="button" data-dismiss="modal">취소</button>
       </div>
     </div>
   </div>
   </form>
 </div>
 
 
    <script type="text/javascript">
      $(function () {
          $('#startDate').datetimepicker();
          $( "#startForm" ).datepicker({
        	    dateFormat: 'yy-mm-dd'
          });
          $( "#endForm" ).datepicker({
      	    dateFormat: 'yy-mm-dd'
          });  
          
          $("#frm").submit(function(){
        	  var searchForm = $("#searchForm").val();
        	  var startForm = $("#startForm").val();
        	  var endForm = $("#endForm").val();
        	  if((searchForm == null || searchForm == "") 
        			  && (startForm == null || startForm == "") 
        			  && (endForm == null || endForm == "")){
				alert("검색어를 입력해주세요.");			        		  
				$("#searchForm").focus();
				return false;
        	  }
          });
          
          $("#searchBtn").click(function(){
        	  $("#frm").submit();
          });
          

          $(".btn-edit").click(function(e){
        	  var name = $(this).parent().siblings().eq(1).text();
        	  var CNUM = $(this).parent().siblings().eq(2).text();
        	  var startDate = $(this).parent().siblings().eq(3).text();
        	  var stopDate = $(this).parent().siblings().eq(4).text();
        	  var pay = $(this).parent().siblings().eq(5).text().replace(",", "");
        	  var email = $(this).parent().siblings().eq(6).text();
        	  var phone = $(this).parent().siblings().eq(7).text();
        	  var type = $(this).parent().siblings().eq(8).text();
        	  
        	  $("#name").val(name);
        	  $("#CNUM").val(CNUM);
        	  $("#startDate").val(startDate);
        	  $("#stopDate").val(stopDate);
        	  $("#email").val(email);
        	  $("#phone").val(phone);
        	  $("#type").val(type);
        	  $("#pay").val(pay);
        	  $("#idx").val($(this).attr("data-val"));
          });
      });

      function updateConfirm(){
    	  if(confirm("수정하시겠습니까?")){
			$("#editFrm").submit();
    	  }
      }
  </script> 	
 <%@ include file="/WEB-INF/views/include/footer.jsp" %> 
