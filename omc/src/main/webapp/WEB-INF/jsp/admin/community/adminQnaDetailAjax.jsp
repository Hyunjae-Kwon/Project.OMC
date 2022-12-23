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
/* 댓글 수정 Ajax */
function ModifyCheckAjax(BC_BCID, BC_NUM, index) {
   if(confirm("수정하시겠습니까?") == true) {

	   var BC_COMMENT = document.getElementById("BC_COMMENT").value;
	   var BC_ID = document.getElementById("BC_ID").value;
	   var BC_BCID = document.getElementById("BC_BCID").value;
	   
      $.ajax({
         url         : "adminQnaComModifyAjax.omc",
         data      : {"BC_BCID" : Number(BC_BCID), "BC_NUM" : Number(BC_NUM), "BC_COMMENT" : BC_COMMENT, "BC_ID" : BC_ID},
         contentType   : "application/json",
         success      : function(data) {
            alert("수정이 완료되었습니다.");
             window.location.reload();
         },
         error:function(request, error) {
            alert("fail");
            // error 발생 이유를 알려준다.
         alert("code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error);
         }
      });
   }
}
</script>

<script>
/* 댓글 삭제 Ajax */
function deleteCheckAjax(BC_BCID, BC_NUM, index) {
   if(confirm("삭제하시겠습니까?") == true) {
      
      $.ajax({
         url         : "adminQnaComDeleteAjax.omc",
         data      : {"BC_BCID" : Number(BC_BCID), "BC_NUM" : Number(BC_NUM)},
         contentType   : "application/json",
         success      : function(data) {
            alert("삭제하였습니다.");
            $("#Dcom"+index).remove();
         },
         error:function(request, error) {
            alert("fail");
            // error 발생 이유를 알려준다.
         alert("code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error);
         }
      });
   }
}
</script>

<script>
/* 댓글 입력 Ajax */
function WriteCheckAjax(BD_NUM) {
   if(confirm("작성하시겠습니까?") == true) {

	   var BC_COMMENT = document.getElementById("newReplyText").value;
	   var BC_ID = document.getElementById("newReplyWriter").value;
	  
	   
      $.ajax({
         url         : "adminQnaComWriteAjax.omc",
         data      : {"BC_NUM" : Number(BD_NUM), "BC_COMMENT" : BC_COMMENT, "BC_ID" : BC_ID},
         contentType   : "application/json",
         success      : function(data) {
        	 console.log(data)
            alert("작성이완료되었습니다.");
            window.location.reload();
         },
         error:function(request, error) {
            alert("fail");
            // error 발생 이유를 알려준다.
         alert("code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error);
         }
      });
   }
}
</script>



<style type="text/css">
p { word-break: break-all;}
</style>

</head>
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
	              			<a class="dropdown-item" href="/allGoodsListCategory.omc?GD_CATEGORY=한식" >한식</a>
			                <a class="dropdown-item" href="/allGoodsListCategory.omc?GD_CATEGORY=양식" >양식</a>
			                <a class="dropdown-item" href="/allGoodsListCategory.omc?GD_CATEGORY=중식/일식" >중식/일식</a>
			                <a class="dropdown-item" href="/allGoodsListCategory.omc?GD_CATEGORY=아시안" >아시아</a>
			                <a class="dropdown-item" href="/allGoodsListCategory.omc?GD_CATEGORY=분식" >분식</a>
			                <a class="dropdown-item" href="/allGoodsListCategory.omc?GD_CATEGORY=건강식" >건강식</a>
						</div>
           	  		</li>
					<li class="nav-item active"><a href="/allGoodsListBest.omc" class="nav-link" style="font-size:x-large; color:#fd7e14;">베스트 상품</a></li>
					<li class="nav-item active"><a href="/allGoodsListNew.omc" class="nav-link" style="font-size:x-large; color:#fd7e14;">신상품</a></li>
					<li class="nav-item active"><a href="/noticeList.omc" class="nav-link" style="font-size:x-large; color:#82ae46;">공지사항</a></li>
					<li class="nav-item active"><a href="/boardList.omc" class="nav-link" style="font-size:x-large; color:#82ae46;">커뮤니티</a></li>
					<li class="nav-item active"><a href="/faqList.omc" class="nav-link" style="font-size:x-large; color:#82ae46;">FAQ</a></li>
					
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
	 

	 
<div style="text-align:center">
	<h1> 상세 내용 </h1>
</div>

<section class="ftco-section ftco-degree-bg">
	<div class="container">
		<div class="row">
			<div class="blog-entry align-self-stretch d-md-flex">
				<div class="text d-block pl-md-4">
					<h3><p style="width: 100%;">${qnaMap.BD_TITLE}</p></h3>
					<div class="meta mb-3">
						<div>${qnaMap.BD_ID}</div>
						<div>${qnaMap.BD_REGDATE}</div>
					</div><hr>
      				<p class="mt-5" style="font-size:large; width: 100%;">${qnaMap.BD_CONTENT}</p>
    			</div>
			</div>
		</div>
		
		<div class="col-md-12"><hr>
		    <p class="mb-5 mt-5">댓글</p>
		    <c:choose>
		    	<c:when test="${comCount!=0}">
					<c:forEach var="comment" items="${comList}" varStatus="status">
					    <ul class="comment-list">
							<li class="comment" id="Dcom${status.index}">
								<div class="vcard bio">
                    				<h5 style="color:#82ae46;">OMC</h5><!-- COMMENTWRITER로 하면 ADMIN으로 떠서 Jumo로 입력 -->
                  				</div>
								<div class="comment-body">
									<div class="meta"><p style="width: 100%;">${comment.BC_REGDATE}</p></div><!-- 답변 작성 날짜 -->
									<p style="font-size:middle; width: 100%;">
									 	${comment.BC_COMMENT}
									 	<input type="hidden" id="BC_BCID" name="BC_BCID" value="${comment.BC_BCID }">
										<input type="hidden" id="BC_NUM" name="BC_NUM" value="${comment.BC_NUM }">															             							              
									 <div>								
									 <a onClick="deleteCheckAjax(${comment.BC_BCID }, ${qnaMap.BD_NUM} ,${status.index});" class="reply">삭제</a>
									 </div>
								</div> 
						     </li>
						</ul>
					</c:forEach>
				</c:when>
				<c:otherwise>
		  
				</c:otherwise>
			</c:choose>
			</div>
			
			<div class="col-md-12 comment-form-wrap">
			
			<form action="adminQnaComWrite.omc?BD_NUM=${qnaMap.BD_NUM}" method="post" ><hr>
				<p>댓글 쓰기</p>
				<input type="hidden" name="BC_NUM" value="${qnaMap.BD_NUM}">
				<input class="form-control input-sm" id="newReplyWriter" 
								name=BC_ID type="hidden" value="관리자" readonly>
					<div class="row">
						<div class="form-group">
							<textarea id="newReplyText" name="BC_COMMENT" cols="120" rows="2" class="form-control"></textarea>
						</div> &nbsp;&nbsp;
						<div class="form-group">
							<input type="button" class="btn py-3 px-4 btn-primary btn-outline-primary" onClick="WriteCheckAjax(${qnaMap.BD_NUM});" value="저장">
					 	</div>
<!-- 						<div class="form-group">
							<input type="button" class="btn py-3 px-4 btn-primary btn-outline-primary" onClick="commentCheck()" value="저장">
						</div> -->
					</div>
				</form>
			</div>
		
	</div>
</section>
      <div class="container">
      	<div class="row">
      		<div class="mouse">
				<a href="#" class="mouse-icon">
					<div class="mouse-wheel"><span class="ion-ios-arrow-up"></span></div>
				</a>
			</div>
      	</div>
        <div class="row mb-5">
          <div class="col-md-5">
            <div class="ftco-footer-widget mb-4">
            <h2><b>사업자 정보</b></h2>
              <p>사업자 이름 : (주)오메추</p>
              <p>사업자 번호 : 110-354-440887</p>
              <p>대표 : 오메추</p>
              <b>오메추는 통신판매중개자로서 통신판매 당사자가 아니며,</b>
              <p><b>판매자가 등록한 상품정보 및 거래에 대해 주모는 책임을 지지 않습니다.</b></p>
            </div>
          </div>
          <div class="col-md-4">
             <div class="ftco-footer-widget mb-3 ml-md-6">
              <div class="d-flex">
	              <ul class="list-unstyled">
	              	<h2><b>고객 센터</b></h2>
	                <p>평일 : 10:00~17:00</p>
	                <p>점심시간 : 12:00~16:00</p>
	                <p>공휴일 영업X<p>
	                <li class="py-2 d-block"><span class="icon icon-map-marker"></span><span class="text">&nbsp;서울시 종로구 관철동 미려빌딩 3층 오메추</span></li>
	                <li class="py-2 d-block"><a href="#"><span class="icon icon-phone"></span><span class="text">&nbsp;+82 10 9999 9999</span></a></li>
	                <li class="py-2 d-block"><a href="#"><span class="icon icon-envelope"></span><span class="text">&nbsp;todaysmeal@omc.com</span></a></li>
	              </ul>
	            </div>
            </div>
          </div>
          <div class="col-md-3">
            <div class="ftco-footer-widget mb-4 ml-md-5">
	          <ul class="list-unstyled">
	          	<h2><b>입금 정보</b></h2>
	          	<li>우리은행 : 1002-353-887291</li>
	          </ul>
            </div>
          </div>

          <div class="col-md">
            
          </div>
        </div>
        <div class="row">
          <div class="col-md-12 text-center">
            <p><!-- Link back to Colorlib can't be removed. Template is licensed under CC BY 3.0. -->
			Copyright &copy;<script>document.write(new Date().getFullYear());</script> All rights reserved | This template is made with <i class="icon-heart color-danger" aria-hidden="true"></i> by <a href="https://colorlib.com" target="_blank">Colorlib</a>
			<!-- Link back to Colorlib can't be removed. Template is licensed under CC BY 3.0. -->
			</p>
          </div>
        </div>
      </div>
</body>
</html>