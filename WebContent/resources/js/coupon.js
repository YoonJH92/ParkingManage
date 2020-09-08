/*수정 모달창 관련 스크립트*/
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

	if (date == "1" || date == "7" || date == "10" || date == "30" || date == "100") {
		$('select[name="date1"]').val(date);
		$("#date1").hide();
	} else {
		$('select[name="date1"]').val("직접 입력");
		$("#date1").show();
		$('input[name="date2"]').val(date);
	}

	$('input[name="cpurpose1"]').val(c_purpose);

	if (price == "1000" || price == "3000" || price == "5000" || price == "10000" || price == "30000") {
		if (price == "1000") {
			$('select[name="price1"]').val("1,000");
		}
		if (price == "3000") {
			$('select[name="price1"]').val("3,000");
		}
		if (price == "5000") {
			$('select[name="price1"]').val("5,000");
		}
		if (price == "10000") {
			$('select[name="price1"]').val("10,000");
		}
		if (price == "30000") {
			$('select[name="price1"]').val("30,000");
		}
		$("#price1").hide();
	} else {
		$('select[name="price1"]').val("직접 입력");
		$("#price1").show();
		$('input[name="price2"]').val(price);
	}

	$('input[name="company1"]').val(company);

	$('input[name="dpurpose1"]').val(d_purpose);

	if (time == "1" || time == "3" || time == "6" || time == "12" || time == "24") {
		$('select[name="time1"]').val(time);
		$("#time1").hide();
	} else {
		$('select[name="time1"]').val("직접 입력");
		$("#time1").show();
		$('input[name="time2"]').val(time);
	}

});


/*화면이 구현되었을 때 동작*/
$(function () {
	/*페이지 접근 시 좌측 목록바 보이게 설정*/
	$("#collapsePages").addClass("show");
	$("#arrow").removeClass("collapsed");
	search();

	if ($("#s_switch").is(':checked')) {
		$("#toggle1").hide();
		$("#toggle2").show();
		search();
	}

/*토글 동작 시 디자인 변경*/
	$("#s_switch").change(function () {
		if ($("#s_switch").is(':checked') == false) {
			$("#toggle1").show();
			$("#toggle2").hide();
			$('div[border]').removeClass("border-left-info");
			$('div[border]').addClass("border-left-primary");
			search();
		} else {
			$("#toggle1").hide();
			$("#toggle2").show();
			$('div[border]').removeClass("border-left-primary");
			$('div[border]').addClass("border-left-info");
			search();
		}
	});
	
	
	$('button[modal]').on('click', function () {
		$('#modalBox').modal('show');
	});

	$("#date").hide();
	$("#price").hide();
	$("#time").hide();
	$("#date1").hide();
	$("#price1").hide();
	$("#time1").hide();
	$("#toggle4").hide();

	//3자리 단위마다 콤마 생성
	function addCommas(x) {
		return x.toString().replace(/\B(?=(\d{3})+(?!\d))/g, ",");
	}

	//모든 콤마 제거
	function removeCommas(x) {
		if (!x || x.length == 0)
			return "";
		else
			return x.split(",").join("");
	}

	//한글 사용X 및 콤마 생성
	$("input[numberOnly]").on("keyup", function () {
		$(this).val($(this).val().replace(/(^0+)/, ""));
		var x = $(this).val();
		x = removeCommas(x);
		$(this).val(x);
	}).on("focusout", function () {
		var x = $(this).val();
		if (x && x.length > 0) {
			if (!$.isNumeric(x)) {
				x = x.replace(/[^0-9]/g, "");
			}
			x = addCommas(x);
			$(this).val(x);
		}
	}).on("keyup", function () {
		$(this).val($(this).val().replace(/[^0-9]/gi, ""));
	});

	$("#a_switch").change(function () {
		if ($("#a_switch").is(':checked') == false) {
			$("#toggle3").show();
			$("#toggle4").hide();
			$('input[name="a_c_d"]').val("a_coupon");
			$('div[mborder]').removeClass("border-left-info");
			$('div[mborder]').addClass("border-left-primary");
			$('input[mborder]').removeClass("btn-info");
			$('input[mborder]').addClass("btn-primary");
			$('input[name="name"]').prop("required", true);
			$('input[name="cpurpose"]').prop("required", true);
			$('input[name="company"]').prop("required", false);
			$('input[name="dpurpose"]').prop("required", false);
		} else {
			$("#toggle3").hide();
			$("#toggle4").show();
			$('input[name="a_c_d"]').val("a_discount");
			$('div[mborder]').removeClass("border-left-primary");
			$('div[mborder]').addClass("border-left-info");
			$('input[mborder]').removeClass("btn-primary");
			$('input[mborder]').addClass("btn-info");
			$('input[name="name"]').prop("required", false);
			$('input[name="cpurpose"]').prop("required", false);
			$('input[name="company"]').prop("required", true);
			$('input[name="dpurpose"]').prop("required", true);

		}
	});

	$('input[type="reset"]').click(function () {
		$("#toggle3").show();
		$("#toggle4").hide();
		$('input[name="a_c_d"]').val("a_coupon");
		$('div[mborder]').removeClass("border-left-info");
		$('div[mborder]').addClass("border-left-primary");
		$('input[mborder]').removeClass("btn-info");
		$('input[mborder]').addClass("btn-primary");
		$("#date").hide();
		$("#price").hide();
		$("#time").hide();
		$('input[name="name"]').prop("required", true);
		$('input[name="cpurpose"]').prop("required", true);
		$('input[name="company"]').prop("required", false);
		$('input[name="dpurpose"]').prop("required", false);
		$('input[name="date"]').prop("required", false);
		$('input[name="price"]').prop("required", false);
		$('input[name="time"]').prop("required", false);
	});

	$('select[name="date"]').change(function () {
		if ($('select[name="date"]').val() == "직접 입력") {
			$("#date").show();
			$('input[name="date"]').prop("required", true);
		} else {
			$("#date").hide();
			$('input[name="date"]').prop("required", false);
		}
	});

	$('select[name="price"]').change(function () {
		if ($('select[name="price"]').val() == "직접 입력") {
			$("#price").show();
			$('input[name="price"]').prop("required", true);
		} else {
			$("#price").hide();
			$('input[name="price"]').prop("required", false);
		}
	});

	$('select[name="time"]').change(function () {
		if ($('select[name="time"]').val() == "직접 입력") {
			$("#time").show();
			$('input[name="time"]').prop("required", true);
		} else {
			$("#time").hide();
			$('input[name="time"]').prop("required", false);
		}
	});

	$('select[name="date1"]').change(function () {
		if ($('select[name="date1"]').val() == "직접 입력") {
			$("#date1").show();
		} else {
			$("#date1").hide();
		}
	});

	$('select[name="price1"]').change(function () {
		if ($('select[name="price1"]').val() == "직접 입력") {
			$("#price1").show();
		} else {
			$("#price1").hide();
		}
	});

	$('select[name="time1"]').change(function () {
		if ($('select[name="time1"]').val() == "직접 입력") {
			$("#time1").show();
		} else {
			$("#time1").hide();
		}
	});

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


/*삭제 동작 구현*/
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


/*업데이트 업데이트 동작 처리*/
	$('button[name="m_c_update"]').click(function () {
		var date;
		var price;
		if ($('select[name="date1"]').val() == "직접 입력") {
			date = $('input[name="date2"]').val()
			if ($('input[name="date2"]').val() == "") {
				alert("빈칸을 채워주세요.");
				$('input[name="date2"]').focus();
				return;
			}
		} else {
			date = $('select[name="date1"]').val()
		}
		if ($('select[name="price1"]').val() == "직접 입력") {
			price = $('input[name="price2"]').val()
			if ($('input[name="price2"]').val() == "") {
				alert("빈칸을 채워주세요.");
				$('input[name="price2"]').focus();
				return;
			}
		} else {
			price = $('select[name="price1"]').val()
		}

		if ($('input[name="name1"]').val() == "") {
			alert("빈칸을 채워주세요.");
			$('input[name="name1"]').focus();
			return;
		} else if ($('input[name="cpurpose1"]').val() == "") {
			alert("빈칸을 채워주세요.");
			$('input[name="cpurpose1"]').focus();
			return;
		}
		$.post("modify_C_D.do", {
			c_d: "coupon",
			num: num,
			name: $('input[name="name1"]').val(),
			date: date,
			c_purpose: $('input[name="cpurpose1"]').val(),
			price: price,
		}, function (data) {});
		$('#c_edit').modal("hide");
		search();
	});

	$('button[name="m_d_update"]').click(function () {
		var time;
		if ($('select[name="time1"]').val() == "직접 입력") {
			time = $('input[name="time2"]').val()
			if ($('input[name="time2"]').val() == "") {
				alert("빈칸을 채워주세요.");
				$('input[name="time2"]').focus();
				return;
			}
		} else {
			time = $('select[name="time1"]').val()
		}

		if ($('input[name="company1"]').val() == "") {
			alert("빈칸을 채워주세요.");
			$('input[name="company1"]').focus();
			return;
		} else if ($('input[name="dpurpose1"]').val() == "") {
			alert("빈칸을 채워주세요.");
			$('input[name="dpurpose1"]').focus();
			return;
		}

		$.post("modify_C_D.do", {
			c_d: "discount",
			num: num,
			d_purpose: $('input[name="dpurpose1"]').val(),
			time: time,
			company: $('input[name="company1"]').val()
		}, function (data) {});
		$('#d_edit').modal("hide");
		search();
	});
});


/*검색 동작 구현*/
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
					htmlStr += "<td>" + val.USE_DATE + "일</td>";
					htmlStr += "<td>" + val.PURPOSE + "</td>";
					htmlStr += "<td>" + val.DISCOUNT.toString().replace(/\B(?=(\d{3})+(?!\d))/g, ",") + "원</td>";
					htmlStr +=
						"<td><span data-placement=\"top\" data-toggle=\"tooltip\" title=\"Edit\"><button class=\"btn btn-primary btn-circle btn-sm\" data-discount=" + val.DISCOUNT + " data-c_purpose=" + val.PURPOSE + " data-date=" + val.USE_DATE + " data-name=" + val.CPNAME + " data-num=" + val.CPNUM + " data-title=\"Edit\" data-toggle=\"modal\" data-target=\"#c_edit\"><i class=\"fas fa-pen\"></i></button></span></td>";
					htmlStr +=
						"<td><span data-placement=\"top\" data-toggle=\"tooltip\" title=\"Delete\"><button class=\"btn btn-circle btn-danger btn-sm\" data-num=" + val.CPNUM + " data-title=\"Delete\" data-toggle=\"modal\" data-target=\"#c_delete\"><i class=\"fas fa-trash\"></i></button></span></td>"
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
					htmlStr += "<td>" + val.PURPOSE + "</td>";
					htmlStr += "<td>" + val.USE_TIME + "시간</td>";
					htmlStr +=
						"<td><span data-placement=\"top\" data-toggle=\"tooltip\" title=\"Edit\"><button class=\"btn btn-info btn-circle btn-sm\" data-num=" + val.COM_NUM + " data-d_purpose=" + val.PURPOSE + " data-use_time=" + val.USE_TIME + " data-company=" + val.COMPANY + " data-title=\"Edit\" data-toggle=\"modal\" data-target=\"#d_edit\"><i class=\"fas fa-pen\"></i></button></span></td>";
					htmlStr +=
						"<td><span data-placement=\"top\" data-toggle=\"tooltip\" title=\"Delete\"><button class=\"btn btn-circle btn-danger btn-sm\" data-num=" + val.COM_NUM + " data-title=\"Delete\" data-toggle=\"modal\" data-target=\"#d_delete\"><i class=\"fas fa-trash\"></i></button></span></td>"
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