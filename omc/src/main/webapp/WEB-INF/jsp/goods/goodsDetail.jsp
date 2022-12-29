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
	var count = document.getElementById('orderCount').value;
	var price = document.getElementById('GD_PRICE').value;
	var salePrice = document.getElementById('GD_DCPRICE').value;
	var totalPrice = salePrice * count;
	
	document.getElementById("totalPrice").innerText = totalPrice.toLocaleString() + '원';
	return count;
}

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
		if(confirm("장바구니에 상품을 담았습니다.\n장바구니로 이동하시겠습니까?") == true){
			location.href="<c:url value='myCart.omc'/>";
		}	      
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
				          			<c:choose>
										<c:when test="${goods.GD_DCPRICE==goods.GD_PRICE}">
											<tr>
						          				<td style="text-align: left">
						          				<b>정가</b>
						          				<td style="text-align: right; width: 50%">
						          				<fmt:formatNumber value="${goods.GD_DCPRICE}" pattern="#,###"/>원
						          				<input type="hidden" id="GD_PRICE" name="GD_PRICE" value="${goods.GD_PRICE}">
						          				<input type="hidden" id="GD_DCPRICE" name="GD_DCPRICE" value="${goods.GD_DCPRICE}">
						          				</td>
						          			</tr>
										</c:when>
										<c:otherwise>
											<tr>
						          				<td style="text-align: left">
						          				<b>정가</b>
						          				<td style="text-align: right; width: 50%"><fmt:formatNumber value="${goods.GD_PRICE}" pattern="#,###"/>원</td>
						          			</tr>
						          			<tr>
						          				<td style="text-align: left">
						          				<b>할인가</b>
						          				<td style="text-align: right; width: 50%">
						          				<b><fmt:formatNumber value="${goods.GD_DCPRICE}" pattern="#,###"/>원</b>
						          				<input type="hidden" id="GD_PRICE" name="GD_PRICE" value="${goods.GD_PRICE}">
						          				<input type="hidden" id="GD_DCPRICE" name="GD_DCPRICE" value="${goods.GD_DCPRICE}">
						          				</td>
						          			</tr>
										</c:otherwise>
									</c:choose>
				          			<tr>
				          				<td style="text-align:left;">
				          				<b>수량</b>
				          				</td>
				          				<td id="contain" style="text-align:right; width:50%">
				          					<input type="number" min="1" max="${goods.GD_STOCK}" id="orderCount" name="orderCount" value="1" onChange="getCount()">
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
				          				<div id="totalPrice" style="font-weight:bold; font-size:25px; color:#fd7e14"></div>
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
					<h3 class="heading" style="text-align: center;">상품 상세 정보</h3><br/><br/><br/>
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
					<h3 style="text-align: center;">상품 후기</h3>
					<tr>
						<td style="width:80%; text-align:right" colspan="3">
			   				<ul class="product-category">
			   					<li><a href="goodsReviewForm.omc?GD_GID=${GD_GID}">상품 후기 글쓰기</a></li>
			   				</ul>
						</td>
					</tr>
					<tr>
						<th>내 용</th>
						<th style="text-align: right;">작성자</th>
						<th>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;작성 일자</th>
					</tr>
					<c:forEach var="review" items="${reviewList}">
					<tr bgcolor="#FFFFFF">
						<td style="width:60%; text-align:left;">
						<h5 class="heading"><b>${review.BD_TITLE}</b></h5>
						</td>
						<td style="width:20%; text-align:right;">
						<h5 class="heading"><b>${review.BD_ID}</b></h5>
						</td>
						<td style="width:20%; text-align:right;">
						<h5 class="heading"><b><fmt:formatDate value="${review.BD_REGDATE}" pattern="yy/MM/dd" type="date"/></b></h5>
						</td>
					</tr>
					<tr>
						<td style="width:80%; text-align:left" colspan="3">
						<p>${review.BD_CONTENT}</p>
						</td>
					</tr>
					<tr><td colspan="3"><hr></td></tr>
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
					<h3 style="text-align: center;">상품 문의</h3>
					<tr>
						<td style="width:80%; text-align:right" colspan="3">
			   				<ul class="product-category">
			   					<li><a href="goodsQnaForm.omc?GD_GID=${GD_GID}">상품 문의 글쓰기</a></li>
			   				</ul>
						</td>
					</tr>
					<tr>
						<th>내 용</th>
						<th style="text-align: right;">작성자</th>
						<th>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;작성 일자</th>
					</tr>
										
					<c:forEach var="qna" items="${qnaList}">
					<tr bgcolor="#FFFFFF">
						<td style="width:60%; text-align:left;">
						<h5 class="heading"><b>${qna.BD_TITLE}</b></h5>
						</td>
						<td style="width:20%; text-align:right;">
						<h5 class="heading"><b>${qna.BD_ID}</b></h5>
						</td>
						<td style="width:20%; text-align:right;">
						<h5 class="heading"><b><fmt:formatDate value="${qna.BD_REGDATE}" pattern="yy/MM/dd" type="date"/></b></h5>
						</td>
					</tr>
					<tr>
						<td style="width:80%; text-align:left" colspan="3">
						<p>${qna.BD_CONTENT}</p>
						</td>
					</tr>
					<tr><td colspan="3"><hr></td></tr>
					</c:forEach>
				</table>
				<hr>				
			</div>
		</div>
		<!-- 관리자일 경우 관리 탭 추가 -->
        <c:if test="${ MEM_ID == 'ADMIN' }">
		    <div align="right">
				<input type="button" value="상품 수정" class="btn btn-primary py-2 px-2" style="height:55px;" onClick="location.href='adminGoodsModifyForm.omc?GD_GID=${GD_GID}'">
		    </div>
        </c:if>
		
	</div>
	</section>
</body>
</html>