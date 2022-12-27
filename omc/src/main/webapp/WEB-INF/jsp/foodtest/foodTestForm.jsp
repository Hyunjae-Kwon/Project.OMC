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
<link rel="stylesheet" href="resources/css/foodtest/result.css">
<script src="https://developers.kakao.com/sdk/js/kakao.js"></script>
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