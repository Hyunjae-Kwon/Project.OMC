<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ include file="/WEB-INF/include/include-header.jspf" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>오늘의 메뉴 추천, 오메추</title>
</head>
<body >
<section>
<div class="container" style="text-align:center;">
	<div class="row justify-content-center">
		<div class="col-xl-7 ftco-animate">
			<form method="post" id="adminOrderDetail" action="adminOrderModify.omc"
				name="adminOrderDetail">
			<h2 class="mb-4 billing-heading">주문 상세보기</h2>
				<div class="row align-items-end" style="padding-left:150px;">
					
					<!-- 주문번호 -->					
					<div class="form-group">
						<h6 class="mb-4" style="text-align:left;">주문번호</h6>
						<input type="text" class="form-control" id="OD_OID" name="OD_OID" style="width:400px;"
							value="${orderDetail.OD_OID}" readonly>
					</div>
					<div class="w-100"></div>
           
					<!-- 주문일 -->
					<div class="form-group">
						<h6 class="mb-4" style="text-align:left;">주문일</h6>
						<input type="text" id="OD_REGDATE"  class="form-control" style="width:400px;"
							value="${orderDetail.OD_REGDATE}" readonly>
					</div>
					<div class="w-100"></div>
					
					<!-- 이메일 -->
					<div class="form-group">
						<h6 class="mb-4" style="text-align:left;">아이디</h6>
						<input type="text" id="OD_ID" name="OD_ID"  class="form-control" style="width:400px;"
							value="${orderDetail.OD_ID}" readonly>
					</div>
					<div class="w-100"></div>
					
					<!-- 상품명 -->
					<div class="form-group">
						<h6 class="mb-4" style="text-align:left;">상품명</h6>
						<input type="text" id="OD_GNAME" name="OD_GNAME"  class="form-control" style="width:400px;"
							value="${orderDetail.OD_GNAME}" readonly>
					</div>
					<div class="w-100"></div>
					
					<!-- 주소 -->
					<div class="form-group">
						<h6 class="mb-4" style="text-align:left;">주소</h6>
						<input type="text" id="OD_ZIPCODE" name="OD_ZIPCODE"  class="form-control" style="width:200px;"
							value="${orderDetail.OD_ZIPCODE}" readonly><br>
						<input type="text" id="OD_ADD1" name="OD_ADD1"  class="form-control" style="width:400px;"
							value="${orderDetail.OD_ADD1}" readonly><br>
						<input type="text" id="OD_ADD2" name="OD_ADD2"  class="form-control" style="width:400px;"
							value="${orderDetail.OD_ADD2}" readonly>
					</div>
					<div class="w-100"></div>
					
					<!-- 상품금액 -->
					<div class="form-group">
						<h6 class="mb-4" style="text-align:left;">상품금액</h6>
						<input type="text" id="OD_PRICE" name="OD_PRICE"  class="form-control" style="width:400px;"
							value="${orderDetail.OD_PRICE}" readonly>
					</div>
					<div class="w-100"></div>					
					
					<!-- 상품수량 -->
					<div class="form-group">
						<h6 class="mb-4" style="text-align:left;">상품수량</h6>
						<input type="text" id="OD_COUNT" name="OD_COUNT"  class="form-control" style="width:400px;"
							value="${orderDetail.OD_COUNT}" readonly>
					</div>
					<div class="w-100"></div>
					
					<!-- 총금액 -->
					<div class="form-group">
						<h6 class="mb-4" style="text-align:left;">총금액</h6>
						<input type="text" id="OD_TOTAL" name="OD_TOTAL"  class="form-control" style="width:400px;"
							value="${orderDetail.OD_TOTAL}" readonly>
					</div>
					<div class="w-100"></div>
					
					<!-- 배송상태 -->
					<div class="select-wrap">
					<h6 class="mb-4" style="text-align:left;">배송상태</h6>
						<select name="OD_STATUS" id="OD_STATUS" class="form-control" style="width:400px;">
							<option id="OD_STATUS" <c:if test="${orderDetail.OD_STATUS eq '상품준비중'}">selected</c:if> value="상품준비중">상품준비중</option>
		                    <option id="OD_STATUS" <c:if test="${orderDetail.OD_STATUS eq '배송중'}">selected</c:if> value="배송중">배송중</option>
		                    <option id="OD_STATUS" <c:if test="${orderDetail.OD_STATUS eq '배송완료'}">selected</c:if> value="배송완료">배송완료</option>
						</select>
					</div>&nbsp;
					<div class="w-100"></div>
					<br/><br/>
					
					<br/><br/><br/><br/>
				</div>
		
				<!-- 버튼 -->
				<div class="form-group" align="center">
					 <button type="submit" class="btn btn-black py-2 px-3" >저장</button>

			         <button type="button" class="btn btn-primary py-2 px-3" onclick="history.back()">취소</button>
				     
				</div>
			</form>	
		</div>
	</div>
</div>
</section>
<br><br>
<!-- <a href="/Jumo/adminOrderModify.al">저장 - 주문정보 수정 기능</a><br>
<a href="/Jumo/adminOrderList.al">취소 - 주문정보 리스트</a><br> -->
</body>
</html>