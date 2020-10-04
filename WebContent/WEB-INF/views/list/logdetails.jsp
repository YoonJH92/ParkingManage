<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/include/header.jsp" %> 
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery.form/4.3.0/jquery.form.min.js" integrity="sha512-YUkaLm+KJ5lQXDBdqBqk7EVhJAdxRnVdT2vtCzwPHSweCzyMgYV/tgGF4/dCyqtCC2eCphz0lRQgatGVdfR0ww==" crossorigin="anonymous"></script>

<link href="https://fonts.googleapis.com/css2?family=Do+Hyeon&display=swap" rel="stylesheet">

<!-- 있어야 함  -->
<style>


   @font-face {
    font-family: 'NEXON Lv1 Gothic OTF';
    src: url('https://cdn.jsdelivr.net/gh/projectnoonnu/noonfonts_20-04@2.1/NEXON Lv1 Gothic OTF.woff') format('woff');
    font-weight: normal;
    font-style: normal;
    
	}

    @font-face {
    font-family: 'MapoDPPA';
    src: url('https://cdn.jsdelivr.net/gh/projectnoonnu/noonfonts_2001@1.1/MapoDPPA.woff') format('woff');
    font-weight: normal;
    font-style: normal;
}


 h1{font-family: 'MapoDPPA';
    	color: #212529;
    	background-color: #fff;
   		border: 1px solid #e3e6f0;
        margin-top: 1.5rem;
        margin-bottom: 1.5rem;
        padding:0.5rem;
 	
 }

 
.fr{float:right!important;}
.fl{float:left!important;}
.py10{ padding: 10px 0;
}

.btn ,span, input ,form {

    font-family: 'NEXON Lv1 Gothic OTF';

}
   #modalimg{            
            max-width: 450px;
            max-height: 300px;
        	display: block; 
        	margin: 0px auto;               	
        	}          
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
 .btndiv{
 	margin-top: 0.5rem;
 	padding-left: 0;
 }
 .list_s{
 	margin-bottom: 0.5rem;
 }
	tr,td{
		color: black;
		font-family: 'NEXON Lv1 Gothic OTF';
		
	}  
	
	
 </style>                  
 <!-- Begin Page Content -->
 		<div class="container-fluid">
  <!-- Page Heading -->
  			<div class="heading">
  		<h1 class="h2 mb-4 text-black-800"> 차량  현황 조회 </h1>
  		</div>
  <!-- DataTales Example -->
  			<div calss="row">
  		<div class="card shadow mb-4">
    		<div class="card-header py-3">   
      	<form action="logdetail.do" method="get" id="frm" name="frm">                   
      <div class="pt10">
      	<div>
     	<span>날짜 검색 : </span>
     	<select name="Search" id="dateSearch" class="form-control1">
       		<option value>입차 시간  </option> 	
           </select>           
           <input  autocomplete="off" type="text" id="FDate" name="FDate" size=17 maxlength=17 value="${FDate}" class="form-control1">
          ~
          <input autocomplete="off" type="text" id="LDate" value="${LDate}" name="LDate" size=17 maxlength=17 class="form-control1">         
             </div>                    
             <div class="btndiv">
   	<div class="row">
        <div class="col-lg-8 mb-8 sm-8">

        <span>차량번호 : </span>

        	<select  id="search" class="form-control1">
       		<option>차량 번호</option>
       			</select>
           <input autocomplete="off"  type="text" name="cnum" id="cnum" size=17 maxlength=8 value="${Scnum}" class="form-control1">
      		<button id="searchbtn" class=" btn btn-warning shadow-sm mb4" ><i class="fas fa-search fa-sm text-white-50"></i> 검색하기</button>
      		<button class="btn btn-danger shadow-sm mb4" type="reset">  <i class="fas fa-undo"></i>  초기화      </button>
      
      </form>  
        </div>  
               <div class="col-lg-4 mb-4 sm-4 text-right">
      	<form action="logDetailExcel.ex" method="post"  name="exfrm">
    	<input type="hidden" id="FDate" name="FDate" value="${FDate}">	  	
      	<input type="hidden" id="LDate" value="${LDate}" name="LDate" > 
      	<input type="hidden" id="cnum" name="cnum" value="${Scnum}">   
      	
      	      	
           <button class="btn  btn-primary shadow-sm mb4"  id="exbtn">
 			 <i class="fas fa-download fa-sm text-white-50"></i> 엑셀 </a>
			</button>	           
        </form>
        </div>
		</div>
        </div>         
		</div>          
      	  	
    </div>      
    <div class="card-body">
    	<div class="list_s text-right">
    <form method="post" action="logdetail.do" name="rowForm">   목록:   	                
            <select name="dRs" id="DR" onchange="read()" >
       		<option value="20"  id="20" <c:if test="${displayRow==20}"> selected </c:if>>20</option> 	
       		<option value="30"  id="30"<c:if test="${displayRow==30}"> selected </c:if>>30</option> 	
       		<option value="50"  id="50"<c:if test="${displayRow==50}"> selected </c:if>>50</option> 	
       	    <option value="100" id="100" <c:if test="${displayRow==100}"> selected </c:if>>100</option> 	       	    
           </select>           
             <input type="hidden" id="FDate" name="FDate" value="${FDate}">	  	
      <input type="hidden" id="LDate" value="${LDate}" name="LDate" > 
      <input type="hidden" name="cnum" value="${Scnum}">     
      
                 
      
         </form>            
    </div>
    <div class="row">
    <div class="col-sm-12">
      <div class="table-responsive text-center">
        <table class="table table-bordered" id="dataTable" width="100%" >
          <thead>
                <c:if test="${empty detail}">
					<tr><td>검색결과가 없습니다</td></tr>
	                </c:if>
				<c:if test="${ not empty detail}">
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
                   <c:if test="${arr.cpNum == 0 }">
              <td><i class="fas fa-times " style="color:red"></i></td>          				
 				</c:if>
 				<c:if test="${arr.cpNum > 0 }">
            	<td><i class="fas fa-check" style="color:green"></i></td>      
     			</c:if>
    			<c:if test="${arr.monthNum == 0 }">
 	   		<td><i class="fas fa-times" style="color:red"></i></td>
    			</c:if>
    		<c:if test="${arr.monthNum != 0 }">
   			<td><i class="fas fa-check" style="color:green"></i></td></c:if>
            	    
           <c:if test="${arr.saleNum == 0 }">
 	   		<td><i class="fas fa-times" style="color:red"></i></td>
    			</c:if>
    		<c:if test="${arr.saleNum != 0 }">
   			<td><i class="fas fa-check" style="color:green"></i></td></c:if>       	          	    
              <td> <fmt:formatNumber value="${arr.totalPay}" pattern="#,###" /></td>
           <td><button type="button" class="btn btn-dark" id="imgbtn" data-toggle="modal"  data-cnum="${cnum}" data-idx="${arr.idx}" data-cimg="${arr.cImg}" data-target="#carModal"> <i class="fas fa-car"></i> 차량 사진 </button></td>
           </tr>													
            </c:forEach>   
    			</c:if>

          </tbody>
        </table>     
  
  
  
  
  
         <%@ include file="/WEB-INF/views/util/pagination.jsp" %>                    
  
        </div>
        </div>    
        
      </div>
    </div>
  </div>
</div>
</div>
</div>
<!-- /.container-fluid -->
 <div class="modal fade" id="carModal" tabindex="-1" role="dialog" aria-labelledby="carModalLabel">
    <div class="modal-dialog" role="document">
      <div class="modal-content">
        <div class="modal-header">
          <h4 class="modal-title" id="carModalLabel">차량이미지</h4>
        </div>
        <div class="modal-body">     
       <table>
           <form action="imgDtailupdate.do"  method="post" enctype="multipart/form-data">    
      <tr><td><input type="hidden" id="FDate" name="FDate" value=""></td></tr>	  	
      <tr><td><input type="hidden" id="LDate" name="LDate" value=""></td></tr> 
      <tr><td><input type="hidden" id="cnum" name="cnum" value=""></td></tr>            
      <tr><td><input type="hidden" id="IDRW" name="idrw" value=""></td></tr>            
      <tr><td><input type="hidden" id="Ipage" name="ipage" value=""></td></tr>            
           <tr><td><input type="hidden" name="idx" id="idx" value="" /></td></tr>
           <tr><td><input type="hidden" name="cimg" id="cimg" value="" ></td>	</tr> 			 
          <tr>
          <td>
           <img id="modalimg" src="" >
         </td></tr>
          <tr> <td ><input type="file" name="fileName" ></td> </tr>  

                   
       </table>
        <div class="modal-footer">
            <input type="submit" class="btn btn-primary" value="수정">
          <button type="button" class="btn btn-dark" data-dismiss="modal">닫기</button></div>
          </form>
        </div>   
    </div></div>
    </div>
    
    
    
    
    
    <div class="modal fade" id="alertModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
<div class="modal-dialog" role="document">
<div class="modal-content">
<div class="modal-header">
<button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">×</span></button>
<h4 class="modal-title" id="myModalLabel"></h4>
</div>
<div class="modal-body">
	검색어를 입력해주세요				
</div>
<div class="modal-footer">
 <button type="button" class="btn btn-primary" data-dismiss="modal">닫기</button>
</div>
</div>
</div>
</div>    
     
   <script>
   
   
   function fn_paging(curPage) {
    var vidrw = $('#DR').val();
   	var Fdate = $('#FDate').val();
    var Ldate = $('#LDate').val();
    var displayRow = $('#dRs').val();
    var cnum = $('#cnum').val();   
     location.href = window.location.pathname +"?Search=&FDate="+Fdate+"&LDate="+Ldate+"&cnum="+cnum+"&dRs="+vidrw+"&p="+curPage;
    } 
   
      
   
      var LOGIDX="";
      var CIMG="";
      var IMGSRC="";
      $(document).ready(function() {  
    	  	var Fdate = $('#FDate').val();
	        var Ldate = $('#LDate').val();
	        var displayRow = $('#dRs').val();
	        var cnum = $('#cnum').val();
	        var vidrw = $('#DR').val();
	        var ipage = ${pagination.curPage}
    	  
          $('#carModal').on('show.bs.modal', function(event) {   
              LOGIDX=$(event.relatedTarget).data('idx');
              CIMG=$(event.relatedTarget).data('cimg');
              var modal=$(this);
              $(".modal-body #idx ").val(LOGIDX);
              $(".modal-body #cimg ").val(CIMG);	
              $(".modal-body #FDate ").val(Fdate);	
              $(".modal-body #LDate ").val(Ldate);	
              $(".modal-body #cnum ").val(cnum);	
              $(".modal-body #IDRW ").val(vidrw);	
              $(".modal-body #Ipage ").val(ipage);	
              $(".modal-body #modalimg ").attr("onerror","this.remove ? this.remove() : this.removeNode();");
              $(".modal-body #modalimg ").attr("src","/ParkingManage/img/"+CIMG );
          });	                   
      });  
  		 	 $('#exbtn').click(function() {
     		  var exfrm =document.exfrm;	   
   	  	  	 exfrm.submit();  
     	 });     	    
      		$('#searchbtn').click(function() {      					
      		 	var cnum=document.getElementById('cnum').value;
      			var FDATE=document.getElementById('FDate').value;
      			var LDATE=document.getElementById('LDate').value;		
      			if((FDATE == "")&&(LDATE=="")&&(cnum=="")) {
				$('#alertModal').modal('show');
				return false;
      			}     			
				var searchfrm=document.frm;  
	   	  	  	 frm.submit();       			
      	});
      		
        function read() {		 
    	   var rowForm =document.rowForm;	   
    	    rowForm.submit();	  	  
		} 
        
    	$(function() {
    	    $("#FDate").datetimepicker(
    	    );
    	    $("#LDate").datetimepicker();
    		});

        
        
        
        
  </script>


<%@ include file="/WEB-INF/views/include/footer.jsp" %> 