<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<table class="table table-bordered">
	<tr>
		<td>쿠폰 이름</td>
		<td><input type="text" name="name" />
	</tr>
	<tr>
		<td>사용 기간</td>
		<td>발급일로부터 <select name="date">
				<option value="1" selected>1일</option>
				<option value="7">7일</option>
				<option value="10">10일</option>
				<option value="30">30일</option>
				<option value="100">100일</option>
				<option value="직접 입력">직접 입력</option>
			</select> <span id="date"><input type="text" name="date" numberOnly>일</span>
		</td>
	</tr>
	<tr>
		<td>사용 목적</td>
		<td><input type="text" name="cpurpose" /></td>
	</tr>
	<tr>
		<td>차감 금액</td>
		<td><span id="show1"></span> <select name="price">
				<option value="1,000" selected>1,000원</option>
				<option value="3,000">3,000원</option>
				<option value="5,000">5,000원</option>
				<option value="10,000">10,000원</option>
				<option value="30,000">30,000원</option>
				<option value="직접 입력">직접 입력</option>
			</select> <span id="price"><input type="text" name="price" numberOnly>원</span>
		</td>
	</tr>
</table>