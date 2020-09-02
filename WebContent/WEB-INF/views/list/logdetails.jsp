<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ include file="/WEB-INF/views/include/header.jsp" %> 
        <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<style>

 #modalimg{
        	max-width: 450px;
            max-height: 300px;
        	display: block; 
        	margin: 0px auto;       
    }

</style>


 <div class="container-fluid">
	  <div class="d-sm-flex align-items-center justify-content-between mb-4">
            <h1 class="h3 mb-0 text-gray-800">차량조회</h1>          
            <a href="logdetaildown.do" class="d-none d-sm-inline-block btn btn-sm btn-primary shadow-sm"><i class="fas fa-download fa-sm text-white-50"></i> 엑셀 </a>
          </div>
          
      
          
   <div class="row">
            <div class="col-xl-12 col-md-12 mb-4">
              <div class="card border-left-primary shadow h-100 py-2">
                <div class="card-body">
                  <div class="row no-gutters align-items-center">
                    <div class="col mr-2">
                      <div class="text-xs font-weight-bold text-primary text-uppercase mb-1">조회</div>
                      <div class="h5 mb-0 font-weight-bold text-gray-800">                  
                      <form action="logdetail.do" method="post" >
                      	기간  : <input type="text" id="FDate" name="FDate" size=17 maxlength=17> ~ <input type="text" id="LDate"  name="LDate" size=17 maxlength=17> 
                      	  차량번호 <input type="text" name="cnum" size=10 maxlength=8>
                      	  <input type="submit" value="검색">                     
                      </form>                                          
                     </div>
                    </div>
                    <div class="col-auto">
                      <i class="fas fa-calendar fa-2x text-gray-300"></i> 
                    </div>
                  </div>
                </div>
              </div>
            </div>
</div>     
            <!-- Pending Requests Card Example -->
        
          <!-- Content Row -->
          <div class="row">
          
            <!-- Content Column -->
            <div class="col-lg-12 mb-12">

              <!-- Project Card Example -->
              <div class="card shadow mb-4">
                <div class="card-header py-3">
                  <h6 class="m-0 font-weight-bold text-black">실시간 조회</h6>
                </div>
                <div class="card-body">
               <table class="table table-hover">
  <thead>
    <tr>
      <th scope="col">No.</th>
      <th scope="col">차량번호</th>
      <th scope="col">입차시간</th>
      <th scope="col">출차시간</th>
      <th scope="col">사용금액</th>
      <th scope="col">월정액여부</th>
      <th scope="col">할인여부</th>
      <th scope="col">구분</th>
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
    <td>${arr.pay}</td> 
    <td>${arr.cpNum}</td>
   <td>${arr.monthNum}</td>
   
   <td></td>
   <td>${arr.totalPay}</td>
  <td><button type="button" class="btn btn-dark" id="imgbtn" data-toggle="modal"  data-idx="${arr.idx}"data-cimg="${arr.cImg}" data-target="#carModal"> 차량 사진 </button></td>
</tr>
	</c:forEach> 
  
  </tbody>
</table>
                       
               </div>
              </div>

        

            </div>
          </div>
        </div>
        <!-- /.container-fluid -->
      

 <!-- 모달창 --> 
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
 		<tr><td><input type="hidden" name="cimg" id="cimg" value=""></td>	<tr>
 			
        <tr>
        <td>
 		<img id="modalimg" src="" >

        </td></tr>
       
        <tr>
        <td><input type="file" name="fileName"></td>
 		</tr>
 		
      <div class="modal-footer">
      	<input type="submit" value="수정">
        <button type="button" class="btn btn-default" data-dismiss="modal">닫기</button>
        </form>
 	</table>
      </div>
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
			$(".modal-body #modalimg ").attr("src","/ParkingManage/img/"+CIMG );
					});		
	});
	
</script>


<%@ include file="/WEB-INF/views/include/footer.jsp" %> 

<script type="text/javascript">

$(function() {
  

	    $("#FDate").datetimepicker(
	    		    
	    
	    );
	    $("#LDate").datetimepicker();


	});

	</script>