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
<div class="container">
<div class="row">
<div class="col-sm-2">
	<div class="sidebar-box ftco-animate" id="menu" style="margin-top:30px;">
		<ul class="categories">
			<li><a href="/myPage.omc" style="font-size:middle;">마이페이지</a></li>
			<c:if test="${MEM_ID != 'ADMIN'}">
			<li><a href="/myPage/myInfoModify.omc" style="font-size:middle;">회원정보 수정</a></li>
			</c:if>
			<li><a href="/myPage/myOrderList.omc" style="font-size:middle; color:#82ae46;">주문조회</a></li>
			<li><a href="/myPage/myReviewList.omc" style="font-size:middle;">후기</a></li>
			<li><a href="/myPage/myQnaList.omc" style="font-size:middle;">문의내역</a></li>
		</ul>
	</div>
</div>
<div class="col-sm-10">
	<div style="text-align:center">
		<h3> 주문 내역 </h3>
	</div>

	<div class="container">
	<br><br>
		<div class="row">
				<div class="col-md-12 ftco-animate">
				
					<div class="cart-list">
				
						<table class="table">
							<thead class="thead-primary">
								<tr class="text-center">
									<th>주문번호</th>
									<th>운송장번호</th>
									<th>상품명</th>
									<th>수량</th>
									<th>가격</th>
									<th>상태</th>
								</tr>
							</thead>
							<tbody>
							
							    <c:choose> 
							    	<c:when test="${orderCount!=0}"> 
							    		<c:forEach var="order" items="${order}" varStatus="status"> 
											<tr class="text-center">
											<td>${order.OD_OID}</td>
											
											<td>${order.OD_WAYBILL}</td>
    			
    										<td><a href="/goodsDetail?GD_GID=${order.OD_GID}">${order.OD_GNAME}</a></td>
    										
    										<td>${order.OD_COUNT}</td>
											
											<td>${order.OD_TOTAL}</td>
											
											<td>${order.OD_STATUS}</td>
											
										</tr><!-- END TR-->
							   			</c:forEach>
									</c:when>
							    
								    <c:otherwise> 
								   		<tr>
											<td colspan="5">주문한 상품이 없습니다.</td> 
										</tr>
									</c:otherwise>
								
								</c:choose> 
							
							</tbody>
						</table>
						
					</div> <!-- end cart-list div -->
									
				</div>
			</div>
		</div>
</div>
</div>
</div>
<br><br><br>
</body>
</html>