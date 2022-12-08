<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>오메추</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
<script charset="utf-8"></script>
</head>

<body>

<div style="text-align:center">
	<br>
	<span style="font-size:xx-large; color:#82ae46;"><b>비밀번호 찾기 결과</b></span>
</div>
<hr>
<div style="text-align:center">
	
         <c:if test="${empty list  || list==''}">
         <br><br><br>
         존재하지 않는 회원 입니다.
         <br><br><br>
         </c:if>
         
         <c:if test="${list!=null && list!=''}">
	 <c:forEach items="${list}" var="item" varStatus="i">
	
		고객님께서 가입하신 비밀번호는
		<div><c:out value="${item.MEM_PW}"/></div>
		입니다.
	</c:forEach>

</c:if>
	
	<p></p>		

	<input type="button" value="로그인" class="btn btn-primary py-2 px-4"
			onClick="location.href='/loginForm.omc'">
			
			<!--   비밀번호 못찾았을때 뒤로가기 버튼 추가함 -->
	<input type="button" value="뒤로가기" class="btn btn-light py-2 px-4"
			onClick="location.href='/findPw.omc'">    
		
	<p></p>		

</div>

</body>
</html>