<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ include file="/WEB-INF/include/include-header.jspf" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body class="goto-here">

    <section id="home-section" class="hero">
		  <div class="home-slider owl-carousel">
	      <div class="slider-item" style="background-image: url(resources/img/bg_1.jpg);">
	      	<div class="overlay"></div>
	        <div class="container">
	          <div class="row slider-text justify-content-center align-items-center" data-scrollax-parent="true">

	            <div class="col-md-12 ftco-animate text-center">
	              <h1 class="mb-2">오늘의 메뉴는?!?!</h1>
	              <h2 class="subheading mb-4">오늘의 메뉴 추천, 오메추</h2>
	            </div>

	          </div>
	        </div>
	      </div>

	      <div class="slider-item" style="background-image: url(resources/img/bg_2.jpg);">
	      	<div class="overlay"></div>
	        <div class="container">
	          <div class="row slider-text justify-content-center align-items-center" data-scrollax-parent="true">

	            <div class="col-sm-12 ftco-animate text-center">
	              <h1 class="mb-2">오늘 뭐 먹지~?</h1>
	              <h2 class="subheading mb-4">오늘의 메뉴 추천, 오메추</h2>
	            </div>

	          </div>
	        </div>
	      </div>
	      
	      <div class="slider-item" style="background-image: url(resources/img/bg_3.jpg);">
	      	<div class="overlay"></div>
	        <div class="container">
	          <div class="row slider-text justify-content-center align-items-center" data-scrollax-parent="true">

	            <div class="col-sm-12 ftco-animate text-center">
	              <h1 class="mb-2">간편하게 즐길 수 있는 셰프들의 요리!</h1>
	              <h2 class="subheading mb-4">오늘의 메뉴 추천, 오메추</h2>
	            </div>

	          </div>
	        </div>
	      </div>
	      
	      <div class="slider-item" style="background-image: url(resources/img/bg_4.jpg);">
	      	<div class="overlay"></div>
	        <div class="container">
	          <div class="row slider-text justify-content-center align-items-center" data-scrollax-parent="true">

	            <div class="col-sm-12 ftco-animate text-center">
	              <h1 class="mb-2">메가 커피</h1>
	              <h2 class="subheading mb-4">오늘의 메뉴 추천, 오메추</h2>
	            </div>

	          </div>
	        </div>
	      </div>
	      
	      <div class="slider-item" style="background-image: url(resources/img/bg_5.jpg);">
	      	<div class="overlay"></div>
	        <div class="container">
	          <div class="row slider-text justify-content-center align-items-center" data-scrollax-parent="true">

	            <div class="col-sm-12 ftco-animate text-center">
	              <h1 class="mb-2">스타벅스</h1>
	              <h2 class="subheading mb-4">오늘의 메뉴 추천, 오메추</h2>
	            </div>

	          </div>
	        </div>
	      </div>
	    </div>
    </section>

    <section class="ftco-section">

    	<div class="container">
			<div class="row justify-content-center mb-3 pb-3">
          		<div class="col-md-12 heading-section text-center ftco-animate">
          			<span class="subheading">판매중인 상품</span>
            			<h2 class="mb-4">베스트 상품</h2>
            	</div>
        	</div> 
        	<!-- 상품 추가 임시 버튼 -->
        	<div align="right">
				<input type="button" value="상품 추가" class="btn btn-primary py-2 px-2" style="height:55px;" onClick="location.href='goodsWriteForm.omc'">
        	</div>	
        </div>
    	<form action="main.omc" method="GET">
    	
    	<div class="container">
    	<div class="row">
    		
    		<div class="container" align="center">
	    		<div class="row">
	    			<c:forEach var="goods" items="${mainList}">
	    			<div class="col-md-6 col-lg-3 ftco-animate" style="float:left; width:33%; padding:10px;">
	    				<div class="goods">
	    					<a href="/goodsDetail.omc?GD_GID=${goods.GD_GID}" class="img-prod"><img class="img-fluid" src="resources/img/goods/${goods.GD_IMAGE}" style="height:250px;">
	    						<div class="overlay"></div>
	    					</a>
	    					<div class="text py-3 pb-4 px-3 text-center">
	    						<h3><a href="/goodsDetail.omc?GD_GID=${goods.GD_GID}">${goods.GD_GNAME}</a></h3>
	    						<div class="d-flex">
	    							<div class="pricing">
	    								<p class="dcPrice"><span>${goods.GD_DCPRICE}원</span></p>
			    						<p class="price"><span>${goods.GD_PRICE}원</span></p>
			    					</div>
		    					</div>
	    					</div>
	    				</div>
	    			</div>
	    			</c:forEach>
					<div class="col-md-6 col-lg-3 ftco-animate" style="float:left; width:33%; padding:10px;">
	    				<div class="goods">
	    					<a href="/allGoodsList.omc" class="img-prod"><img src="resources/img/main_bp.jpg" style="height:250px;">
	    						<div class="overlay"></div>
	    					</a>
	    					<div class="text py-3 pb-4 px-3 text-center">
	    						<h3><a href="/allGoodsList.omc">상품 더보기</a></h3>
	    					</div>
	    				</div>
	    			</div>
    			</div>
    		</div>    		
    	</div>
    </div>
    </form>
</section>

</body>
</html>