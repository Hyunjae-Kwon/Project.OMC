<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ include file="/WEB-INF/include/include-header.jspf" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>오늘의 메뉴 추천, 오메추</title>
</head>
<script>
function deleteCheckAjax(BD_NUM, index) {
	if(confirm("삭제하시겠습니까?") == true) {
		
		$.ajax({
			url			: "adminReviewDeleteAjax.omc",
			data		: {"BD_NUM" : BD_NUM},
			contentType	: "application/json",
			success		: function(data) {
				alert("삭제하였습니다.");
				$("#rev"+index).remove();
			},
			error:function(request, error) {
				alert("fail");
				// error 발생 이유를 알려준다.
			alert("code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error);
			}
		});
	}
}
</script>
<body>
<section class="ftco-section ftco-cart">
<div style="text-align:center">
	<h2> 후기 </h2>
</div>
<br>
<div class="container">
	<div class="row">
		<div class="col-md-12 ftco-animate">
			<div class="cart-list">
				<table class="table">
					<thead class="thead-primary">
						<tr class="text-center">
							<th>번호</th>
							<th>제품명</th>
							<th>내용</th>
							<th>작성자</th>
							<th>작성일</th>
							<th>&nbsp;</th>
						</tr>
					</thead>
					<tbody>
					
					    <c:choose>
					    	<c:when test="${reviewCount!=0}"> 
					    		<c:forEach var="review" items="${reviewList}" varStatus="status"> 
									<tr id="rev${status.index}" class="text-center">
										<td>${review.BD_NUM}</td>
										
										<td>
											<a href="goodsDetail.omc?GD_GID=${review.BD_GID}"class="img-prod">
											<img class="img-fluid" src="resources/img/goods/goods-${review.BD_GID}.png" style="height:70px;">
											</a>
										</td>
										
										<td><b>${review.BD_TITLE}</b><br>${review.BD_CONTENT}</td>
										
										<td>${review.BD_ID}</td>
										
										<td ${review.BD_REGDATE }>
            									<fmt:formatDate value="${review.BD_REGDATE}" pattern="yyyy-MM-dd"/>   
           									 </td>
										<td>
											<button class="btn btn-light py-2 px-3"
															onClick="deleteCheckAjax(${review.BD_NUM}, ${status.index});">삭제</button>
										</td>											
										
									</tr><!-- END TR-->
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
				
			</div> <!-- end cart-list div -->
			
			${paging.pageHtml}
			
		</div>
	</div>
</div>
</section>
	
</body>
</html>