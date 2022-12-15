<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>  
<%@ include file="/WEB-INF/include/include-header.jspf" %>
<!DOCTYPE html>
<html lang="ko">
  <head>
    <title>오늘의 메뉴 추천, 오메추</title>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
<script>
function getCount() {
	
	var size = document.getElementById('listSize').value;
	var sum = 0; //원가의 전체 합
	var saleSum = 0; //할인된 총 전체 합
	for(var i=0;i<size;i++){
		var count = 0;
		count = document.getElementById('CT_COUNT'+i).value;
		var price = document.getElementById('CT_PRICE'+i).value;
		var sale = document.getElementById('CT_DCPRICE'+i).value;
		var salePrice = price * (100-sale) / 100;
		var totalPrice = salePrice * count; // 할인된 상품 가격
		
		sum += price * count;
		saleSum += (price*sale/100)*count;// 상품이 할인받은 가격
		document.getElementById("totalPrice"+i).innerText = totalPrice + '원';
		document.getElementById("saled"+i).innerText = (price*sale/100)*count + '원';
	}
	document.getElementById("originalSum").innerText = sum + '원'; // 페이지의 sum값 출력
	document.getElementById("saleSum").innerText = saleSum + '원'; // 페이지의 saleSum값 출력
	
	var finalSum = sum - saleSum + 3000;
	document.getElementById("finalSum").innerText = finalSum + '원'; // 페이지의 finalSum값 출력
	
	return;
}

function basketModify(i) {
	var CT_CID = document.getElementById('CT_CID'+i).value;
	var CT_COUNT = document.getElementById('CT_COUNT'+i).value;		
	location.href="cartModify.omc?CT_CID=" + CT_CID + "&CT_COUNT=" + count;
}

function basketDelete(i) {
if(confirm("정말 삭제하시겠습니까?")==true){
		var CT_CID = document.getElementById('CT_CID'+i).value;
		location.href="cartDelete.omc?CT_CID=" + CT_CID;
}	
return;
}
/* function basketDelete(i) {
	var bIdx = document.getElementById('BIDX'+i).value;
	location.href="basketDelete.al?BIDX=" + bIdx;
} */

function basketOrderForm(i) {
	var CT_CID = document.getElementById('CT_CID'+i).value;
	var CT_COUNT = document.getElementById('CT_COUNT'+i).value;		
	location.href="cartOrderForm.omc?CT_CID=" + CT_CID + "&CT_COUNT=" + CT_COUNT;	
}

function orderConfirm(){
	var MEM_ID = document.getElementById('MEM_ID').value;
	var size = document.getElementById('listSize').value;
	if(confirm("구매하시겠습니까?") == true){
		if(size<=0){
			alert("구매할 수 없습니다.");
			return false;
		}
		location.href="/cartOrderForm.omc?CT_CID=" + MEM_ID;
	}
	return;
}
</script>
<script>
window.onload = function() {
	getCount();
};
</script>
<script>
    function numberMaxLength(e){
        if(e.value.length > e.maxLength){
            e.value = e.value.slice(0, e.maxLength);
        }
    }//수량입력폼 글자수제한
</script>
  </head>
  
  <body>
  	 <input type="hidden" id="MEM_ID" name="MEM_ID" value="<%=request.getSession().getAttribute("MEM_ID")%>">
  <hr>
		
		<div style="text-align:center">
			<h2>장바구니</h2>
		</div>
		
		<br>
		  <div class="container">
		  	<div style="text-align:center" id="board" >
		  	<div class="cart-list">
	    		<table class="table">
					<thead class="thead-primary">
						<tr>
						    <!-- <th>&nbsp;</th> -->
							<th colspan="2">상품명</th>
							<th>수량</th>
							<th>정가</th>
							<th>할인가</th>
							<th>주문 금액</th>
							<th>수정/삭제</th>
						</tr>
					</thead>
							<c:forEach var="goods" items="${cartList}">
							<tbody>
						      <tr class="text-center">
						        <td class="image-prod"><div class="img" style="background-image:url(resources/img/goods/goods-${memInfo.MEM_ID}.png);"></div></td>
						        <!-- 상품명 -->
						        <td class="product-name">
						        	<a href="goodsDetail.omc?GD_GID=${cartList.CT_GID}">${cartList.CT_NAME}</a>
				          			<h3>${basketBeanList[i].BSALE}%</h3>
				          			<input type="hidden" id="CT_CID" name="CT_CID" value="${cartList.CT_CID}">
				          			<input type="hidden" id="CT_GID" name="CT_GID" value="${cartList.CT_GID}">
				          			<input type="hidden" id="CT_DCPRICE" name="CT_DCPRICE" value="${cartList.CT_DCPRICE}">
						        	
						        </td>
						        
						        <!-- 수량 -->
						        <td>
						        	<input type="number" style="width: 3em;" maxlength="2" oninput="numberMaxLength(this);" id="CT_COUNT" value="${cartList.CT_COUNT}" onChange="getCount()">
					          	</td>
					          	
					          	<!-- 정가 -->
						        <td class="price">${cartList.CT_PRICE}원
						        	<input type="hidden" id="CT_PRICE" name="CT_PRICE" value="${cartList.CT_PRICE}">
						        </td>
						        
						        <!-- 할인가 -->
						        <td>
							        <c:set var="salePrice" value="${cartList.CT_DCPRICE}" />
			    					<b><fmt:formatNumber value="${salePrice}" pattern="#.#" />원</b>
			    					
			    					<input type="hidden" id="CT_DCPRICE" name="CT_DCPRICE" value="${cartList.CT_DCPRICE}">
				          		</td>
						        
						        <!-- 주문 금액 -->
						        <td id="totalPrice" style="font-weight : bold;">
						        	<c:set var="total" value="${salePrice * cartList.CT_COUNT}" />
						        	<fmt:parseNumber var="totalPrice" integerOnly="true" value="${total}" />
						        	<b><fmt:formatNumber value="${totalPrice}" pattern="#.#" />원</b>
						        </td>
						    	
						    	<!-- 수정/삭제 -->
						        <td>
								<input type="button" class="btn btn-primary py-1 px-2" onClick="cartModify()" value="수정">
						        <input type="button" class="btn btn-dark py-1 px-2" onClick="cartDelete()" value="삭제">
						        </td>
						      </tr>
							</c:forEach>
					<%-- <c:if test="${Size<=0}">
						<tr><td style="text-align:center" colspan="8">장바구니에 상품이 없습니다.</td></tr>
					</c:if> --%>
			 </tbody>
		</table>
			
					</div> <!-- end cart-list div -->
		<br>				  						  
	<!-- 쇼핑계속버튼 -->		  
		<div style='float: right;'>
			<input type="button" class="btn btn-dark py-2 px-3" value="쇼핑 계속하기" onclick="location.href='/allGoodsList.omc'"/>
    	</div>
    	
	</div>
</div>

	<input type="hidden" id="listSize" name="listSize" value="${Size}">
    	 
    		<br><br><br><br>
    		<div class="container">
    				<div class="cart-total mb-3" style="text-align:center">
    					<h2>결제 금액</h2><br>
    					<p class="d-flex">
    						<span>주문금액</span>
    						<span id="originalSum"></span>
    					</p>
    					<p class="d-flex">
    						<span>할인금액</span>
    						<span id="saleSum" style="color:Crimson"></span>
    					</p>
    					<p class="d-flex">
    						<span>배송비</span>
    						<span>3000원</span>
    					</p>
    					<hr>
    					<p class="d-flex total-price">
    						<span>총 금액</span>
    						<span id="finalSum"></span>
    					</p>
    				</div>
    			
    				<div style="text-align:center">
    				<input type="button" class="btn btn-primary py-3 px-5"
    				 onClick="return orderConfirm()" value="구매하기"><br><br><br><br>
    				<!--선택주문 나중에 구현 -->
    				<!-- <a href="/Jumo/basketOrderForm.al" class="btn btn-primary py-3 px-4">선택 상품 주문</a> -->
    				</div>
    			</div>
    			
    			
  </body>
</html>