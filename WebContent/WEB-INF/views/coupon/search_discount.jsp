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
						<option value="use_time">할인 시간</option>
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
				<button class="btn btn-info ml-auto" add_modal>쿠폰 및 할인권 생성</button>
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
								<th>발급 목적</th>
								<th>할인 시간</th>
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
				<button type="button" class="btn btn-warning btn-lg" style="width: 100%;" name="m_d_update"><span
						class="glyphicon glyphicon-ok-sign"></span>완료</button>
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