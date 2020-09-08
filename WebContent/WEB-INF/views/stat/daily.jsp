<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="f" %>	
<!-- header -->
<%@ include file="/WEB-INF/views/include/header.jsp" %> 

<link href="https://cdn.datatables.net/1.10.21/css/jquery.dataTables.min.css" rel="stylesheet">
<script type="text/javascript" src="https://cdn.datatables.net/1.10.21/js/jquery.dataTables.min.js"></script>
<style>
.py50 {padding: 50px 0;}
.v-mid{vertical-align: middle!important;}
table thead th{padding: 6px 10px!important;}
table tbody td{padding: 4px 10px!important;}
}

</style>
<div class="container py50">
	<table id="example" class="table table-bordered text-center">
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
	              <td>${list.inMonth}</td>
	              <td>${list.inNomal}</td>
	              <td>${list.inMonth + list.inNomal}</td>
	              <td>${list.outNomal}</td>
	              <td>${list.outMonth}</td>
	              <td>${list.outNomal + list.outMonth}</td>
	              <td><f:formatNumber type="number" maxFractionDigits="3" value="${list.pay}" /></td>
	              <td>${list.monthCount}</td>
	              <td><f:formatNumber type="number" maxFractionDigits="3" value="${list.monthPay}" /></td>
	              <td><f:formatNumber type="number" maxFractionDigits="3" value="${list.pay + list.monthPay}" /></td>
	            </tr>
          	</c:forEach>
        </tbody>
        <tfoot>
			<tr></tr>
        </tfoot>
    </table>
</div>
<script>

function numberWithCommas(x) {
    return x.toString().replace(/\B(?=(\d{3})+(?!\d))/g, ",");
}

$(document).ready(function() {
    $('#example').DataTable( {
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

} );
</script>
  
<!-- footer -->
<%@ include file="/WEB-INF/views/include/footer.jsp" %>