<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/include/include-header.jspf" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %> 
<% pageContext.setAttribute("replaceChar","\n"); %>
<!DOCTYPE html>
<html lang="ko">
<head>

<meta charset="utf-8">
<title>QNA</title>

<body>
<div class="container">
<table class="board_view">
		<colgroup>
			<col width="13%"/>
			<col width="13%"/>
			<col width="13%"/>
			<col width="13%"/>
			<col width="12%"/>
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
            <c:if test="${boardDetail.BD_TYPE eq 'C' }">커뮤니티</c:if>
            <c:if test="${boardDetail.BD_TYPE eq 'N' }">공지사항</c:if>
            <c:if test="${boardDetail.BD_TYPE eq 'Q' }">FAQ</c:if>
            </td>
            <th scope="row">작성시간</th>
            <td>${boardDetail.BD_REGDATE }</td>
            <th scope="row">조회수</th>
            <td>${boardDetail.BD_COUNT }</td>
			</tr>
			<tr>
				<th scope="row">제목</th>
				<td colspan="5">${boardDetail.BD_TITLE}</td>
			</tr>
			<tr>
				<th scope="row">내용</th>
				<td colspan="6">${fn:replace(boardDetail.BD_CONTENT, replaceChar,"<br/>") }</td>
			</tr>
			
		</tbody>
	</table>
	
	
	<br/>
	
	  <div align="center" style="height:100px;"> 
		<input type="button" value="목록" class="btn btn-primary py-2 px-2" style="height:55px;" onClick="window.history.back()">
	
<%-- 		<input type="button" value="수정" class="btn btn-primary py-2 px-2" style="height:55px;" onClick="location.href='updateBoardForm.omc?BD_NUM=${boardDetail.BD_NUM}'">
 --%>    </div>
   </c:forEach>
</div>
</body>
</html>