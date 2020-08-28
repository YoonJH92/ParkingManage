<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<div class="row my-2">
	<div class="col-xs-1 ml-5">
		<select name="condition">
			<option value="cpname" selected>할인명</option>
			<option value="use_date">할인 시간</option>
			<option value="purpose">발급 목적</option>
		</select>
	</div>
	<div class="col-xs-1 ml-5">
		<input type="text" name="value" />
	</div>
	<div class="col-xs-1 ml-3">
		<button class="btn-primary" name="search">검색</button>
	</div>

	<div class="col-xs-1 ml-3">
		<select name="align">
			<option value="10" selected>10개씩 보기</option>
			<option value="50">50개씩 보기</option>
			<option value="100">100개씩 보기</option>
		</select>
	</div>
</div>

<div class="my-3">
	<table class="table table-bordered" id="area">
		<tr>
			<th><input type="checkbox" id="chk" />전체선택</th>
			<th>순번</th>
			<th>할인명</th>
			<th>할인 시간</th>
			<th>발급 목적</th>
		</tr>
	</table>
</div>
<script>
		$(function () {
			$('button[name="search"]').click(function () {
				search();
			});

			$('select[name="align"]').change(function () {
				search();
			});
		});

		function search() {
			$.getJSON("search_C_D.do", {
					"condition": $('select[name="condition"]').val(),
					"value": $('input:text[name="value"]').val(),
					"align": $('select[name="align"]').val(),
					"c_d": $('input[name="c_d"]').val()					
				},
				function (data) { //콜백함수
					var htmlStr =
						"<table class=\"table table-bordered\" id=\"area\"><tr><th><input type=\"checkbox\" id=\"chk\"/>전체선택</th><th>순번</th><th>할인명</th><th>할인 시간</th><th>발급 목적</th></tr></table>";

					$.each(data, function (key, val) {
						htmlStr += "<tr>";
						htmlStr += "<td><input type=\"checkbox\" name=\"chk\"/></td>";
						htmlStr += "<td>" + val.CPNUM + "</td>";
						htmlStr += "<td>" + val.CPNAME + "</td>";
						htmlStr += "<td>" + val.USE_DATE + "</td>";
						htmlStr += "<td>" + val.PURPOSE + "</td>";
						htmlStr += "<td>" + val.DISCOUNT + "</td>";
						htmlStr += "</tr>";
					});
					$("#area").html(htmlStr);

					$("#chk").click(function () {
						if ($("#chk").prop("checked")) {
							$('input[name="chk"]').prop("checked", true);
						} else {
							$('input[name="chk"]').prop("checked", false);
						}
					});
				});
		}
	</script>