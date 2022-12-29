<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/include/include-header.jspf"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" href="resources/css/join.css">
<meta charset="UTF-8">
<title>오메추</title>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
<script>
/* 아이디 중복 체크 */
function checkId() {
    
    var MEM_ID = $("#MEM_ID").val();
    
    if(MEM_ID.search(/\s/) != -1) { 
        alert("아이디에는 공백이 들어갈 수 없습니다.");        
    } else {             
        if(MEM_ID.trim().length != 0) {
        	
            $.ajax({
                url: "/confirmId.omc", //통신할 url
                data: { "MEM_ID" : MEM_ID}, // 좌항-변수, 우항-입력된 데이터
                contentType: "application/json",
                method: "POST",
                success: function(data) {   
                	if(data.message == "사용할 수 있는 아이디입니다.") {
                		alert(data.message);
                		$("#submit").removeAttr("MEM_ID");
                	} else {
                		alert(data.message);
                        $("#submit").attr("MEM_ID", "MEM_ID");
                        window.location.reload();
                	}
                },
        		error:function(request, error) {
        			alert("fail");
        			// error 발생 이유를 알려준다.
        		alert("code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error);
        		}      
            });
        } else {
            alert("아이디를 입력해주세요.");
        }        
    }
}
</script>
<script>
	function checks() {
		var MEM_ID = document.getElementById("MEM_ID");
		var MEM_PW = document.getElementById("MEM_PW");
		var MEM_PW2 = document.getElementById("MEM_PW2");
		var MEM_NAME = document.getElementById("MEM_NAME");
		var MEM_ADD1 = document.getElementById("MEM_ADD1");
		var MEM_ADD2 = document.getElementById("MEM_ADD2");
		var MEM_ZIPCODE = document.getElementById("MEM_ZIPCODE");
		var MEM_PHONE = document.getElementById("MEM_PHONE");
		
		if(MEM_ID.value.trim() == ""){
			alert("아이디를 입력해주세요.");
			MEM_ID.focus();
			return false;
		}
		
		if(MEM_PW.value.trim() == ""){
			alert("비밀번호를 입력해주세요.");
			MEM_PW.focus();
			return false;
		}
		
		if(MEM_PW.value != MEM_PW2.value){
			alert("비밀번호가 일치하지 않습니다.");
			MEM_PW2.focus();
			return false;
		}
		
		if(MEM_NAME.value.trim() == ""){
			alert("이름을 입력해주세요.");
			MEM_NAME.focus();
			return false;
		}
		
		if(MEM_ADD1.value.trim() == ""){
			alert("주소를 입력해주세요.");
			MEM_ADD1.focus();
			return false;
		}
		
		if(MEM_ADD2.value.trim() == ""){
			alert("상세 주소를 입력해주세요.");
			MEM_ADD2.focus();
			return false;
		}
		
		if(MEM_ZIPCODE.value.trim() == ""){
			alert("우편번호를 입력해주세요.");
			MEM_ZIPCODE.focus();
			return false;
		}

		if(MEM_PHONE.value.trim() == ""){
			alert("핸드폰 번호를 입력해주세요.");
			MEM_PHONE.focus();
			return false;
		}		
		document.joinForm.submit();
	}
</script>
<script>
$(document).ready(function() {
	var MEM_ID=$('#MEM_ID').val();
});
</script>
<script
	src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
<script>
    function sample6_execDaumPostcode() {
        new daum.Postcode({
            oncomplete: function(data) {
                // 팝업에서 검색결과 항목을 클릭했을때 실행할 코드를 작성하는 부분.

                // 각 주소의 노출 규칙에 따라 주소를 조합한다.
                // 내려오는 변수가 값이 없는 경우엔 공백('')값을 가지므로, 이를 참고하여 분기 한다.
                var addr = ''; // 주소 변수
                var extraAddr = ''; // 참고항목 변수

                //사용자가 선택한 주소 타입에 따라 해당 주소 값을 가져온다.
                if (data.userSelectedType === 'R') { // 사용자가 도로명 주소를 선택했을 경우
                    addr = data.roadAddress;
                } else { // 사용자가 지번 주소를 선택했을 경우(J)
                    addr = data.jibunAddress;
                }

                // 사용자가 선택한 주소가 도로명 타입일때 참고항목을 조합한다.
                if(data.userSelectedType === 'R'){
                    // 법정동명이 있을 경우 추가한다. (법정리는 제외)
                    // 법정동의 경우 마지막 문자가 "동/로/가"로 끝난다.
                    if(data.bname !== '' && /[동|로|가]$/g.test(data.bname)){
                        extraAddr += data.bname;
                    }
                    // 건물명이 있고, 공동주택일 경우 추가한다.
                    if(data.buildingName !== '' && data.apartment === 'Y'){
                        extraAddr += (extraAddr !== '' ? ', ' + data.buildingName : data.buildingName);
                    }
                    // 표시할 참고항목이 있을 경우, 괄호까지 추가한 최종 문자열을 만든다.
                    if(extraAddr !== ''){
                        extraAddr = ' (' + extraAddr + ')';
                    }
                    // 조합된 참고항목을 해당 필드에 넣는다.
                    document.getElementById("MEM_ADD2").value = extraAddr;
                
                } else {
                    document.getElementById("MEM_ADD2").value = '';
                }

                // 우편번호와 주소 정보를 해당 필드에 넣는다.
                document.getElementById('MEM_ZIPCODE').value = data.zonecode;
                document.getElementById("MEM_ADD1").value = addr;
                // 커서를 상세주소 필드로 이동한다.
                document.getElementById("MEM_ADD2").focus();
            }
        }).open();
    }
</script>
<script>
/* 첫 화면 로딩 시 포커스 */
window.onload = function() {
	document.getElementById("MEM_ID").focus();
}
</script>
</head>
<body>
	<div id="container">
		<div id="contents">
			<div class="sub_content">
				<div class="content_box" style="padding-left: 300px;">
					<div class="join_base_wrap" >
						<div class="member_tit" style="text-align:center;">
							<form id="joinForm" name="joinForm" method="POST" action="/joinSuccess.omc" class="billing-form">
								<h2>회원가입</h2>
						</div>
						<!-- //member_tit -->
						<div class="member_cont">
							<!-- 회원가입/정보 기본정보 -->
							<div class="base_info_box">
								<h3>기본정보</h3>
								<div class="base_info_sec">
									<table border="0" cellpadding="0" cellspacing="0">
										<colgroup>
											<col width="25%">
											<col width="75%">
										</colgroup>
										<tbody>
											<tr>
												<th><span class="important">아이디</span></th>
												<td>
													<div class="member_warning">
														<input type="text" style="width: 200px;" id="MEM_ID" name="MEM_ID" data-pattern="gdMemberId">
														<input type="button" value="중복확인" class="submit px-3" name="check_id" onClick="checkId()">
													</div>
												</td>
											</tr>
											<tr class="">
												<th><span class="important">비밀번호</span></th>
												<td class="member_password">
													<div class="member_warning">
														<input type="password" style="width: 200px;" id="MEM_PW" name="MEM_PW" autocomplete="off" placeholder="">
													</div>
												</td>
											</tr>
											<tr class="">
												<th><span class="important">비밀번호 확인</span></th>
												<td>
													<div class="member_warning">
														<input type="password" style="width: 200px;" id="MEM_PW2" name="MEM_PW2" autocomplete="off">
													</div>
												</td>
											</tr>
											<tr>
												<th><span class="important">이름</span></th>
												<td>
													<div class="member_warning">
														<input type="text" style="width: 200px;" id="MEM_NAME" name="MEM_NAME" data-pattern="gdMemberNmGlobal" value="" maxlength="30">
													</div>
												</td>
											</tr>
											<tr>
												<th><span class="important">휴대폰번호</span></th>
												<td class="member_address">
													<div class="address_postcode">
														<input type="text" style="width: 200px;" id="MEM_PHONE" name="MEM_PHONE" maxlength="11" placeholder="- 없이 입력하세요." data-pattern="gdNum" value="">
													</div>
												</td>
											</tr>
											<tr>
												<th><span>주소</span></th>
												<td class="member_address">
													<div class="address_postcode">
														<input type="text" style="width: 100px;" name="MEM_ZIPCODE" id="MEM_ZIPCODE" value="" numberOnly>
														<input type="button" class="submit px-3" onclick="sample6_execDaumPostcode()" value="우편번호 찾기">
													</div> <br />
													<div class="address_input">
														<div class="member_warning">
															<input type="text" name="MEM_ADD1" id="MEM_ADD1" readonly="readonly" value="">
														</div>
														<br />
														<div class="member_warning js_address_sub">
															<input type="text" name="MEM_ADD2" id="MEM_ADD2" value="">
														</div>
													</div>
												</td>
											</tr>
										</tbody>
									</table>
								</div>
							</div>
							<div class="btn_center_box" align="center">
								<br /> <br />
								<button type="button" id="btnCancel" class="btn btn-black py-3 px-5" onclick="location.href='/loginForm.omc'">취소</button>
								&nbsp;&nbsp;
								<button type="reset" class="btn btn-black py-3 px-5">다시 입력</button>
								&nbsp;&nbsp;
								<button type="button" class="btn btn-primary py-3 px-5" value="회원가입" onClick="checks(this.form)">회원가입</button>
							</div>
							<!-- //btn_center_box -->
							</form>
						</div>
						<!-- //member_cont -->
					</div>
					<!-- //join_base_wrap -->
				</div>
			</div>
		</div>
	</div>
</body>
</html>