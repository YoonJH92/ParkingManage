<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/include/header.jsp" %> 
		<div class="container">
				<h1 class="mb-3">쿠폰 할인권 조회</h1>
				<div class="card my-3">
					<div>쿠폰 할인권 선택</div>
					<div class="row">
						<div class="col-xs-3 ml-3 col-md-offset-4"><input id="coupon" type="radio" name="c_d" value="coupon"
								checked />쿠폰
						</div>
						<div class="col-xs-3 ml-3"><input id="discount" type="radio" name="c_d" value="discount" />할인권
						</div>
					</div>
				</div>
			<div id="toggle1">
				<%@ include file="search_coupon.jsp"%>
			</div>
			<div id="toggle2">
				<%@ include file="search_discount.jsp"%>
			</div>
		</div>
<script>
	$(function () {
		$("#toggle2").hide();

		$('input[name="c_d"]').change(function () {
			if ($('input:radio[value="coupon"]').is(':checked')) {
				$("#toggle1").show();
				$("#toggle2").hide();
			} else{
				$("#toggle1").hide();
				$("#toggle2").show();
			}
		});
	});
</script>
 <%@ include file="/WEB-INF/views/include/footer.jsp" %>
