



<!-- header -->

<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ include file="/WEB-INF/views/include/header2.jsp" %> 
<!-- footer -->
<%@ include file="/WEB-INF/views/include/footer2.jsp" %>



<style>

#main{
padding-top: 20px;
padding-bottom: 20px;
}
</style>

  <!-- 본문 -->

 <div class="container"  id="main">
      <h2>기본테이블</h2>
      <h3><code>.table</code></h3>
      <table class="table table-hover">
        <thead class="thead-dark">
          <tr>
            <th>성</th>
            <th>이름</th>
            <th>이메일</th>
          </tr>
        </thead>
        <tbody>
          <tr>
            <td>존</td>
            <td>도우</td>
            <td>john@naver.com</td>
          </tr>
          <tr>
            <td>메리</td>
            <td>모어</td>
            <td>merry@naver.com</td>
          </tr>
          <tr class="bg-info">
            <td>줄리</td>
            <td>둘리</td>
            <td>july@naver.com</td>
          </tr>
        </tbody>
      </table>
    </div>
 