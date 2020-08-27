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
				<h1 class="mb-3">쿠폰 할인권 생성</h1>
				<div class="card my-3">
					<div>쿠폰 할인권 선택</div>
					<div class="row">
						<div class="col-xs-3 ml-3 col-md-offset-4"><input id="coupon" type="radio" name="c_d" value="쿠폰"
								checked />쿠폰
						</div>
						<div class="col-xs-3 ml-3"><input id="discount" type="radio" name="c_d" value="할인권" />할인권
						</div>
					</div>
				</div>
		<div id="toggle1">
			<%@ include file="scoupon.jsp"%>
		</div>
		<div id="toggle2">
			<%@ include file="discount.jsp"%>
		</div>
		</div>
	</main>
	<!-- footer -->
	<%@ include file="/WEB-INF/views/include/footer.jsp"%>
</body>
<script>
	$(function () {
		$("#toggle2").hide();

		$('input[name="c_d"]').change(function () {
			if ($('input:radio[value="쿠폰"]').is(':checked')) {
				$("#toggle1").show();
				$("#toggle2").hide();
			} else {
				$("#toggle1").hide();
				$("#toggle2").show();
			}
		});
	});
</script>

</html>