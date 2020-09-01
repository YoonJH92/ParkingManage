<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<div class="modal fade" id="modalBox" tabindex="-1" role="dialog" aria-hidden="true">
	<div class="modal-dialog modal-lg" role="document">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
						aria-hidden="true">×</span></button>
			</div>
			<div class="modal-body">
				<div class="container">
					<form method="GET" action="addc_d.do">
						<h1 class="mb-3">쿠폰 할인권 생성</h1>
						<div class="card my-3">
							<span>쿠폰 할인권 선택</span>
							<span><input type="radio" name="a_c_d" value="a_coupon" checked />쿠폰</span>
							<span><input type="radio" name="a_c_d" value="a_discount" />할인권</span>
						</div>
						<div id="toggle3">
							<%@ include file="add_coupon.jsp"%>
						</div>
						<div id="toggle4">
							<%@ include file="add_discount.jsp"%>
						</div>
						<div class="modal-footer">
							<input class="btn -btn-lg btn-success" type="submit" value="생성" />
							<input class="btn -btn-lg btn-success" type="reset" value="초기화" />
						</div>
					</form>
				</div>
			</div>
		</div>
	</div>
</div>
<script>
	$(function () {
		$("#date").hide();
		$("#price").hide();
		$("#toggle4").hide();
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

		$('input[name="a_c_d"]').change(function () {
			if ($('input:radio[value="a_coupon"]').is(':checked')) {
				$("#toggle3").show();
				$("#toggle4").hide();
			} else {
				$("#toggle3").hide();
				$("#toggle4").show();
			}
		});

		$('input[type="reset"]').click(function () {
			$("#toggle3").show();
			$("#toggle4").hide();
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