<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<table class="table table-bordered">
	<tr>
		<td style="width:30%; text-align: center;">쿠폰 이름</td>
		<td><input class="form-control" type="text" name="name" />
	</tr>
	<tr>
		<td style="width:30%; text-align: center;">사용 기간</td>
		<td>발급일로부터<select style="width: 30%; display:inline-block; margin-left: 3%;"
				class="form-control animated--grow-in" name="date">
				<option value="1" selected>1일</option>
				<option value="7">7일</option>
				<option value="10">10일</option>
				<option value="30">30일</option>
				<option value="100">100일</option>
				<option value="직접 입력">직접 입력</option>
			</select> <span id="date"><input style="width: 40%; display:inline-block;" class="form-control" type="text"
					name="date" numberOnly>일</span>
		</td>
	</tr>
	<tr>
		<td style="width:30%; text-align: center;">사용 목적</td>
		<td><input class="form-control size" type="text" name="cpurpose" /></td>
	</tr>
	<tr>
		<td style="width:30%; text-align: center;">차감 금액</td>
		<td><select style="width: 30%; display:inline-block;" class="form-control animated--grow-in" name="price">
				<option value="1,000" selected>1,000원</option>
				<option value="3,000">3,000원</option>
				<option value="5,000">5,000원</option>
				<option value="10,000">10,000원</option>
				<option value="30,000">30,000원</option>
				<option value="직접 입력">직접 입력</option>
			</select> <span id="price"><input style="width: 65%; display:inline-block;" class="form-control" type="text"
					name="price" numberOnly>원</span>
		</td>
	</tr>
</table>