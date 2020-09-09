<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<style>
  .p_switchToggle input[type=checkbox] {
    height: 0;
    width: 0;
    visibility: hidden;
    position: absolute;
  }

  .p_switchToggle label {
    cursor: pointer;
    text-indent: -9999px;
    width: 70px;
    max-width: 70px;
    height: 30px;
    background: #4e73df;
    display: block;
    border-radius: 100px;
    position: relative;
  }

  .p_switchToggle label:after {
    content: '';
    position: absolute;
    top: 2px;
    left: 2px;
    width: 26px;
    height: 26px;
    background: #fff;
    border-radius: 90px;
    transition: 0.3s;
  }

  .p_switchToggle input:checked+label,
  .p_switchToggle input:checked+input+label {
    background: #36b9cc;
  }

  .p_switchToggle input+label:before,
  .p_switchToggle input+input+label:before {
    content: '단일';
    position: absolute;
    top: 3px;
    left: 30px;
    width: 50px;
    height: 50px;
    border-radius: 90px;
    transition: 0.3s;
    text-indent: 0;
    color: #fff;
  }

  .p_switchToggle input:checked+label:before,
  .p_switchToggle input:checked+input+label:before {
    content: '대량';
    position: absolute;
    top: 3px;
    left: 10px;
    width: 50px;
    height: 50px;
    border-radius: 90px;
    transition: 0.3s;
    text-indent: 0;
    color: #fff;
  }

  .p_switchToggle input:checked+label:after,
  .p_switchToggle input:checked+input+label:after {
    left: calc(100% - 2px);
    transform: translateX(-100%);
  }

  .p_switchToggle label:active:after {
    width: 60px;
  }

  .p_toggle-switchArea {
    margin: 10px 0 10px 0;
  }
</style>	
	
<div class="modal fade" id="publish_modalBox" tabindex="-1" role="dialog" aria-hidden="true">
	<div class="modal-dialog modal-lg" role="document" style="max-width: 70%; width: auto; ">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
						aria-hidden="true">×</span></button>
			</div>
			<div class="modal-body">
				<div class="container-fluid mb-3">
					<div class="card border-left-primary shadow h-100 py-2" pborder>
						<div class="card-body">
							<form method="POST" action="publish_c_d.do">
								<h2 class="mb-3">쿠폰 발급</h2>
								<span class="p_switchToggle">
									<input type="checkbox" id="p_switch" />
									<label class="ml-auto" for="p_switch">Toggle</label>
									<input type="hidden" name="p_c_d" value="p_coupon" />
								</span>
								<div id="toggle5">
									<%@ include file="publish_single.jsp"%>
								</div>
								<div id="toggle6">
									<%@ include file="publish_multi.jsp"%>
								</div>
								<div class="modal-footer" style="border-top: 0px;">
									<input class="btn -btn-lg btn-primary" type="submit" value="생성" pborder />
									<input class="btn -btn-lg btn-primary" type="reset" value="초기화" pborder />
								</div>
							</form>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>