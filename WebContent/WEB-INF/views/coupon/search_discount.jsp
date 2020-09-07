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
					<button class="btn btn-info" name="d_search">검색</button>
				</div>

				<div class="col-xs-1 ml-1">
					<button class="btn btn-info" name="d_delete">선택 삭제</button>
				</div>

				<div class="col-xs-1 ml-3">
					<select class="form-control animated--grow-in" name="d_align">
						<option value="10" selected>10개씩 보기</option>
						<option value="50">50개씩 보기</option>
						<option value="100">100개씩 보기</option>
					</select>
				</div>
				<button class="btn btn-info ml-auto" modal>쿠폰 및 할인권 생성</button>
			</div>
		</div>
		<div class="card-body row">
			<div class="col-md-12">
				<div class="table-responsive">

					<table class="table table-bordred table-striped text-center">

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


<div class="modal fade" id="d_edit" tabindex="-1" role="dialog" aria-labelledby="edit" aria-hidden="true">
	<div class="modal-dialog modal-lg" role="document">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
						aria-hidden="true">×</span></button>
			</div>
			<div class="modal-body">
				<table class="table table-bordered">
					<tr>
						<td style="width:30%; text-align: center;">발급처</td>
						<td><input class="form-control" type="text" name="company1" /></td>
					</tr>
					<tr>
						<td style="width:30%; text-align: center;">발급 목적</td>
						<td><input class="form-control" type="text" name="dpurpose1" /></td>
					</tr>
					<tr>
						<td style="width:30%; text-align: center;">할인 시간</td>
						<td><select style="width: 30%; display:inline-block;" class="form-control animated--grow-in"
								name="time1">
								<option value="1" selected>1시간</option>
								<option value="3">3시간</option>
								<option value="6">6시간</option>
								<option value="12">12시간</option>
								<option value="24">24시간</option>
								<option value="직접 입력">직접 입력</option>
							</select> <span id="time1"><input style="width: 61%; display:inline-block;"
									class="form-control" type="text" name="time2" numberOnly>시간</span>
						</td>
					</tr>
				</table>
			</div>
			<div class="modal-footer" style="border-top: 0px;">
				<button type="button" class="btn btn-warning btn-lg" style="width: 100%;"><span
						class="glyphicon glyphicon-ok-sign"></span> Update</button>
			</div>
		</div>
		<!-- /.modal-content -->
	</div>
	<!-- /.modal-dialog -->
</div>



<div class="modal fade" id="d_delete" tabindex="-1" role="dialog" aria-labelledby="edit" aria-hidden="true">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<h4 class="modal-title">확인</h4>
			</div>
			<div class="modal-body">
				<div class="alert alert-danger"><span class="glyphicon glyphicon-warning-sign"></span> 정말 삭제하시겠습니까?
				</div>
			</div>
			<div class="modal-footer">
				<button type="button" class="btn btn-success" name="m_d_delete"><span
						class="glyphicon glyphicon-ok-sign"></span>&nbsp;&nbsp;&nbsp;예&nbsp;&nbsp;&nbsp;</button>
				<button type="button" class="btn btn-default" data-dismiss="modal"><span
						class="glyphicon glyphicon-remove"></span>아니오</button>
			</div>
		</div>
		<!-- /.modal-content -->
	</div>
	<!-- /.modal-dialog -->
</div>

<script>
	var num;
	var name;
	var date;
	var purpose;
	var price;
	
	$(document).on("click", ".btn-sm", function () {
		num = $(this).data('num');
		name = $(this).data('name');
		date = $(this).data('date');
		c_purpose = $(this).data('c_purpose');
		d_purpose = $(this).data('d_purpose');
		price = $(this).data('discount');
		time = $(this).data('use_time');
		company = $(this).data('company');
						
		$('input[name="name1"]').val(name);
		
		if(date == "1" || date == "7" || date == "10" || date == "30" || date == "100"){
			$('select[name="date1"]').val(date);
		}else{
			$('select[name="date1"]').val("직접 입력");
			$("#date1").show();
			$('input[name="date2"]').val(date);
		}
		
		$('input[name="cpurpose1"]').val(c_purpose);
		
		if(price == "1000" || price == "3000" || price == "5000" || price == "10000" || price == "30000"){
			if(price == "1000"){
			$('select[name="price1"]').val("1,000");
			}
			if(price == "3000"){
			$('select[name="price1"]').val("3,000");
			}
			if(price == "5000"){
			$('select[name="price1"]').val("5,000");
			}
			if(price == "10000"){
			$('select[name="price1"]').val("10,000");
			}
			if(price == "30000"){
			$('select[name="price1"]').val("30,000");
			}
		}else{
			$('select[name="price1"]').val("직접 입력");
			$("#price1").show();
			$('input[name="price2"]').val(price);		
		}
		
		$('input[name="company1"]').val(company);
		
		$('input[name="dpurpose1"]').val(d_purpose);
		
		if(time == "1" || time == "3" || time == "6" || time == "12" || time == "24"){
			$('select[name="time1"]').val(time);
		}else{
			$('select[name="time1"]').val("직접 입력");
			$("#time1").show();
			$('input[name="time2"]').val(date);
		}

	});

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
				$("#d_chk").prop("checked", false);
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
				$("#c_chk").prop("checked", false);
				alert("삭제 완료!");
			} else {
				alert("체크 박스를 선택해주세요.");
			}
			search();
		});

		$('button[name="m_c_delete"]').click(function () {
			$.post("delete_C_D.do", {
				c_d: "coupon",
				num: num
			}, function (data) {});
			$('#c_delete').modal("hide");
			search();
		});

		$('button[name="m_d_delete"]').click(function () {
			$.post("delete_C_D.do", {
				c_d: "discount",
				num: num
			}, function (data) {});
			$('#d_delete').modal("hide");
			search();
		});
	});

	function search() {
		if ($("#s_switch").is(':checked') == false) {
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
						htmlStr += "<td>" + val.DISCOUNT + "원</td>";
						htmlStr +=
							"<td><span data-placement=\"top\" data-toggle=\"tooltip\" title=\"Edit\"><button class=\"btn btn-primary btn-circle btn-sm\" data-discount=" +val.DISCOUNT+ " data-c_purpose=" +val.PURPOSE+ " data-date=" +val.USE_DATE+ " data-name=" +val.CPNAME+ " data-num=" +val.CPNUM+ " data-title=\"Edit\" data-toggle=\"modal\" data-target=\"#c_edit\"><i class=\"fas fa-pen\"></i></button></span></td>";
						htmlStr +=
							"<td><span data-placement=\"top\" data-toggle=\"tooltip\" title=\"Delete\"><button class=\"btn btn-circle btn-danger btn-sm\" data-num="+val.CPNUM+" data-title=\"Delete\" data-toggle=\"modal\" data-target=\"#c_delete\"><i class=\"fas fa-trash\"></i></button></span></td>"
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
						htmlStr += "<td>" + val.USE_TIME + "시간</td>";
						htmlStr += "<td>" + val.PURPOSE + "</td>";
						htmlStr +=
							"<td><span data-placement=\"top\" data-toggle=\"tooltip\" title=\"Edit\"><button class=\"btn btn-info btn-circle btn-sm\" data-d_purpose="+val.PURPOSE+" data-use_time="+val.USE_TIME+" data-company="+val.COMPANY+" data-num="+val.CON_NUM+" data-title=\"Edit\" data-toggle=\"modal\" data-target=\"#d_edit\"><i class=\"fas fa-pen\"></i></button></span></td>";
						htmlStr +=
							"<td><span data-placement=\"top\" data-toggle=\"tooltip\" title=\"Delete\"><button class=\"btn btn-circle btn-danger btn-sm\" data-num="+val.COM_NUM+" data-title=\"Delete\" data-toggle=\"modal\" data-target=\"#d_delete\"><i class=\"fas fa-trash\"></i></button></span></td>"
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
</script>