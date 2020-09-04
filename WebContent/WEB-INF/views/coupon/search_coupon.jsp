<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<link href="//maxcdn.bootstrapcdn.com/font-awesome/4.2.0/css/font-awesome.min.css" rel="stylesheet">

<div class="container-fluid ">
  <div class="card shadow mb-4">
    <div class="card-header py-3">
      <div class="row my-2">
        <div class="col-xs-1">
          <select class="form-control animated--grow-in" name="c_condition">
            <option value="cpname" selected>쿠폰명</option>
            <option value="use_date">유효 기간</option>
            <option value="purpose">발급 목적</option>
            <option value="discount">할인 금액</option>
          </select>
        </div>
        <div class="col-xs-1 ml-2">
          <input class="form-control" type="text" name="c_value" />
        </div>
        <div class="col-xs-1 ml-2">
          <button class="btn btn-primary" name="c_search">검색</button>
        </div>

        <div class="col-xs-1 ml-1">
          <button class="btn btn-primary" name="c_delete">선택 삭제</button>
        </div>

        <div class="col-xs-1 ml-3">
          <select class="form-control animated--grow-in" name="c_align">
            <option value="10" selected>10개씩 보기</option>
            <option value="50">50개씩 보기</option>
            <option value="100">100개씩 보기</option>
          </select>
        </div>
        <button class="btn btn-primary ml-auto" modal>쿠폰 및 할인권 생성</button>
      </div>
    </div>
    <div class="card-body row">
      <div class="col-md-12">
        <div class="table-responsive">

          <table class="table table-bordred table-striped text-center">

            <thead>
              <th><input type="checkbox" id="c_chk" /></th>
              <th>순번</th>
              <th>쿠폰명</th>
              <th>유효 기간</th>
              <th>발급 목적</th>
              <th>할인 금액</th>
              <th>수정</th>
              <th>삭제</th>
            </thead>

            <tbody id="c_area">
            </tbody>
          </table>
        </div>
      </div>

    </div>
  </div>
</div>


<div class="modal fade" id="edit" tabindex="-1" role="dialog" aria-labelledby="edit" aria-hidden="true">
  <div class="modal-dialog">
    <div class="modal-content">
      <div class="modal-header">
        <h4 class="modal-title">수정</h4>
        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
            aria-hidden="true">×</span></button>
      </div>
      <div class="modal-body">
        <div class="form-group">
          <input class="form-control " type="text" placeholder="Mohsin">
        </div>
        <div class="form-group">
          <input class="form-control " type="text" placeholder="Irshad">
        </div>
        <div class="form-group">
          <textarea rows="2" class="form-control"
            placeholder="CB 106/107 Street # 11 Wah Cantt Islamabad Pakistan"></textarea>
        </div>
      </div>
      <div class="modal-footer ">
        <button type="button" class="btn btn-warning btn-lg" style="width: 100%;"><span
            class="glyphicon glyphicon-ok-sign"></span>완료</button>
      </div>
    </div>
    <!-- /.modal-content -->
  </div>
  <!-- /.modal-dialog -->
</div>



<div class="modal fade" id="delete" tabindex="-1" role="dialog" aria-labelledby="edit" aria-hidden="true">
  <div class="modal-dialog">
    <div class="modal-content">
      <div class="modal-header">
        <h4 class="modal-title">확인</h4>
      </div>
      <div class="modal-body">
        <div class="alert alert-danger"><span class="glyphicon glyphicon-warning-sign"></span> 정말 삭제하시겠습니까?</div>
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-success"><span class="glyphicon glyphicon-ok-sign"></span>&nbsp;&nbsp;&nbsp;예&nbsp;&nbsp;&nbsp;</button>
        <button type="button" class="btn btn-default" data-dismiss="modal"><span
            class="glyphicon glyphicon-remove"></span>아니오</button>
      </div>
    </div>
    <!-- /.modal-content -->
  </div>
  <!-- /.modal-dialog -->
</div>