


<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="f" %>
<%@ include file="/WEB-INF/views/include/header.jsp" %> 
 <script src="https://use.fontawesome.com/releases/v5.2.0/js/all.js"></script>

<main>
	<div class="container-fluid">
	<br><br>
		<h1><i class="fas fa-cog"></i>&nbsp;설정</h1>
		<br> <br>
		<table class="table table-hover">
			<form action="setting.do" method="post">
				<tbody>
					<tr>
						<td align="center" width="30%">주차개수</td>
						<td><input class="form-control" type="text" name="count"
							value="${sdto.getCount() }"></td>
					</tr>
					<tr>
						<td align="center" width="30%">기본시간</td>
						<td><input class="form-control" type="text" name="dtime"
							value="${sdto.getDtime() }"></td>
					</tr>
					<tr>
						<td align="center" width="30%">기본 요금</td>
						<td><input class="form-control" type="text" name="fare"
							value="${sdto.getFare() }"></td>
					</tr>
					<tr>
						<td align="center" width="30%">오버시 시간</td>
						<td><input class="form-control" type="text" name="otime"
							value="${sdto.getOtime() }"></td>
					</tr>
					<tr>
						<td align="center" width="30%">오버시 추가요금</td>
						<td><input class="form-control" type="text" name="ofare"
							value="${sdto.getOfare() }"></td>
					</tr>
					<tr>
						<td align="center" width="30%">월정액 요금</td>
						<td><input class="form-control" type="text" name="month_fare"
							value="${sdto.getMonth_fare() }"></td>
					</tr>
					<tr>
						<td colspan="2">
							<div style="display: flex; justify-content: flex-end;">
								<input style="margin-right: 5px;" class="btn btn-primary btn-lg"
									type="submit" value="변경">
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
 <%@ include file="/WEB-INF/views/include/footer.jsp" %> 

