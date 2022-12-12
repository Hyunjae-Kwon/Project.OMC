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
<script>
 function formCheck() {
	var form = document.getElementById("goodsWriteForm");
	var GD_CATEGORY = document.getElementById("GD_CATEGORY");
	var GD_NAME = document.getElementById("GD_NAME");
	var GD_IMAGE = document.getElementById("main_image");
	var GD_STOCK = document.getElementById("GD_STOCK");
	var GD_PRICE = document.getElementById("GD_PRICE");
	var GD_DCPRICE = document.getElementById("GD_DCPRICE");
	
	if(confirm("상품을 수정하시겠습니까?") == true) {
		if(GD_CATEGORY.value.trim()=="") {
			alert("상품 종류를 선택해주세요.");
			return false;
		} else if(GD_GNAME.value.trim()=="") {
			alert("상품 이름을 입력해주세요.");
			GD_GNAME.focus();
			return false;
		} else if(GD_IMAGE.value.trim()=="") {
			alert("상품 이미지를 넣어주세요.");
			return false;
		} else if(GD_STOCK.value.trim()=="") {
			alert("상품 수량을 입력해주세요.");
			GD_STOCK.focus();
			return false;
		} else if(GD_PRICE.value.trim()=="") {
			alert("상품 가격을 입력해주세요.");
			GD_PRICE.focus();
			return false;
		} else if(GD_DCPRICE.value.trim()=="") {
			alert("할인 가격을 입력해주세요.");
			GD_DCPRICE.focus();
			return false;
		} else {	
			form.submit();
		}
	}
}
/* 이미지 미리보기 스크립트 */
function readImage(input) {
	// 인풋 태그에 파일이 있는 경우
	if(input.files && input.files[0]) {
		// 이미지 파일인지 검사 (생략)
		
		// FileReader 인스턴스 생성
		const reader = new FileReader();
		
		// 이미지가 로드가 된 경우
		reader.onload = e => {
			const previewImage = document.getElementById("preview-image");
			previewImage.src = e.target.result;
		};
		
		// reader가 이미지 읽도록 하기
		reader.readAsDataURL(input.files[0]);
	}	
}

window.onload = function() {
	document.getElementById("GD_NAME").focus();
}
</script>
</head>
<body>
<form action="goodsModify.omc" method="post" encType="multipart/form-data"
	id="goodsWriteForm">
	
<input type="hidden" id="GD_GID" name="GD_GID" value="${goods.GD_GID}">
	
<section class="ftco-section ftco-cart">
	<div style="text-align:center"><h2>상품 수정</h2></div>
	<br/>
		<div class="container">
			<div class="row">
				<div>
				</div>
				<br>
				<div class="container" style="padding-right:70px;">				
					<div class="col-md-12 ftco-animate">
						<div class="cart-list">
							<table class="table">
								<tbody>
									<tr>
										<td style="text-align:center;">
											<b><label for="GD_CATEGORY">상품 종류</label></b>
										</td>
										<td>
											<select id="GD_CATEGORY" name="GD_CATEGORY" class="form-control">
												<option value="KOREAN">한식</option>
												<option value="ITALIAN">양식</option>
												<option value="CHIJAP">중식/일식</option>
												<option value="ASIAN">아시안</option>
												<option value="SNACKBAR">분식</option>
												<option value="HEALTHY">건강식</option>
											</select>
										</td>
									</tr>
									<tr>
										<td>
											<b><label for="GD_GNAME">상품명</label></b>
										</td>
										<td>
											<input type="text" maxlength="50" id="GD_GNAME" name="GD_GNAME" class="form-control" value="${goods.GD_GNAME}">
										</td>
									</tr>
									<tr>
										<td>
											<b><label for="main_image">상품 이미지</label></b>
										</td>
										<td>
											<input type="file" id="main_image" name="main_image" class="form-control">
											<input type="hidden" id="GD_IMAGE" name="GD_IMAGE" value="${goods.GD_IMAGE}">
										</td>
									</tr>
									<!-- 파일 이미지 출력  -->
									<tr>
										<td>
											<b><label style="color:slategray">상품 이미지 미리보기</label></b>
										</td>
										<td>
											<img src="resources/img/goods-${goods.GD_GID}.png" width="300" border="0"
												id="preview-image">
											<script>
												// input file에 change 이벤트 부여
												const inputImage = document.getElementById("main_image");
												inputImage.addEventListener("change", e=> {
													readImage(e.target)	
												});
											</script>
										</td>
									</tr>
									<tr>
										<td>
											<b><label for="GD_STOCK">상품 수량</label></b>
										</td>
										<td>
											<input type="number" maxlength="20" id="GD_STOCK" name="GD_STOCK" class="form-control" value="${productBean.PSTOCK}"
												onKeyup="this.value=this.value.replace(/[^0-9]/g,'');"/>
										</td>
									</tr>
									<tr>
										<td>
											<b><label for="GD_PRICE">상품 원가</label></b>
										</td>
										<td>
											<input type="number" maxlength="20" id="GD_PRICE" name="GD_PRICE" class="form-control" value="${goods.GD_PRICE}"
												onKeyup="this.value=this.value.replace(/[^0-9]/g,'');"/>	
										</td>
									</tr>
									<tr>
										<td>
											<b><label for="GD_DCPRICE">할인률</label></b>
										</td>
										<td>
											<input type="number" min="0" max="100" class="form-control" value="${goods.GD_DCPRICE}"
												id="GD_DCPRICE" name="GD_DCPRICE" value="0"
												onKeyup="this.value=this.value.replace(/[^0-9]/g,'');"/>
										</td>
									</tr>
									<tr>
										<td>
											<b><label for="image1">상세 이미지1</label></b>
										</td>
										<td>
											<input type="file" id="image1" name="image1" class="form-control">
										</td>
									</tr>
									<tr>
										<td>
											<b><label for="image2">상세 이미지2</label></b>
										</td>
										<td>
											<input type="file" id="image2" name="image2" class="form-control">
										</td>
									</tr>
									<tr>
										<td>
											<b><label for="image3">상세 이미지3</label></b>
										</td>
										<td>
											<input type="file" id="image3" name="image3" class="form-control">
										</td>
									</tr>
									<tr>
										<td>
											<b><label for="image4">상세 이미지4</label></b>
										</td>
										<td>
											<input type="file" id="image4" name="image4" class="form-control">
										</td>
									</tr>
									<tr>
										<td colspan="2">
											<input type="button" value="수정" class="btn btn-dark py-2 px-3"
												onClick="return formCheck()">
											<input type="button" value="메뉴" class="btn btn-primary py-2 px-3"
												onClick="javascrpit:loaction.href='main.omc'">
										</td>
									</tr>
								</tbody>
							</table>
						</div>
						${paging.pageHtml}
					</div>			
				</div>
		</div>
	</div>
</section>	

</form>
</body>
</html>