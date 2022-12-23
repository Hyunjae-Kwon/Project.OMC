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

<section class="ftco-section ftco-cart">
	<div style="text-align:center">
		<h2>매출 리스트</h2>
	</div>
	<br>
		<div class="container">
			<div class="row">
				<div class="col-md-12 ftco-animate">
				
					<div class="cart-list">
			   			<div class="col-md-10 mb-5 text-center">
			   				<ul class="product-category">
			   					<li><a href="adminSellList.omc?goodsOrder=HIGH">판매량높은순</a></li>
			   					<li><a href="adminSellList.omc?goodsOrder=LOW">판매량낮은순</a></li>
			   				</ul>
		   				</div>
						<table class="table">
							<thead class="thead-primary">
								<tr class="text-center">
									<th>&nbsp;</th>
									<th>상품 리스트</th>
									<th>가격</th>
									<th>재고</th>
									<th>판매량</th>
									<th>매출액</th>
								</tr>
							</thead>
							<tbody>
							
								<c:forEach var="goods" items="${sellList}">
								<tr class="text-center">
									<td></td>
									
									<td class="product-name">
										<h3><b>${goods.GD_GNAME}</b></h3>
									</td>
									
									<td class="price">${goods.GD_PRICE}</td>
									
									<td class="quantity">
										<div class="input-group mb-3">
										   	<input type="text" name="quantity" class="quantity form-control input-number" value="${goods.GD_STOCK}" readonly>
										</div>
									</td>
									
									<td class="total">${goods.GD_SELL}</td>
									<td class="totalPrice">${goods.GD_PRICE * goods.GD_SELL}</td>
								</tr><!-- END TR-->
								</c:forEach>
								<%-- <div class="container">
				    				<div class="cart-total mb-3" style="text-align:center">
				    					<p class="d-flex total-price">
				    						<span style="font-size:20px">총 매출액</span>
				    						<span style="font-size:20px" id="finalSum">${(goods.GD_PRICE * goods.GD_SELL) * (goods.GD_PRICE * goods.GD_SELL)}</span>
				    					</p>
				    				</div>
				    			</div> --%>
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