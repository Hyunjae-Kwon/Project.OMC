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
<script>
function getCount() {
	var count = document.getElementById('CT_COUNT').value;
	var price = document.getElementById('GD_PRICE').value;
	var sale = document.getElementById('GD_DCPRICE').value;
	var salePrice = price * (100-sale) / 100;
	/* var totalPrice = salePrice * count; */
	var totalPrice = sale
	
	document.getElementById("totalPrice").innerText = totalPrice + '원';
	return count;
}

/* function insertCart() {
	var goodsId = document.getElementById('GD_GID').value;
	var count = Number(document.getElementById('orderCount').value);
	var stock = Number(document.getElementById('GD_STOCK').value);
	
	if(confirm("장바구니에 넣으시겠습니까?") == true) {
		if(count>stock) {
			alert("상품의 재고 수량보다 많은 양을 장바구니에 넣을 수 없습니다. 재고 :" + stock + "개");
			return false;
		}
		location.href="insertCart.omc?GD_GID=" + goodsId + "&orderCount=" + count;
	} else {
		return;
	}
} */

/* function insertCart() {
	if(confirm("장바구니에 넣으시겠습니까?") == true) {
		//서버로 전송할 데이터
		const form = {
				GD_GNAME : '${goods.GD_GNAME}',
				GD_GID : '${goods.GD_GID}',
				GD_PRICE : '${goods.GD_PRICE}',
				GD_DCPRICE : '${goods.GD_DCPRICE}',
				orderCount : '${orderCount}'
		}

		$(".btn_cart").on("click", function(e){
			$.ajax({
				url: 'insertCart.omc',
				type: 'GET',
				data: form,
				success: function (result){
					cartAlert(result);
				}
			})
		});

		function cartAlert(result){
			if(result == '0'){
				alert("장바구니에 추가를 하지 못하였습니다.");
			} else if(result == '1'){
				alert("장바구니에 추가되었습니다.");
			} else if(result == '2'){
				alert("장바구니에 이미 추가되어져 있습니다.");
			} else if(result == '5'){
				alert("로그인이 필요합니다.");	
			}
		}
		location.href="insertCart.omc";
	} else {
		return;
	}
} */

function insertCart(){
	   var items=$("td[id=contain]").get();
	   if(${MEM_ID ne null}){
	      $.each(items,function(index,item){
	         var goodsName=$("td[id=contain]:eq("+index+")").find('#GD_GNAME').val();
	         var goodsID=$("td[id=contain]:eq("+index+")").find('#GD_GID').val();
	         var goodsPrice=parseInt($("td[id=contain]:eq("+index+")").find('#GD_PRICE').val());
	         var goodsDCPrice=parseInt($("td[id=contain]:eq("+index+")").find('#GD_DCPRICE').val());
	         var count=parseInt($("td[id=contain]:eq("+index+")").find('#orderCount').val());
	         
	         $.ajax({
	            type : "POST",
	            url : '<c:url value="insertCart.omc"/>',
	            data : {CT_NAME:goodsName,CT_GID:goodsID,CT_PRICE:goodsPrice,CT_DCPRICE:goodsDCPrice,CT_COUNT:count},
	            success : function(data){
	                
	            }
	         });  
	      })
	      alert("장바구니에 담겼습니다!");
	      location.href="<c:url value='myCart.omc'/>";
	   } else {
	      alert("로그인 후 이용해주세요.");
	      location.href = "/loginForm.omc";
	   }
	   
	   }

function orderForm() {
	var goodsId = document.getElementById('GD_GID').value;
	var count = Number(document.getElementById('orderCount').value);
	var stock = Number(document.getElementById('GD_STOCK').value);
	// 구매수량이 상품 재고보다 많을 경우 구매 정지
	
	if(count>stock) {
		alert("상품의 재고 수량보다 많은 양을 살 수 없습니다. 재고 :" + stock + "개");
		return false;		
	}
	location.href="orderForm.omc?GD_GID=" + goodsId + "&orderCount="+ count;	
}

window.onload = function() {
	getCount();
};
</script>
</head>
<body>
	<p/>
	<div>
		<div>
			<div>

			    <section class="ftco-section">
			    	<div class="container">
			    		<div class="row">
			    			<div class="col-lg-6 mb-5">
			    				<a href="resources/img/goods/${goods.GD_IMAGE}" class="image-popup"><img src="resources/img/goods/${goods.GD_IMAGE}" class="img-fluid" alt="이미지 없음"></a>
			    			</div>
			    			<div class="col-lg-6 product-details pl-md-5" style="text-align:right">
			    				<h3>${goods.GD_GNAME}&nbsp;</h3>
			    				<input type="hidden" id="GD_GNAME" name="GD_GNAME" value="${goods.GD_GNAME}">
			    				<input type="hidden" id="GD_GID" name="GD_GID" value="${goods.GD_GID}">
							<div class="col-md-12">
				          		<table style="width:80%; margin-left:auto">
				          			<%-- <tr>
				          				<td style="text-align:left">
				          				<b>정가</b>
				          				</td>
				          				<td style="text-align:right; width:50%">
   										<c:if test="${productBean.PSALE == 0}">
				          				${productBean.PPRICE}원
				          				</c:if>
   										<c:if test="${productBean.PSALE != 0}">
				          				<del>${productBean.PPRICE}원</del>
				          				</c:if>
				          				</td>
				          			</tr>
									<tr>
				          				<td style="text-align:left;">
				          				<b>판매가</b>
				          				</td>
				          				<td style="text-align:right; width:50%">
				          				<c:set var="salePrice" value="${productBean.PPRICE * (100-productBean.PSALE) * 0.01}" />
				          				<fmt:formatNumber value="${salePrice}" pattern="#.#" />원
				          				<input type="hidden" id="PPRICE" name="PPRICE" value="${productBean.PPRICE}">
				          				</td>
				          			</tr> --%>
				          			<tr>
				          				<td style="text-align: left">
				          				<b>정가</b>
				          				<td style="text-align: right; width: 50%">${goods.GD_PRICE} 원</td>
				          			</tr>
				          			<tr>
				          				<td style="text-align: left">
				          				<b>할인가</b>
				          				<td style="text-align: right; width: 50%">${goods.GD_DCPRICE} 원
				          				<input type="hidden" id="GD_PRICE" name="GD_PRICE" value="${goods.GD_PRICE}">
				          				<input type="hidden" id="GD_DCPRICE" name="GD_DCPRICE" value="${goods.GD_DCPRICE}">
				          				</td>
				          			</tr>
									<%-- <tr>
				          				<td style="text-align:left;">
				          				<b>할인률</b>
				          				</td>
				          				<td style="text-align:right; width:50%">
				          				${productBean.PSALE}%
				          				<input type="hidden" id="PSALE" name="PSALE" value="${productBean.PSALE}">
				          				</td>
				          			</tr> --%>
									<tr>
				          				<td style="text-align:left;">
				          				<b>수량</b>
				          				</td>
				          				<td id="contain" style="text-align:right; width:50%">
				          					<input type="number" min="0" max="${goods.GD_STOCK}" id="orderCount" name="orderCount" value="1" onChange="getCount()">
				          					<input type="hidden" id="GD_GNAME" name="GD_GNAME" value="${goods.GD_GNAME}">
			    							<input type="hidden" id="GD_GID" name="GD_GID" value="${goods.GD_GID}">
			    							<input type="hidden" id="GD_PRICE" name="GD_PRICE" value="${goods.GD_PRICE}">
			    							<input type="hidden" id="GD_DCPRICE" name="GD_DCPRICE" value="${goods.GD_DCPRICE}">
				          					<input type="hidden" id="GD_STOCK" value="${goods.GD_STOCK}">
				          				</td>
				          			</tr>
									<tr>
				          				<td style="text-align:left;">
				          				<b>총 상품 금액</b>
				          				</td>
				          				<td style="text-align:right; width:50%">
				          				<div id="totalPrice" style="font-weight:bold; font-size:25px; color:#82AE46">
											<%-- <input type="hidden" id="GD_GNAME" name="GD_GNAME" value="${goods.GD_GNAME}">
			    							<input type="hidden" id="GD_GID" name="GD_GID" value="${goods.GD_GID}">
			    							<input type="hidden" id="GD_PRICE" name="GD_PRICE" value="${goods.GD_PRICE}">
				          					<input type="hidden" id="GD_DCPRICE" name="GD_DCPRICE" value="${goods.GD_DCPRICE}">
				          					<input type="hidden" id="GD_STOCK" name="GD_STOCK" value="${goods.GD_STOCK}"> --%>
				          				</div>
				          				</td>
				          			</tr>
				          			<tr>
				          				<td colspan="2" style="text-align:right;">
				          					<p><input type="button" class="btn btn-primary py-2 px-4" onClick="insertCart()" value="장바구니">
				          					<input type="button" class="btn btn-black py-2 px-4" onClick="orderForm()" value="구&emsp;&emsp;매"></p>
				          				</td>
				          			</tr>				          			
				          		</table>
				          	</div>
						</div>
						</div>
			    	</div>
			    </section>
			</div>
		</div>
	</div>

	<div>
		<div>
			<div>
				<section>
					<c:set var="GD_GID" value="${goods.GD_GID}" />
					<%
						String imgDir1 = "resources/img/goods/goods-" + pageContext.getAttribute("GD_GID").toString() + "-detail1.png";
						String imgDir2 = "resources/img/goods/goods-" + pageContext.getAttribute("GD_GID").toString() + "-detail2.png";
						String imgDir3 = "resources/img/goods/goods-" + pageContext.getAttribute("GD_GID").toString() + "-detail3.png";
						String imgDir4 = "resources/img/goods/goods-" + pageContext.getAttribute("GD_GID").toString() + "-detail4.png";
						
						String imgRealDir1 = request.getSession().getServletContext().getRealPath(imgDir1);
						String imgRealDir2 = request.getSession().getServletContext().getRealPath(imgDir2);
						String imgRealDir3 = request.getSession().getServletContext().getRealPath(imgDir3);
						String imgRealDir4 = request.getSession().getServletContext().getRealPath(imgDir4);	
						
						java.io.File file1 = new java.io.File(imgRealDir1);
						java.io.File file2 = new java.io.File(imgRealDir2);
						java.io.File file3 = new java.io.File(imgRealDir3);
						java.io.File file4 = new java.io.File(imgRealDir4);
					%>

					<% if(file1.exists()) { %>
					<p style="text-align:center;"><img src="resources/img/goods/goods-${goods.GD_GID}-detail1.png" /></p>
					<% } %>
					<% if(file2.exists()) { %>
					<p style="text-align:center;"><img src="resources/img/goods/goods-${goods.GD_GID}-detail2.png" /></p>
					<% } %>
					<% if(file3.exists()) { %>
					<p style="text-align:center;"><img src="resources/img/goods/goods-${goods.GD_GID}-detail3.png" /></p>
					<% } %>
					<% if(file4.exists()) { %>
					<p style="text-align:center;"><img src="resources/img/goods/goods-${goods.GD_GID}-detail4.png" /></p>
					<% } %>
				</section>			
			</div>
		</div>
	</div>

	<section>	
	<div class="container">
		<div class="row slider-text justify-content-center align-items-center">
			<div class="col-lg-10 mb-5">
				<table style="text-align:center; margin-left:auto; margin-right:auto; width:80%;">
					<tr>
						<td style="width:80%; text-align:right" colspan="2">
			   				<ul class="product-category">
			   					<li><a href="goodsReviewForm.omc?GD_GID=${GD_GID}">상품 후기 글쓰기</a></li>
			   				</ul>
						</td>
					</tr>
					<c:forEach var="review" items="${reviewList}">
					<tr bgcolor="#E4F7BA">
						<td style="width:60%; text-align:left;">
						<h3 class="heading"><b>${review.BD_TITLE}</b></h3>
						</td>
						<td style="width:20%; text-align:right;">
						<h3 class="heading"><b>${review.BD_ID}</b></h3>
						</td>
						<td style="width:20%; text-align:right;">
						<h3 class="heading"><b>${review.BD_REGDATE}</b></h3>
						</td>
					</tr>
					<tr>
						<td style="width:80%; text-align:left" colspan="2">
						<p>${review.BD_CONTENT}</p>
						</td>
					</tr>
					<tr><td colspan="2"><hr></td></tr>
					</c:forEach>
				</table>
				<hr>				
			</div>
		</div>
	</div>
	<div class="container">
		<div class="row slider-text justify-content-center align-items-center">
			<div class="col-lg-10 mb-5">
				<table style="text-align:center; margin-left:auto; margin-right:auto; width:80%;">
					<tr>
						<td style="width:80%; text-align:right" colspan="2">
			   				<ul class="product-category">
			   					<li><a href="goodsQnaForm.omc?GD_GID=${GD_GID}">상품 문의 글쓰기</a></li>
			   				</ul>
						</td>
					</tr>
					<c:forEach var="qna" items="${qnaList}">
					<tr bgcolor="#E4F7BA">
						<td style="width:60%; text-align:left;">
						<h3 class="heading"><b>${qna.BD_TITLE}</b></h3>
						</td>
						<td style="width:20%; text-align:right;">
						<h3 class="heading"><b>${qna.BD_ID}</b></h3>
						</td>
						<td style="width:20%; text-align:right;">
						<h3 class="heading"><b>${qna.BD_REGDATE}</b></h3>
						</td>
					</tr>
					<tr>
						<td style="width:80%; text-align:left" colspan="2">
						<p>${qna.BD_CONTENT}</p>
						</td>
					</tr>
					<tr><td colspan="2"><hr></td></tr>
					</c:forEach>
				</table>
				<hr>				
			</div>
		</div>
	</div>
	<!-- 상품 수정 임시 버튼 -->
    <div align="center">
		<input type="button" value="상품 수정" class="btn btn-primary py-2 px-2" style="height:55px;" onClick="location.href='goodsModifyForm.omc?GD_GID=${GD_GID}'">
    </div>
	</section>
</body>
</html>