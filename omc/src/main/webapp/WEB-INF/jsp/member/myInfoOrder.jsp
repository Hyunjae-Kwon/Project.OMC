<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ include file="/WEB-INF/include/include-header.jspf"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>오늘의 메뉴 추천, 오메추</title>
</head>
<script>
	function fn_search(pageNo) {
		var comSubmit = new ComSubmit();
		comSubmit.setUrl("<c:url value='/myOrderList.omc' />");
		comSubmit.addParam("currentPageNo", pageNo);
		comSubmit.submit();
	}
</script>
<body class="goto-here">
	<div class="container">
		<div class="row">
			<div class="col-sm-2">
				<div class="sidebar-box ftco-animate" id="menu"
					style="margin-top: 30px;">
					<ul class="categories">
						<li><a href="/myPage.omc" style="font-size: middle;">마이페이지</a></li>
						<c:if test="${MEM_ID != 'ADMIN'}">
							<li><a href="/myInfoModify.omc" style="font-size: middle;">회원정보
									수정</a></li>
						</c:if>
						<li><a href="/myOrderList.omc"
							style="font-size: middle; color: #fd7e14;">주문조회</a></li>
						<li><a href="/myReviewList.omc" style="font-size: middle;">후기</a></li>
						<li><a href="/myQnaList.omc" style="font-size: middle;">문의내역</a></li>
					</ul>
				</div>
			</div>
			<div class="col-sm-10">
				<div style="text-align: center">
					<h3>주문 내역</h3>
				</div>

				<div class="container" align="center">
					<br>
					<br>
					<div class="row">
						<div class="col-md-12 ftco-animate">
							<div class="cart-list">
								<table class="table">
									<thead class="thead-primary">
										<tr class="text-center">
											<th>주문날짜</th>
											<th>주문내용</th>
											<th>결제금액</th>
											<th>진행상태</th>
											<th>주문번호</th>
										</tr>
									</thead>
									<tbody>
										<c:choose>
											<c:when test="${orderCount!=0}">
												<c:forEach var="order" items="${order}" varStatus="status">
													<tr class="text-center">
														<td><fmt:formatDate value="${order.OD_REGDATE}"
																pattern="yy/MM/dd" type="date" /></td>

														<c:choose>
															<c:when test="${order.CNT!=1}">
																<td><a
																	href="myOrderDetail.omc?OD_NUM=${order.OD_NUM}">${order.OD_GNAME}
																		외 ${order.CNT-1}건</a> <input type="hidden" id="OD_NUM"
																	name="OD_NUM" value="${order.OD_NUM}"></td>
															</c:when>
															<c:otherwise>
																<td><a
																	href="myOrderDetail.omc?OD_NUM=${order.OD_NUM}">${order.OD_GNAME}</a>
																	<input type="hidden" id="OD_NUM" name="OD_NUM"
																	value="${order.OD_NUM}"></td>
															</c:otherwise>
														</c:choose>

														<td><fmt:formatNumber value="${order.TOTALPAY}"
																pattern="#,###" />원</td>

														<td>${order.OD_STATUS}</td>

														<td>${order.OD_NUM}</td>
													</tr>
												</c:forEach>
											</c:when>

											<c:otherwise>
												<tr>
													<td colspan="5">주문한 상품이 없습니다.</td>
												</tr>
											</c:otherwise>

										</c:choose>

									</tbody>
								</table>
							</div>
						</div>
					</div>
					<form id="commonForm" name="commonForm"></form>
					<!-- ***** -->
					<c:if test="${not empty paginationInfo}">
						<ui:pagination paginationInfo="${paginationInfo}" type="text"
							jsFunction="fn_search" />
					</c:if>
					<input type="hidden" id="currentPageNo" name="currentPageNo" /> <br />
				</div>
			</div>
		</div>
	</div>

	<br>
	<br>
	<br>
</body>
</html>