<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/include/header.jsp" %>

<div class="container-fluid mb-3">
	<div class="card border-left-secondary shadow h-100 py-2" border>
		<div class="card-body">
			<h1 class="mb-3">쿠폰 사용 내역 조회</h1>
		</div>
	</div>
</div>
<div class="container-fluid ">
	<div class="card shadow mb-4">
		<div class="card-header py-3">
			<div class="row my-2">
				<div class="col-xs-1">
					<select class="form-control animated--grow-in" name="l_condition">
						<option value="cpnum" selected>쿠폰 타입</option>
						<option value="cnum">차량 번호</option>
					</select>
				</div>
				<div class="col-xs-1 ml-2">
					<input class="form-control" type="text" name="l_value" />
				</div>
				<div class="col-xs-1 ml-2">
					<button class="btn btn-secondary" name="l_search">검색</button>
				</div>

				<div class="col-xs-1 ml-3">
					<select class="form-control animated--grow-in" name="l_align">
						<option value="10" selected>10개씩 보기</option>
						<option value="50">50개씩 보기</option>
						<option value="100">100개씩 보기</option>
					</select>
				</div>
			</div>
		</div>
		<div class="card-body row">
			<div class="col-md-12">
				<div class="table-responsive">

					<table class="table table-bordred table-striped text-center">

						<thead>
							<th><input type="checkbox" id="l_chk" /></th>
							<th>순번</th>
							<th>쿠폰 타입</th>
							<th>쿠폰 코드</th>
							<th>유효 기간</th>
							<th>사용 여부</th>
							<th>차량 번호</th>
						</thead>

						<tbody id="l_area">
						<input type="hidden" startidx="1">
						</tbody>
					</table>
				</div>
				<div id = "page">
				</div>
			</div>
		</div>
	</div>
</div>


<script>
var page = 1; //실시간 페이지
var endpage; // 끝나는 페이지
var startidx = 1; // 가져온 자료 순번(=rnum)
var total = 0; // 자료의 총 개수
var pagesub = 0; // 페이지의 총 개수
var startpage = 1; // 시작 페이지;
var endpage; // 끝나는 페이지;
var plus = 0; 

	$(function () {
		$("#collapsePages").addClass("show");
		$("#arrow").removeClass("collapsed");
		log_search();
		
		$('button[name="l_search"]').click(function () {
			page = 1;
			startidx = 1;
			startpage = 1;
			plus = 0;
			log_search();
			paging();
		});

		$('select[name="l_align"]').change(function () {
			page = 1;
			startidx = 1;
			startpage = 1;
			plus = 0;
			log_search();
			paging();
		});
		
	});

	function log_search() {
		$.getJSON("search_log_proc.do", {
				"l_condition": $('select[name="l_condition"]').val(),
				"l_value": $('input:text[name="l_value"]').val(),
				"l_align": $('select[name="l_align"]').val(),
				"startidx": startidx
			},
			function (data) { //콜백함수
				var htmlStr = "<input type=\"hidden\" total="+data[0]["TOTAL"]+">";
				data.shift();
				$.each(data, function (key, val) {
					htmlStr += "<tr>";
					htmlStr += "<td><input type=\"checkbox\" name=\"l_chk\" value=" + val.CPNUM + "></td>";
					htmlStr += "<td>" + val.IDX + "</td>";
					htmlStr += "<td>" + val.CPNUM + "</td>";
					htmlStr += "<td>" + val.CPCODE + "</td>";
					htmlStr += "<td>" + val.VALIDITY+ "</td>";
					if(val.USED == "0"){
						htmlStr += "<td><i class=\"fas fa-times \" style=\"color:red\"></i></td>"
					}else{
						htmlStr += "<td><i class=\"far fa-circle \" style=\"color:red\"></i></td>"
					}
					htmlStr += "<td>" + val.CNUM + "</td>";
					htmlStr += "</tr>";
				});
				htmlStr += "</tbody>";
				$("#l_area").html(htmlStr);
				$("#l_chk").click(function () {
					if ($("#l_chk").prop("checked")) {
						$('input[name="l_chk"]').prop("checked", true);
					} else {
						$('input[name="l_chk"]').prop("checked", false);
					}
				});
				total = Number($('input:hidden').attr('total'));
				paging();
			});
	}
	
	function paging(){
		var htmlStr = "";
		if(total<Number($('select[name="l_align"]').val())){
			pagesub = 1
		}else{
			if(total%Number($('select[name="l_align"]').val()) == 0){
				pagesub = parseInt(total/Number($('select[name="l_align"]').val()));
			}else{
				pagesub = parseInt(total/Number($('select[name="l_align"]').val())+1);
			}	
		}
		
		if(pagesub>10){
			endpage = parseInt(((page + (10-1)))/10) * 10;
			if(page >= 11){
				htmlStr += "<button class=\"btn\" onclick=previous()>이전</button>";
			}
			if(endpage >= pagesub){
				endpage = pagesub;
				for(var i = startpage+plus; i<=endpage; i++){
					htmlStr += "<button class=\"btn\" onclick=page_click("+i+"); >"+i+"</button>";
				}
			}else{
				for(var i = startpage+plus; i<=endpage; i++){
					htmlStr += "<button class=\"btn\" onclick=page_click("+i+"); >"+i+"</button>";
				}
				htmlStr += "<button class=\"btn\" onclick=next();>다음</button>";
			}
		}else
			{
			for(var i = 1; i<=pagesub; i++){
				htmlStr += "<button class=\"btn\" onclick=page_click("+i+"); >"+i+"</button>";
			}
		}
		$("#page").html(htmlStr);
	}
	
	function page_click(e){
		if(page < Number(e)){
			sub = Number(e)-page;
			startidx += Number($('select[name="l_align"]').val()*sub);
			log_search();

		}else if(page > Number(e)){
			sub = page - Number(e);
			startidx -= Number($('select[name="l_align"]').val())*sub;
			log_search();
		}
		page = Number(e);
	}
	
	function next(){
		plus += 10;
		page = endpage+1;
		startidx = Number(endpage * $('select[name="l_align"]').val()+1);
		log_search();	
		paging();
	}
	
	function previous(){
		plus -= 10;
		page = startpage+plus;
		startidx = Number(startpage+plus * $('select[name="l_align"]').val());
		log_search();
		paging();
	}
</script>
</div>
<%@ include file="/WEB-INF/views/include/footer.jsp" %>