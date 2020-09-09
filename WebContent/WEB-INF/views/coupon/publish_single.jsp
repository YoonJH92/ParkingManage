<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<table class="table table-bordered">
	<tr>
		<td><select style="width: 15%; display: inline-block; margin-left: 1%;"
			class="form-control animated--grow-in" name="date">
				<option value="name" selected>이름</option>
				<option value="7">전화번호</option>
				<option value="10">이메일</option>
				<option value="직접 입력">차량 번호</option>
		</select> 
		
		<input style="width: 59%; display: inline-block; margin-left: 1%;"
			class="form-control" type="text" name="name" required />
			<button class="btn btn-primary ml-2" name="s_search">검색</button> 
		
		<select
			class="form-control animated--grow-in" style="width: 15%; display: inline-block; margin-left: 1%;" name="s_align">
				<option value="10" selected>10개씩 보기</option>
				<option value="50">50개씩 보기</option>
				<option value="100">100개씩 보기</option>
		</select></td>
	</tr>

</table>

<div class="table-responsive">
	<table class="table table-bordred table-striped text-center">
		<thead>
			<th><input type="checkbox" id="s_chk" /></th>
			<th>회원 이름</th>
			<th>월정액 등록</th>
			<th>월정액 시작</th>
			<th>월정액 종료</th>
			<th>차량 번호</th>
			<th>전화번호</th>
			<th>이메일</th>
			<th>월 사용 요금</th>
		</thead>
		<tbody id="s_area">
		</tbody>
	</table>
</div>
