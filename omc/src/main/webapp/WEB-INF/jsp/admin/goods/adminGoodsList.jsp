<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ include file="/WEB-INF/include/include-header.jspf" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>오늘의 메뉴 추천, 오메추</title>
<script>
function deleteCheck(gid, index) {
	if(confirm("삭제하시겠습니까?") == true) {
		
		$.ajax({
			url			: "adminGoodsDelete.omc",
			data		: {"GD_GID" : gid},
			contentType	: "application/json",
			success		: function(data) {
				alert("삭제하였습니다.");
				$("#pro"+index).remove();
			},
			error:function(request, error) {
				alert("fail");
				// error 발생 이유를 알려준다.
			alert("code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error);
			}
		});
	}
}
</script>
<script>
window.onload = function() {
	document.getElementById("keyword").focus();	
}
</script>
</head>

<body class="goto-here">
<section class="ftco-section ftco-cart">
	<div style="text-align:center"><h2>상품 리스트</h2></div>
	<br/>
		<div class="container" style="padding-right:70px;">
		<div class="col-xl-12 ftco-animate center">
			<div class="row">
				<br>
				
			<div class="col-md-6 ml-auto">
				<form action="adminGoodsList.omc">
					<div class="form-group d-flex">
						<input type="text" name="keyword" id="keyword" class="form-control" value="${keyword}">
						<input type="submit" value="검색" class="btn-black px-4">
						&nbsp;
					<div align="right">
						<input type="button" value="상품 추가" class="btn btn-primary py-2 px-2" style="height:55px;" onClick="location.href='adminGoodsWriteForm.omc'">
		        	</div>	
					</div>
				</form>
			</div>
				
				
				<div class="container">				
					<div class="cart-list">
							<table class="table">
								<thead class="thead-primary">
									<tr class="text-center">
										<th>상품번호</th>
										<th>카테고리</th>
										<th colspan="2">상품명</th>										
										<th>가격</th>
										<th>재고량</th>
										<th>&nbsp;</th>
									</tr>
								</thead>
								 
								<tbody>
									<c:forEach var="goods" items="${adminGoodsList}" varStatus="status">
									<tr id="pro${status.index}">
										<td>${goods.GD_GID}</td>
										<td>${goods.GD_CATEGORY}</td>										
										<td>
										   	<div style="width:90px; height:70px;">
												<a href="goodsDetail.omc?GD_GID=${goods.GD_GID}" class="img-prod"><img class="img-fluid" src="/resources/img/goods/${goods.GD_IMAGE}"></a>
											</div> 
										</td>
										<td>
										    <a href="adminGoodsModifyForm.omc?GD_GID=${goods.GD_GID}">${goods.GD_GNAME}</a>
										</td>
										<td>${goods.GD_PRICE}</td>
										<td>${goods.GD_STOCK}</td>
										<td>
											<input type="hidden" id="del${goods.GD_GID}" value="${goods.GD_GID}">
											<a href="javascript:deleteCheck(${goods.GD_GID}, ${status.index});">삭제</a>
										</td>
									</tr>
									</c:forEach>
								</tbody>
							</table>
						</div>
						
						${paging.pageHtml}
					</div>
				</div>
		</div>
	</div>
</section>

<br>
</body>
</html>