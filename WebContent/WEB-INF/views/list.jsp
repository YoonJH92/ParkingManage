<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ include file="/WEB-INF/views/include/header.jsp" %> 
        <!-- Begin Page Content -->
        
        <style>        
        #modalimg{
        	  max-width: 450px;
             max-height: 300px;
        	display: block; 
        	margin: 0px auto;       
        }
              
        #logcard1{
        border-right: .25rem solid #4e73df !important;        
        }
        
         #logcard2{
        border-right: .25rem solid #1cc88a !important;
                
        }
         #logcard3{
        border-right: .25rem solid #f6c23e !important;       
        }
        
        </style>        
        <div class="container-fluid">
          <!-- Page Heading -->
          <div class="d-sm-flex align-items-center justify-content-between mb-4">
            <h1 class="h3 mb-0 text-gray-800">실시간 현황 조회</h1>
            <a href="logexceldown.do" class="d-none d-sm-inline-block btn btn-sm btn-primary shadow-sm"><i class="fas fa-download fa-sm text-white-50"></i> 엑셀 </a>
          </div>

          <!-- Content Row -->
          <div class="row">

            <!-- Earnings (Monthly) Card Example -->
            <div class="col-xl-4 col-md-4 mb-4">
              <div class="card border-left-primary shadow h-100 py-2" id="logcard1">
                <div class="card-body">
                  <div class="row no-gutters align-items-center">
                    <div class="col mr-2">
                      <div class="text-s font-weight-bold text-primary text-center text-uppercase mb-1">총 주자수</div>
                      <div class="h4 mb-0 font-weight-bold text-gray-800 text-center">
                      
                      <c:out value="${total['allCum']}"/>
                      
                      </div>
                    </div>
                  </div>
                </div>
              </div>
            </div>

            <!-- Earnings (Monthly) Card Example -->
            <div class="col-xl-4 col-md-4 mb-4">
              <div class="card border-left-success shadow h-100 py-2" id="logcard2">
                <div class="card-body">
                  <div class="row no-gutters align-items-center">
                    <div class="col mr-2">
                      <div class="text-s font-weight-bold text-success text-center text-uppercase mb-1" >일반 </div>
                      <div class="h4 mb-0 font-weight-bold text-gray-800 text-center"><c:out value="${total['gNum']}"/></div>
                    </div>                 
                  </div>
                </div>
              </div>
            </div>
   
             <div class="col-xl-4 col-md-4 mb-4">
              <div class="card border-left-warning shadow h-100 py-2" id="logcard3">
                <div class="card-body">
                  <div class="row no-gutters align-items-center">
                    <div class="col mr-2">
                      <div class="text-s font-weight-bold text-warning text-uppercase mb-1 text-center" >월정액</div>
                      <div class="h4 mb-0 font-weight-bold text-gray-800 text-center"><c:out value="${total['mNum']}"/></div>
                    </div>
                   
                  </div>
                </div>
              </div>
            </div>
          </div>
          
          
          
          
          
          
          

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
      <th scope="col">사용금액</th>
      <th scope="col">여부</th>
      <th scope="col">월정액여부</th>
      <th scope="col">구분</th>
      <th scope="col">차량이미지</th>
    </tr>
  </thead>
  <tbody>
 
 
	<c:set var="cFare" value="${farelist}"></c:set>
	<c:forEach var="arr" items="${list}"  varStatus="status">
<tr>
     <th scope="row">${arr.idx}</th>
	<td>${arr.cnum}</td>
	<td>${arr.inTime}</td>  	
	<c:if test="${arr.monthNum == 0}">
     <td>${cFare[status.index]}</td>	
     </c:if>
     <c:if test="${arr.monthNum != 0}">
     <td>0</td>	
     </c:if>
    <td>${arr.cpNum}</td>
   <td>${arr.monthNum}</td>
   <td></td>
  <td><button type="button" class="btn btn-dark" data-toggle="modal"  data-idx="${arr.idx}"data-cimg="${arr.cImg}" data-target="#carModal"> 차량 사진 </button></td>
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
      </div>
      <!-- End of Main Content -->   
      <!-- 모달창 --> 
        <div class="modal fade" id="carModal" tabindex="-1" role="dialog" aria-labelledby="carModalLabel">
  <div class="modal-dialog" role="document">
    <div class="modal-content">
      <div class="modal-header">
        <h4 class="modal-title" id="carModalLabel">차량이미지</h4>
      </div>
      <div class="modal-body">     
 	<table>
 		<form action="imgupdate.do" enctype="multipart/form-data" method="post">
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
 		
 	</table>
      <div class="modal-footer">
      	<input type="submit" value="수정">
        <button type="button" class="btn btn-default" data-dismiss="modal">닫기</button>
        </form>
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