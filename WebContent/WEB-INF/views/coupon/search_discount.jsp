<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<div class="row my-2">
	<div class="col-xs-1 ml-5">
		<select name="d_condition">
			<option value="company" selected>업체명</option>
			<option value="use_date">할인 시간</option>
			<option value="purpose">발급 목적</option>
		</select>
	</div>
	<div class="col-xs-1 ml-5">
		<input type="text" name="d_value" />
	</div>
	<div class="col-xs-1 ml-3">
		<button class="btn-primary" name="d_search">검색</button>
	</div>

	<div class="col-xs-1 ml-3">
		<select name="d_align">
			<option value="10" selected>10개씩 보기</option>
			<option value="50">50개씩 보기</option>
			<option value="100">100개씩 보기</option>
		</select>
	</div>
</div>

<div class="my-3">
	<table class="table table-bordered" id="d_area">
		<tr>
			<th><input type="checkbox" id="d_chk" />전체선택</th>
			<th>순번</th>
			<th>할인명</th>
			<th>할인 시간</th>
			<th>발급 목적</th>
		</tr>
	</table>
</div>
<script>
	$(function () {
		$('button[name="c_search"]').click(function () {
			search();
		});
		
		$('button[name="d_search"]').click(function () {
			search();
		});

		$('select[name="c_align"]').change(function () {
			search();
		});
		
		$('select[name="d_align"]').change(function () {
			search();
		});

		$('input:radio[value="쿠폰"]').change(function () {
			if ($('input:radio[value="쿠폰"]').is(':checked')) {
				var htmlStr =
					"<table class=\"table table-bordered\" id=\"area\"><tr><th><input type=\"checkbox\" id=\"chk\"/>전체선택</th><th>순번</th><th>쿠폰명</th><th>유효 기간</th><th>발급 목적</th><th>할인 금액</th></tr></table>";
			} else {
				var htmlStr =
					"<table class=\"table table-bordered\" id=\"area\"><tr><th><input type=\"checkbox\" id=\"chk\"/>전체선택</th><th>순번</th><th>할인명</th><th>할인 시간</th><th>발급 목적</th></tr></table>";
			}

			$("#area").html(htmlStr);
		})
	});

	function search() {
		if ($('input:radio[value="쿠폰"]').is(':checked')) {
			$.getJSON("search_C_D.do", {
					"c_condition": $('select[name="c_condition"]').val(),
					"c_value": $('input:text[name="c_value"]').val(),
					"c_align": $('select[name="c_align"]').val(),
					"c_d": "쿠폰"
				},
				function (data) { //콜백함수
					var htmlStr =
						"<table class=\"table table-bordered\" id=\"area\"><tr><th><input type=\"checkbox\" id=\"chk\"/>전체선택</th><th>순번</th><th>쿠폰명</th><th>유효 기간</th><th>발급 목적</th><th>할인 금액</th></tr></table>";

					$.each(data, function (key, val) {
						htmlStr += "<tr>";
						htmlStr += "<td><input type=\"checkbox\" name=\"c_chk\"/></td>";
						htmlStr += "<td>" + val.CPNUM + "</td>";
						htmlStr += "<td>" + val.CPNAME + "</td>";
						htmlStr += "<td>" + val.USE_DATE + "</td>";
						htmlStr += "<td>" + val.PURPOSE + "</td>";
						htmlStr += "<td>" + val.DISCOUNT + "</td>";
						htmlStr += "</tr>";
					});
					$("#c_area").html(htmlStr);
					$("#c_chk").click(function () {
						if ($("#c_chk").prop("checked")) {
							$('input[name="c_chk"]').prop("checked", true);
						} else {
							$('input[name="c_chk"]').prop("checked", false);
						}
					});
				});
		} else {
			$.getJSON("search_C_D.do", {
					"d_condition": $('select[name="d_condition"]').val(),
					"d_value": $('input:text[name="d_value"]').val(),
					"d_align": $('select[name="d_align"]').val(),
					"c_d": "할인권"
				},
				function (data) { //콜백함수
					var htmlStr =
						"<table class=\"table table-bordered\" id=\"area\"><tr><th><input type=\"checkbox\" id=\"chk\"/>전체선택</th><th>순번</th><th>할인명</th><th>할인 시간</th><th>발급 목적</th></tr></table>";

					$.each(data, function (key, val) {
						htmlStr += "<tr>";
						htmlStr += "<td><input type=\"checkbox\" name=\"d_chk\"/></td>";
						htmlStr += "<td>" + val.COM_NUM + "</td>";
						htmlStr += "<td>" + val.COMPANY + "</td>";
						htmlStr += "<td>" + val.USE_TIME + "</td>";
						htmlStr += "<td>" + val.PURPOSE + "</td>";
						htmlStr += "</tr>";
					});
					$("#d_area").html(htmlStr);
					$("#d_chk").click(function () {
						if ($("#d_chk").prop("checked")) {
							$('input[name="d_chk"]').prop("checked", true);
						} else {
							$('input[name="d_chk"]').prop("checked", false);
						}
					});
				});
		}
	}
</script>