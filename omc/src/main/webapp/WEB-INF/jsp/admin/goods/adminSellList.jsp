<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui" %>
<%@ include file="/WEB-INF/include/include-header.jspf" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>오늘의 메뉴 추천, 오메추</title>
</head>

<script>
	function fn_search(pageNo) {
		var comSubmit = new ComSubmit();
		var sort = $("#sortVal").val();
		if(sort == null){
			comSubmit.setUrl('<c:url value="adminSellList.omc" />');
			comSubmit.addParam("currentPageNo", pageNo);
			comSubmit.submit();
		}else{
			comSubmit.setUrl('<c:url value="adminSellList.omc" />');
			comSubmit.addParam("sort", sort)
			comSubmit.addParam("currentPageNo", pageNo);
			comSubmit.submit();
		}
		
		
	}
</script>
<script>
$(document).ready(function(){
	$('#sortDiv a').click(function(){
		var id=$(this).attr('id');
		location.href='<c:url value="adminSellList.omc?sort='+id+'"/>';
	});
});

</script>

<style type="text/css">
.sort-tab {
    width: 100%;
    height: 32px;
    font-size: 18px;
    line-height: 30px;
    color: #828282;
    text-align: right;
}
.sort-tab a{
   cursor:pointer;
}
.sort-tab .on {
    font-weight: bold;
}
.AllSellPrice{
   font-size: 25px;
   text-align: right;
   line-height: 30px;
   width: 100%;
   font-weight: bold;  
}
</style>
<body>
<section class="ftco-section ftco-cart">
	<div style="text-align:center">
		<h2>매출 리스트</h2>
	</div>
	<br>
	<form action="adminSellList.omc" method="get">
		<div class="container">
		   <div id="sortDiv" class="sort-tab center-block">
				<a id="1" >매출액낮은순</a>
				<span class="separation-bar">|</span> 
				<a id="2" >매출액높은순</a>
			</div>
		</div>
		<div class="container">	
			<div class="row">			
				<div class="col-md-12 ftco-animate">			
					<div class="cart-list">
						
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
							   <c:if test="${not empty sort}">
								<input type="hidden" id="sortVal" value="${sort}">
							   </c:if>
								<c:forEach var="goods" items="${sellList}">
								<tr class="text-center">
									<td></td>	
																	
									<td class="product-name">
										<h3><b>${goods.GD_GNAME}</b></h3>
									</td>
									
									<td class="price">
									<fmt:formatNumber value="${goods.GD_DCPRICE}" type= "number" maxFractionDigits="3"/>원
									</td>
									
									<td class="quantity">
										<div class="input-group mb-3">
										   	<input type="text" name="quantity" class="quantity form-control input-number" value="${goods.GD_STOCK}" readonly>
										</div>
									</td>									
									<td class="total">${goods.GD_SELL}</td>
									<td class="totalPrice">
									<c:set var="totalPrice" value="${goods.GD_DCPRICE * goods.GD_SELL}"/>
									<fmt:formatNumber value="${totalPrice}" type= "number" maxFractionDigits="3"/>원</td>
								</tr><!-- END TR-->										    
								</c:forEach>
							</tbody>
						</table>
						
					</div> <!-- end cart-list div -->
					<div class="AllSellPrice">					
					총매출액 :<fmt:formatNumber value="${sum}" type= "number" maxFractionDigits="3"/>원					
					</div>					
					<c:if test="${not empty paginationInfo}">
		            	<ui:pagination paginationInfo="${paginationInfo}" type="text" jsFunction="fn_search" />
		            </c:if>
	            	<input type="hidden" id="currentPageNo" name="currentPageNo" /> <br />					
				</div>
			</div>
	  	</div>
		</form>
		<form id="commonForm" name="commonForm"></form>
	</section>
	
</body>
</html>