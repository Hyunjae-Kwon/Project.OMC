<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ include file="/WEB-INF/include/include-header.jspf" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>오늘의 메뉴 추천, 오메추</title>
<style>
input[type="number"]::-webkit-outer-spin-button,
input[type="number"]::-webkit-inner-spin-button {
    -webkit-appearance: none;
    margin: 0;
}
</style>
<script>
function getCount(){
	
	var totalPrice = 0;				// 총 원가
	var totalDCPrice = 0;			// 총 할인 금액
	var dcprice = 0;				// 할인 받은 금액
	var finalTotalPrice = 0; 		// 최종 가격(총 가격 + 배송비)
	var count = 0;					// 구매 개수
	
	$(".ct_info").each(function(){
		var index = $(".ct_info").index(this);
		// 구매 개수
		count = parseInt($(".ct_info").eq(index).find(".ct_count").val());
		// 총 원가
		totalPrice += parseInt($(".ct_info").eq(index).find(".ct_price").val()) * count;
		// 총 할인 금액
		totalDCPrice += parseInt($(".ct_info").eq(index).find(".ct_dcprice").val()) * count;
		// 총 구매 금액
		finalTotalPrice += parseInt($(".ct_info").eq(index).find(".ct_totalprice").val());
	});
	
	finalTotalPrice += 3000;
	
	dcprice = totalPrice - totalDCPrice;
	
	// 총 원가
	$("#ORIGINPAY").val(totalPrice);
	// 총 할인 금액
	$("#SALEPAY").val(dcprice);
	// 최종 가격(총 할인 금액 + 배송비)
	$("#TOTALPAY").val(finalTotalPrice);
	
	// 총 원가
	$("#originalSum").text(totalPrice.toLocaleString());
	// 총 할인 금액
	$("#saleSum").text(dcprice.toLocaleString());
	// 최종 가격(총 할인 금액 + 배송비)
	$("#finalSum").text(finalTotalPrice.toLocaleString());
}

function orderCheck() {
	/* var OrderForm = document.getElementById("orderForm"); */
	
	var MEM_NAME = document.getElementById("MEM_NAME");
	var MEM_ADD1 = document.getElementById("MEM_ADD1");
	var MEM_ADD2 = document.getElementById("MEM_ADD2");
	var MEM_ZIPCODE = document.getElementById("MEM_ZIPCODE");
	var MEM_PHONE = document.getElementById("MEM_PHONE");
	
	if(confirm("주문하시겠습니까?") == true) {
				
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
		
		$(".ct_info").each(function(){
			var index = $(".ct_info").index(this);
			
			id = $(".ct_info").eq(index).find("#OD_ID").val();
			gid = parseInt($(".ct_info").eq(index).find("#OD_GID").val());
			gname = $(".ct_info").eq(index).find("#OD_GNAME").val();
			count = $(".ct_info").eq(index).find("#OD_COUNT").val();
			price = $(".ct_info").eq(index).find("#OD_PRICE").val();
			dcprice = $(".ct_info").eq(index).find("#OD_DCPRICE").val();
			total = $(".ct_info").eq(index).find("#OD_TOTAL").val();
			
			odnum = $(document).find("#OD_NUM").val();
			name = $(document).find("#OD_NAME").val();
			phone = $(document).find("#MEM_PHONE").val();
			zipcode = $(document).find("#MEM_ZIPCODE").val();
			add1 = $(document).find("#MEM_ADD1").val();
			add2 = $(document).find("#MEM_ADD2").val();
			
			totalpay = $(document).find("#TOTALPAY").val();
			
			$.ajax({
				type : "POST",
				url : "<c:url value='/orderCart.omc'/>",
				data :{OD_ID:id, OD_GID:gid, OD_GNAME:gname, OD_COUNT:count, OD_PRICE:price, OD_DCPRICE:dcprice, OD_TOTAL:total,
					  OD_NUM:odnum, OD_NAME:name, OD_PHONE:phone, OD_ZIPCODE:zipcode, OD_ADD1:add1, OD_ADD2:add2, TOTALPAY:totalpay},
				async: false,
				success : function(data){
				}
			});
		});
		location.href="<c:url value='orderResult.omc'/>";
		/* OrderForm.submit(); */
	}
}
</script>
<script>

/* 체크박스 체크 여부에 따라 값 입력/지우기 */
$(document).ready(function() {
	$('#infoCheck').change(function() {
		var MEM_NAME = document.getElementById("MEM_NAME");
		var MEM_PHONE = document.getElementById("MEM_PHONE");
		var MEM_ZIPCODE = document.getElementById("MEM_ZIPCODE");
		var MEM_ADD1 = document.getElementById("MEM_ADD1");
		var MEM_ADD2 = document.getElementById("MEM_ADD2");
		
		var OD_NAME = document.getElementById("OD_NAME");
		var OD_PHONE = document.getElementById("OD_PHONE");
		var OD_ZIPCODE = document.getElementById("OD_ZIPCODE");
		var OD_ADD1 = document.getElementById("OD_ADD1");
		var OD_ADD2 = document.getElementById("OD_ADD2");
		
		if($('#infoCheck').is(":checked")) {
			MEM_NAME.value = OD_NAME.value;
			MEM_PHONE.value = OD_PHONE.value;
			MEM_ZIPCODE.value = OD_ZIPCODE.value;
			MEM_ADD1.value = OD_ADD1.value;
			MEM_ADD2.value = OD_ADD2.value;
		} else {
			MEM_NAME.value = "";
			MEM_PHONE.value = "";
			MEM_ZIPCODE.value = "";
			MEM_ADD1.value = "";
			MEM_ADD2.value = "";
		}		
	});
})	

$(document).on("keyup", "input[numberOnly]", function() {$(this).val( $(this).val().replace(/[^0-9]/gi,"") );})
window.onload = function(){
	getCount();
}

</script>
<!-- 우편번호 API -->
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
    function numberMaxLength(e){
        if(e.value.length > e.maxLength){
            e.value = e.value.slice(0, e.maxLength);
        }
    }//글자수제한
</script>
</head>
<body>
	<div style="text-align:center">
		<h1> 주문 페이지 </h1>
	</div>
	<form method="post" id="orderForm" action="orderCart.omc">
	<section class="ftco-section ftco-cart">
		<div class="container">
			<div class="row">
				<div class="col-md-12 ftco-animate">
					<div class="cart-list">
						<table class="table">
							<thead class="thead-primary">
								<tr class="text-center">
									<th>&nbsp;</th>
									<th colspan="2">상품명</th>
									<th>수량</th>
									<th>상품 금액</th>
									<th>판매 금액</th>
									<th>할인 금액</th>
									<th>결제 금액</th>
								</tr>
							</thead>
							<c:forEach var="goods" items="${cartInfo}">
							<tbody id="tbody">
								<tr class="text-center">
									<td class="ct_info">
										<input type="hidden" class="ct_count" value="${goods.CT_COUNT}">
					          			<input type="hidden" class="ct_price" value="${goods.CT_PRICE}">
					          			<input type="hidden" class="ct_dcprice" value="${goods.CT_DCPRICE}">
					          			<input type="hidden" class="ct_totalprice" value="${goods.CT_DCPRICE * goods.CT_COUNT}">
										<input type="hidden" id="OD_ID" name="OD_ID" value="${goods.CT_ID}">
										<input type="hidden" id="OD_GID" name="OD_GID" value="${goods.CT_GID}">
										<input type="hidden" id="OD_GNAME" name="OD_GNAME" value="${goods.CT_NAME}">
										<input type="hidden" id="OD_COUNT" name="OD_COUNT" value="${goods.CT_COUNT}"> 
										<input type="hidden" id="OD_PRICE" name="OD_PRICE" value="${goods.CT_PRICE}">
										<input type="hidden" id="OD_DCPRICE" name="OD_DCPRICE" value="${goods.CT_DCPRICE}">
										<input type="hidden" id="OD_TOTAL" name="OD_TOTAL" value="${goods.CT_DCPRICE * goods.CT_COUNT}">
									</td>
									<td class="image-prod">
									<div class="img" style="background-image:url(resources/img/goods/goods-${goods.CT_GID}.png);"></div></td>
									
									<!-- 상품명 -->
									<td class="product-name">
										<h3><b>${goods.CT_NAME}</b></h3>
									</td>
									
									<!-- 수량 -->
									<td class="count">${goods.CT_COUNT} 개</td>
									
									<!-- 상품 금액 -->
									<td class="price">${goods.CT_PRICE} 원</td>
									
									<!-- 판매금액 -->
									<td class="price">
				          				<c:set var="salePrice" value="${goods.CT_DCPRICE}" />
				          				<fmt:formatNumber value="${salePrice}" pattern="#.#" />원
									</td>
									
									<!-- 할인금액 -->
									<td class="price" style="color:Crimson">
									<c:set var="sale" value="${(goods.CT_PRICE-goods.CT_DCPRICE)*goods.CT_COUNT}" />
									<fmt:parseNumber var="saled" integerOnly="true" value="${sale}"/>
									<fmt:formatNumber value="${saled}" pattern="#.#" />원</td> 
									
									<!-- 결제 금액 -->
									<td class="total">
										<c:set var="total" value="${salePrice * goods.CT_COUNT}" />
										<fmt:parseNumber var="totalPrice" integerOnly="true" value="${total}"/>
										<b><fmt:formatNumber value="${totalPrice}" pattern="#.#" />원</b>
									</td>
								</tr><!-- END TR-->
							</tbody>
							</c:forEach>
						</table>
						
					</div> <!-- end cart-list div -->

				</div>
			</div>
		</div>
	</section>
<!-- memInfo -->
<input type="hidden" id="OD_NAME" name="OD_NAME" value="${memInfo.MEM_NAME}">
	<div class="col-md-12">
	<section class="ftco-section">
		<div class="container" style="text-align:center;">
			<div class="row justify-content-center">
				<div class="col-xl-7 ftco-animate">
				
					<!-- 주문자 정보 -->
					<h2 class="mb-4 billing-heading">주문자 정보</h2>
					<div class="row slider-text justify-content-center align-items-center">
					
						<!-- 이름 -->
						<div class="form-group">
							<h6 class="mb-4" style="text-align:left;">이름</h6>
							<input type="text" class="form-control" style="width:400px;"
								value="${memInfo.MEM_NAME}" readonly>
						</div>
						<div class="w-100"></div>
	
						<!-- 핸드폰 번호 -->
						<div class="form-group">
							<h6 class="mb-4" style="text-align:left;">핸드폰 번호</h6>
							<input type="text" class="form-control"
								style="width:400px;" value="${memInfo.MEM_PHONE}" readonly> 
							<div class="w-100"></div>
						</div>
						<div class="w-100"></div>
						<br/><br/>
					</div>
						
					<hr>
					<br><br>
					<!-- 주문자 정보와 동일한지 체크-->
					<div class="row slider-text justify-content-center align-items-center">
						<div class="form-group">
							<h6 class="mb-4" style="text-align:left;">
							주문자 정보와 동일
							<input type="checkbox" style="width:50px;"
								id="infoCheck" name="infoCheck" checked>
							</h6>
						</div>
						<div class="w-100"></div>
					</div>
						
					<hr>
						
					<!-- 받는 사람 정보 -->
					<h2 class="mb-4 billing-heading">배송 정보</h2>
					<div class="row slider-text justify-content-center align-items-center">
					
						<!-- 받는 사람 이름 -->
						<div class="form-group">
							<h6 class="mb-4" style="text-align:left;">받으시는 분</h6>
							<input type="text" id="MEM_NAME" name="OD_NAME"  class="form-control" style="width:400px;"
								value="${memInfo.MEM_NAME}">
							<input type="hidden" id="OD_NUM" name="OD_NUM" value="${orderNum}">
						</div>
						<div class="w-100"></div>
	
						<!-- 핸드폰 번호 -->
						<div class="form-group">
							<h6 class="mb-4" style="text-align:left;">핸드폰 번호</h6>
							<input type="text" id="MEM_PHONE" name="OD_PHONE" class="form-control"
								onKeyup="this.value=this.value.replace(/[^0-9]/g,'');" size="24" style="width:400px;"
								value="${memInfo.MEM_PHONE}" maxlength="11" oninput="numberMaxLength(this);"> 
							<div class="w-100"></div>
							<h6 class="mb-4" style="text-align:left;">'-'는 빼고 숫자만 입력해주세요.</h6>
						</div>
						<div class="w-100"></div>
						<br/><br/>
						
						<!-- 주소 -->
						<h6 class="mb-4" style="text-align:left;">우편번호</h6>
						<div class="w-100"></div>
						<div class="form-group d-flex">
							<input type="text" class="form-control" name="OD_ZIPCODE" id="MEM_ZIPCODE" placeholder="우편번호" style="width:270px;"
								value="${memInfo.MEM_ZIPCODE}" maxlength="7"
								onKeyup="this.value=this.value.replace(/[^0-9]/g,'');">
							<input type="button" class="submit px-3" onclick="sample6_execDaumPostcode()" value="우편번호 찾기">
						</div>
						<div class="w-100"></div>
						<h6 class="mb-4" style="text-align:left;">주소</h6>
						<div class="w-100"></div>
						<div class="form-group">
							<input type="text" name="OD_ADD1" id="MEM_ADD1" placeholder="주소" class="form-control" style="width:190px;"
								value="${memInfo.MEM_ADD1}">
						</div>
						&emsp;
						<div class="form-group">
							<input type="text" name="OD_ADD2" id="MEM_ADD2" placeholder="상세주소" class="form-control" style="width:190px;"
								value="${memInfo.MEM_ADD2}">
						</div>			
						<div class="w-100"></div>
					</div>

					<hr>
					
					<br>	
					<div class="container">  
						<div class="cart-total mb-3" style="text-align:center">
	    					<h2>결제 금액</h2><br>
	    					<p class="d-flex">
	    						<span>주문금액</span>
	    						<input type="hidden" id="ORIGINPAY" name="originalSum">
	    						<span id="originalSum"></span>원
	    					</p>
	    					<p class="d-flex">
	    						<span>할인금액</span>
	    						<input type="hidden" id="SALEPAY" name="saleSum">
	    						<span id="saleSum" style="color:Crimson"></span>원
	    					</p>
	    					<p class="d-flex">
	    						<span>배송비</span>
	    						<span>3000원</span>
	    					</p>
	    					<hr>
	    					<p class="d-flex total-price">
	    						<span>총 금액</span>
	    						<input type="hidden" id="TOTALPAY" name="finalSum">
	    						<span id="finalSum"></span>원
	    					</p>
	    				</div>
	    			</div>

					<hr>
					
					<!-- 결제 정보 -->
					<h2 class="mb-4 billing-heading">결제 정보</h2>
					<div class="row slider-text justify-content-center align-items-center">
					
						<!-- 결제 방법 -->
						<div class="form-group">
							<h6 class="mb-4" style="text-align:left;">무통장 입금</h6>
							<input type="text" class="form-control" style="width:400px;"
								value="우리은행 1222222-266" readonly>
						</div>
						<div class="w-100"></div>
					</div>
					<br>	
					<br>	
					<!-- 버튼 -->
					<div class="form-group" align="center">
						<button type="button" class="btn btn-primary py-3 px-5" onClick="orderCheck()">주문하기</button>
						&emsp;&emsp;
						<button type="reset" class="btn btn-black py-3 px-5"
				          	onClick="javascript:location.href='main.omc'">주문취소</button>
					</div>
					<br>
					<br>
				</div>
			</div>
		</div>
	</section>
	</div>
	</form>	
</body>
</html>