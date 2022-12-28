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
		<h1> 주문 결과 </h1>
	</div>
	
	<form method="post" id="orderCart" action="orderCart.omc">
	<!-- 단일 주문일 때의 결과 표시 -->
	
	<section class="ftco-section ftco-cart">
		<div class="container">
			<div class="row">
				<div class="col-md-12 ftco-animate">
				
					<div class="cart-list">
						<table class="table">
							<thead class="thead-primary">
								<tr class="text-center">
									<th colspan="2">상품명</th>
									<th>수량</th>
									<th>상품 단가</th>
									<th>할인 단가</th>
									<th>할인 금액</th>
									<th>합계 금액</th>
								</tr>
							</thead>
							<tbody>
								<c:forEach var="order" items="${orderResult}">
								<tr class="text-center">
									<td class="image-prod">
									<!-- 상품명 -->
									<div class="img" style="background-image:url(resources/img/goods/goods-${order.OD_GID}.png);"></div></td>
									
									<td class="product-name">
										<h3><b>${order.OD_GNAME}</b></h3>
									</td>
									
									<!-- 상품수량 -->
									<td class="count">${order.OD_COUNT}</td>
									
									<!-- 상품 단가 -->
									<td class="price"><fmt:formatNumber value="${order.OD_PRICE}" pattern="#,###" />원</td>
									
									<!-- 할인 단가 -->
									<td class="dcPrice"><fmt:formatNumber value="${order.OD_DCPRICE}" pattern="#,###" />원</td>
									
									<!-- 할인 금액 -->
									<td class="saled" style="color:Crimson">
										<c:set var="saled" value="${(order.OD_PRICE - order.OD_DCPRICE) * order.OD_COUNT}" />
										<fmt:formatNumber value="${saled}" pattern="#,###" />원
									</td>
																		
									<!-- 합계 금액 -->
									<td class="price">
				          				<c:set var="salePrice" value="${order.OD_DCPRICE * order.OD_COUNT}" />
				          				<b><fmt:formatNumber value="${salePrice}" pattern="#,###" />원</b>
									</td>
								</tr><!-- END TR-->
							</c:forEach>	
							</tbody>
						</table>
												
					</div> <!-- end cart-list div -->
					<div class="row justify-content-end">
						<div class="col-lg-4 mt-5 cart-wrap ftco-animate">
							<div class="cart-total mb-3">
								<h5><b>주문자 정보</b></h5><br>
								<p class="d-flex total-price">
									<span>이름</span>
									<span>${memInfo.MEM_NAME}</span>
								</p>
								<p class="d-flex total-price">
									<span>휴대폰번호</span>
									<span>${memInfo.MEM_PHONE}</span>
								</p><br>
								<p class="d-flex total-price"></p>
							</div>
						</div>
						<div class="col-lg-4 mt-5 cart-wrap ftco-animate">
							<div class="cart-total mb-3">
								<h5><b>배송 정보</b></h5><br>
								<p class="d-flex total-price">
									<span>이름</span>
									<span>${orderResult[0].OD_NAME}</span>
								</p>
								<p class="d-flex total-price">
									<span>휴대폰번호</span>
									<span>${orderResult[0].OD_PHONE}</span>
								</p>
								<p class="d-flex total-price">
									<span>우편번호</span>
									<span>${orderResult[0].OD_ZIPCODE}</span>
								</p>
								<p class="d-flex total-price">
									<span>주소</span>
									<span>${orderResult[0].OD_ADD1}</span>
								</p>
								<p class="d-flex total-price">
									<span>상세주소</span>
									<span>${orderResult[0].OD_ADD2}</span>
								</p><br>
								<p class="d-flex total-price"></p>
							</div>
						</div>
 					<div class="col-lg-4 mt-5 cart-wrap ftco-animate">
							<div class="cart-total mb-3">
								<h5><b>결제 정보</b></h5><br>
							<!--  	<p class="d-flex total-price">
									<span>적립 포인트</span>
									<span>${jumo_point.JUMO_POINT}&nbsp;Point</span>
								</p> -->
								<p class="d-flex total-price">
									<span>총 결제 금액</span>
									<span><b><fmt:formatNumber value="${payResult.TOTALPAY}" pattern="#,###" />&nbsp;원</b></span>
								</p>
								<p class="d-flex total-price">
									무통장 입금
								</p>
								<p class="d-flex total-price">
									<span><b>우리은행</b></span>
									<span>1222222-266</span>
								</p>
							</div>
						</div>
					</div>				
					<div align="center">
						<input type="button" value="메인" class="btn btn-primary py-2 px-3" onClick="location.href='main.omc'">
					</div>
			</div>
	 </div>
	</div>
</section>
	
	</form>	
	
</body>
</html>