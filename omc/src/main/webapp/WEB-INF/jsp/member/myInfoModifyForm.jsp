<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/include/include-header.jspf"%>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" href="resources/css/join.css">
<meta charset="UTF-8">
<title>오늘의 메뉴 추천, 오메추</title>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
<script>
	function checks() {
		if (confirm("수정하시겠습니까?") == true) {
			var MEM_PW = document.getElementById("MEM_PW");
			var MEM_PW2 = document.getElementById("MEM_PW2");
			var MEM_NAME = document.getElementById("MEM_NAME");
			var MEM_ADD1 = document.getElementById("MEM_ADD1");
			var MEM_ADD2 = document.getElementById("MEM_ADD2");
			var MEM_ZIPCODE = document.getElementById("MEM_ZIPCODE");
			var MEM_PHONE = document.getElementById("MEM_PHONE");

			if (MEM_PW.value.trim() == "") {
				alert("비밀번호를 입력해주세요.");
				MEM_PW.focus();
				return false;
			}

			if (MEM_PW2.value != MEM_PW.value) {
				alert("비밀번호가 일치하지 않습니다.");
				MEM_PW2.focus();
				return false;
			}

			if (MEM_NAME.value.trim() == "") {
				alert("이름을 입력해주세요.");
				MEM_NAME.focus();
				return false;
			}

			if (MEM_PHONE.value.trim() == "") {
				alert("핸드폰 번호를 입력해주세요.");
				MEM_PHONE.focus();
				return false;
			}

			if (MEM_ZIPCODE.value.trim() == "") {
				alert("우편번호를 입력해주세요.");
				MEM_ZIPCODE.focus();
				return false;
			}

			if (MEM_ADD1.value.trim() == "") {
				alert("주소를 입력해주세요.");
				MEM_ADD1.focus();
				return false;
			}

			if (MEM_ADD2.value.trim() == "") {
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
		if (confirm("탈퇴하시겠습니까?") == true) {
			del.action = "/myInfoDelete.omc";
			del.submit();
			return true;
		}
	}
</script>
<script>
	$(document).ready(function() {
		var MEM_ID = $('#MEM_ID').val();
	});
</script>
<script
	src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
<script>
	function sample6_execDaumPostcode() {
		new daum.Postcode({
			oncomplete : function(data) {
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
				if (data.userSelectedType === 'R') {
					// 법정동명이 있을 경우 추가한다. (법정리는 제외)
					// 법정동의 경우 마지막 문자가 "동/로/가"로 끝난다.
					if (data.bname !== '' && /[동|로|가]$/g.test(data.bname)) {
						extraAddr += data.bname;
					}
					// 건물명이 있고, 공동주택일 경우 추가한다.
					if (data.buildingName !== '' && data.apartment === 'Y') {
						extraAddr += (extraAddr !== '' ? ', '
								+ data.buildingName : data.buildingName);
					}
					// 표시할 참고항목이 있을 경우, 괄호까지 추가한 최종 문자열을 만든다.
					if (extraAddr !== '') {
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
					<div class="sidebar-box ftco-animate" id="menu"
						style="margin-top: 30px;">
						<ul class="categories">
							<li><a href="/myPage.omc" style="font-size: middle;">마이페이지</a></li>
							<li><a href="/myInfoModify.omc"
								style="font-size: middle; color: #fd7e14;">회원정보 수정</a></li>
							<li><a href="/myOrderList.omc" style="font-size: middle;">주문조회</a></li>
							<li><a href="/myReviewList.omc" style="font-size: middle;">후기</a></li>
							<li><a href="/myQnaList.omc" style="font-size: middle;">문의내역</a></li>
						</ul>
					</div>
				</div>
				<div class="col-sm-10">
					<div class="container" style="text-align: center;">
						<div class="row justify-content-center">
							<div class="col-xl-9 ftco-animate">
								<form id="modifyForm" name="modifyForm" method="POST"
									action="/myInfoModifyOk.omc" class="billing-form">
									<h2 class="mb-4 billing-heading">회원 정보 수정</h2>
									<div class="row align-items-end">
										<div id="contents" style="text-align: left;">
											<div class="sub_content">
												<div class="content_box">
													<div class="join_base_wrap">
														<div class="member_cont">
															<div class="base_info_box">
																<div class="base_info_sec">
																	<table>
																		<tbody>
																			<tr>
																				<th><span class="important">아이디</span></th>
																				<td>
																					<div class="member_warning">
																						<input type="text" id="MEM_ID" name="MEM_ID"
																							style="width: 200px" value="${map.MEM_ID}"
																							readonly>
																					</div>
																				</td>
																			</tr>
																			<tr class="">
																				<th><span class="important">비밀번호</span></th>
																				<td class="member_password">
																					<div class="member_warning">
																						<input type="password" id="MEM_PW" name="MEM_PW"
																							style="width: 200px" autocomplete="off"
																							placeholder="" value="${map.MEM_PW}">
																					</div>
																				</td>
																			</tr>
																			<tr class="">
																				<th><span class="important">비밀번호 확인</span></th>
																				<td>
																					<div class="member_warning">
																						<input type="password" id="MEM_PW2" name="MEM_PW2"
																							style="width: 200px" autocomplete="off"
																							value="${map.MEM_PW}">
																					</div>
																				</td>
																			</tr>
																			<tr>
																				<th><span class="important">이름</span></th>
																				<td>
																					<div class="member_warning">
																						<input type="text" id="MEM_NAME" name="MEM_NAME"
																							style="width: 200px"
																							data-pattern="gdMemberNmGlobal" maxlength="30"
																							value="${map.MEM_NAME}" readonly>
																					</div>
																				</td>
																			</tr>
																			<tr>
																				<th><span class="important">휴대폰번호</span></th>
																				<td class="member_address">
																					<div class="address_postcode">
																						<input type="text" id="MEM_PHONE" name="MEM_PHONE"
																							style="width: 200px" maxlength="12"
																							placeholder="- 없이 입력하세요." data-pattern="gdNum"
																							onKeyup="this.value=this.value.replace(/[^0-9]/g,'');"
																							value="${map.MEM_PHONE}">
																					</div>
																				</td>
																			</tr>
																			<tr>
																				<th><span>주소</span></th>
																				<td class="member_address">
																					<div class="address_postcode">
																						<input type="text" name="MEM_ZIPCODE"
																							id="MEM_ZIPCODE" style="width: 100px"
																							value="${map.MEM_ZIPCODE}" readonly> <input
																							type="button" class="submit px-3"
																							onclick="sample6_execDaumPostcode()"
																							value="우편번호 찾기">
																					</div> <br>
																					<div class="address_input">
																						<div class="member_warning">
																							<input type="text" name="MEM_ADD1" id="MEM_ADD1"
																								readonly="readonly" value="${map.MEM_ADD1}">
																						</div>
																						<br>
																						<div class="member_warning js_address_sub">
																							<input type="text" name="MEM_ADD2" id="MEM_ADD2"
																								value="${map.MEM_ADD2}">
																						</div>
																						<br>
																					</div>
																				</td>
																			</tr>
																		</tbody>
																	</table>
																</div>
															</div>
														</div>
													</div>
												</div>

											</div>
										</div>
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
	</section>
</body>

</html>