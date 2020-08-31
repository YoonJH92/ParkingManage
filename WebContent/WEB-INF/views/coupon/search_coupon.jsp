<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<div class="row my-2">
	<div class="col-xs-1 ml-5">
		<select name="c_condition">
			<option value="cpname" selected>쿠폰명</option>
			<option value="use_date">유효 기간</option>
			<option value="purpose">발급 목적</option>
			<option value="discount">할인 금액</option>
		</select>
	</div>
	<div class="col-xs-1 ml-5">
		<input type="text" name="c_value" />
	</div>
	<div class="col-xs-1 ml-3">
		<button class="btn-primary" name="c_search">검색</button>
	</div>

	<div class="col-xs-1 ml-3">
		<select name="c_align">
			<option value="10" selected>10개씩 보기</option>
			<option value="50">50개씩 보기</option>
			<option value="100">100개씩 보기</option>
		</select>
	</div>
</div>

<div class="my-3">
	<table class="table table-bordered" id="c_area">
		<tr>
			<th><input type="checkbox" id="c_chk" />전체선택</th>
			<th>순번</th>
			<th>쿠폰명</th>
			<th>유효 기간</th>
			<th>발급 목적</th>
			<th>할인 금액</th>
		</tr>
	</table>
</div>