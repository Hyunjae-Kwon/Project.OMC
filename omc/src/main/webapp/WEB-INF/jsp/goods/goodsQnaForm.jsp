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
function confirmQna() {
	var form = document.getElementById("qnaForm");
	var GD_GID = document.getElementById("GD_GID");
	var BD_ID = document.getElementById("BD_ID");
	var BD_CONTENT = document.getElementById("BD_CONTENT");
	var BD_TITLE = document.getElementById("BD_TITLE");
	
	if(confirm("상품 문의를 등록하시겠습니까?") == true) {
		if(BD_TITLE.value.trim()=="") {
			alert("제목을 입력해주세요.");
			BD_TITLE.focus();	
			return false;
		} else if(BD_CONTENT.value.trim()=="") {
			alert("내용을 입력해주세요.");
			BD_CONTENT.focus();
			return false;
		} else {	
			form.submit();
		}
	}
}
window.onload = function() {
	document.getElementById("BD_TITLE").focus();
}
</script>
</head><body>
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
	</section>
</body>

	<section>	
	<div class="container">
		<div class="row slider-text justify-content-center align-items-center">
			<div class="col-lg-10 mb-5">

					<form id="qnaForm" action="goodsQna.omc" method="post">
					<table style="text-align:center; margin-left:auto; margin-right:auto; width:80%;">
						<tr>
							<td style="width:10%; text-align:left">
			   					작성자
			   				</td>
			   				<td>
			   				 	${memInfo.MEM_ID}
			   					<input type="hidden" id="BD_ID" name="BD_ID" value="${memInfo.MEM_ID}">
			   					<input type="hidden" id="BD_GID" name="BD_GID" value="${goods.GD_GID}">
							</td>
						</tr>
						<tr>
							<td style="width:10%; text-align:left;">
								제목 
							</td>
							<td>
								<input type="text" id="BD_TITLE" name="BD_TITLE" style="width:90%;" maxlength="50">
							</td>
						</tr>
						<tr>
							<td style="width:100%; text-align:left;" colspan="2">
								<textarea name="BD_CONTENT" id="BD_CONTENT" cols="30" rows="7" maxlength="500" class="form-control"></textarea>
							</td>
						</tr>
						<tr>
							<td style="width:100%; text-align:left;" colspan="2">
								<input type="button" class="btn btn-primary py-2 px-3"
									onClick="return confirmQna()" value="작성">
								<a href="goodsDetail.omc?GD_GID=${goods.GD_GID}" class="btn btn-black py-2 px-3">취소</a>
							</td>
						</tr>
					</table>
					</form>
					<hr>				

			</div>
		</div>
	</div>
	</section>	
</body>
</html>