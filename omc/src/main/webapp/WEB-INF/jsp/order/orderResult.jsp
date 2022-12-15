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
	
	<form method="post" id="orderForm" action="order.omc">
	<!-- 단일 주문일 때의 결과 표시 -->

	
	<section class="ftco-section ftco-cart">
		<div class="container">
			<div class="row">
				<div class="col-md-12 ftco-animate">
				
					<div class="cart-list">

						<!-- 단독 주문일 경우 주문 리스트 출력 -->
						<%-- <c:if test="${result=='direct'}">	 --%>
				
						<table class="table">
							<thead class="thead-primary">
								<tr class="text-center">
									<th colspan="2">상품명</th>
									<th>수량</th>
									<th>상품 금액</th>
									<th>판매 금액</th>
									<th>할인 금액</th>
									<th>결제 금액</th>
								</tr>
							</thead>
							<tbody>
							
								<tr class="text-center">
									<td class="image-prod"><div class="img" style="background-image:url(resources/img/goods/goods-${orderResult.OD_GID}.png);"></div></td>
									
									<td class="product-name">
										<h3><b>${orderResult.OD_GNAME}</b></h3>
									</td>
									
									<td class="count">${orderResult.OD_COUNT}</td>
									
									<td class="price">${orderResult.OD_PRICE}원</td>
									
									<td class="price">
				          				<c:set var="salePrice" value="${orderResult.OD_PRICE * (100-orderResult.OD_DCPRICE) / 100}" />
				          				<fmt:formatNumber value="${salePrice}" pattern="#.#" />원
									</td>
									
								<%-- 	<td id="saled${i}" style="color:Crimson">
				          			<c:set var="salePrice" value="${basketBeanList[i].BPRICE * (100-basketBeanList[i].BSALE) * 0.01}" />
				          			<input type="hidden" id="OPRICE${i}" name="OPRICE" value="${basketBeanList[i].BPRICE}"></td>
						      		 --%>
						      		
									<td class="saled" style="color:Crimson">
									<c:set var="saled" value="${(orderResult.OD_PRICE-salePrice) * orderResult.OD_COUNT}" />
									<fmt:formatNumber value="${saled}" pattern="#.#" />원</td>
									
						      		
									<td class="total">
										<b>${orderResult.OD_TOTAL}원</b>
									</td>
								</tr><!-- END TR-->
							
							</tbody>
						</table>
						
<%-- 						<table class="table">
							<tbody>
								<tr class="text-center">
									<td rowspan="4"><h4><b>주문자 정보</b></h4></td>
								</tr>
							
								<tr class="text-center">
									<td class="count">이름</td>
									<td class="count">${memberBean.NAME}</td>
								</tr>
								
								<tr class="text-center">
									<td class="count">전화번호</td>				
									<td class="count">${memberBean.PHONE}</td>
								</tr>
								
								<tr class="text-center">	
									<td class="count">휴대폰번호</td>
									<td class="count">${memberBean.MOBILE}</td>
								</tr><!-- END TR-->

								<tr class="text-center">
									<td rowspan="4"><h4><b>배송 정보</b></h4></td>
								</tr>
							
								<tr class="text-center">
									<td class="count">우편번호</td>
									<td class="count">${orderBean.OPOSTCODE}</td>
								</tr>
								
								<tr class="text-center">
									<td class="count">주소</td>				
									<td class="count">${orderBean.OADDRESS1}</td>
								</tr>
								
								<tr class="text-center">	
									<td class="count">상세주소</td>
									<td class="count">${orderBean.OADDRESS2}</td>
								</tr><!-- END TR-->
							
								<tr class="text-center">
									<td rowspan="3"><h4><b>결제 정보</b></h4></td>
								</tr>
							
								<tr class="text-center">
									<td class="count">적립 포인트</td>
									<td class="count"><b>${jumo_point.JUMO_POINT}Point</b></td>
								</tr>
							
								<tr class="text-center">
									<td class="count">총 결제 금액</td>
									<td class="count"><b>${orderBean.OTOTAL}원</b></td>
								</tr>
								
								<tr class="text-center">
									<td class="count">무통장 입금</td>				
									<td class="count">우리은행 1222222-266</td>
								</tr>
							
							</tbody>
						</table> --%>
						
						<%-- </c:if> --%>
						
						<!-- 장바구니일 경우 주문 리스트 출력 -->
						<c:if test="${result=='cart'}">	
					
						<table class="table">
							<thead class="thead-primary">
								<tr class="text-center">
									<th colspan="2">상품명</th>
									<th>수량</th>
									<th>상품 금액</th>
									<th>판매 금액</th>
									<th>할인 금액</th>
									<th>결제 금액</th>
								</tr>
							</thead>
							<tbody>
								<c:forEach var="order" items="${orderBeanList}">
								<tr class="text-center">
									<td class="image-prod">
									<div class="img" style="background-image:url(resources/img/goods/goods-${order.OD_GID}.png);"></div></td>
									
									<td class="product-name">
										<h3><b>${order.OD_GNAME}</b></h3>
									</td>
									
									<td class="count">${order.OD_COUNT}</td>
									
									<td class="price">${order.OD_PRICE}원</td>
									
									<%-- <td class="price">${order.OSALE}%</td> --%>
									
									<td class="price">
				          				<c:set var="salePrice" value="${order.OD_PRICE * (100-order.OD_DCPRICE) / 100}" />
				          				<fmt:formatNumber value="${salePrice}" pattern="#.#" />원
									</td>
									
									<td class="saled" style="color:Crimson">
										<c:set var="saled" value="${(order.OD_PRICE-salePrice) * order.OD_COUNT}" />
										<fmt:formatNumber value="${saled}" pattern="#.#" />원</td>
									
									<td class="total">
										<b><c:set var="total" value="${salePrice * order.OD_COUNT}" />
										<fmt:formatNumber value="${total}" pattern="#.#" />원</b>
									</td>
								</tr><!-- END TR-->
								
							</c:forEach>	
							</tbody>
						</table>
						
						</c:if>
						
					</div> <!-- end cart-list div -->
					
					<!-- 직접 구매 -->
					<%-- <c:if test="${result=='direct'}"> --%>
									
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
									<span>우편번호</span>
									<span>${memInfo.MEM_ZIPCODE}</span>
								</p>
								<p class="d-flex total-price">
									<span>주소</span>
									<span>${memInfo.MEM_ADD1}</span>
								</p>
								<p class="d-flex total-price">
									<span>상세주소</span>
									<span>${memInfo.MEM_ADD2}</span>
								</p><br>
								<p class="d-flex total-price"></p>
							</div>
						</div>
<!-- 	 					<div class="col-lg-4 mt-5 cart-wrap ftco-animate">
							<div class="cart-total mb-3">
								<h5><b>결제 정보</b></h5><br>
								<p class="d-flex total-price">
									<span>적립 포인트</span>
									<span>${jumo_point.JUMO_POINT}&nbsp;Point</span>
								</p> -->
								<p class="d-flex total-price">
									<span>총 결제 금액</span>
									<span>${orderResult.OD_TOTAL}&nbsp;원</span>
								</p>
								<p class="d-flex total-price">
									무통장 입금
								</p>
								<p class="d-flex total-price">
									&emsp;&emsp;<span><b>우리은행</b></span>
									<span>1222222-266</span>
								</p>
							</div>
						</div>
					</div>				
					
					<%-- </c:if>	 --%>
					
					<!-- 장바구니 -->
					<c:if test="${result=='cart'}">
					
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
									<span>우편번호</span>
									<span>${orderInfo[0].OD_ZIPCODE}</span>
								</p>
								<p class="d-flex total-price">
									<span>주소</span>
									<span>${orderInfo[0].OD_ADD1}</span>
								</p>
								<p class="d-flex total-price">
									<span>상세주소</span>
									<span>${orderInfo[0].OD_ADD2}</span>
								</p><br>
								<p class="d-flex total-price"></p>
							</div>
						</div>
						<div class="col-lg-4 mt-5 cart-wrap ftco-animate">
							<div class="cart-total mb-3">
								<h5><b>결제 정보</b></h5><br>
						<!-- 		<p class="d-flex total-price">
									<span>적립 포인트</span>
									<span>${jumo_point.JUMO_POINT}&nbsp;Point</span>
								</p> -->
								<p class="d-flex total-price">
									<span>총 결제 금액</span>
									<span>${orderInfo[0].OD_TOTAL}&nbsp;원</span>
								</p>
								<p class="d-flex total-price">
									무통장 입금
								</p>
								<p class="d-flex total-price">
									&emsp;&emsp;<span><b>국민은행</b></span>
									<span>1222222-266</span>
								</p>
							</div>
						</div>
					</div>
					
					</c:if>
					<div align="center">
						<td>
							<input type="button" value="메인" class="btn btn-primary py-2 px-3"
								onClick="location.href='main.omc'">
						</td>
					</div>
</section>
	

	
	</form>	
	
</body>
</html>