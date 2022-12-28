<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui" %>
<!DOCTYPE html>
<html>
<head>
<%@ include file="/WEB-INF/include/include-header.jspf"%>
<meta charset="UTF-8">
<title>오늘 메뉴 추천, 오메추</title>
</head>
<script>
	function fn_search(pageNo) {
		var comSubmit = new ComSubmit();
		var sort = $("#sortVal").val();
		if(sort == null){
			comSubmit.setUrl('<c:url value="allGoodsList.omc" />');
			comSubmit.addParam("currentPageNo", pageNo);
			comSubmit.submit();
		}else{
			comSubmit.setUrl('<c:url value="allGoodsList.omc" />');
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
		location.href='<c:url value="allGoodsList.omc?sort='+id+'"/>';
	});
});
</script>
<style type="text/css">
.sort-tab {
    width: 100%;
    height: 32px;
    font-size: 11px;
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
</style>
<body>
	<section class="ftco-section">
		<div class="container">
			<div class="row justify-content-center mb-3 pb-3">
				<div class="col-md-12 heading-section text-center ftco-animate">
					<span class="subheading">판매중인 상품</span>
					<h2 class="mb-4">전체 상품</h2>
				</div>
			</div>
			<div id="sortDiv" class="sort-tab center-block">
				<a id="1" >낮은가격순</a>
				<span class="separation-bar">|</span> 
				<a id="2" >높은가격순</a>
			</div>
		</div>
		<form action="allGoodsList.omc" method="GET">
			<div class="container">
				<div class="row">
					<div class="container" align="center">
						<div class="row">
							<c:if test="${not empty sort}">
								<input type="hidden" id="sortVal" value="${sort}">
							</c:if>
							<c:forEach var="goods" items="${allGoodsList}">
								<div class="col-md-6 col-lg-3 ftco-animate"
									style="float: left; width: 33%; padding: 10px;">
									<div class="goods">
										<a href="/goodsDetail.omc?GD_GID=${goods.GD_GID}"
											class="img-prod"><img class="img-fluid"
											src="resources/img/goods/${goods.GD_IMAGE}"
											style="height: 250px;">
											<div class="overlay"></div> </a>
										<div class="text py-3 pb-4 px-3 text-center">
											<h5>
												<a href="/goodsDetail.omc?GD_GID=${goods.GD_GID}">${goods.GD_GNAME}</a>
											</h5>
											<div class="pricing">
												<c:choose>
													<c:when test="${goods.GD_DCPRICE==goods.GD_PRICE}">
														<p class="dcPrice">
														<fmt:formatNumber value="${goods.GD_DCPRICE}" pattern="#,###"/>원
														</p>
														</c:when>
													<c:otherwise>
														<p class="price" style="color: gray;">
														<strike><fmt:formatNumber value="${goods.GD_PRICE}" pattern="#,###"/>원</strike>
														</p>
														<p class="dcPrice">
														<fmt:formatNumber value="${goods.GD_DCPRICE}" pattern="#,###"/>원
														</p>
													</c:otherwise>
												</c:choose>
											</div>
										</div>
									</div>
								</div>
							</c:forEach>
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