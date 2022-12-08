<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>오메추</title>
</head>
<body>

<div style="text-align:center">
	<br>
	<span style="font-size:xx-large; color:#82ae46;"><b>아이디 찾기 결과</b></span>
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
	
		고객님께서 가입하신 아이디는
		<div><c:out value="${item.MEM_ID}"/></div>
		입니다.
	</c:forEach>

</c:if>
		<p></p>
		<input type="button" value="로그인" class="btn btn-primary py-2 px-4"
			onClick="location.href='/loginForm.omc'">
		<input type="button" value="회원가입" class="btn btn-light py-2 px-4"
			onClick="location.href='/joinForm.omc'">
		
	<p></p>		
</div>

</body>
</html>