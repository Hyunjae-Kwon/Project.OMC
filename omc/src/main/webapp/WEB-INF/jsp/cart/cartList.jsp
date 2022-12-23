<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
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
	
	var totalPrice = 0;				// 총 원가
	var totalDCPrice = 0;			// 총 할인 금액
	var dcprice = 0;				// 할인 받은 금액
	var finalTotalPrice = 0; 		// 최종 가격(총 가격 + 배송비)
	var count = 0;					// 구매 개수
	
	var checkbox = $("input:checkbox[name=checkRow]:checked");	// 체크된 체크박스
	
	checkbox.each(function(){
		
		var index = $(".checkbox").index(this);
		
		// 구매 개수
		count = parseInt($("#tbody tr").eq(index).find(".count").val());
		// 총 원가
		totalPrice += parseInt($("#tbody tr").eq(index).find(".CT_PRICE").val()) * count;
		// 총 할인 금액
		totalDCPrice += parseInt($("#tbody tr").eq(index).find(".CT_DCPRICE").val()) * count;
		// 총 구매 금액
		//finalTotalPrice += parseInt($("#tbody tr").eq(index).find(".ct_totalprice").val());
	});
	
	dcprice = totalPrice - totalDCPrice;
	finalTotalPrice = totalDCPrice + 3000;
	/* ※ 세자리 컴마 Javscript Number 객체의 toLocaleString() */
	
	// 총 원가
	$("#originalSum").text(totalPrice.toLocaleString());
	// 총 할인 금액
	$("#saleSum").text(dcprice.toLocaleString());
	// 최종 가격(총 할인 금액 + 배송비)
	$("#finalSum").text(finalTotalPrice.toLocaleString());		
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
	if($("#chkAll").prop("checked")){
		$("#chkAll").prop("checked", false);
		$("input[name=checkRow]").prop("checked", false);
	} else {
		$("#chkAll").prop("checked", true);
		$("input[name=checkRow]").prop("checked", true);
	}
	
	getCount(".ct_info");
}

function chkCount(){
	$("input[name=checkRow]").click(function(){
		var total = $("input[name=checkRow]").length;
		var checked = $("input:checkbox[name=checkRow]:checked").length;
		
		if(total != checked) $("#chkAll").prop("checked", false);
		else $("#chkAll").prop("checked", true);
	});
}
</script>
<script>
$(document).ready(function(){
	getCount();
	chkCount();
	
	$(".checkbox").on("change", function(){
		getCount(".ct_info");
	});
	
	$("#chkAll").on("change", function(){
		getCount(".ct_info");
	});
});

</script>
<script>
	//수량입력폼 글자수제한
    function numberMaxLength(e){
        if(e.value.length > e.maxLength){
            e.value = e.value.slice(0, e.maxLength);
        }
    }
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
					<c:choose>
						<c:when test="${fn:length(cartList)<=0}">
							<tr>
								<td style="text-align: center" colspan="8">장바구니에 상품이 없습니다.</td>
							</tr>
							<input type="button" class="btn btn-dark py-2 px-3" value="쇼핑 계속하기" onclick="location.href='/allGoodsList.omc'"/>
						</c:when>
						<c:otherwise>
							<c:forEach var="goods" items="${cartList}">
							<tbody id="tbody">
						      <tr id="tr" class="text-center">
						      	<td class="ct_info">
			        				<input type="checkbox" class="checkbox" id="checkRow" name="checkRow">
			        				<input type="hidden" class="CT_CID" value="${goods.CT_CID}">
				          			<input type="hidden" class="CT_PRICE" value="${goods.CT_PRICE}">
				          			<input type="hidden" class="CT_DCPRICE" value="${goods.CT_DCPRICE}">
				          			<input type="hidden" class="ct_totalprice" value="${goods.CT_DCPRICE * goods.CT_COUNT}">
			        			</td>
						        <td class="image-prod"><div class="img" style="background-image:url(resources/img/goods/goods-${goods.CT_GID}.png);"></div></td>
						        <!-- 상품명 -->
						        <td class="product-name">
						        	<a href="goodsDetail.omc?GD_GID=${goods.CT_GID}">${goods.CT_NAME}</a>
				          			<input type="hidden" id="CT_CID" name="CT_CID" value="${goods.CT_CID}">
				          			<input type="hidden" id="CT_GID" name="CT_GID" value="${goods.CT_GID}">
				          			<input type="hidden" id="CT_DCPRICE" name="CT_DCPRICE" value="${goods.CT_DCPRICE}">
				          			<input type="hidden" id="CT_ID" name="CT_ID" value="${goods.CT_ID}">
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
			    					<%-- <input type="hidden" id="salePrice" name="salePrice" value="${goods.CT_DCPRICE}"> --%>
			    					<input type="hidden" id="CT_DCPRICE" name="CT_DCPRICE" value="${goods.CT_DCPRICE}">
				          		</td>
						        
						        <!-- 주문 금액 -->
						        <td style="font-weight : bold;">
						        	<c:set var="total" value="${goods.CT_DCPRICE * goods.CT_COUNT}" />
						        	<fmt:parseNumber var="totalPrice" integerOnly="true" value="${total}" />
						        	<%-- <b><fmt:formatNumber value="${totalPrice}" type="number" />원</b> --%>
						        	<span class="totalSum">${goods.CT_DCPRICE * goods.CT_COUNT}원</span>
						        </td>
						      </tr>
			 				</tbody>
						</c:forEach>
					</c:otherwise>
				</c:choose>
				</table>
			</div> <!-- end cart-list div -->
		</div>
	</div>
	<input type="hidden" id="listSize" name="listSize" value="${Size}">
    		<br><br>
    		<div class="container">
    				<div class="cart-total mb-3" style="text-align:center">
    					<h2>결제 금액</h2><br>
    					<p class="d-flex">
    						<span>주문금액</span>
    						<span id="originalSum">0</span>원
    					</p>
    					<p class="d-flex">
    						<span>할인금액</span>
    						<span id="saleSum" style="color:Crimson">0</span>원
    					</p>
    					<p class="d-flex">
    						<span>배송비</span>
    						<span>3000원</span>
    					</p>
    					<hr>
    					<p class="d-flex total-price">
    						<span>총 금액</span>
    						<span id="finalSum">0</span>원
    					</p>
    				</div>
    			</div>
				<div align="center">
					<button type="button" class="btn btn-primary" onclick="chkAll2();">전체선택</button>
					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					<button type="button" class="btn btn-primary" id="deleteChk">선택삭제</button>
					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					<button type="button" class="btn btn-primary" onclick="buyItem()">선택주문</button>
					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					<button type="button" class="btn btn-primary" onclick="location.href='<c:url value='/main.omc'/>';">메인으로</button>
				</div>		  						  
<script>
$(document).ready(function(){
	
	/* 상품 수량 수정 시 화면에서 금액 변경 (상품 수량 추가) */
    $(document).on('click','.plus',function(){
    	
    	$(this).prev('.count').val(parseInt($(this).prev('.count').val()) + 1 );
		
    	var tr = $(".plus").index(this);
		var cid = $("#tbody tr").eq(tr).find("#CT_CID").val();
    	var cnt = parseInt($(this).prev('.count').val());
    	
    	var price = $("#tbody tr").eq(tr).find("#CT_DCPRICE").val();
    	var totalPrice = cnt*price;
    	
    	$(this).closest("tr").find('.totalSum').text(totalPrice.toLocaleString() + "원");
    	
    	$.ajax({
			type: "POST",
			url:"<c:url value='updateMyCart.omc'/>",
			data:{CT_COUNT:cnt, CT_CID:cid},
			success: function(data){
				}	
        	});
    	
    	var totalPrice = cnt*price;
 	});
 	
    /* 상품 수량 수정 시 화면에서 금액 변경 (상품 수량 감소) */
 	$(document).on('click','.minus',function(){
   		$(this).next('.count').val(parseInt($(this).next('.count').val()) - 1 );
   		
   		var tr = $(".minus").index(this);
   		var cnt = parseInt($(this).next('.count').val());
   		var price = $("#tbody tr").eq(tr).find("#CT_DCPRICE").val();
   		var cid = $("#tbody tr").eq(tr).find("#CT_CID").val();
   		var totalPrice = cnt*price;
   		
   		$(this).closest("tr").find('.totalSum').text(totalPrice.toLocaleString() + "원");
   		 
       	 if ($(this).next('.count').val() == 0) {
          	$(this).next('.count').val(1);
          	cnt = parseInt($(this).next('.count').val());
        	price = parseInt($(this).closest("tr").find('#CT_DCPRICE').val());
        	totalPrice = cnt*price;
        	alert(totalPrice);
        	$(this).closest("tr").find('.totalSum').text(totalPrice.toLocaleString() + "원");
        	
         	return;
    	 }  
       	 $.ajax({
				type: "POST",
				url:"<c:url value='updateMyCart.omc'/>",
				data:{CT_COUNT:cnt, CT_CID:cid},
				success: function(data){
				}	
	        });
	    	var totalPrice = cnt*price;
	});

    /* 장바구니 상품 삭제 */
	$(document).on('click','#deleteChk', function(){
 		if($("input:checkbox[name=checkRow]:checked").length == 0){
 			alert("삭제할 상품을 선택해주세요.");
 			return false;
 	 	} else if(confirm("선택하신 상품을 삭제하시겠습니까?") == true){
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

//선택주문
function buyItem(){
	if($("input:checkbox[name=checkRow]:checked").length == 0){
		alert("구매하실 상품을 체크해주세요.");
		return false;
	} else if(confirm("선택하신 상품을 주문하시겠습니까?") == true){
		/* 주문하기로 넘어갈 때 카트 번호 부여 */
		$("input:checkbox[name=checkRow]:checked").each(function(index, item){
			var tr = $(".checkbox").index(this);
			var num = $("#tbody tr").eq(tr).find("#CT_GID").val();
			var id = $("#tbody tr").eq(tr).find("#CT_ID").val();
			$.ajax({
				type : "POST",
				url : "<c:url value='/updateNum.omc'/>",
				data :{CT_GID:num, CT_NUM:num, CT_ID:id},
				async: false,
				success : function(data){
				}
			});
		});
		$("input:checkbox[name=checkRow]:not(:checked)").each(function(index, item){
			var tr = $(".checkbox").index(this);
			var num = $("#tbody tr").eq(tr).find("#CT_GID").val();
			var id = $("#tbody tr").eq(tr).find("#CT_ID").val();
			$.ajax({
				type : "POST",
				url : "<c:url value='/updateNum.omc'/>",
				data :{CT_GID:num, CT_NUM:"", CT_ID:id},
				async: false,
				success : function(data){
				}
			});
		});
		
		location.href="<c:url value='orderCartForm.omc'/>";
	}
}
</script>
  </body>
</html>