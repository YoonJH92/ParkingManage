<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/include/header.jsp" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="f" %>

<style>
  .switchToggle input[type=checkbox] {
    height: 0;
    width: 0;
    visibility: hidden;
    position: absolute;
  }

  .switchToggle label {
    cursor: pointer;
    text-indent: -9999px;
    width: 70px;
    max-width: 70px;
    height: 30px;
    background: #4e73df;
    display: block;
    border-radius: 100px;
    position: relative;
  }

  .switchToggle label:after {
    content: '';
    position: absolute;
    top: 2px;
    left: 2px;
    width: 26px;
    height: 26px;
    background: #fff;
    border-radius: 90px;
    transition: 0.3s;
  }

  .switchToggle input:checked+label,
  .switchToggle input:checked+input+label {
    background: #36b9cc;
  }

  .switchToggle input+label:before,
  .switchToggle input+input+label:before {
    content: '쿠폰';
    position: absolute;
    top: 3px;
    left: 30px;
    width: 50px;
    height: 50px;
    border-radius: 90px;
    transition: 0.3s;
    text-indent: 0;
    color: #fff;
  }

  .switchToggle input:checked+label:before,
  .switchToggle input:checked+input+label:before {
    content: '할인';
    position: absolute;
    top: 3px;
    left: 10px;
    width: 50px;
    height: 50px;
    border-radius: 90px;
    transition: 0.3s;
    text-indent: 0;
    color: #fff;
  }

  .switchToggle input:checked+label:after,
  .switchToggle input:checked+input+label:after {
    left: calc(100% - 2px);
    transform: translateX(-100%);
  }

  .switchToggle label:active:after {
    width: 60px;
  }

  .toggle-switchArea {
    margin: 10px 0 10px 0;
  }
</style>

<div class="container-fluid mb-3">
  <div class="card border-left-primary shadow h-100 py-2" border>
    <div class="card-body">
      <h1 class="mb-3">쿠폰 할인권 조회</h1>
      <c:choose>
        <c:when test="${c_d=='a_discount'}">
            <span class="switchToggle">
              <input type="checkbox" id="s_switch" checked>
              <label class="ml-auto" for="s_switch">Toggle</label>
            </span>
            <%session.removeAttribute("c_d"); %>
        </c:when>
        <c:otherwise>
          <span class="switchToggle">
            <input type="checkbox" id="s_switch">
            <label class="ml-auto" for="s_switch">Toggle</label>
          </span>
        </c:otherwise>
      </c:choose>
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

    if ($("#s_switch").is(':checked')) {
      $("#toggle1").hide();
      $("#toggle2").show();
      search();
    }
    
    $("#s_switch").change(function () {
      if ($("#s_switch").is(':checked') == false) {
        $("#toggle1").show();
        $("#toggle2").hide();
        $('div[border]').removeClass("border-left-info");
        $('div[border]').addClass("border-left-primary");
        search();
      } else {
        $("#toggle1").hide();
        $("#toggle2").show();
        $('div[border]').removeClass("border-left-primary");
        $('div[border]').addClass("border-left-info");
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