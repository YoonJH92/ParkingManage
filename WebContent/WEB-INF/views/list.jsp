<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ include file="/WEB-INF/views/include/header.jsp" %>
<jsp:useBean id="now" class="java.util.Date" />

 
        <!-- Begin Page Content -->      
        <style>        
        #modalimg{
            max-width: 450px;
            max-height: 300px;
        	display: block; 
        	margin-bottom: 1.4rem;
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
        	td,th{
        	text-align: center;
        	color: black;
        	}
        	          	
        </style>        
        <div class="container-fluid">
          <!-- Page Heading -->
          <div class="d-sm-flex align-items-center  mb-4">        
            <div class="p-2"> <h1 class="h3 mb-0 text-gray-800"> 실시간 현황 조회    </h1>
            </div>
 			 <div class="p-2">	<body onload="printClock()">		
		<div style="border:1px solid #dedede; width:200px; height:50px; line-height:50px; background-color:white; color:#666;font-size:20px; text-align:center;" id="clock">
		</div></div>
  			<div class="ml-auto p-2"> <a href="logexcel.ex" class="btn btn-sm btn-primary shadow-sm"><i class="fas fa-download fa-sm text-white-50"></i> 엑셀 </a>
  			</div>
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
          <div class="row" >
                  
            <!-- Content Column -->
            <div class="col-lg-12 mb-12 sm-6">
              <!-- Project Card Example -->
              <div class="card shadow mb-4">
                <div class="card-header py-3">
                          <div class="row">               
                  <div class="col-lg-6 mb-6 sm-3 text-left" >
                  <h6 class="h5 m-0 font-weight-bold text-black">실시간 조회</h6>
                  </div>
                  <div class="col-lg-6 mb-6 sm-3 text-right" >
                <form method="get" action="loglist.do" name="rowForm"> 목록 :
             <select name="dRs" id="DR" onchange="submit(this.value)" > 
       		<option value="20"  id="20"<c:if test="${displayRow==20}"> selected </c:if>>20</option> 	
       		<option value="30"  id="30"<c:if test="${displayRow==30}"> selected </c:if>>30</option> 	
       		<option value="50"  id="50"<c:if test="${displayRow==50}"> selected </c:if>>50</option> 	
       	    <option value="100" id="100" <c:if test="${displayRow==100}"> selected </c:if>>100</option> 	       	    
           </select>           
         </form>
                </div>  
                </div>
                </div>
                <div class="card-body">
                <div ="row">
                <div class="ml-auto col-lg-12 mb-12 sm-6 ">
           </div>
                 <div class="table-responsive text-center">     
            <table class="table  table-hover  table-bordered" >
 				<thead>
    <tr>
      <th scope="col">No.</th>
      <th scope="col">차량번호</th>
      <th scope="col">입차시간</th>
      <th scope="col">사용금액</th>
      <th scope="col">쿠폰 여부</th>
      <th scope="col">월 정액 여부</th>
      <th scope="col">할인 적용 여부 </th>      
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
         <c:if test="${arr.cpNum == 0 }">
             <td><i class="fas fa-times " style="color:red"></i></td>      
     </c:if>

 <c:if test="${arr.cpNum != 0 }">
            <td><i class="fas fa-ticket-alt"></i></td>      
     </c:if>

    <c:if test="${arr.monthNum == 0 }">
    <td><i class="fas fa-times" style="color:red"></i></td>
    </c:if>
    <c:if test="${arr.monthNum != 0 }">
            <td><i class="fas fa-ticket-alt"></i></td>      
</c:if>
   
       <c:if test="${arr.saleNum == 0 }">
             <td><i class="fas fa-times " style="color:red"></i></td>      
     </c:if>

 <c:if test="${arr.saleNum != 0 }">
            <td><i class="fas fa-ticket-alt"></i></td>      
     </c:if>
   
    <td><button type="button" class="btn btn-dark" data-toggle="modal"  data-idx="${arr.idx}"data-cimg="${arr.cImg}" data-target="#carModal"> 차량 사진 </button></td>
</tr>	
	</c:forEach> 
  </tbody>
</table>
</div>
</div>
    <jsp:include page="test.jsp">
    <jsp:param value="${paging.page}" name="page"/>
    <jsp:param value="${paging.beginPage}" name="beginPage"/>
    <jsp:param value="${paging.endPage}" name="endPage"/>
    <jsp:param value="${paging.prev}" name="prev"/>
    <jsp:param value="${paging.next}" name="next"/> 
    <jsp:param value="${paging.displayRow}" name="displayRow"/> 
 </jsp:include>
                     
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
 	 <tr><td><input type="hidden" id="IDRW" name="idrw" value=""></td></tr>            
      <tr><td><input type="hidden" id="Ipage" name="ipage" value=""></td></tr>  
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
      	<input type="submit" class="btn btn-primary" value="수정">
        <button type="button" class="btn btn-dark" data-dismiss="modal">닫기</button>
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
	 var vidrw = $('#DR').val();
     var ipage = ${paging.page}
	$(document).ready(function() {
		$('#carModal').on('show.bs.modal', function(event) {   
			LOGIDX=$(event.relatedTarget).data('idx');
			CIMG=$(event.relatedTarget).data('cimg');
			var modal=$(this);
			$(".modal-body #idx ").val(LOGIDX);
			$(".modal-body #cimg ").val(CIMG);
	      	 $(".modal-body #IDRW ").val(vidrw);	
         	  $(".modal-body #Ipage ").val(ipage);
			$(".modal-body #modalimg ").attr("onerror","this.remove ? this.remove() : this.removeNode();");
			$(".modal-body #modalimg ").attr("src","/ParkingManage/img/"+CIMG );
		});
			
	});
	
  
	
	
	function printClock() {
	    
	    var clock = document.getElementById("clock");            // 출력할 장소 선택
	    var currentDate = new Date();                                     // 현재시간
	    var calendar = currentDate.getFullYear() + "-" + (currentDate.getMonth()+1) + "-" + currentDate.getDate() // 현재 날짜
	    var amPm = 'AM'; // 초기값 AM
	    var currentHours = addZeros(currentDate.getHours(),2); 
	    var currentMinute = addZeros(currentDate.getMinutes() ,2);
	    var currentSeconds =  addZeros(currentDate.getSeconds(),2);
	    
	    if(currentHours >= 12){ // 시간이 12보다 클 때 PM으로 세팅, 12를 빼줌
	    	amPm = 'PM';
	    	currentHours = addZeros(currentHours - 12,2);
	    }

	   
	    clock.innerHTML = currentHours+":"+currentMinute+":"+currentSeconds +" <span style='font-size:10px;'>"+ amPm+"</span>"; //날짜를 출력해 줌
	    
	    setTimeout("printClock()",1000);         // 1초마다 printClock() 함수 호출
	}

	function addZeros(num, digit) { // 자릿수 맞춰주기
		  var zero = '';
		  num = num.toString();
		  if (num.length < digit) {
		    for (i = 0; i < digit - num.length; i++) {
		      zero += '0';
		    }
		  }
		  return zero + num;
	}

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
		
</script>
      
          
      
 <%@ include file="/WEB-INF/views/include/footer.jsp" %> 