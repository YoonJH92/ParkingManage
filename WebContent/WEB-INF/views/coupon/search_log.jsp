<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/include/header.jsp" %>

<div class="container-fluid mb-3">
	<div class="card border-left-primary shadow h-100 py-2" border>
		<div class="card-body">
			<h1 class="mb-3">로그 조회</h1>
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
					<button class="btn btn-info" name="l_search">검색</button>
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
						</tbody>
					</table>
				</div>
			</div>
		</div>
	</div>
</div>


<script>
	$(function () {
		$("#collapsePages").addClass("show");
		$("#arrow").removeClass("collapsed");
		log_search();
		
		$('button[name="l_search"]').click(function () {
			log_search();
		});

		$('select[name="l_align"]').change(function () {
			log_search();
		});
	});

	function log_search() {
		$.getJSON("search_log_proc.do", {
				"l_condition": $('select[name="l_condition"]').val(),
				"l_value": $('input:text[name="l_value"]').val(),
				"l_align": $('select[name="l_align"]').val()
			},
			function (data) { //콜백함수
				var htmlStr = "";
				$.each(data, function (key, val) {
					htmlStr += "<tr>";
					htmlStr += "<td><input type=\"checkbox\" name=\"l_chk\" value=" + val.CPNUM + "></td>";
					htmlStr += "<td>" + val.IDX + "</td>";
					htmlStr += "<td>" + val.CPNUM + "</td>";
					htmlStr += "<td>" + val.CPCODE + "</td>";
					htmlStr += "<td>" + val.VALIDITY+ "</td>";
					htmlStr += "<td>" + val.USED+ "</td>";
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
			});
	}
</script>
</div>
<%@ include file="/WEB-INF/views/include/footer.jsp" %>