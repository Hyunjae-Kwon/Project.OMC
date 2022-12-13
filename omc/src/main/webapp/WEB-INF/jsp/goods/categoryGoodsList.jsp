<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ include file="/WEB-INF/include/include-header.jspf"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<!-- <script>
	function fn_search(pageNo) {
		var comSubmit = new ComSubmit();
		comSubmit.setUrl("<c:url value='/allGoodsListCategory.omc' />");
		comSubmit.addParam("currentPageNo", pageNo);
		comSubmit.submit();
	}
</script> -->
<body>
	<section class="ftco-section">

		<div class="container">
			<div class="row justify-content-center mb-3 pb-3">
				<div class="col-md-12 heading-section text-center ftco-animate">
					<span class="subheading">판매중인 상품</span>
					<h2 class="mb-4">
						<c:if test="${category eq 'KOREAN'}">한식</c:if>
						<c:if test="${category eq 'ITALIAN'}">양식</c:if>
						<c:if test="${category eq 'CHIJAP'}">중식/일식</c:if>
						<c:if test="${category eq 'ASIAN'}">아시안 푸드</c:if>
						<c:if test="${category eq 'SNACKBAR'}">분식</c:if>
						<c:if test="${category eq 'HEALTHY'}">건강식</c:if>
					</h2>
				</div>
			</div>
		</div>
		<form action="allGoodsListCategory.omc" method="GET">
			<div class="container">
				<div class="row">
					<div class="container" align="center">
						<div class="row">
							<c:forEach var="goods" items="${categoryGoodsList}">
								<div class="col-md-6 col-lg-3 ftco-animate"
									style="float: left; width: 33%; padding: 10px;">
									<div class="goods">
										<a href="/goodsDetail.omc?GD_GID=${goods.GD_GID}"
											class="img-prod"><img class="img-fluid"
											src="resources/img/goods/${goods.GD_IMAGE}"
											style="height: 250px;">
											<div class="overlay"></div> </a>
										<div class="text py-3 pb-4 px-3 text-center">
											<h3>
												<a href="/goodsDetail.omc?GD_GID=${goods.GD_GID}">${goods.GD_GNAME}</a>
											</h3>
											<div class="d-flex">
												<div class="pricing">
													<p class="dcPrice">
														<span>할인가 : ${goods.GD_DCPRICE}원</span>
													</p>
													<p class="price">
														<span>판매가 : ${goods.GD_PRICE}원</span>
													</p>
												</div>
											</div>
										</div>
									</div>
								</div>
							</c:forEach>

						</div>
					</div>
				</div>
			</div>
		</form>
		<form id="commonForm" name="commonForm"></form>
		<!-- ***** -->
		<%-- 		<c:if test="${not empty paginationInfo}">
			<ui:pagination paginationInfo="${paginationInfo}" type="text"
				jsFunction="fn_search" />
		</c:if>
		<input type="hidden" id="currentPageNo" name="currentPageNo" /> <br /> --%>
	</section>
</body>
</html>