<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/include/include-header.jspf" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>오늘의 메뉴 추천, 오메추</title>
<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
<script>
function checks() {
	if(confirm("수정하시겠습니까?") == true) {
		var MEM_PW = document.getElementById("MEM_PW");
		var MEM_PW2 = document.getElementById("MEM_PW2");
		var MEM_NAME = document.getElementById("MEM_NAME");
		var MEM_ADD1 = document.getElementById("MEM_ADD1");
		var MEM_ADD2 = document.getElementById("MEM_ADD2");
		var MEM_ZIPCODE = document.getElementById("MEM_ZIPCODE");
		var MEM_PHONE = document.getElementById("MEM_PHONE");
		
		if(MEM_PW.value.trim() == ""){
			alert("비밀번호를 입력해주세요.");
			MEM_PW.focus();
			return false;
		}
		
		if(MEM_PW2.value != MEM_PW.value){
			alert("비밀번호가 일치하지 않습니다.");
			MEM_PW2.focus();
			return false;
		}
		
		if(MEM_NAME.value.trim() == ""){
			alert("이름을 입력해주세요.");
			MEM_NAME.focus();
			return false;
		}		
		
		if(MEM_PHONE.value.trim() == ""){
			alert("핸드폰 번호를 입력해주세요.");
			MEM_PHONE.focus();
			return false;
		}
		
		if(MEM_ZIPCODE.value.trim() == ""){
			alert("우편번호를 입력해주세요.");
			MEM_ZIPCODE.focus();
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

		document.modifyForm.submit();
	}
}
</script>
<script type="text/javascript">
	function changeForm(val) {
		if (val == "-1") {
			location.href = "myPage.omc";
		} else if (val == "0") {
			location.href = "main.jsp?contentPage=/myInfoModify.omc";
		} else if (val == "1") {
			location.href = "main.jsp?contentPage=/myInfoDelete.omc";
		}
	}
</script>
<script>
function submit2(del) {
	if(confirm("탈퇴하시겠습니까?") == true) {
		del.action="/myInfoDelete.omc";
		del.submit();
		return true;
	}
}
</script>
<script>
$(document).ready(function() {
	var MEM_ID=$('#MEM_ID').val();
});
</script>
<script src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
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
<body class="goto-here">
<section class="ftco-section">
<div class="container">
<div class="row">
<div class="col-sm-2">
	<div class="sidebar-box ftco-animate" id="menu" style="margin-top:30px;">
		<ul class="categories">
			<li><a href="/myPage.omc" style="font-size:middle;">마이페이지</a></li>
			<li><a href="/myInfoModify.omc" style="font-size:middle; color:#82ae46;">회원정보 수정</a></li>
			<li><a href="/myOrderList.omc" style="font-size:middle;">주문조회</a></li>
			<li><a href="/myReviewList.omc" style="font-size:middle;">후기</a></li>
			<li><a href="/myQnaList.omc" style="font-size:middle;">문의내역</a></li>
		</ul>
	</div>
</div>
<div class="col-sm-10">
<div class="container" style="text-align:center;">
	<div class="row justify-content-center">
		<div class="col-xl-9 ftco-animate">
			<form id="modifyForm" name="modifyForm" method="POST" action="/myInfoModifyOk.omc" class="billing-form" >
			<h2 class="mb-4 billing-heading">회원 정보 수정</h2><br><br>
				<div class="row align-items-end" style="padding-left:150px;">
					
					<!-- 아이디 -->
					<h6 class="mb-4" style="text-align:left;">아이디</h6>
					<div class="w-100"></div>
					<div class="form-group d-flex">
						<input type="text" class="form-control" id="MEM_ID" name="MEM_ID" style="width:400px;"
							value="${map.MEM_ID}" readonly>
					</div>
					<div class="w-100"></div>
           
					<!-- 비밀번호 -->
					
					<div class="form-group">
						<h6 class="mb-4" style="text-align:left;">비밀번호</h6>
						<input type="password" id="MEM_PW" name="MEM_PW" class="form-control" style="width:400px;"
							value="${map.MEM_PW}">
					</div>
					<div class="w-100"></div>
					
					<!-- 비밀번호 확인 -->
					<div class="form-group">
						<h6 class="mb-4" style="text-align:left;">비밀번호 확인</h6>
						<input type="password" id="MEM_PW2" name="MEM_PW2"  class="form-control" style="width:400px;"
							value="${map.MEM_PW}">
					</div>
					<div class="w-100"></div>
					<br/><br/>
		
					<!-- 이름 -->
					<h6 class="mb-4" style="text-align:left;">이름</h6>
					<div class="w-100"></div>
					<div class="form-group d-flex">
						<input type="text" class="form-control" id="MEM_NAME" name="MEM_NAME" style="width:400px;"
							value="${map.MEM_NAME}" readonly>
					</div>
					<div class="w-100"></div>

					<!-- 핸드폰 번호 -->
					<div class="form-group">
						<h6 class="mb-4" style="text-align:left;">핸드폰 번호</h6>
						<input type="text" id="MEM_PHONE" name="MEM_PHONE" class="form-control"
							onKeyup="this.value=this.value.replace(/[^0-9]/g,'');" size="24" style="width:400px;"
							maxlength="11" value="${map.MEM_PHONE}"> 
						<div class="w-100"></div>
						<h6 class="mb-4" style="text-align:left;">'-'는 빼고 숫자만 입력해주세요.</h6>
					</div>
					<div class="w-100"></div>
					<br/><br/>
					
					<!-- 주소 -->
					<h6 class="mb-4" style="text-align:left;">우편번호</h6>
					<div class="w-100"></div>
					<div class="form-group d-flex">
						<input type="text" class="form-control" name="MEM_ZIPCODE" id="MEM_ZIPCODE" placeholder="우편번호"
							maxlength="7" style="width:270px;" value="${map.MEM_ZIPCODE}">
						<input type="button" class="submit px-3" onclick="sample6_execDaumPostcode()" value="우편번호 찾기">
					</div>
					<div class="w-100"></div>
					<div class="form-group">
						<h6 class="mb-4" style="text-align:left;">주소</h6>
						<input type="text" name="MEM_ADD1" id="MEM_ADD1" placeholder="주소" class="form-control" style="width:190px;"
							value="${map.MEM_ADD1}">
					</div>
					&emsp;
					<div class="form-group">
						<input type="text" name="MEM_ADD2" id="MEM_ADD2" placeholder="상세주소" class="form-control" style="width:190px;"
							value="${map.MEM_ADD2}">
					</div>			
					<div class="w-100"></div>
					<br/><br/><br/><br/>
					</div>

					
				<!-- 버튼 -->
					<div class="form-group" align="center">
						 <button type="button" class="btn btn-primary py-3 px-5" onClick="checks(this.form)">수정</button>
				            
				          &emsp;&emsp;
				          <button type="button" class="btn btn-black py-3 px-5" onClick="return submit2(this.form);">탈퇴</button>
				
				          &emsp;&emsp;
				          <button type="button" class="btn btn-black py-3 px-5" onclick="changeForm(-1)">뒤로</button>
				     
					</div>
			</form>
		</div>
	</div>
</div>
</div>
</div>
</div>
</section>
</body>

</html>