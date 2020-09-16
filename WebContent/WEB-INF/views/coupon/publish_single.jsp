<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<table class="table table-bordered">
	<tr>
		<td>
		<span>기간 검색</span>
     	<select name="dateSearch" class="form-control" style="width: 10%; display: inline-block; margin-left: 1%">
       		<option value="SDATE">시작 기간</option>
       		<option value="EDATE">종료 기간</option>
       	</select>
      	<input type="text" class="form-control" name="startForm" style="width: 10%; display: inline-block; margin-left: 1%;" readonly  autocomplete="off">
      	~
      	<input type="text" class="form-control" name="endForm" style="width: 10%; display: inline-block;" readonly autocomplete="off">
		
		<select style="width: 10%; display: inline-block; margin-left: 2%;"
			class="form-control animated--grow-in" name="s_condition">
				<option value="name" selected>이름</option>
				<option value="phone">전화번호</option>
				<option value="email">이메일</option>
				<option value="cnum">차량 번호</option>
		</select> 
		
		<input style="width: 20%; display: inline-block; margin-left: 1%;"
			class="form-control" type="text" name="s_value"/>
			<button class="btn btn-primary ml-2" name="s_search">검색</button> 
		
		<select
			class="form-control animated--grow-in ml-4" style="width: 10%; display: inline-block;" name="s_align2">
				<option value="name" selected>이름순</option>
				<option value="sdate">시작 일자순</option>
				<option value="edate">종료 일자순</option>
		</select>
		
		<select
			class="form-control animated--grow-in ml-1" style="width: 10%; display: inline-block;" name="s_align1">
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
	<div class="my-1" style="text-align:center " id = "p_page">
	</div>
</div>
