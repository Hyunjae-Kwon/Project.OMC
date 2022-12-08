<%@ page language="java" contentType="text/html; charset=EUC-KR"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/include/include-header.jspf"%>
<!DOCTYPE html>
<html lang="ko">

<head>

<script charset="utf-8"></script>
<script type="text/javascript">
$(document).ready(function(){ 
	var message = "${message}";
	if(message!=null && message!=''){
		alert(message);
		location.href = "http://localhost:8080/loginForm.omc";
	}else{
		alert("로그인 성공!");
		location.href = "http://localhost:8080/main.omc"
	}
})
</script>
</head>
</html>