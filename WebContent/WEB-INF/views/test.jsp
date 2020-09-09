<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>


<c:url var="action" value="/loglist.do"/>

<style>

.pagination {
  list-style: none;
  display: inline-block;
  padding: 0;
  margin-top: 10px;
}
.pagination li {
  display: inline;
  text-align: center;
}
.pagination a {
  float: left;
  display: block;
  font-size: 14px;
  text-decoration: none;
  padding: 5px 12px;
  color: #fff;
  margin-left: -1px;
  border: 1px solid transparent;
  line-height: 1.5;
}
 .pagination a.active {
  cursor: default;
}
.pagination a:active {
  outline: none;
}

.modal-5 {
  position: relative;
}
.modal-5:after {
  content: '';
  position: absolute;
  width: 100%;
  height: 35px;
  left: 0;
  bottom: 0;
  z-index: -1;
  background-image: -moz-linear-gradient(left, rgba(0, 0, 0, 0) 0%, rgba(0, 0, 0, 0.65) 40%, rgba(0, 0, 0, 0.65) 50%, rgba(0, 0, 0, 0.65) 60%, rgba(0, 0, 0, 0) 100%);
  background-image: -webkit-linear-gradient(left, rgba(0, 0, 0, 0) 0%, rgba(0, 0, 0, 0.65) 40%, rgba(0, 0, 0, 0.65) 50%, rgba(0, 0, 0, 0.65) 60%, rgba(0, 0, 0, 0) 100%);
  background-image: linear-gradient(to right, rgba(0, 0, 0, 0) 0%, rgba(0, 0, 0, 0.65) 40%, rgba(0, 0, 0, 0.65) 50%, rgba(0, 0, 0, 0.65) 60%, rgba(0, 0, 0, 0) 100%);
}
.modal-5 a {
  color: #666;
  padding: 13px 5px 5px;
  margin: 0 10px;
  position: relative;
}
.modal-5 a:hover {
  color: #fff;
}
.modal-5 a:hover:after {
  content: '';
  position: absolute;
  width: 24px;
  height: 24px;
  background: #1E7EE2;
  -moz-border-radius: 100%;
  -webkit-border-radius: 100%;
  border-radius: 100%;
  z-index: -1;
  left: -3px;
  bottom: 4px;
  margin: auto;
}
.modal-5 a.next, .modal-5 a.prev {
  color: #1E7EE2;
}
.modal-5 a.next:hover, .modal-5 a.prev:hover {
  color: #fff;
}
.modal-5 a.next:hover:after, .modal-5 a.prev:hover:after {
  display: none;
}
.modal-5 a.active {
  background: #1E7EE2;
  color: #fff;
}
.modal-5 a.active:before {
  content: '';
  position: absolute;
  top: -11px;
  left: -10px;
  width: 18px;
  border: 10px solid transparent;
  border-bottom: 7px solid #104477;
  z-index: -1;
}
.modal-5 a.active:hover:after {
  display: none;
}




</style>
      
	<div class="pagination modal-5">
<c:if test="${param.prev}">
  <a class="prev" href="${action}?dRs=${param.displayRow}&page=${param.beginPage-1}"> prev </a>
   </c:if>
<c:forEach begin="${param.beginPage}" end="${param.endPage}" step="1" var="index">
    <c:choose>
        <c:when test="${param.page==index}"> <a> ${index}  </a> </c:when>
        <c:otherwise> 
       <a href="${action}?dRs=${param.displayRow}&page=${index}">  ${index} </a>
        </c:otherwise>
    </c:choose>
</c:forEach>
<c:if test="${param.next}">
    <a class="next" href="${action}?dRs=${param.displayRow}&page=${param.endPage+1}">next</a>
</c:if>
</div>

<script>

$('.pagination-inner a').on('click', function() {
	$(this).siblings().removeClass('pagination-active');
	$(this).addClass('pagination-active');
})
</script>