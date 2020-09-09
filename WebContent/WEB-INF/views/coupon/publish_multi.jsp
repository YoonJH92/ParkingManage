<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	<table class="table table-bordered">
		<tr>
			<td style="width:30%; text-align: center;">발급처</td>
			<td><input class="form-control" type="text" name="company" /></td>
		</tr>
		<tr>
			<td style="width:30%; text-align: center;">발급 목적</td>
			<td><input class="form-control" type="text" name="dpurpose" /></td>
		</tr>
		<tr>
			<td style="width:30%; text-align: center;">할인 시간</td>
			<td><select style="width: 30%; display:inline-block;" class="form-control animated--grow-in" name="time">
					<option value="1" selected>1시간</option>
					<option value="3">3시간</option>
					<option value="6">6시간</option>
					<option value="12">12시간</option>
					<option value="24">24시간</option>
					<option value="직접 입력">직접 입력</option>
			</select> <span id="time"><input style="width: 61%; display:inline-block;" class="form-control" type="text" name="time" numberOnly>시간</span>
			</td>
		</tr>
	</table>