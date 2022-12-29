<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ include file="/WEB-INF/include/include-header.jspf"%>
<!DOCTYPE html>
<html>
<head>

<meta charset="UTF-8">
<title>오메추 커뮤니티</title>

</head>

<script type="text/javascript">
	function fn_search(pageNo) {
		var comSubmit = new ComSubmit();
		comSubmit.setUrl("<c:url value='/noticeList.omc'/>");
		comSubmit.addParam("currentPageNo", pageNo);
		comSubmit.submit();
	}
	
	//로그인후 글쓰기
	function fn_writeForm(){
		var comSubmit = new ComSubmit();
		comSubmit.setUrl("/boardWrite.omc");
		if(${MEM_ID ne null}){
			comSubmit.submit();
		} else {
		      alert("로그인 후 이용해주세요.");
		      location.href = "/loginForm.omc";
		   }
	}
</script>
<body>

	<br>
	<div style="text-align: center">
		<h3>커뮤니티</h3>
	</div>
	<br>
	<div class="container">
		<div style='width:100px; float: right;'>
			<input type="button" class="btn btn-dark py-2 px-3" value="글쓰기" onclick="return fn_writeForm()" />
		</div>
		<br> <br>
		<!-- 고객센터 목록 부분 -->
		<div style="text-align: center" id="board">
			<div class="col-md-12 ftco-animate">
				<div class="cart-list">
					<table class="table">
						<thead class="thead-primary">
							<tr>
								<th scope="col" width="10%">번호</th>
								<th scope="col">제목</th>
								<th scope="col" width="15%">작성자</th>
								<th scope="col" width="15%">작성일</th>
								<th scope="col" width="15%">조회수</th>

							</tr>
						</thead>

						<tbody>
							<c:choose>

								<c:when test="${qnaCount!=0}">
									<c:forEach var="board" items="${boardList}" varStatus="status">
										<tr>
											<td>${board.BD_NUM }</td>
											<td><a href="boardDetail.omc?BD_NUM=${board.BD_NUM}">${board.BD_TITLE}</a>
											</td>
											<td>${board.BD_ID}</td>
											<td ${board.BD_REGDATE}><fmt:formatDate
													value="${board.BD_REGDATE}" pattern="yyyy-MM-dd" /></td>
											<td>${board.BD_COUNT}</td>
										</tr>
									</c:forEach>
								</c:when>

								<c:otherwise>
									<tr>
										<td colspan="4">조회된 결과가 없습니다.</td>
									</tr>
								</c:otherwise>

							</c:choose>
						</tbody>

					</table>
					<form id="commonForm" name="commonForm"></form>
					<!-- ***** -->
					<c:if test="${not empty paginationInfo}">
						<ui:pagination paginationInfo="${paginationInfo}" type="text"
							jsFunction="fn_search" />
					</c:if>
					<input type="hidden" id="currentPageNo" name="currentPageNo" /> <br />
				</div>
				<!-- end cart-list div -->
			</div>

		</div>
	</div>

	<br>
	<br>
	<br>


</body>
</html>