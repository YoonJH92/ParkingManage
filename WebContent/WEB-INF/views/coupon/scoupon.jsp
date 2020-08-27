<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<div class="row my-2">
	<div class="col-xs-1 ml-5">
		<select name="condition">
			<option value="cpname" selected>쿠폰명</option>
			<option value="use_date">사용 시간</option>
			<option value="purpose">발급 목적</option>
			<option value="discount">할인 금액</option>
		</select>
	</div>
	<div class="col-xs-1 ml-5">
		<input type="text" name="value" />
	</div>
	<div class="col-xs-1 ml-3">
		<button class="btn-primary" name="search">검색</button>
	</div>
</div>

<div class="my-3">
	<table class="table table-bordered">
		<tr>
			<th>
				<input type="checkbox" />전체선택
			</th>
			<th>
				쿠폰명
			</th>
			<th>
				사용 기간
			</th>
			<th>
				발급 목적
			</th>
			<th>
				할인 금액
			</th>
		</tr>
	</table>
</div>
<div id="area"></div>

<script>
	$(function () {
				$('button[name="search"]').click(function () {
					$.getJSON("search_C_D.do", {
							"condition": $('select[name="condition"]').val(),
							"value": $('input:text[name="value"]').val()
						},
						function (data) { //콜백함수
							var htmlStr = "<table class=table table-bordered>";
							$.each(data, function (key, val) {
								htmlStr += "<tr>";
								htmlStr += "<td>" + val.CPNAME + "</td>";
								htmlStr += "<td>" + val.USE_DATE + "</td>";
								htmlStr += "<td>" + val.PURPOSE + "</td>";
								htmlStr += "<td>" + val.DISCOUNT + "</td>";
								htmlStr += "</tr>";
							});
							htmlStr += "</table>";
							$("#area").html(htmlStr);
						});
				});
	});
</script>