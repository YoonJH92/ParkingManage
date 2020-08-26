<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>

<head>
	<meta charset="UTF-8">
	<title>쿠폰 생성</title>
</head>

<body>
	<!-- header -->
	<%@ include file="/WEB-INF/views/include/header.jsp"%>
	<main>
		<div class="container">
			<form method="POST" action="addc_d.do">
				<h1 class="mb-3">쿠폰 할인권 생성</h1>
				<div class="card my-3">
					<span>쿠폰 할인권 선택</span>
					<span><input id="coupon" type="radio" name="c_d" value="쿠폰" checked />쿠폰</span>
					<span><input id="discount" type="radio" name="c_d" value="할인권" />할인권</span>
				</div>
				<div id="toggle1">
					<%@ include file="coupon.jsp"%>
				</div>
				<div id="toggle2">
					<%@ include file="discount.jsp"%>
				</div>
				<input class="btn -btn-lg btn-success" type="submit" value="생성" />
				<input class="btn -btn-lg btn-success" type="reset" value="초기화" />
				<button class="btn -btn-lg btn-success">취소</button>
			</form>
		</div>
	</main>
	<!-- footer -->
	<%@ include file="/WEB-INF/views/include/footer.jsp"%>
</body>
<script>
	$(function () {
		$("#date").hide();
		$("#price").hide();
		$("#toggle2").hide();
		$("#time").hide();

		$("#show1").text("1,000원");
		$("#show2").text("1시간");

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

		$('input[name="price"]').on("focusout", function () {
			$("#show1").text($(this).val() + "원");
		});

		$('input[name="time"]').on("focusout", function () {
			$("#show2").text($(this).val() + "시간");
		});

		$('input[name="c_d"]').change(function () {
			if ($('input:radio[value="쿠폰"]').is(':checked')) {
				$("#toggle1").show();
				$("#toggle2").hide();
			} else {
				$("#toggle1").hide();
				$("#toggle2").show();
			}
		});

		$('input[type="reset"]').click(function () {
			$("#toggle1").show();
			$("#toggle2").hide();
		});

		$('select[name="date"]').change(function () {
			if ($('select[name="date"]').val() == "직접 입력") {
				$("#date").show();
			} else {
				$("#date").hide();
			}
		});

		$('select[name="price"]').change(function () {
			if ($('select[name="price"]').val() == "직접 입력") {
				$("#show1").text("");
				$("#price").show();
			} else {
				$("#show1").text($(this).val() + "원");
				$("#price").hide();
			}
		});

		$('select[name="time"]').change(function () {
			if ($('select[name="time"]').val() == "직접 입력") {
				$("#show2").text("");
				$("#time").show();
			} else {
				$("#show2").text($(this).val() + "시간");
				$("#time").hide();
			}
		});
	});
</script>

</html>