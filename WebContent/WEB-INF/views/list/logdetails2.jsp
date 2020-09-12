<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/include/header.jsp" %> 
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery.form/4.3.0/jquery.form.min.js" integrity="sha512-YUkaLm+KJ5lQXDBdqBqk7EVhJAdxRnVdT2vtCzwPHSweCzyMgYV/tgGF4/dCyqtCC2eCphz0lRQgatGVdfR0ww==" crossorigin="anonymous"></script>
<!-- 있어야 함  -->
<style>
.fr{float:right!important;}
.fl{float:left!important;}
.py10{padding: 10px 0;}
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
 </style>                  
 <!-- Begin Page Content -->
 <div class="container-fluid">
  <!-- Page Heading -->
  <h1 class="h2 mb-5 text-gray-800">차량 조회 </h1>
  <!-- DataTales Example -->
  <div class="card shadow mb-4">
    <div class="card-header py-3">   
      <div class="py10">
      	<div>
     	<span>날짜 검색</span>
     	<select name="Search" id="dateSearch" class="form-control1">
       		<option value>입차 시간</option> 	
           </select>           
           <input type="text" id="FDate" name="FDate" size=17 maxlength=17 value="${FDate}" class="form-control1">
          ~
          <input type="text" id="LDate" value="${LDate}" name="LDate" size=17 maxlength=17 class="form-control1"> 
        <span>차량번호</span>
        <select  id="search" class="form-control1">
       		<option>차량 번호</option>
       	</select>
           <input type="text" name="cnum" size=10 maxlength=8 value="${cnum}" class="form-control1">
             </div>
           <div class="btndiv">
           <button id="test">전송</button>
           <input type="submit"  class="d-none d-sm-inline-block btn btn-warning shadow-sm mb4" value="검색하기">
          <a href="logdetaildown.do" class="d-none d-sm-inline-block btn  btn-primary shadow-sm mb4">
          <i class="fas fa-download fa-sm text-white-50"></i> 엑셀 </a>
         </div>
      </div>
            <select name="dRs" id="DR" onchange="read()" >                  	
       		<option value="20"  id="20" <c:if test="${displayRow==20}"> selected </c:if>>20</option> 	
       		<option value="30"  id="30"<c:if test="${displayRow==30}"> selected </c:if>>30</option> 	
       		<option value="50"  id="50"<c:if test="${displayRow==50}"> selected </c:if>>50</option> 	
       	    <option value="100" id="100" <c:if test="${displayRow==100}"> selected </c:if>>100</option> 	       	    
           </select>           
             <input type="hidden" id="FDate" name="FDate" value="${FDate}">	  	
      <input type="hidden" id="LDate" value="${LDate}" name="LDate" > 
      <input type="hidden" name="cnum" value="${cnum}">     
                    
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
          <tbody id="dtbody">
        
        </table>         
    <jsp:include page="test1.jsp"> 
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
<!-- /.container-fluid -->
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
       <form id="mdFrm" method="post" enctype="multipart/form-data" >
         <td><input type="hidden" name="idx" id="idx" value="" readonly="readonly"/></td></tr>
          <tr><td><input type="hidden" name="cimg" id="cimg" value="" ></td><tr> 	
          <tr><td><img id="modalimg" src="" ></td></tr>
          <tr><td><input type="file" id="fileup"name="fileName" accept="image/*"></td> </tr>           
       </table>
        <div class="modal-footer">
	          <button type="button" class="btn btn-default" id="imgUpdateBtn"  onClick="imgEvents(event)"> 수정 </button></div>
	          <button type="button" class="btn btn-default" data-dismiss="modal" class="close">닫기</button></div>
          </form>
        </div>   
    </div></div>
    </div>      
        <!-- 모달창 -->                 
        <script>              
        $(document).ready(function() { 
        	$.getJSON('logdetail.do',  
        	 {
        		"FDate": $('input:text[name="FDate"]').val(),
        		"LDate": $('input:text[name="LDate"]').val(),
				"cnum": $('input:text[name="cnum"]').val(),
				"dRs": $('select[name="dRs"]').val() 		    				   
        		},       		      			
        		function(data) {
        			var htmlStr ="";
        			$.each(data, function(key, val)
        					{ 
        					htmlStr += "<tr>";
        					htmlStr += "<td>" + val.idx + "</td>";
        					htmlStr += "<td>" + val.cnum + "</td>";
        					htmlStr += "<td>" + val.in_time + "</td>";
        					htmlStr += "<td>" + val.out_time+ "</td>";
        					htmlStr += "<td>" + val.pay + "</td>";
        					htmlStr += "<td>" + val.cpNum+ "</td>";
        					htmlStr += "<td>" + val.monthNum + "</td>";
        					htmlStr += "<td>" + val.sale_num + "</td>";
        					htmlStr += "<td>" + val.total_pay + "</td>";
        					htmlStr += "<td>" + val.c_img + "</td>";
							htmlStr += "<td><button type='button' class='btn btn-dark' id='imgbtn' data-toggle='modal' data-cnum="+val.cnum+ " data-idx="+val.idx+" data-cimg="+val.c_img+" data-target='#carModal'> 차량 사진 </button></td>";

        					
        					htmlStr += "</tr>";
        		            });
            				htmlStr += "</tbody>";
            				$("#dtbody").html(htmlStr);

        				}); 
    				});
        
        $('#carModal').on('show.bs.modal', function(event) {   		   		  
            LOGIDX=$(event.relatedTarget).data('idx');
            CIMG=$(event.relatedTarget).data('cimg');
            var modal=$(this);
            $(".modal-body #idx ").val(LOGIDX);
            $(".modal-body #cimg ").val(CIMG);	
            $(".modal-body #modalimg ").attr("onerror","this.remove ? this.remove() : this.removeNode();");
            $(".modal-body #modalimg ").attr("src","/ParkingManage/img/"+CIMG );
        		              
          	});	 
</script>
<script>


		$(document).ready(function() {
       $("#test").click(function() {
    	   $.getJSON('logdetail.do',  
     			  {
     		"FDate": $('input:text[name="FDate"]').val(),
     		"LDate": $('input:text[name="LDate"]').val(),
				"cnum": $('input:text[name="cnum"]').val(),
				"dRs": $('select[name="dRs"]').val() 		    				   
     			 },       		
    	   function(data) {   
         			var htmlStr ="";

            $.each(data, function(key, val) {
            	htmlStr += "<tr>";
        		htmlStr += "<td>" + val.idx + "</td>";
				htmlStr += "<td>" + val.cnum + "</td>";
				htmlStr += "<td>" + val.in_time + "</td>";
				htmlStr += "<td>" + val.out_time+ "</td>";
				htmlStr += "<td>" + val.pay + "</td>";
				htmlStr += "<td>" + val.cpNum+ "</td>";
				htmlStr += "<td>" + val.monthNum + "</td>";
				htmlStr += "<td>" + val.sale_num + "</td>";
				htmlStr += "<td>" + val.total_pay + "</td>";
				htmlStr += "<td>" + val.c_img + "</td>";
				htmlStr += "<td><button type='button' class='btn btn-dark' id='imgbtn' data-toggle='modal' data-cnum="+val.cnum+ " data-idx="+val.idx+" data-cimg="+val.c_img+" data-target='#carModal'> 차량 사진 </button></td>";
				htmlStr += "</tr>";
            });
				htmlStr += "</tbody>";
				$("#dtbody").html(htmlStr);
        });
    });

});


 


$(function() {
    $("#FDate").datetimepicker(
    );
    $("#LDate").datetimepicker();
});

var LOGIDX="";
var CIMG="";
var IMGSRC="";

function imgEvents(e){
e.stopPropagation();
e.stopImmediatePropagation();
		var frm=document.getElementById('mdFrm');
		var fileData  = new FormData(frm);
		fileData.append("idx", $('input[name="idx"]').val());		
		fileData.append("filename", $('input[name="fileName"]')[0].files[0]);			
			$.ajax({
				type:'post',
				url : 'imgDtailupdate.do',
				data:fileData,
				entype:'multipart/form-data',
		    	processData: false,
				contentType: false,
			    cache: false,
				//dataType: 'json', 
			  	success : function(data) {
		       		alert("파일 업로드 성공.");
		       		 },
		       		error : function(error) {
		        			alert(error.status);
		     		}		
				});	
			}	
			 function read() {   
				document.rowForm.submit();
		}
		  	  
	                	       
	  
		</script>

 <!--     function log_search() {
    		$.getJSON("logdetail.do",
    			function (data) { 
    				var htmlStr = "";
    				
    				$.each(data, function (key, val) {
    					htmlStr += "<tr>";
    					htmlStr += "<td>" + val.idx + "</td>";
    					htmlStr += "<td>" + val.cnum + "</td>";
    					htmlStr += "<td>" + val.in_time + "</td>";
    					htmlStr += "<td>" + val.out_time+ "</td>";
    					htmlStr += "<td>" + val.cpNum+ "</td>";
    					htmlStr += "<td>" + val.monthNum + "</td>";
    					htmlStr += "<td>" + val.sale_num + "</td>";
    					htmlStr += "<td>" + val.pay + "</td>";
    					htmlStr += "<td>" + val.total_pay + "</td>";
    					htmlStr += "<td>" + val.c_img + "</td>";
    					htmlStr += "</tr>";
    					});
    				htmlStr += "</tbody>";
    				$("#dtbody").html(htmlStr);
    			});
  			});
			 -->
			
	

<%@ include file="/WEB-INF/views/include/footer.jsp" %> 