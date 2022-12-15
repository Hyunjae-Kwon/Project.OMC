<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ include file="/WEB-INF/include/include-header.jspf" %>
<html>
<script>
function logoutCheck() {
	if(confirm("로그아웃 하시겠습니까?") == true) {
		location.href = "/logout.omc";	
	} 
}
</script>
<body>
	<div class="py-1" style="background-color:#fff; height:150px;">
		<div class="container" style="text-align:right;">
		
			<!-- 로그인을 하지 않았을 경우 -->
			<c:if test="${ empty MEM_ID }">
					<a class="text-dark" href="/loginForm.omc" style="font-size:small;">로그인</a> 
						<span class="text-dark px-2">|</span> 
					<a class="text-dark" href="/joinForm.omc" style="font-size:small;">회원가입</a>
			</c:if>
	
			<!-- 로그인을 했을 경우 -->
			<c:if test="${! empty MEM_ID }">
				<span class="text-dark" style="font-size:small;">
				<%= session.getAttribute("MEM_ID") %>님
				</span>
					<span class="text-dark px-2">|</span>
				<a class="text-dark" href="/myPage.omc" style="font-size:small;">마이페이지</a> 
					<span class="text-dark px-2">|</span> 
				<a class="text-dark" href="/myCart.omc" style="font-size:small;">장바구니</a>
					<span class="text-dark px-2">|</span> 
				<a class="text-dark" href="javascript:logoutCheck()" style="font-size:small;">로그아웃</a> 
			</c:if>
			
		</div>
		<div class="container" style="text-align:center;">
			<a class="navbar-brand" href="/main.omc"> 
				<img src="resources/img/omc_logoMain.png;" style="display: block; margin: 0 auto; width: 100%; max-width: 200px; height: auto;">
			</a>
		</div>
	</div>

	<nav class="navbar navbar-expand-lg navbar-dark ftco_navbar ftco-navbar-light" id="ftco-navbar">
	    <div class="container">
	    	<!-- 모바일버전 메뉴 토글버튼 -->
	      <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#ftco-nav" aria-controls="ftco-nav" aria-expanded="false" aria-label="Toggle navigation">
	        <span class="oi oi-menu"></span> Menu
	      </button>
	               
			<div class="collapse navbar-collapse" id="ftco-nav">
				<div align="left">
				<ul class="navbar-nav ml-auto">
					<li class="nav-item active dropdown">
              		<a class="nav-link dropdown-toggle" href="#" id="dropdown04" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false"
              	  		style="font-size:large; color:dark;">카테고리</a>
	              		<div class="dropdown-menu" aria-labelledby="dropdown04">
	              			<a class="dropdown-item" href="/allGoodsList.omc">전체 상품</a>
	              			<a class="dropdown-item" href="/allGoodsListCategory.omc?GD_CATEGORY=KOREAN" >한식</a>
			                <a class="dropdown-item" href="/allGoodsListCategory.omc?GD_CATEGORY=ITALIAN" >양식</a>
			                <a class="dropdown-item" href="/allGoodsListCategory.omc?GD_CATEGORY=CHIJAP" >중식/일식</a>
			                <a class="dropdown-item" href="/allGoodsListCategory.omc?GD_CATEGORY=ASIAN" >아시아</a>
			                <a class="dropdown-item" href="/allGoodsListCategory.omc?GD_CATEGORY=SNACKBAR" >분식</a>
			                <a class="dropdown-item" href="/allGoodsListCategory.omc?GD_CATEGORY=HEALTHY" >건강식</a>
						</div>
           	  		</li>
					<li class="nav-item active"><a href="/allGoodsListBest.omc" class="nav-link" style="font-size:x-large; color:#fd7e14;">베스트 상품</a></li>
					<li class="nav-item active"><a href="/allGoodsListNew.omc" class="nav-link" style="font-size:x-large; color:#fd7e14;">신상품</a></li>
					<li class="nav-item active"><a href="/event.omc" class="nav-link" style="font-size:x-large; color:#fd7e14;">이벤트</a></li>
					<li class="nav-item active"><a href="/board.omc" class="nav-link" style="font-size:x-large; color:#fd7e14;">커뮤니티</a></li>
				</ul>
				</div>

	        <ul class="navbar-nav ml-auto">
	        
	          <!-- 관리자일 경우 관리 탭 추가 -->
	          <c:if test="${ MEM_ID == 'ADMIN' }">
	        	<li class="nav-item"><a href="/adminMain.omc" class="nav-link" style="font-size:large; color:dark;">관리</a></li>
	          </c:if>
			  
	        </ul>
	      </div>
	    </div>
	  </nav>
	 

	 
</body>