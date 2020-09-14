<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>


<c:url var="action" value="/loglist.do"/>

<style>

	.pagination {
	margin-bottom: 20px;
	display:block;
	text-align: center;
}
		
	.modal-3 a {
  	margin-left: 0.5rem;
	width: 28px;
  	height: 28px;
  	text-decoration:none;
   	color: black;
   	padding: 0.3rem;
	

}
	.modal-3 a:hover {
	border: 1px solid #e4e4e4;
   color: black;
 
}
	.modal-3 a.active, .modal-3 a:active {
	border: 1px solid #e4e4e4;
  	color: black;
}

</style>
      
<div class="pagination modal-3 ">
<c:if test="${param.prev}">
  <a class="prev" href="${action}?dRs=${param.displayRow}&page=${param.beginPage-1}"> &laquo </a>
   </c:if>
<c:forEach begin="${param.beginPage}" end="${param.endPage}" step="1" var="index">
    <c:choose>
        <c:when test="${param.page==index}"> <a class="active"> ${index}  </a> </c:when>
        <c:otherwise> 
       <a  href="${action}?dRs=${param.displayRow}&page=${index}">  ${index} </a>
        </c:otherwise>
    </c:choose>
</c:forEach>
<c:if test="${param.next}">
    <a class="next" href="${action}?dRs=${param.displayRow}&page=${param.endPage+1}"> &raquo;</a>
</c:if>
</div>

<script>

$('.pagination-inner a').on('click', function() {
	$(this).siblings().removeClass('pagination-active');
	$(this).addClass('pagination-active');
})
</script>