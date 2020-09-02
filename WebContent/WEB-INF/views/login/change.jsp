

<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="f"%>
<%@ include file="/WEB-INF/views/include/header.jsp"%>
<script src="https://use.fontawesome.com/releases/v5.2.0/js/all.js"></script>



<main>
	
	<div class="container" id="contain">
		<br><br>
		<h1><i class="fas fa-user-cog"></i>&nbsp;정보 변경</h1>
		<br> <br>
		<table class="table table-hover">
			<form action="change.do" method="post">
				<tbody>
					<tr>
						<td align="center" width="30%"><h4>ID</h4> </td>
						<td><h3>${id }</h3></td>
					</tr>
					<tr>
						<td align="center" width="30%"><h4>Name</h4></td>
						<td><input type="text" name="name"
						class="form-control" value="${name }"></td>
					</tr>
					<tr>
						<td align="center" width="30%"><h4>Email</h4></td>
						<td><input type="email" name="email"
						class="form-control" value="${email }"></td>
					</tr>
					<tr>
						<td align="center" width="30%"><h4>Tel</h4></td>
						<td><input type="tel" name="tel"
						class="form-control" value="${tel }"></td>
					</tr>
					<tr>
						<td align="center" width="30%"><h4>Password</h4></td>
						<td><input type="password"
						name="pass" class="form-control" placeholder="현재 비밀번호를 입력해 주세요"
						required></td>
					</tr>
					<tr>
						<td align="center" width="30%"><h4>New Password</h4></td>
						<td><input type="password"
						name="pass2" class="form-control" placeholder="새 비밀번호를 등록해 주세요"
						required></td>
					</tr>
					<tr>
						<td colspan="2">
							<div style="display: flex; justify-content: flex-end;">
					<input style="margin-right: 5px;" class="btn btn-primary btn-lg"
						type="submit" value="수정">
					<button type="button" class="btn btn-primary btn-lg"
						onclick="location.href = 'loglistac.do' ">취소</button>
				</div>
						</td>
					</tr>
				</tbody>

			</form>
		</table>
	</div>

</main>
</div>
<%@ include file="/WEB-INF/views/include/footer.jsp"%>


