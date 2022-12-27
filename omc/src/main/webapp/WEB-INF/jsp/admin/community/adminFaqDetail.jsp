<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/include/include-header.jspf" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %> 
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<% pageContext.setAttribute("replaceChar","\n"); %>
<!DOCTYPE html>
<html lang="ko">
<head>

<meta charset="utf-8">
<title>FAQ</title>

</head>
<body>
<div class="container">
<table class="board_view">
		<colgroup>
			<col width="12%"/>
			<col width="13%"/>
			<col width="12%"/>
			<col width="13%"/>
			<col width="12%"/>
			<col width="13%"/>
			<col width="12%"/>
			<col width="13%"/>
			
			<col width="25%"/>
			<col width="40%"/>
		</colgroup>
		
		<tbody>
			<c:forEach var="boardDetail" items="${boardDetail}">
			 <tr>
            <th scope="row">글 번호</th>
            <td>${boardDetail.BD_NUM }</td>
            <th scope="row">카테고리</th>
            <td>
            <c:if test="${boardDetail.BD_TYPE eq 'F' }">FAQ</c:if>
            <c:if test="${boardDetail.BD_TYPE eq 'C' }">커뮤니티</c:if>
            <c:if test="${boardDetail.BD_TYPE eq 'N' }">공지사항</c:if>
            <c:if test="${boardDetail.BD_TYPE eq 'Q' }">Q&A</c:if>
            </td>
            <th scope="row">작성시간</th>
             <td ${boardDetail.BD_REGDATE }>
          	<fmt:formatDate value="${boardDetail.BD_REGDATE}" pattern="yyyy-MM-dd"/>   
            </td>
            <th scope="row">조회수</th>
            <td>${boardDetail.BD_COUNT }</td>
			</tr>
			<tr>
				<th scope="row">제목</th>
				<td colspan="8" style="font-size:25px">${boardDetail.BD_TITLE}</td>
			</tr>
			<tr height="200">
				<th scope="row">내용</th>
				<td colspan="6">${fn:replace(boardDetail.BD_CONTENT, replaceChar,"<br/>") }</td>
			</tr>
			
		
	</table>
	
	<%-- <div class="col-md-12"><hr>
		    <b>댓글</b><br><br>
		    	<c:if test="${comListCount!=0}">
		    	
					<c:forEach var="comment" items="${comList}" varStatus="status">
					    <ul class="comment-list">
							<li class="comment">
								<div class="vcard bio">
                    				<h5 style="color:#82ae46;">OMC</h5><!-- COMMENTWRITER로 하면 ADMIN으로 떠서 Jumo로 입력 -->
                  				</div>
								<div class="comment-body">
									<div class="meta">${comment.BC_REGDATE}</div><!-- 답변 작성 날짜 -->
									<p style="font-size:middle;">
									 	${comment.BC_COMMENT}
									 	<input type="hidden" id="BC_BCID" name="BC_BCID" value="${comment.BC_BCID }">
										<input type="hidden" id="BC_NUM" name="BC_NUM" value="${comment.BC_NUM }">
									 </p>				
								</div>
						
						     </li>
						</ul>
					</c:forEach>
					
				</c:if>
				
			</div> --%>
	
	<br/>
	
	  <div align="center" style="height:100px;"> 
		<input type="button" value="이전" class="btn btn-primary py-2 px-2" style="height:55px;" onClick="window.history.back()">	
		<input type="button" value="수정" class="btn btn-primary py-2 px-2" style="height:55px;" onClick="location.href='adminUpdateFaqForm.omc?BD_NUM=${boardDetail.BD_NUM}'">
		<input type="button" value="삭제" class="btn btn-primary py-2 px-2" style="height:55px;" onClick="location.href='adminFaqDelete.omc?BD_NUM=${boardDetail.BD_NUM}'">
    </div>
    
   </c:forEach>
   </tbody>
</div>
</body>
<!-- <script>
function deleteCheck() {
	var BD_NUM = document.getElementById('BD_NUM').value;
	if(confirm("삭제하시겠습니까?") == true) {
		location.href="adminFaqDelete.omc?BD_NUM=" + BD_NUM;
	}
}
</script> -->


</html>