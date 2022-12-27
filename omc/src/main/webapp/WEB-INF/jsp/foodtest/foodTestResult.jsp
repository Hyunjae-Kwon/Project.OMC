<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ include file="/WEB-INF/include/include-header.jspf"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="author" content="PandaCoding">
<meta name="keywords" content="십이간지 연애유형">
<meta name="description" content="십이간지 연애유형">

<!-- sns share -->
<meta property="og:url" content="https://twelvelovetype.netlify.app" />
<meta property="og:title" content="십이간지 연애유형" />
<meta property="og:type" content="website" />
<meta property="og:image" content="resources/img/foodtest/share.png" />
<meta property="og:description" content="십이간지로 알아보는 연애유형" />

<!--favicon-->
<link rel="shortcut icon" href="img/favicon.ico">
<link rel="apple-touch-icon-precomposed" href="img/favicon.ico" />
<title>오메추 음식 취향 테스트</title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-BmbxuPwQa2lc/FVzBcNJ7UAyJxM6wuqIj61tLrc4wSX0szH/Ev+nYRRuWlolflfl" crossorigin="anonymous">
<link rel="preconnect" href="https://fonts.gstatic.com">
<link href="https://fonts.googleapis.com/css2?family=Nanum+Pen+Script&display=swap" rel="stylesheet">
<link rel="stylesheet" href="resources/css/foodtest/default.css">
<link rel="stylesheet" href="resources/css/foodtest/main.css">
<link rel="stylesheet" href="resources/css/foodtest/qna.css">
<link rel="stylesheet" href="resources/css/foodtest/animation.css">
<link rel="stylesheet" href="resources/css/foodtest/result2.css">
<script src="https://developers.kakao.com/sdk/js/kakao.js"></script>
</head>
<body>
	<div class="container">
    <section id="result" class="mx-auto my-5 py-5 px-3">
      <h1>당신의 결과는?!</h1>
      <form id="resultSet">
	      <div id="FT_TITLE" class="resultname">
	      	${testResult.FT_TITLE}
	      </div>
	      <div id="resultImg" class="my-3 col-lg-6 col-md-8 col-sm-10 col-12 mx-auto">
			<img src="resources/img/foodtest/${testResult.FT_IMAGE}" class="img-fluid">
	      </div>
	      <div id="FT_CONTENT" class="resultDesc">
			${testResult.FT_CONTENT}
	      </div>
	      <h3>오메추 추천 상품</h3>
	      <div>
	      	<div class="row">
	    			<c:forEach var="goods" items="${resultGoods}">
	    			<div class="col-md-6 col-lg-3 ftco-animate" style="float:left; width:33%; padding:10px;">
	    				<div class="goods">
	    					<a href="/goodsDetail.omc?GD_GID=${goods.GD_GID}" class="img-prod">
	    						<img class="img-fluid" src="resources/img/goods/${goods.GD_IMAGE}" style="height:250px;">
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
	    					<a href="/allGoodsList.omc" class="img-prod">
	    						<img class="img-fluid" src="resources/img/main_bp.png" style="height:250px;">
	    						<div class="overlay"></div>
	    					</a>
	    					<div class="text py-3 pb-4 px-3 text-center">
	    						<h3><a href="/allGoodsList.omc">상품 더보기</a></h3>
	    					</div>
	    				</div>
	    			</div>
    			</div>
	      </div>
      </form>
    </section>

  </div>
</body>
</html>