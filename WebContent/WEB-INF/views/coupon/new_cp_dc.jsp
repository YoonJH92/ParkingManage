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
				<div class="container-fluid mb-3">
					<div class="card border-left-primary shadow h-100 py-2" mborder>
						<div class="card-body">
							<form method="GET" action="addc_d.do">
								<h2 class="mb-3">쿠폰 할인권 생성</h2>
								<span class="switchToggle">
									<input type="checkbox" id="a_switch" />
									<label class="ml-auto" for="a_switch">Toggle</label>
									<input type="hidden" name="a_c_d" value="a_coupon" />
								</span>
								<div id="toggle3">
									<%@ include file="add_coupon.jsp"%>
								</div>
								<div id="toggle4">
									<%@ include file="add_discount.jsp"%>
								</div>
								<div class="modal-footer" style="border-top: 0px;">
									<input class="btn -btn-lg btn-primary" type="submit" value="생성" mborder />
									<input class="btn -btn-lg btn-primary" type="reset" value="초기화" mborder />
								</div>
							</form>
						</div>
					</div>
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
			} else {
				$("#toggle3").hide();
				$("#toggle4").show();
				$('input[name="a_c_d"]').val("a_discount");
				$('div[mborder]').removeClass("border-left-primary");
				$('div[mborder]').addClass("border-left-info");
				$('input[mborder]').removeClass("btn-primary");
				$('input[mborder]').addClass("btn-info");
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
				$("#price").show();
			} else {
				$("#price").hide();
			}
		});

		$('select[name="time"]').change(function () {
			if ($('select[name="time"]').val() == "직접 입력") {
				$("#time").show();
			} else {
				$("#time").hide();
			}
		});
	});
</script>