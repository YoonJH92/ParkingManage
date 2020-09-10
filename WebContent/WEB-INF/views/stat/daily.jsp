<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="f" %>	
<!-- header -->
<%@ include file="/WEB-INF/views/include/header.jsp" %> 

<link href="https://cdn.datatables.net/1.10.21/css/jquery.dataTables.min.css" rel="stylesheet">
<script type="text/javascript" src="https://cdn.datatables.net/1.10.21/js/jquery.dataTables.min.js"></script>
<!--
<script src="https://cdnjs.cloudflare.com/ajax/libs/Chart.js/2.9.3/Chart.min.js"/>

-->

<script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/Chart.js/2.8.0/Chart.bundle.min.js"></script>
<script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/Chart.js/2.8.0/Chart.min.js"></script>
	<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/Chart.js/2.9.3/Chart.css"/>
<style>
.fr{float:right!important;}
.fl{float:left!important;}
.py50 {padding: 50px 0;}
.v-mid{vertical-align: middle!important;}
table#stat thead{background-color: #526bbf59;color: #fff;}
table#stat tfoot{background-color: #526bbf59;color: #fff;font-weight: bold;}
table#stat thead th{padding: 6px 10px!important;}
table#stat tbody td{padding: 4px 10px!important; cursor:pointer;}
.btn-custom {
    color: #fff;
    background-color: #BCA9F5;
    border-color: #BCA9F5;
}
.btn-custom:hover {
	color: #fff;
    background-color: #8258FA;
    border-color: #8258FA;
}
.btn-custom1 {
    color: #fff;
    background-color: #A9A9F5;
    border-color: #A9A9F5;
}
.btn-custom1:hover {
	color: #fff;
    background-color: #8181F7;
    border-color: #8181F7;
}
.selected{
	color: #fff;
    background-color: #A9A9F5!important;
}
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

.display-inline{display:inline;}
</style>

<div class="container py50">
	<div style="margin-bottom: 60px;">
		<div class="display-inline fl">
		   <form method="post" action="dailySearch.do" name="frm" id="frm">
		   	   <span>기간 검색 : </span>
			   <input type="text" class="form-control1" id="startForm" name="startForm" value="${startForm}" autocomplete="off">
		      	~
		      	<input type="text" class="form-control1" id="endForm" name="endForm" value="${endForm}" autocomplete="off">
		      	<a href="#" id="searchBtn" class="d-none d-sm-inline-block btn btn-warning shadow-sm mb4">
			      	<i class="fas fa-search fa-sm text-white-50"></i> 검색하기
		      	</a>
	      	</form>
		</div>
		<div class="display-inline fr">
			<a href="#" id="grapBtn" class="d-none d-sm-inline-block btn btn-custom shadow-sm mb4">
	    		<i class="fas fa-search fa-sm text-white-50"></i> 출입차 현황 그래프
	   		</a>
	   		<a href="#" id="grapBtn1" class="d-none d-sm-inline-block btn btn-custom1 shadow-sm mb4">
	    		<i class="fas fa-search fa-sm text-white-50"></i> 사용요금 현황 그래프
	   		</a>
		</div>
	</div>

	<canvas id="myChart" style="display:none;"></canvas>
	<canvas id="myChart1" style="display:none;" ></canvas>
	<table id="stat" class="table table-bordered text-center">
        <thead>
            <tr>
                <th rowspan=2 class="v-mid">시간</th>
                <th colspan=3>입차 수</th>
                <th colspan=4>출차 수</th>
                <th colspan=2>월정액 가입</th>
                <th rowspan=2 class="v-mid">합 계</th>
            </tr>
            <tr>
                <th>일 반</th>
                <th>월정액</th>
                <th>합 계</th>
                <th>일 반</th>
                <th>월정액</th>
                <th>합 계</th>
                <th>사용 요금</th>
                <th>등 록</th>
                <th>사용 요금</th>
            </tr>
        </thead>
        <tbody>
             <c:forEach var="list" items="${arr}">
          		<tr>
	              <td>${list.time}</td>
	              <td><f:formatNumber type="number" maxFractionDigits="3" value="${list.inNomal}" /></td>
	              <td><f:formatNumber type="number" maxFractionDigits="3" value="${list.inMonth}" /></td>
	              <td><f:formatNumber type="number" maxFractionDigits="3" value="${list.inMonth + list.inNomal}" /></td>
	              <td><f:formatNumber type="number" maxFractionDigits="3" value="${list.outNomal}" /></td>
	              <td><f:formatNumber type="number" maxFractionDigits="3" value="${list.outMonth}" /></td>
	              <td><f:formatNumber type="number" maxFractionDigits="3" value="${list.outNomal + list.outMonth}" /></td>
	              <td><f:formatNumber type="number" maxFractionDigits="3" value="${list.pay}" /></td>
	              <td><f:formatNumber type="number" maxFractionDigits="3" value="${list.monthCount}" /></td>
	              <td><f:formatNumber type="number" maxFractionDigits="3" value="${list.monthPay}" /></td>
	              <td><f:formatNumber type="number" maxFractionDigits="3" value="${list.pay + list.monthPay}" /></td>
	            </tr>
          	</c:forEach>
        </tbody>
        <tfoot>
			<tr style="font-size:15px;"></tr>
        </tfoot>
    </table>
</div>
</div>
<script>

function numberWithCommas(x) {
    return x.toString().replace(/\B(?=(\d{3})+(?!\d))/g, ",");
}

function removeComma(str){
	return parseInt(str.replace(/,/g,""));
}



$(document).ready(function() {
	$( "#startForm" ).datepicker({
	    dateFormat: 'yy-mm-dd'
	});
	$( "#endForm" ).datepicker({
	   dateFormat: 'yy-mm-dd'
	});  
    $('#stat').DataTable( {
    	// 표시 건수기능 숨기기
    	lengthChange: false,
    	// 검색 기능 숨기기
    	searching: false,
    	// 정보 표시 숨기기
    	info: false,
    	// 페이징 기능 숨기기
    	paging: false,
    	
        "footerCallback": function ( row, data, start, end, display ) {
            var api = this.api(), data;
 
            // Remove the formatting to get integer data for summation
            var intVal = function ( i ) {
                return typeof i === 'string' ?
                    i.replace(/[\$,]/g, '')*1 :
                    typeof i === 'number' ?i : 0;
            };
 
			var row = "<td>합계</td>";
			for(var i = 1; i <= 10; i++ ){
				row += "<td>"+numberWithCommas(api.column( i ).data().reduce( function (a, b) {
			        return intVal(a) + intVal(b);
			    }, 0 ))+"</td>"
			};
            // Update footer
            $( "tfoot tr" ).html(row);
        }
    
    } );
    
    $("#grapBtn").click(function(){
    	$("#myChart").toggle();
    });
    $("#grapBtn1").click(function(){
    	$("#myChart1").toggle();
    });
    $("#stat tbody tr").click(function (event) {
    	if($(this).hasClass('selected')){
    		$(this).removeClass('selected');
    	}else{
    		$(this).addClass('selected');
    	}
    });
    $("#searchBtn").click(function(){
  	  $("#frm").submit();
    });
} );




var ctx = document.getElementById('myChart').getContext('2d');
var ctx1 = document.getElementById('myChart1').getContext('2d');
ctx.canvas.width = 1000;
ctx.canvas.height = 500;
var labelArray =[];
var inArray =[];
var outArray =[];
var payArray =[];
var paySumArray =[];
$("#stat tbody tr").each(function(index){
	labelArray.push($("#stat tbody tr:eq("+index+")").children().eq(0).text());
	inArray.push(removeComma($("#stat tbody tr:eq("+index+")").children().eq(3).text()));
	outArray.push(removeComma($("#stat tbody tr:eq("+index+")").children().eq(6).text()));
	paySumArray.push(removeComma($("#stat tbody tr:eq("+index+")").children().eq(10).text()));
	payArray.push(removeComma($("#stat tbody tr:eq("+index+")").children().eq(7).text()));
});
var config = {
	type: 'bar',
	data: {
		labels: labelArray ,
		datasets: [
			{
				label: '입차 수',
				/*backgroundColor: 'transparent',*/
				backgroundColor: '#CEECF5',
				borderColor: '#CEECF5',
				data: inArray,
				
			}, 
			{
				label: '출차 수',
				backgroundColor: '#ECCEF5',
				borderColor: '#ECCEF5',
				data: outArray,
			}
		]
	},
	options: {
        //maintainAspectRatio: true, // default value. false일 경우 포함된 div의 크기에 맞춰서 그려짐.
	}
};
var config1 = {
		type: 'line',
		data: {
			labels: labelArray ,
			datasets: [
				{
					label: '총 합계',
					backgroundColor: 'transparent',
					borderColor: '#F781BE',
					data: paySumArray,
					
				}, 
				{
					label: '금액',
					backgroundColor: 'transparent',
					borderColor: '#ECE0F8',
					data: payArray,
				}, 
			]
		},
		options: {
	        //maintainAspectRatio: true, // default value. false일 경우 포함된 div의 크기에 맞춰서 그려짐.
		}
	};
//차트 그리기
var myChart = new Chart(ctx, config);
var myChart1 = new Chart(ctx1, config1);
</script>
  
<!-- footer -->
<%@ include file="/WEB-INF/views/include/footer.jsp" %>