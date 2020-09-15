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
                 <div>
                    <c:if test="${pagination.curRange ne 1 }">
                        <a href="#" onClick="fn_paging(1)">[처음]</a> 
                    </c:if>
                    <c:if test="${pagination.curPage ne 1}">
                        <a href="#" onClick="fn_paging('${pagination.prevPage }')">[이전]</a> 
                    </c:if>
                    <c:forEach var="pageNum" begin="${pagination.startPage }" end="${pagination.endPage }">
                        <c:choose>
                            <c:when test="${pageNum eq  pagination.curPage}">
                                <span style="font-weight: bold;"><a href="#" onClick="fn_paging('${pageNum }')">${pageNum }</a></span> 
                            </c:when>
                            <c:otherwise>
                                <a href="#" onClick="fn_paging('${pageNum }')">${pageNum }</a> 
                            </c:otherwise>
                        </c:choose>
                    </c:forEach>
                    <c:if test="${pagination.curPage ne pagination.pageCnt && pagination.pageCnt > 0}">
                        <a href="#" onClick="fn_paging('${pagination.nextPage }')">[다음]</a> 
                    </c:if>
                    <c:if test="${pagination.curRange ne pagination.rangeCnt && pagination.rangeCnt > 0}">
                        <a href="#" onClick="fn_paging('${pagination.pageCnt }')">[끝]</a> 
                    </c:if>
                </div>
                
                <div>
                    총 게시글 수 : ${pagination.listCnt } /    총 페이지 수 : ${pagination.pageCnt } / 현재 페이지 : ${pagination.curPage } / 현재 블럭 : ${pagination.curRange } / 총 블럭 수 : ${pagination.rangeCnt }
                </div>      
      
      
      
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