<%@ page import="org.springframework.web.bind.annotation.SessionAttributes"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>오늘의 메뉴 추천, 오메추</title>
</head>
<script>
function escapeCheck() {
	if(confirm("탈퇴하시겠습니까?") == true) {
		location.href = "/deleteMember.omc";
	}
}
</script>
<body>
<br><br>
<div style="text-align:center">

	<div style="text-align: center;">
		<!-- <img src="resources/img/mypage_main.png" width="80px" height="80px"> -->
		<img src="resources/img/omc_logoIcon.png" width="80px" height="80px">
	</div>
	<br>
	<p></p>
	
	<h3 style="text-align: center;">${MEM_ID}님 환영합니다.</h3>
	
	<p></p>
	<c:if test="${MEM_ID != 'ADMIN'}">
		<a href="/myInfoModify.omc" class="nav-link" style="font-size:large; color:#fd7e14;">회원 정보 수정</a>
	</c:if>
		
	<p></p>		

	<a href="/myOrderList.omc" class="nav-link" style="font-size:large; color:#fd7e14;">주문조회</a>

	<p></p>
	
	<a href="/myReviewList.omc" class="nav-link" style="font-size:large; color:#fd7e14;">후기</a>
	
	<p></p>
	
	<a href="/myQnaList.omc" class="nav-link" style="font-size:large; color:#fd7e14;">문의내역</a>
	
	<p></p>
		
	<c:if test="${MEM_ID != 'ADMIN'}">	
		<a href="javascript:escapeCheck()" class="nav-link" style="font-size:large; color:#fd7e14;">회원탈퇴</a>
	</c:if>
	
	<p></p>				
</div>
<br><br>
</body>
</html>