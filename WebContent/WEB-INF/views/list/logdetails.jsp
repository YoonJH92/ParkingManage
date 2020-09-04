
<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/include/header.jsp" %> 
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
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
  <h1 class="h2 mb-5 text-gray-800">차량 조회 </h1>

  <!-- DataTales Example -->
  <div class="card shadow mb-4">
    <div class="card-header py-3">
      <form action="logdetail.do" method="post" id="frm" name="frm">
      <div class="py10">
      	<div>
      	
     	<span>날짜 검색</span>
     	<select name="dateSearch" id="dateSearch" class="form-control1">
       		<option value="JDATE">입차 시간</option> 	
           </select>
           <input type="text" id="FDate" name="FDate" size=17 maxlength=17 value="${FDate}" class="form-control1">
          ~
          <input type="text" id="LDate" value="${LDate}" name="LDate" size=17 maxlength=17 class="form-control1"> 
        <span>차량번호</span>
        <select name="search" id="search" class="form-control1">
       		<option value="CARN">차량 번호</option>
       	</select>
           <input type="text" name="cnum" size=10 maxlength=8 value="${cnum}" class="form-control1">
             </div>
           <div class="btndiv ">
           <input type="submit"  class="d-none d-sm-inline-block btn btn-warning shadow-sm mb4 " value="검색하기">
          <a href="logdetaildown.do" class="d-none d-sm-inline-block btn  btn-primary shadow-sm mb4">
          <i class="fas fa-download fa-sm text-white-50"></i> 엑셀 </a>
         </div>
      </div>
      </form>
    </div>
    <div class="card-body">
      <div class="table-responsive text-center">
        <table class="table table-bordered" id="dataTable" width="100%" cellspacing="0">
          <thead>
            <tr>
                <th scope="col">No.</th>
                <th scope="col">차량번호</th>
                <th scope="col">입차시간</th>
                <th scope="col">출차시간</th>
                <th scope="col">사용금액</th>
                <th scope="col">쿠폰 적용여부 </th>
                <th scope="col">월정액여부</th>
                <th scope="col">할인여부</th>
                <th scope="col">최종금액</th>      
                <th scope="col">차량이미지</th>
            </tr>
          </thead>
          <tbody>
            <c:forEach var="arr" items="${detail}">
	
                <tr>
                      <th scope="row">${arr.idx}</th>
                    <td>${arr.cnum}</td>
                    <td>${arr.inTime}</td>  	
                    <td>${arr.outTime}</td>  	
                  <td> <fmt:formatNumber value="${arr.pay}" pattern="#,###" /></td>
                    <td>${arr.cpNum}</td>
                   <td>${arr.monthNum}</td>  
                   <td>${arr.saleNum }</td>
                  <td> <fmt:formatNumber value="${arr.totalPay}" pattern="#,###" /></td>
                  <td><button type="button" class="btn btn-dark" id="imgbtn" data-toggle="modal"  data-idx="${arr.idx}" data-cimg="${arr.cImg}" data-target="#carModal"> 차량 사진 </button></td>
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

 
	
 <div class="modal fade" id="carModal" tabindex="-1" role="dialog" aria-labelledby="carModalLabel">
    <div class="modal-dialog" role="document">
      <div class="modal-content">
        <div class="modal-header">
          <h4 class="modal-title" id="carModalLabel">차량이미지</h4>
        </div>
        <div class="modal-body">     
       <table>
           <form action="imgDtailupdate.do"  method="post" enctype="multipart/form-data">
           <tr>
           <td><input type="hidden" name="idx" id="idx" value="" readonly="readonly"/></td></tr>
           <tr><td><input type="hidden" name="cimg" id="cimg" value="" ></td>	<tr> 			 
          <tr>
          <td>
           <img id="modalimg" src="" >
         </td></tr>
          <tr>
          <td><input type="file" name="fileName"></td>
           </tr>           
       </table>
        <div class="modal-footer">
            <input type="submit" value="수정">
          <button type="button" class="btn btn-default" data-dismiss="modal">닫기</button></div>
          </form>
        </div>   
    </div></div>
    </div>      
        <!-- 모달창 -->        
        <script>
      var LOGIDX="";
      var CIMG="";
      var IMGSRC="";
      $(document).ready(function() {
          $('#carModal').on('show.bs.modal', function(event) {   
              LOGIDX=$(event.relatedTarget).data('idx');
              CIMG=$(event.relatedTarget).data('cimg');
              var modal=$(this);
              $(".modal-body #idx ").val(LOGIDX);
              $(".modal-body #cimg ").val(CIMG);	
              $(".modal-body #modalimg ").attr("onerror","this.remove ? this.remove() : this.removeNode();");
              $(".modal-body #modalimg ").attr("src","/ParkingManage/img/"+CIMG );
          });	                   
      });
      
  </script>
  
  
  
  <script type="text/javascript">
  
  $(function() {
    
  
          $("#FDate").datetimepicker(
          );
          $("#LDate").datetimepicker();
  
      });  
      </script>

<%@ include file="/WEB-INF/views/include/footer.jsp" %> 