<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%@include file="/WEB-INF/include/include-header.jspf" %> 

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>로그인폼</title>
</head>
<style>
#MEM_ID {
  display: block;
  min-width : 15%;
  max-width : 100%;
  height: calc(2.25rem + 2px);
  margin : 0 auto;
  padding: 0.375rem 0.75rem;
  font-size: 1rem;
  font-weight: 400;
  line-height: 1.5;
  color: #495057;
  background-color: #fff;
  background-clip: padding-box;
  border: 1px solid #ced4da;
  border-radius: 0.25rem;
}
#MEM_PW {
  display: block;
  min-width : 15%;
  max-width : 100%;
  height: calc(2.25rem + 2px);
  margin : 0 auto;
  padding: 0.375rem 0.75rem;
  font-size: 1rem;
  font-weight: 400;
  line-height: 1.5;
  color: #495057;
  background-color: #fff;
  background-clip: padding-box;
  border: 1px solid #ced4da;
  border-radius: 0.25rem;
}
</style>
<script type="text/javascript">
/* 로그인 유효성 체크 */
function fsubmit() {
	var mem_id = $("#mem_id").val()
	var mem_pw = $("#mem_pw").val()

	if (mem_id == null || mem_id == '') {
		alert("아이디를 입력하세요.");
		return 0;
	} else if (mem_pw == null || mem_pw == '') {
		alert("비밀번호를 입력하세요");
		return 0;
	}

	
	$("#MEM_ID").val(mem_id);
	$("#MEM_PW").val(mem_pw);

	loginForm.submit();
}

/* 로그인 화면에서 엔터키 입력시 로그인 시도 */
function keyPress() {
	if(window.event.keyCode ==13) {
		return fsubmit();	
	}	
}

/* 첫 화면 로딩 시 포커스 */
window.onload = function() {
	document.getElementById("mem_id").focus();
}
</script>
<!-- <script>
$(document).on("keyup", "input[noSpecial]", function() {$(this).val( $(this).val().replace(/[^ㄱ-힣a-zA-Z0-9@]/gi,"") );})
$(document).on("keyup", "input[noBlank]", function() {$(this).val( $(this).val().replace(/\s/gi,"") );})
</script> -->
<body>

<div style="text-align:center">
	<br>
	<span style="font-size:xx-large; color:#82ae46;"><b>로 그 인</b></span>
</div>
<hr>
<div style="text-align:center">
	<form id="loginForm" action="/login.omc" method="post">
		<label for="ID">ID</label>
		<br/>
		<input type="text" id="mem_id" name="MEM_ID" maxlength="10">
		<br/>
		<label for="PW">비밀번호</label>
		<br/>
		<input type="password" id="mem_pw" name="MEM_PW" onkeypress="keyPress()" >
	
		<!-- 아이디 저장 &nbsp; <input type="checkbox" id="ID_SAVE" name="ID_SAVE"><br> -->
		<p></p>
		
		<input type="button" value="로 그 인" class="btn btn-primary py-2 px-4"
			onClick="fsubmit();">
		<input type="button" value="회원가입" onClick="location.href='/joinForm.omc'" class="btn btn-primary py-2 px-4"><br>
		
		<p></p>
		<a href="/findId.omc" class="nav-link" style="font-size:large; color:#82ae46;">아이디 찾기</a>
		<a href="/findPw.omc" class="nav-link" style="font-size:large; color:#82ae46;">비밀번호 찾기</a>

	</form>
</div>

</body>
</html>