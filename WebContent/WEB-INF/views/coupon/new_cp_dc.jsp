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
							<form method="POST" action="addc_d.do">
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