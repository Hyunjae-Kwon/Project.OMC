<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%--  <%@ include file="/WEB-INF/include/include-header.jspf" %>  --%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>오메추</title>
</head>
<script>
/* 아이디 찾기 유효성 체크 */
function formCheck() {
	var form = document.getElementById("findIdForm");
	var MEM_NAME = document.getElementById("MEM_NAME");
	var MEM_PHONE = document.getElementById("MEM_PHONE");
	
	if(MEM_NAME.value.trim()=="") {
		alert("이름을 입력해주세요.");
		MEM_NAME.focus();
		return false;
	} else if(MEM_PHONE.value.trim()=="") {
		alert("휴대폰번호를 입력해주세요.");
		MEM_PHONE.focus();
		return false;
	} 
	else {	
		form.submit();
	}
}

/* 비밀번호를 다 입력하고 엔터키 입력시 로그인 시도 */
function keyPress() {
	if(window.event.keyCode ==13) {
		return formCheck();	
	}	
}
</script>
<script>
$(document).on("keyup", "input[numberOnly]", function() {$(this).val( $(this).val().replace(/[^0-9]/gi,"") );})
$(document).on("keyup", "input[noBlank]", function() {$(this).val( $(this).val().replace(/\s/gi,"") );})
$(document).on("keyup", "input[noBlank]", function() {$(this).val( $(this).val().replace("관리자","") );})
</script>
<script>
/* 첫 화면 로딩 시 포커스 */
window.onload = function() {
	document.getElementById("MEM_NAME").focus();
}
</script>
<body>
<div class="container" style="text-align:center;">
	<div class="row slider-text justify-content-center align-items-center">
		<div class="col-xl-12 ftco-animate">
			<form id="findIdForm" action="/findIdResult.omc" method="post" class="billing-form">
			<h2 class="mb-4 billing-heading">아이디 찾기</h2><br>
			<hr><br>
			<div class="row align-items-end"  style="text-align:center">

				<div class="col-md-4 mx-auto" align="center">
					<h6 class="mb-4" style="text-align:center;">이름</h6>
					<div class="form-group d-flex">
					<input type="text" id="MEM_NAME" name="MEM_NAME"  class="form-control" maxlength="8" noBlank>
				</div>
				</div>
				<div class="w-100"></div>
				
				<div class="col-md-4 mx-auto">
					<h6 class="mb-4" style="text-align:center;">휴대폰번호</h6>
					<div class="form-group d-flex">
						<input type="text" id="MEM_PHONE" name="MEM_PHONE" maxlength="11"  class="form-control"
								numberOnly>
					</div>
				</div>
				<div class="w-100"></div>
			<p></p>
			
			<div class="col-md-6 mx-auto">
			<input type="button" value="찾기" class="btn btn-primary py-2 px-4" style="padding-right:30px;"
				onClick="return formCheck()">
				</div>
				</div>
			<p></p>		
			</form>
		</div>
	</div>
</div>
<br><br><br><br><br><br>
</body>
</html>