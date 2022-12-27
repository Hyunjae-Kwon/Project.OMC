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
            alert("작성이 완료되었습니다.");
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

	


	 

	 
<div style="text-align:center">
<br/><br/><h5> Q&A </h5>
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
                    				<h5 style="color:#82ae46;">ADMIN</h5><!-- 관리자답변이므로 OMC에서 ADMIN으로 변경  -->
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
    
       
     
</body>
</html>