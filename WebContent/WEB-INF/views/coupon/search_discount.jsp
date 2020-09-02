<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<link href="//maxcdn.bootstrapcdn.com/font-awesome/4.2.0/css/font-awesome.min.css" rel="stylesheet">

<div class="container-fluid ">
	<div class="card shadow mb-4">
		<div class="card-header py-3">
			<div class="row my-2">
				<div class="col-xs-1">
					<select class="form-control animated--grow-in" name="d_condition">
						<option value="company" selected>업체명</option>
						<option value="use_date">할인 시간</option>
						<option value="purpose">발급 목적</option>
					</select>
				</div>
				<div class="col-xs-1 ml-2">
					<input class="form-control" type="text" name="d_value" />
				</div>
				<div class="col-xs-1 ml-2">
					<button class="btn btn-primary" name="d_search">검색</button>
				</div>

				<div class="col-xs-1 ml-1">
					<button class="btn btn-primary" name="d_delete">선택 삭제</button>
				</div>

				<div class="col-xs-1 ml-3">
					<select class="form-control animated--grow-in" name="d_align">
						<option value="10" selected>10개씩 보기</option>
						<option value="50">50개씩 보기</option>
						<option value="100">100개씩 보기</option>
					</select>
				</div>
				<button class="btn btn-primary ml-auto" modal>쿠폰 및 할인권 생성</button>
			</div>
		</div>
		<div class="card-body row">
			<div class="col-md-12">
				<div class="table-responsive">

					<table class="table table-bordred table-striped">

						<thead>
							<th><input type="checkbox" id="d_chk" /></th>
							<th>순번</th>
							<th>할인명</th>
							<th>할인 시간</th>
							<th>발급 목적</th>
							<th>수정</th>
							<th>삭제</th>
						</thead>

						<tbody id="d_area">
						</tbody>
					</table>
				</div>
			</div>

		</div>
	</div>
</div>


<div class="modal fade" id="edit" tabindex="-1" role="dialog" aria-labelledby="edit" aria-hidden="true">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal" aria-hidden="true"><span
						class="glyphicon glyphicon-remove" aria-hidden="true"></span></button>
				<h4 class="modal-title custom_align" id="Heading">Edit Your Detail</h4>
			</div>
			<div class="modal-body">
				<div class="form-group">
					<input class="form-control " type="text" placeholder="Mohsin">
				</div>
				<div class="form-group">

					<input class="form-control " type="text" placeholder="Irshad">
				</div>
				<div class="form-group">
					<textarea rows="2" class="form-control"
						placeholder="CB 106/107 Street # 11 Wah Cantt Islamabad Pakistan"></textarea>


				</div>
			</div>
			<div class="modal-footer ">
				<button type="button" class="btn btn-warning btn-lg" style="width: 100%;"><span
						class="glyphicon glyphicon-ok-sign"></span> Update</button>
			</div>
		</div>
		<!-- /.modal-content -->
	</div>
	<!-- /.modal-dialog -->
</div>



<div class="modal fade" id="delete" tabindex="-1" role="dialog" aria-labelledby="edit" aria-hidden="true">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal" aria-hidden="true"><span
						class="glyphicon glyphicon-remove" aria-hidden="true"></span></button>
				<h4 class="modal-title custom_align" id="Heading">Delete this entry</h4>
			</div>
			<div class="modal-body">

				<div class="alert alert-danger"><span class="glyphicon glyphicon-warning-sign"></span> Are you sure you
					want to
					delete this Record?</div>

			</div>
			<div class="modal-footer ">
				<button type="button" class="btn btn-success"><span
						class="glyphicon glyphicon-ok-sign"></span> Yes</button>
				<button type="button" class="btn btn-default" data-dismiss="modal"><span
						class="glyphicon glyphicon-remove"></span> No</button>
			</div>
		</div>
		<!-- /.modal-content -->
	</div>
	<!-- /.modal-dialog -->
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

		$('input:radio[value="s_coupon"]').change(function () {
			if ($('input:radio[value="s_coupon"]').is(':checked')) {
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
		if ($('input:radio[value="s_coupon"]').is(':checked')) {
			$.getJSON("search_C_D.do", {
					"c_condition": $('select[name="c_condition"]').val(),
					"c_value": $('input:text[name="c_value"]').val(),
					"c_align": $('select[name="c_align"]').val(),
					"c_d": "coupon"
				},
				function (data) { //콜백함수
					var htmlStr = "";

					$.each(data, function (key, val) {
						htmlStr += "<tr>";
						htmlStr += "<td><input type=\"checkbox\" name=\"c_chk\" value=" + val.CPNUM + "></td>";
						htmlStr += "<td>" + val.CPNUM + "</td>";
						htmlStr += "<td>" + val.CPNAME + "</td>";
						htmlStr += "<td>" + val.USE_DATE + "</td>";
						htmlStr += "<td>" + val.PURPOSE + "</td>";
						htmlStr += "<td>" + val.DISCOUNT + "</td>";
						htmlStr +=
							"<td><p data-placement=\"top\" data-toggle=\"tooltip\" title=\"Edit\"><button class=\"btn btn-primary btn-circle btn-sm\"data-title=\"Edit\" data-toggle=\"modal\" data-target=\"#edit\"><i class=\"fas fa-pen\"></i></button></p></td>";
						htmlStr +=
							"<td><p data-placement=\"top\" data-toggle=\"tooltip\" title=\"Delete\"><button class=\"btn btn-circle btn-danger btn-sm\"data-title=\"Delete\" data-toggle=\"modal\" data-target=\"#delete\"><i class=\"fas fa-trash\"></i></button></p></td>"
						htmlStr += "</tr>";
					});
					htmlStr += "</tbody>";
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
					"c_d": "discount"
				},
				function (data) { //콜백함수
					var htmlStr = "";
					$.each(data, function (key, val) {
						htmlStr += "<tr>";
						htmlStr += "<td><input type=\"checkbox\" name=\"d_chk\" value=" + val.COM_NUM +
							"></td>";
						htmlStr += "<td>" + val.COM_NUM + "</td>";
						htmlStr += "<td>" + val.COMPANY + "</td>";
						htmlStr += "<td>" + val.USE_TIME + "</td>";
						htmlStr += "<td>" + val.PURPOSE + "</td>";
						htmlStr +=
							"<td><p data-placement=\"top\" data-toggle=\"tooltip\" title=\"Edit\"><button class=\"btn btn-primary btn-circle btn-sm\"data-title=\"Edit\" data-toggle=\"modal\" data-target=\"#edit\"><i class=\"fas fa-pen\"></i></button></p></td>";
						htmlStr +=
							"<td><p data-placement=\"top\" data-toggle=\"tooltip\" title=\"Delete\"><button class=\"btn btn-circle btn-danger btn-sm\"data-title=\"Delete\" data-toggle=\"modal\" data-target=\"#delete\"><i class=\"fas fa-trash\"></i></button></p></td>"
						htmlStr += "</tr>";
					});
					htmlStr += "</tbody>";
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

	$('button[name="d_delete"]').click(function () {
		if ($('input[name="d_chk"]').is(":checked")) {
			$('input[name="d_chk"]').each(function () {
				if ($(this).is(":checked")) {
					$.post("delete_C_D.do", {
						c_d: "discount",
						num: $(this).val()
					}, function (data) {});
				}
			});
			$("#d_chk").prop("checked",false);
			alert("삭제 완료!");
		} else {
			alert("체크 박스를 선택해주세요.");
		}
		search();
	});
	$('button[name="c_delete"]').click(function () {
		if ($('input[name="c_chk"]').is(":checked")) {
			$('input[name="c_chk"]').each(function () {
				if ($(this).is(":checked")) {
					$.post("delete_C_D.do", {
						c_d: "coupon",
						num: $(this).val()
					}, function (data) {});
				}
			});
			$("#c_chk").prop("checked",false);
			alert("삭제 완료!");
		} else {
			alert("체크 박스를 선택해주세요.");
		}
		search();
	});
</script>