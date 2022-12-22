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
<body>
<div style="text-align:center">
	<h3> 후기 </h3>
</div>
	<section class="ftco-section ftco-cart">
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
									<tr class="text-center">
										<td>&emsp;&emsp;&emsp;${review.BD_NUM}</td>
										
										<td>&emsp;&emsp;&emsp;&emsp;
											<a href="goodsDetail.omc?GD_GID=${review.BD_GID}"class="img-prod">
											<img class="img-fluid" src="resources/img/goods/goods-${review.BD_GID}.png" style="height:70px;">
											</a>
										</td>&emsp;&emsp;&emsp;&emsp;
										
										<td><b>${review.BD_TITLE}</b><br>${review.BD_CONTENT}</td>
										
										<td>${review.BD_ID}</td>
										
										<td>${review.BD_REGDATE}</td>	
										
										<td>
											<button class="btn btn-light py-2 px-3"
												onClick="javascript:if(confirm('삭제하시겠습니까?')==true){ location.href='adminReviewDelete.omc?BD_NUM=${review.BD_NUM}' } else{ return false; }">삭제</button>
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