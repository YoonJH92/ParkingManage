<%@page import="java.net.URLDecoder"%>
<%@page import="java.net.URLEncoder"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>


	<style>
	
	.page-item.active .page-link {
    z-index: 3;
    color: #fff;
    background-color: black;
    border-color: #4e73df;
}
	.page-link {
	color: black;
	}


	</style>

	<ul class="pagination justify-content-center">
	<c:url var="action" value="/logdetail.do"/>
	<c:if test="${param.prev}">
	
	
   <li class="page-item">  <a class="page-link" href="${action}?Search=&FDate=${param.FDate }&LDate=${param.LDate}&cnum=${param.cnum}&dRs=${param.displayRow}&page=${param.beginPage-1}">&laquo</a></li>
    </c:if>
      <c:if test="${param.page ne 1}">
      
         <li class="page-item">  <a class="page-link" href="${action}?Search=&FDate=${param.FDate }&LDate=${param.LDate}&cnum=${param.cnum}&dRs=${param.displayRow}&page=${param.page-1}">perv</a></li>
      
      
                    </c:if>
		<c:forEach begin="${param.beginPage}" end="${param.endPage}" step="1" var="index">
    <c:choose>
        <c:when test="${param.page==index}">
		 <li class="page-item active"><a class="page-link" id="page_"> ${index} </a></li>
        </c:when>
        <c:otherwise>
         <li class="page-item"><a class="page-link" href="${action}?Search=&FDate=${param.FDate}&LDate=${param.LDate}&cnum=${param.cnum}&dRs=${param.displayRow}&page=${index}">${index}</a></li>
        </c:otherwise>
    </c:choose>
</c:forEach>

	 <c:if test="${param.page ne param.endPage && param.endPage > 0}">
	    <li class="page-item">  <a class="page-link" href="${action}?Search=&FDate=${param.FDate}&LDate=${param.LDate}&cnum=${param.cnum}&dRs=${param.displayRow}&page=${param.page+1}">next</a></li>
                    </c:if>

<c:if test="${param.next}">
   <li class="page-item"><a class="page-link" href="${action}?Search=&FDate=${param.FDate}&LDate=${param.LDate}&cnum=${param.cnum}&dRs=${param.displayRow}&page=${param.endPage+1}">&raquo;
   
   </a></li>
</c:if>
</ul>
