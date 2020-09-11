

<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="f"%>
<%@ include file="/WEB-INF/views/include/header.jsp"%>
<script src="https://use.fontawesome.com/releases/v5.2.0/js/all.js"></script>

<style>
body{
		background-color: #25274d;
	}
	.contact{
		padding: 4%;
		height: 400px;
	}
	.col-md-3{
		background: #606d9a;
		padding: 4%;
		border-top-left-radius: 0.5rem;
		border-bottom-left-radius: 0.5rem;
		color : #ffffff;
	}
	.contact-info{
		margin-top:10%;
	}
	.contact-info img{
		margin-bottom: 15%;
	}
	.contact-info h2{
		margin-bottom: 10%;
	}
	.col-md-9{
		background: #fff;
		padding: 3%;
		border-top-right-radius: 0.5rem;
		border-bottom-right-radius: 0.5rem;
	}
	.contact-form label{
		font-weight:600;
	}
	.contact-form button{
		background: #25274d;
		color: #fff;
		font-weight: 600;
		width: 25%;
	}
	.contact-form button:focus{
		box-shadow:none;
	}
</style>

<main>
	
	<div class="container contact">
	<div class="row">
		<div class="col-md-3">
			<div class="contact-info">
				<h2><i class="fas fa-user-cog"></i></h2>
				<h2>정보 수정</h2>
				<h4></h4>
			</div>
		</div>
		<div class="col-md-9">
			<div class="container" id="contain">
		<br><br>
		
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

		</div>
	</div>
	</div>

</main>
</div>
<%@ include file="/WEB-INF/views/include/footer.jsp"%>


