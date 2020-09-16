	var c_page = 1; //실시간 페이지
	var c_endpage; // 끝나는 페이지
	var c_startidx = 1; // 가져온 자료 순번(=rnum)
	var c_total = 0; // 자료의 총 개수
	var c_pagesub = 0; // 페이지의 총 개수
	var c_startpage = 1; // 시작 페이지;
	var c_endpage; // 끝나는 페이지;
	var c_plus = 0;

	var d_page = 1; //실시간 페이지
	var d_endpage; // 끝나는 페이지
	var d_startidx = 1; // 가져온 자료 순번(=rnum)
	var d_total = 0; // 자료의 총 개수
	var d_pagesub = 0; // 페이지의 총 개수
	var d_startpage = 1; // 시작 페이지;
	var d_endpage; // 끝나는 페이지;
	var d_plus = 0;

	var p_page = 1; //실시간 페이지
	var p_endpage; // 끝나는 페이지
	var p_startidx = 1; // 가져온 자료 순번(=rnum)
	var p_total = 0; // 자료의 총 개수
	var p_pagesub = 0; // 페이지의 총 개수
	var p_startpage = 1; // 시작 페이지;
	var p_endpage; // 끝나는 페이지;
	var p_plus = 0;

	/*화면이 구현되었을 때 동작*/
	$(function () {
		/*환경 변수*/
		var phone = [];
		var name = [];
		var scnum = [];
		var scpname = [];
		var scpnum = [];
		var sdate = [];
		var sdiscount = [];
		var spurpose = [];

		var cnum;
		var dnum;
		var cpname;
		var date;
		var c_purpose;
		var d_purpose;
		var price;
		var time;
		var company;

		/*단일 쿠폰 발급*/
		$('button[name="sms_send"]').click(function () {
			if ($('input[name="s_chk"]').is(":checked")) {
				$('input[name="s_chk"]').each(function () {
					if ($(this).is(":checked")) {
						name.push($(this).parent().siblings().eq(0).text());
						scnum.push($(this).parent().siblings().eq(3).text());
						phone.push($(this).parent().siblings().eq(4).text());
					}
				});
				$.ajax({
					url: 'send_coupon.do', // 전송 URL
					type: 'POST', // GET or POST 방식
					traditional: true,
					data: {
						'num': phone,
						'name': name,
						'cnum': scnum,
						'cpnum': scpnum,
						'cpname': scpname,
						'date': sdate,
						'discount': sdiscount,
						'purpose': spurpose
					},
					//Ajax 성공시 호출 
					success: function (data) {
						alert("발급 성공했습니다.");
					},
					//Ajax 실패시 호출
					error: function (jqXHR, textStatus, errorThrown) {
						alert("발급에 실패하였습니다.");
					}
				});
				$('input[name="s_chk"]').prop("checked", false);
			} else {
				alert("체크 박스를 선택해주세요.");
			}
		});

		/*버튼 누를 시 모달창 표시*/
		$('button[add_modal]').on('click', function () {
			$('#add_modalBox').modal('show');
		});

		$('button[publish_modal]').on('click', function () {
			if ($('input[name="c_chk"]').is(":checked")) {
				$('input[name="c_chk"]').each(function () {
					if ($(this).is(":checked")) {
						scpnum.push($(this).parent().siblings().eq(0).text());
						scpname.push($(this).parent().siblings().eq(1).text());
						sdate.push($(this).parent().siblings().eq(2).text());
						spurpose.push($(this).parent().siblings().eq(3).text());
						sdiscount.push($(this).parent().siblings().eq(4).text());
					}
				});
				$('#publish_modalBox').modal('show');
				p_search();
			} else {
				alert("발급할 쿠폰을 체크해주세요!");
			}
		});

		/*수정 모달창 관련 스크립트*/
		$(document).on("click", ".coupon", function () {
			cnum = $(this).parent().parent().siblings().eq(1).text();
			cpname = $(this).parent().parent().siblings().eq(2).text();
			date = $(this).parent().parent().siblings().eq(3).text().replace(/일/gi, "");
			c_purpose = $(this).parent().parent().siblings().eq(4).text();
			price = $(this).parent().parent().siblings().eq(5).text().replace(/원/gi, "");

			$('input[name="name1"]').val(cpname);

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
		});

		$(document).on("click", ".discount", function () {
			dnum = $(this).parent().parent().siblings().eq(1).text();
			company = $(this).parent().parent().siblings().eq(2).text();
			d_purpose = $(this).parent().parent().siblings().eq(3).text();
			time = $(this).parent().parent().siblings().eq(4).text().replace(/시간/gi, "");

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

		/*날짜 선택을 위한 데이트 피커 구현*/
		$('input[name="startForm"]').datepicker({
			dateFormat: 'yy-mm-dd'
		});
		$('input[name="endForm"]').datepicker({
			dateFormat: 'yy-mm-dd'
		});

		/*데이트 피커의 날짜 비교*/
		$('input[name="startForm"]').change(function () {
			compare();
		});

		$('input[name="endForm"]').change(function () {
			compare();
		});

		function compare() {
			if ($('input[name="endForm"]').val() != "") {
				s = $('input[name="startForm"]').val().replace(/-/gi, "");
				e = $('input[name="endForm"]').val().replace(/-/gi, "");
				if (Number(s) >= Number(e)) {
					alert("선택한 종료 날짜를 시작 날짜보다 크게 설정해주세요.");
					$('input[name="startForm"]').val("");
					$('input[name="endForm"]').val("");
				}
			}
		}

		/*숨김 처리*/
		$("#date").hide();
		$("#price").hide();
		$("#time").hide();
		$("#date1").hide();
		$("#price1").hide();
		$("#time1").hide();
		$("#toggle4").hide();
		$("#toggle6").hide();


		/*검색 이벤트 설정*/
		$('button[name="c_search"]').click(function () {
			c_page = 1;
			c_startidx = 1;
			c_startpage = 1;
			c_plus = 0;
			search();
			c_paging();
		});

		$('button[name="d_search"]').click(function () {
			d_page = 1;
			d_startidx = 1;
			d_startpage = 1;
			d_plus = 0;
			search();
			d_paging();
		});

		$('select[name="c_align"]').change(function () {
			c_page = 1;
			c_startidx = 1;
			c_startpage = 1;
			c_plus = 0;
			search();
			c_paging();
		});

		$('select[name="d_align"]').change(function () {
			d_page = 1;
			d_startidx = 1;
			d_startpage = 1;
			d_plus = 0;
			search();
			d_paging();
		});

		$('button[name="s_search"]').click(function () {
			p_page = 1;
			p_startidx = 1;
			p_startpage = 1;
			p_plus = 0;
			p_search();
			p_paging();
		});

		$('select[name="s_align1"]').change(function () {
			p_page = 1;
			p_startidx = 1;
			p_startpage = 1;
			p_plus = 0;
			p_search();
			p_paging();
		});

		$('select[name="s_align2"]').change(function () {
			p_page = 1;
			p_startidx = 1;
			p_startpage = 1;
			p_plus = 0;
			p_search();
			p_paging();
		});

		/*페이지 접근 시 좌측 목록바 보이게 설정*/
		$("#collapsePages").addClass("show");
		$("#arrow").removeClass("collapsed");
		search();


		/*토글 동작 시 디자인 및 값 변경*/
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

		if ($("#s_switch").is(':checked')) {
			$("#toggle1").hide();
			$("#toggle2").show();
			search();
		}

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

		$("#p_switch").change(function () {
			if ($("#p_switch").is(':checked') == false) {
				$("#toggle5").show();
				$("#toggle6").hide();
				$('div[pborder]').removeClass("border-left-info");
				$('div[pborder]').addClass("border-left-primary");
				$('button[pborder]').removeClass("btn-info");
				$('button[pborder]').addClass("btn-primary");
				p_search();
			} else {
				$("#toggle5").hide();
				$("#toggle6").show();
				$('div[pborder]').removeClass("border-left-primary");
				$('div[pborder]').addClass("border-left-info");
				$('button[pborder]').removeClass("btn-primary");
				$('button[pborder]').addClass("btn-info");
			}
		});

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

		/*리셋 버튼을 눌렀을 때 설정*/
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

		/*직접 입력을 선택했을 경우 input 표시처리 및 required 활성*/
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


		/*삭제 동작 구현*/
		$('button[name="d_delete"]').click(function () {
			if ($('input[name="d_chk"]').is(":checked")) {
				$('input[name="d_chk"]').each(function () {
					if ($(this).is(":checked")) {
						$.post("delete_C_D.do", {
							c_d: "discount",
							num: $(this).parent().siblings().eq(0).text()
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
							num: $(this).parent().siblings().eq(0).text()
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
				num: cnum
			}, function (data) {});
			$('#c_delete').modal("hide");
			search();
		});

		$('button[name="m_d_delete"]').click(function () {
			$.post("delete_C_D.do", {
				c_d: "discount",
				num: dnum
			}, function (data) {});
			$('#d_delete').modal("hide");
			search();
		});


		/*업데이트 동작 처리*/
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
				num: cnum,
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
				num: dnum,
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
					"c_d": "coupon",
					"c_startidx": c_startidx
				},
				function (data) { //콜백함수
					var htmlStr = "<input type=\"hidden\" name=\"coupon\" c_total=" + data[0]["TOTAL"] + ">";
					data.shift();
					$.each(data, function (key, val) {
						htmlStr += "<tr>";
						htmlStr += "<td><input type=\"checkbox\" name=\"c_chk\"></td>";
						htmlStr += "<td>" + val.CPNUM + "</td>";
						htmlStr += "<td>" + val.CPNAME + "</td>";
						htmlStr += "<td>" + val.USE_DATE + "일</td>";
						htmlStr += "<td>" + val.PURPOSE + "</td>";
						htmlStr += "<td>" + val.DISCOUNT.toString().replace(/\B(?=(\d{3})+(?!\d))/g, ",") + "원</td>";
						htmlStr +=
							"<td><span data-placement=\"top\" data-toggle=\"tooltip\" title=\"Edit\"><button class=\"btn btn-primary btn-circle btn-sm coupon\" data-title=\"Edit\" data-toggle=\"modal\" data-target=\"#c_edit\"><i class=\"fas fa-pen\"></i></button></span></td>";
						htmlStr +=
							"<td><span data-placement=\"top\" data-toggle=\"tooltip\" title=\"Delete\"><button class=\"btn btn-circle btn-danger btn-sm coupon\" data-title=\"Delete\" data-toggle=\"modal\" data-target=\"#c_delete\"><i class=\"fas fa-trash\"></i></button></span></td>"
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
					c_total = Number($('input[name="coupon"]').attr('c_total'));
					c_paging();
				});
		} else {
			$.getJSON("search_C_D.do", {
					"d_condition": $('select[name="d_condition"]').val(),
					"d_value": $('input:text[name="d_value"]').val(),
					"d_align": $('select[name="d_align"]').val(),
					"c_d": "discount",
					"d_startidx": d_startidx
				},
				function (data) { //콜백함수
					var htmlStr = "<input type=\"hidden\" name=\"discount\" d_total=" + data[0]["TOTAL"] + ">";
					data.shift();
					$.each(data, function (key, val) {
						htmlStr += "<tr>";
						htmlStr += "<td><input type=\"checkbox\" name=\"d_chk\"></td>";
						htmlStr += "<td>" + val.COM_NUM + "</td>";
						htmlStr += "<td>" + val.COMPANY + "</td>";
						htmlStr += "<td>" + val.PURPOSE + "</td>";
						htmlStr += "<td>" + val.USE_TIME.toString().replace(/\B(?=(\d{3})+(?!\d))/g, ",") + "시간</td>";
						htmlStr +=
							"<td><span data-placement=\"top\" data-toggle=\"tooltip\" title=\"Edit\"><button class=\"btn btn-info btn-circle btn-sm discount\" data-title=\"Edit\" data-toggle=\"modal\" data-target=\"#d_edit\" ><i class=\"fas fa-pen\"></i></button></span></td>";
						htmlStr +=
							"<td><span data-placement=\"top\" data-toggle=\"tooltip\" title=\"Delete\"><button class=\"btn btn-circle btn-danger btn-sm discount\" data-title=\"Delete\" data-toggle=\"modal\" data-target=\"#d_delete\" ><i class=\"fas fa-trash\"></i></button></span></td>"
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
					d_total = Number($('input[name="discount"]').attr('d_total'));
					d_paging();
				});
		}
	}

	function p_search() {
		if ($("#p_switch").is(':checked') == false) {
			$.getJSON("publish_sg_mt_proc.do", {
					"s_condition": $('select[name="s_condition"]').val(),
					"s_value": $('input:text[name="s_value"]').val(),
					"s_align1": $('select[name="s_align1"]').val(),
					"s_align2": $('select[name="s_align2"]').val(),
					"s_date": $('select[name="dateSearch"]').val(),
					"s_startForm": $('input:text[name="startForm"]').val(),
					"s_endForm": $('input:text[name="endForm"]').val(),
					"s_m": "single",
					"p_startidx": p_startidx
				},
				function (data) { //콜백함수
					var htmlStr = "<input type=\"hidden\" name=\"publish\" p_total=" + data[0]["TOTAL"] + ">";
					data.shift();
					$.each(data, function (key, val) {
						htmlStr += "<tr>";
						htmlStr += "<td><input type=\"checkbox\" name=\"s_chk\"></td>";
						htmlStr += "<td>" + val.name + "</td>";
						htmlStr += "<td>" + val.startdate + "</td>";
						htmlStr += "<td>" + val.stopdate + "</td>";
						htmlStr += "<td>" + val.cnum + "</td>";
						htmlStr += "<td>" + val.phone + "</td>";
						htmlStr += "<td>" + val.email + "</td>";
						htmlStr += "<td>" + val.pay.toString().replace(/\B(?=(\d{3})+(?!\d))/g, ",") + "원</td>";
					});
					htmlStr += "</tbody>";
					$("#s_area").html(htmlStr);
					$("#s_chk").click(function () {
						if ($("#s_chk").prop("checked")) {
							$('input[name="s_chk"]').prop("checked", true);
						} else {
							$('input[name="s_chk"]').prop("checked", false);
						}
					});
					p_total = Number($('input[name="publish"]').attr('p_total'));
					p_paging();
				});
		}
	}

	function c_paging() {
		var htmlStr = "";
		if (c_total < Number($('select[name="c_align"]').val())) {
			c_pagesub = 1
		} else {
			if (c_total % Number($('select[name="c_align"]').val()) == 0) {
				c_pagesub = parseInt(c_total / Number($('select[name="c_align"]').val()));
			} else {
				c_pagesub = parseInt(c_total / Number($('select[name="c_align"]').val()) + 1);
			}
		}

		if (c_pagesub > 10) {
			c_endpage = parseInt(((c_page + (10 - 1))) / 10) * 10;
			if (c_page >= 11) {
				htmlStr += "<button class=\"btn btn-primary mx-1\" onclick=c_previous()>이전</button>";
			}
			if (c_endpage >= c_pagesub) {
				c_endpage = c_pagesub;
				for (var i = c_startpage + c_plus; i <= c_endpage; i++) {
					if (i == c_page) {
						htmlStr += "<button class=\"btn mx-1\" onclick=c_page_click(" + i + "); disabled>" + i + "</button>";

					} else {
						htmlStr += "<button class=\"btn mx-1\" onclick=c_page_click(" + i + "); >" + i + "</button>";
					}
				}
			} else {
				for (var i = c_startpage + c_plus; i <= c_endpage; i++) {
					if (i == c_page) {
						htmlStr += "<button class=\"btn mx-1\" onclick=c_page_click(" + i + "); disabled>" + i + "</button>";

					} else {
						htmlStr += "<button class=\"btn mx-1\" onclick=c_page_click(" + i + "); >" + i + "</button>";
					}
				}
				htmlStr += "<button class=\"btn mx-1\" onclick=c_next();>다음</button>";
			}
		} else {
			for (var i = 1; i <= c_pagesub; i++) {
				if (i == c_page) {
					htmlStr += "<button class=\"btn mx-1\" onclick=c_page_click(" + i + "); disabled>" + i + "</button>";

				} else {
					htmlStr += "<button class=\"btn mx-1\" onclick=c_page_click(" + i + "); >" + i + "</button>";
				}
			}
		}
		$("#c_page").html(htmlStr);
	}

	function c_page_click(e) {
		if (c_page < Number(e)) {
			c_sub = Number(e) - c_page;
			c_startidx += Number($('select[name="c_align"]').val() * c_sub);
			search();

		} else if (c_page > Number(e)) {
			c_sub = c_page - Number(e);
			c_startidx -= Number($('select[name="c_align"]').val()) * c_sub;
			search();
		}
		c_page = Number(e);
	}

	function c_next() {
		c_plus += 10;
		c_page = c_endpage + 1;
		c_startidx = Number(c_endpage * $('select[name="c_align"]').val() + 1);
		search();
		c_paging();
	}

	function c_previous() {
		c_plus -= 10;
		c_page = c_startpage + c_plus;
		c_startidx = Number(c_startpage + c_plus * $('select[name="c_align"]').val());
		search();
		c_paging();
	}


	function d_paging() {
		var htmlStr = "";
		if (d_total < Number($('select[name="d_align"]').val())) {
			d_pagesub = 1
		} else {
			if (d_total % Number($('select[name="d_align"]').val()) == 0) {
				d_pagesub = parseInt(d_total / Number($('select[name="d_align"]').val()));
			} else {
				d_pagesub = parseInt(d_total / Number($('select[name="d_align"]').val()) + 1);
			}
		}
		if (d_pagesub > 10) {
			d_endpage = parseInt(((d_page + (10 - 1))) / 10) * 10;
			if (d_page >= 11) {
				htmlStr += "<button class=\"btn btn-info mx-1\" onclick=d_previous()>이전</button>";
			}
			if (d_endpage >= d_pagesub) {
				d_endpage = d_pagesub;
				for (var i = d_startpage + d_plus; i <= d_endpage; i++) {
					if (i == d_page) {
						htmlStr += "<button class=\"btn mx-1\" onclick=d_page_click(" + i + "); disabled>" + i + "</button>";

					} else {
						htmlStr += "<button class=\"btn mx-1\" onclick=d_page_click(" + i + "); >" + i + "</button>";
					}
				}
			} else {
				for (var i = d_startpage + d_plus; i <= d_endpage; i++) {
					if (i == d_page) {
						htmlStr += "<button class=\"btn mx-1\" onclick=d_page_click(" + i + "); disabled>" + i + "</button>";

					} else {
						htmlStr += "<button class=\"btn mx-1\" onclick=d_page_click(" + i + "); >" + i + "</button>";
					}
				}
				htmlStr += "<button class=\"btn btn-info mx-1\" onclick=d_next();>다음</button>";
			}
		} else {
			for (var i = 1; i <= d_pagesub; i++) {
				if (i == d_page) {
					htmlStr += "<button class=\"btn mx-1\" onclick=d_page_click(" + i + "); disabled>" + i + "</button>";

				} else {
					htmlStr += "<button class=\"btn mx-1\" onclick=d_page_click(" + i + "); >" + i + "</button>";
				}
			}
		}
		$("#d_page").html(htmlStr);
	}

	function d_page_click(e) {
		if (d_page < Number(e)) {
			d_sub = Number(e) - d_page;
			d_startidx += Number($('select[name="d_align"]').val() * d_sub);
			search();

		} else if (d_page > Number(e)) {
			d_sub = d_page - Number(e);
			d_startidx -= Number($('select[name="d_align"]').val()) * d_sub;
			search();
		}
		d_page = Number(e);
	}

	function d_next() {
		d_plus += 10;
		d_page = d_endpage + 1;
		d_startidx = Number(d_endpage * $('select[name="d_align"]').val() + 1);
		search();
		d_paging();
	}

	function d_previous() {
		d_plus -= 10;
		d_page = d_startpage + d_plus;
		d_startidx = Number(d_startpage + d_plus * $('select[name="d_align"]').val());
		search();
		d_paging();
	}


	function p_paging() {
		var htmlStr = "";
		if (p_total < Number($('select[name="s_align1"]').val())) {
			p_pagesub = 1
		} else {
			if (p_total % Number($('select[name="s_align1"]').val()) == 0) {
				p_pagesub = parseInt(p_total / Number($('select[name="s_align1"]').val()));
			} else {
				p_pagesub = parseInt(p_total / Number($('select[name="s_align1"]').val()) + 1);
			}
		}

		if (p_pagesub > 10) {
			p_endpage = parseInt(((p_page + (10 - 1))) / 10) * 10;
			if (p_page >= 11) {
				htmlStr += "<button class=\"btn btn-primary mx-1\" onclick=p_previous()>이전</button>";
			}
			if (p_endpage >= p_pagesub) {
				p_endpage = p_pagesub;
				for (var i = p_startpage + p_plus; i <= p_endpage; i++) {
					if (i == p_page) {
						htmlStr += "<button class=\"btn mx-1\" onclick=p_page_click(" + i + "); disabled>" + i + "</button>";

					} else {
						htmlStr += "<button class=\"btn mx-1\" onclick=p_page_click(" + i + "); >" + i + "</button>";
					}
				}
			} else {
				for (var i = p_startpage + p_plus; i <= p_endpage; i++) {
					if (i == p_page) {
						htmlStr += "<button class=\"btn mx-1\" onclick=p_page_click(" + i + "); disabled>" + i + "</button>";

					} else {
						htmlStr += "<button class=\"btn mx-1\" onclick=p_page_click(" + i + "); >" + i + "</button>";
					}
				}
				htmlStr += "<button class=\"btn btn-primary mx-1\" onclick=p_next();>다음</button>";
			}
		} else {
			for (var i = 1; i <= p_pagesub; i++) {
				if (i == p_page) {
					htmlStr += "<button class=\"btn mx-1\" onclick=p_page_click(" + i + "); disabled>" + i + "</button>";

				} else {
					htmlStr += "<button class=\"btn mx-1\" onclick=p_page_click(" + i + "); >" + i + "</button>";
				}
			}
		}
		$("#p_page").html(htmlStr);
	}

	function p_page_click(e) {
		if (p_page < Number(e)) {
			p_sub = Number(e) - p_page;
			p_startidx += Number($('select[name="s_align1"]').val() * p_sub);
			p_search();

		} else if (p_page > Number(e)) {
			p_sub = p_page - Number(e);
			p_startidx -= Number($('select[name="s_align1"]').val()) * p_sub;
			p_search();
		}
		p_page = Number(e);
	}

	function p_next() {
		p_plus += 10;
		p_page = p_endpage + 1;
		p_startidx = Number(p_endpage * $('select[name="s_align1"]').val() + 1);
		p_search();
		p_paging();
	}

	function p_previous() {
		p_plus -= 10;
		p_page = p_startpage + p_plus;
		p_startidx = Number(p_startpage + p_plus * $('select[name="s_align1"]').val());
		p_search();
		p_paging();
	}