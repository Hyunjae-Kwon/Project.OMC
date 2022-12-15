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
function getCount(){
	
	var size = ${cartList.size()};
	var sum = 0; //원가의 전체 합
	var saleSum = 0; //할인된 총 전체 합
	for(var i=0;i<size;i++){
		var count = 0;
		count = document.getElementById('CT_COUNT'+i).value;
		
		var price = document.getElementById('CT_PRICE'+i).value; //원가
		var sale = document.getElementById('CT_DCPRICE'+i).value; //할인가
		
		var salePrice = sale; //원가- 할인가 = 할인금액 */
		var totalPrice = sale * count; // 할인된 상품 가격 
		
		sum += price * count; //원가총금액
		
		saleSum += (price-sale) * count;// 상품 총 할인받은 가격
		
		//document.getElementById("totalPrice"+i).innerText = totalPrice + '원';
		//document.getElementById("saled"+i).innerText = (price-sale)*count + '원'; 
	}

	document.getElementById("originalSum").innerText = sum + '원'; // 페이지의 sum값 출력
	document.getElementById("saleSum").innerText = saleSum + '원'; // 페이지의 saleSum값 출력
	
	var finalSum = sum - saleSum + 3000;
	document.getElementById("finalSum").innerText = finalSum + '원'; // 페이지의 finalSum값 출력
	
}

function cartUpdate() {
	var cid = document.getElementById('CT_CID').value;
	var count = document.getElementById('CT_COUNT').value;		
	location.href="updateMyCart.omc?CT_CID" + cid + "&CT_COUNT=" + count;
}

function basketOrderForm(i) {
	var CT_CID = document.getElementById('CT_CID').value;
	var CT_COUNT = document.getElementById('CT_COUNT').value;		
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

function chkAll(){
	 if( $("#chkAll").is(':checked') ){
	     $("input[name=checkRow]").prop("checked", true);
	 }else{
	     $("input[name=checkRow]").prop("checked", false);
	 }	 	 
}

function chkAll2(){
    $("input[name=checkRow]").prop("checked", true);
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
						    <th scope="col" style="font-size:10px;">
			        			<input type="checkbox" id="chkAll" onclick="chkAll();">
			        		</th>
							<th colspan="2">상품명</th>
							<th>수량</th>
							<th>정가</th>
							<th>할인가</th>
							<th>주문 금액</th>
						</tr>
					</thead>
							<c:forEach var="goods" items="${cartList}">
							<tbody id="tbody">
						      <tr class="text-center">
						      	<td>
			        				<input type="checkbox" class="checkbox" id="checkRow" name="checkRow">
			        			</td>
						        <td class="image-prod"><div class="img" style="background-image:url(resources/img/goods/goods-${goods.CT_GID}.png);"></div></td>
						        <!-- 상품명 -->
						        <td class="product-name">
						        	<a href="goodsDetail.omc?GD_GID=${goods.CT_GID}">${goods.CT_NAME}</a>
				          			<input type="hidden" id="CT_CID" name="CT_CID" value="${goods.CT_CID}">
				          			<input type="hidden" id="CT_GID" name="CT_GID" value="${goods.CT_GID}">
				          			<input type="hidden" id="CT_DCPRICE" name="CT_DCPRICE" value="${goods.CT_DCPRICE}">
						        	
						        </td>
						        
						        <!-- 수량 -->
						        <td>
						        	<span class="minus">-</span>
		            				<input type="number" class="count" style="width: 3em;" maxlength="2" oninput="numberMaxLength(this);" id="CT_COUNT" name="CT_COUNT" value="${goods.CT_COUNT}" readonly>
									<span class="plus">+</span>
					          	</td>
					          	
					          	<!-- 정가 -->
						        <td class="price">${goods.CT_PRICE}원
						        	<input type="hidden" id="CT_PRICE" name="CT_PRICE" value="${goods.CT_PRICE}">
						        </td>
						        
						        <!-- 할인가 -->
						        <td class="saleprice">${goods.CT_DCPRICE}원
							        <%-- <c:set var="salePrice" value="${goods.CT_DCPRICE}" />
			    					<b><fmt:formatNumber value="${salePrice}" pattern="#.#" />원</b> --%>
			    					<input type="hidden" id="salePrice" name="salePrice" value="${goods.CT_DCPRICE}">
			    					<input type="hidden" id="CT_DCPRICE" name="CT_DCPRICE" value="${goods.CT_DCPRICE}">
				          		</td>
						        
						        <!-- 주문 금액 -->
						        <td id="totalPrice" style="font-weight : bold;" value="${totalPrice}">
						        	<c:set var="total" value="${salePrice * goods.CT_COUNT}" />
						        	<fmt:parseNumber var="totalPrice" integerOnly="true" value="${total}" />
						        	<b><fmt:formatNumber value="${totalPrice}" pattern="#.#" />원</b>
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
		<div align="center">
			<button type="button" class="btn btn-primary" onclick="chkAll2();">전체선택</button>
			&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			<button type="button" class="btn btn-primary" id="deleteChk">선택삭제</button>
			&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			<button type="button" class="btn btn-primary" onclick="buyItem()">선택주문</button>
			&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			<button type="button" class="btn btn-primary" onclick="location.href='<c:url value='/main.omc'/>';">메인으로</button>
		</div>		  						  
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
<script>
$(document).ready(function(){

	/* 상품 수량 수정 시 화면에서 금액 변경 (상품 수량 추가) */
    $(document).on('click','.plus',function(){
    	
    	$(this).prev('.count').val(parseInt($(this).prev('.count').val()) + 1 );
		
    	var tr = $(".plus").index(this);
		var cid = $("#tbody tr").eq(tr).find("#CT_CID").val();
    	var cnt = parseInt($(this).prev('.count').val());
    	
    	var price = parseInt($(this).closest("td").find('.salePrice').val());
    	var sum = cnt*price;
    	
    	$(this).closest("tr").find('#totalPrice').val(sum);
    	var num = parseInt($(this).parent().siblings("#CT_CID").val());
    	
    	$.ajax({
			type: "POST",
			url:"<c:url value='updateMyCart.omc'/>",
			data:{CT_COUNT:cnt, CT_CID:cid},
			success: function(data){
				}	
        	});
    	var sum = cnt*price;	
 	});
 	
    /* 상품 수량 수정 시 화면에서 금액 변경 (상품 수량 감소) */
 	$(document).on('click','.minus',function(){
   		 $(this).next('.count').val(parseInt($(this).next('.count').val()) - 1 );
   		var cnt = parseInt($(this).next('.count').val());
    	var price =parseInt($(this).closest("tr").find('.price').val());
    	var sum = cnt*price;
    	$(this).closest("tr").find('#sum').val(sum);
   		 
       	 if ($(this).next('.count').val() == 0) {
          	$(this).next('.count').val(1);
          	cnt = parseInt($(this).next('.count').val());
        	price = parseInt($(this).closest("tr").find('.price').val());
        	sum = cnt*price; 
        	$(this).closest("tr").find('#sum').val(sum);
        	
         	return;
    	 }   
	       	var num = parseInt($(this).parent().siblings("#num").val());
	    	$.ajax({
				type: "POST",
				url:"<c:url value='/myPage/myCartUpdate'/>",
				data:{CART_NUM:num, CART_CNT:cnt},
				success: function(data){
						
					}	
	        });	 
	});

    /* 장바구니 상품 삭제 */
	$(document).on('click','#deleteChk', function(){
 		if($("input:checkbox[name=checkRow]:checked").length == 0){
 			alert("삭제할 상품을 선택해주세요.");
 			return false;
 	 	} else if(confirm("선택하신 상품을 삭제하시겠습니까?")== true){
 	 		$("input:checkbox[name=checkRow]:checked").each(function(index, item){
 	 	 		
 	 			var tr = $(".checkbox").index(this);
 				var num = $("#tbody tr").eq(tr).find("#CT_CID").val();
 				$.ajax({
 	 				type: "POST",
 	 				url:"<c:url value='delSelectMyCart.omc'/>",
 	 				data:{CT_CID:num},
 	 				success: function(data){
 	 						location.reload();
 	 				}	
 	 	        });
 	 		});
 	 	}
 	});
});

/* 선택 상품 구매 */
function buyItem(){

	if($("input:checkbox[name=checkRow]:checked").length == 0){
			alert("구매하실 상품을 체크해주세요.");
			return false;
 	} else if(confirm("선택하신 상품을 주문하시겠습니까?")== true){
 		$.ajax({
		      type : "POST",
		      url : '<c:url value="/item/delBuyItemCart"/>',
		      async: false,
		      success : function(data){
		      }
		   }); 
 		$("input:checkbox[name=checkRow]:checked").each(function(){
 			
 			var formOrder=$(this).closest("tr").find("form").serialize();
 			$.ajax({
 				type : "POST",
 				url : "<c:url value='/item/buyItemCart'/>",
 				data : formOrder,
 				async: false,
 				success : function(data){
 					
 				}
 			});
	    });
	    location.href="<c:url value='/item/qmember'/>";
	}
}
</script>
  </body>
</html>