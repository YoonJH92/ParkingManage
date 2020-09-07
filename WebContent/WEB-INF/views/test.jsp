<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>


<c:url var="action" value="/loglist.do"/>
<c:if test="${param.prev}">
    <a href="${action}?Row=${param.displayRow}&page=${param.beginPage-1}">prev</a>
</c:if>
<c:forEach begin="${param.beginPage}" end="${param.endPage}" step="1" var="index">
    <c:choose>
        <c:when test="${param.page==index}">
            ${index}
        </c:when>
        <c:otherwise>
        <a href="${action}?Row=${param.displayRow}&page=${index}">${index}</a>
        </c:otherwise>
    </c:choose>
</c:forEach>
<c:if test="${param.next}">
    <a href="${action}?Row=${param.displayRow}&page=${param.endPage+1}">next</a>
</c:if>
