<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/include/header1.jsp" %> 
<style>
.fr{float:right!important;}
.py10{padding: 10px 0;}
.al-center{align-items: center;}
</style>
<!-- Begin Page Content -->
<div class="container-fluid">

  <!-- Page Heading -->
  <h1 class="h3 mb-5 text-gray-800">월정액 회원관리</h1>

  <!-- DataTales Example -->
  <div class="card shadow mb-4">
    <div class="card-header py-3">
      <a href="#" class="d-none d-sm-inline-block btn btn-sm btn-primary shadow-sm fr" data-toggle="modal" data-target="#AddModal">
      	<i class="fas fa-user-plus fa-sm text-white-50"></i> 추가하기
      </a>
    </div>
    <div class="card-body">
      <div class="table-responsive">
        <table class="table table-bordered" id="dataTable" width="100%" cellspacing="0">
          <thead>
            <tr>
              <th>등록 순서</th>
              <th>회원 이름</th>
              <th>등록 기간</th>
              <th>시작 시간</th>
              <th>종료 시간</th>
              <th>사용 금액</th>
              <th>차량 번호</th>
            </tr>
          </thead>
          <tbody>
            <tr>
              <td>Tiger Nixon</td>
              <td>System Architect</td>
              <td>Edinburgh</td>
              <td>61</td>
              <td>2011/04/25</td>
              <td>$320,800</td>
              <td>$320,800</td>
            </tr>
            <tr>
              <td>Tiger Nixon</td>
              <td>System Architect</td>
              <td>Edinburgh</td>
              <td>61</td>
              <td>2011/04/25</td>
              <td>$320,800</td>
              <td>$320,800</td>
            </tr>
            <tr>
              <td>Tiger Nixon</td>
              <td>System Architect</td>
              <td>Edinburgh</td>
              <td>61</td>
              <td>2011/04/25</td>
              <td>$320,800</td>
              <td>$320,800</td>
            </tr>
            <tr>
              <td>Tiger Nixon</td>
              <td>System Architect</td>
              <td>Edinburgh</td>
              <td>61</td>
              <td>2011/04/25</td>
              <td>$320,800</td>
              <td>$320,800</td>
            </tr>

          </tbody>
        </table>
      </div>
    </div>
  </div>
</div>
<!-- /.container-fluid -->
</div>
<!-- End of Main Content -->

 <!-- Logout Modal-->
 <div class="modal" id="AddModal" tabindex="-1" role="dialog" aria-hidden="true">
 <form class="user" action="memberInsert.do" method="post">
   <div class="modal-dialog" role="document">
     <div class="modal-content">
       <div class="modal-header">
         <h5 class="modal-title">월정액 회원 추가</h5>
         <button class="close" type="button" data-dismiss="modal" aria-label="Close">
           <span aria-hidden="true">×</span>
         </button>
       </div>
       <div class="modal-body">
		
			<div class="row py10 al-center">
			  <div class="col-md-4" style="text-align: center;">회원 이름</div>
			  <div class="col-md-8"><input type="text" class="form-control" id="name" name="name"></div>
			</div>
			<div class="row py10 al-center">
			  <div class="col-md-4" style="text-align: center;">차량 번호</div>
			  <div class="col-md-8"><input type="text" class="form-control" id="CNUM" name="CNUM"></div>
			</div>
			<div class="row py10 al-center">
			  <div class="col-md-4" style="text-align: center;">시작 시간</div>
			  <div class="col-md-8"><input type="text" class="form-control" id="startDate" name="startDate"></div>
			</div>
			<div class="row py10 al-center">
			  <div class="col-md-4" style="text-align: center;">이메일</div>
			  <div class="col-md-8"><input type="text" class="form-control" id="email" name="email"></div>
			</div>
			<div class="row py10 al-center">
			  <div class="col-md-4" style="text-align: center;">Phone</div>
			  <div class="col-md-8"><input type="text" class="form-control" id="phone" name="phone"></div>
			</div>
			<div class="row py10 al-center">
			  <div class="col-md-4" style="text-align: center;">구 분</div>
			  <div class="col-md-8"><input type="text" class="form-control" id="type" name="type"></div>
			</div>
       </div>
       <div class="modal-footer">
         <input type="submit" value="확인" class="btn btn-primary">
         <button class="btn btn-secondary" type="button" data-dismiss="modal">취소</button>
       </div>
     </div>
   </div>
   </form>
 </div>
 <%@ include file="/WEB-INF/views/include/footer1.jsp" %> 
   <script type="text/javascript">
      $(function () {
          $('#startDate').datetimepicker();
      });
  </script> 	