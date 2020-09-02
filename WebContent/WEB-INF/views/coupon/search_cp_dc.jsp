<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/include/header.jsp" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="f" %>

<div class="container-fluid mb-3">
  <div class="card border-left-primary shadow h-100 py-2">
    <div class="card-body">
      <h1 class="mb-3">쿠폰 할인권 조회</h1>
      <c:choose>
        <c:when test="${c_d=='a_discount'}">
          <div>쿠폰 할인권 선택</div>
          <div class="row">
            <div class="col-xs-3 ml-3 col-md-offset-4"><input type="radio" name="s_c_d" value="s_coupon" />쿠폰
            </div>
            <div class="col-xs-3 ml-3"><input id="discount" type="radio" name="s_c_d" value="s_discount" checked />할인권
            </div>
            <%session.removeAttribute("c_d"); %>
          </div>
        </c:when>
        <c:otherwise>
          <h4>쿠폰 할인권 선택</h4>
          <div class="row">
            <div class="col-xs-3 ml-3 col-md-offset-4"><input type="radio" name="s_c_d" value="s_coupon" checked />쿠폰
            </div>
            <div class="col-xs-3 ml-3"><input id="discount" type="radio" name="s_c_d" value="s_discount" />할인권
            </div>
          </div>
        </c:otherwise>
      </c:choose>
      <button class="btn btn-primary" modal>생성</button>
    </div>

  </div>
</div>

<div id="toggle1">
  <%@ include file="search_coupon.jsp"%>
</div>
<div id="toggle2" style="display: none;">
  <%@ include file="search_discount.jsp"%>
</div>
<script>
  $(function () {
    search();

    if ($('input:radio[value="s_discount"]').is(':checked')) {
      $("#toggle1").hide();
      $("#toggle2").show();
      search();
    }
    $('input[name="s_c_d"]').change(function () {
      if ($('input:radio[value="s_coupon"]').is(':checked')) {
        $("#toggle1").show();
        $("#toggle2").hide();
        search();
      } else {
        $("#toggle1").hide();
        $("#toggle2").show();
        search();
      }
    });
    $('button[modal]').on('click', function () {
      $('#modalBox').modal('show');
    });
  });
</script>
<%@ include file="new_cp_dc.jsp"%>

</div>
<%@ include file="/WEB-INF/views/include/footer.jsp" %>