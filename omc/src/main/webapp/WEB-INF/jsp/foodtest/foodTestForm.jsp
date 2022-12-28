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

<!--favicon-->
<link rel="shortcut icon" href="img/favicon.ico">
<link rel="apple-touch-icon-precomposed" href="img/favicon.ico" />
<title>오메추 음식 취향 테스트</title>
<link rel="preconnect" href="https://fonts.gstatic.com">
<link href="https://fonts.googleapis.com/css2?family=Nanum+Pen+Script&display=swap" rel="stylesheet">
<link rel="stylesheet" href="resources/css/foodtest/default.css">
<link rel="stylesheet" href="resources/css/foodtest/main.css">
<link rel="stylesheet" href="resources/css/foodtest/qna.css">
<link rel="stylesheet" href="resources/css/foodtest/animation.css">
<link rel="stylesheet" href="resources/css/foodtest/result.css">
<script src="https://developers.kakao.com/sdk/js/kakao.js"></script>
<script>
function resultSet(){
	
}
</script>
</head>
<body>
	<div class="container">
    <section id="main" class="mx-auto my-5 py-5 px-3">
      <h1>오늘 먹고 싶은 음식은?!</h1><br/><br/>
      <div class="col-lg-6 col-md-8 col-sm-10 col-12 mx-auto">
        <img src="resources/img/omc_logoIcon.png" alt="mainImage" class="img-fluid"><br/><br/>
      </div>
      <p>
        오메추의 음식 취향 테스트입니다! <br>
        아래 시작하기 버튼을 눌러 시작해 주십시오.
      </p>
      <button type="button" class="btn btn-outline-danger mt-3" onclick="js:begin()">시작하기</button>
    </section>
    <section id="qna">
      <div class="status mx-auto mt-5">
        <div class="statusBar">
        </div>
      </div>
      <div class="qBox my-5 py-3 mx-auto">

      </div>
      <div id="qImg" class="my-3 col-lg-6 col-md-8 col-sm-10 col-12 mx-auto" style="max-width: 35%;">
      
      </div>
      <div class="answerBox">

      </div>

    </section>
    <section id="result" class="mx-auto my-5 py-5 px-3">
      <h1>당신의 결과는?!</h1>
      <form id="resultSet">
	      <div id="FT_TITLE" class="resultname">
	
	      </div>
	      <div id="resultImg" class="my-3 col-lg-6 col-md-8 col-sm-10 col-12 mx-auto">
	      	  <input type="hidden" class="img-fluid">
		      <input type="hidden" class="img-fluid">
		      <input type="hidden" class="img-fluid">
		      <input type="hidden" class="img-fluid">
	
	      </div>
	      <div id="FT_CONTENT" class="resultDesc">
	
	      </div>
	      <h3>오메추 추천 상품</h3>
	      <div>
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
	    					<a href="/allGoodsList.omc" class="img-prod"><img class="img-fluid" src="resources/img/main_bp.png" style="height:250px;">
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
      <button type="button" class="foodTest" id="foodTest" name="foodTest" onClick="resultSet()">저장하기</button>
      <button type="button" class="kakao mt-3 py-2 px-3" onclick="js:setShare()">공유하기</button>
    </section>

    <script src="resources/js/foodtest/data.js" charset="utf-8"></script>
    <script src="resources/js/foodtest/start.js" charset="utf-8"></script>
    <script src="resources/js/foodtest/share.js" charset="utf-8"></script>
  </div>
</body>
</html>