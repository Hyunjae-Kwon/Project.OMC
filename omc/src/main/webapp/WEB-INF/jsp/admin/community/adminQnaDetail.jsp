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

<!-- 삭제 유효성검사 -->
<script>
function deleteCheck1() {
	var BC_BCID = document.getElementById('BC_BCID').value;
	var BC_NUM = document.getElementById('BC_NUM').value;
	if(confirm("삭제하시겠습니까?") == true) {
		location.href="adminQnaComDelete.omc?BC_BCID=" + BC_BCID +"&BC_NUM="+ BC_NUM;
	}/* adminQnaComDelete.al?COMMENTIDX=${comment.COMMENTIDX}&ARTICLEIDX=${comment.ARTICLEIDX } */
}
</script>

<!-- 수정 유효성검사 -->
<script>
function deleteCheck2() {
	var BC_BCID = document.getElementById('BC_BCID').value;
	var BC_NUM = document.getElementById('BC_NUM').value;
	if(confirm("수정하시겠습니까?") == true) {
		location.href="adminQnaComModify.omc?BC_BCID=" + BC_BCID +"&BC_NUM="+ BC_NUM;
	}
}	
</script>

<!-- 댓글 입력 -->
<script type="text/javascript">
function commentCheck() {
	var commentForm = document.getElementById('commentForm');
	var BD_NUM = document.getElementById('BD_NUM');
	//commentForm.action = 
	commentForm.submit();
}
</script>

<!-- 댓글 수정 -->
<script type="text/javascript">
$(function (){
	$('input[type="button"][id="commentModify"]').on('click', function(){
	var etcChk = $('input[type=button][id="commentModify"]:checked').val();
		if(etcChk=='Modify'){
		$('#comModify').css('display','block');
		} 
	});
});
</script>
</head>
<body>
<div style="text-align:center">
	<h5> 상세 내용 </h5>
	<hr width="80%">
</div>
<section class="ftco-section ftco-degree-bg">
	<div class="container">
		<div class="row">
			<div class="blog-entry align-self-stretch d-md-flex">
				<div class="text d-block pl-md-4">
					<h3>${qnaMap.BD_TITLE}</h3>
					<div class="meta mb-3">
						<div>${qnaMap.BD_ID}</div>
						<div>${qnaMap.BD_REGDATE}</div>
					</div><hr>
      				<p class="mt-5" style="font-size:large;">${qnaMap.BD_CONTENT}</p>
    			</div>
			</div>
		</div>
		
		<div class="col-md-12"><hr>
		    <b>댓글</b><br><br>
		    	<c:if test="${comCount!=0}">
					<c:forEach var="comment" items="${comList}" varStatus="status">
					    <ul class="comment-list">
							<li class="comment">
								<div class="vcard bio">
                    				<h5 style="color:#82ae46;">OMC</h5><!-- COMMENTWRITER로 하면 ADMIN으로 떠서 Jumo로 입력 -->
                  				</div>
								<div class="comment-body">
									<div class="meta">${comment.BC_REGDATE}</div><!-- 답변 작성 날짜 -->
									<p style="font-size:middle;">
									 	${comment.BC_COMMENT}
									 	<input type="hidden" id="BC_BCID" name="BC_BCID" value="${comment.BC_BCID }">
										<input type="hidden" id="BC_NUM" name="BC_NUM" value="${comment.BC_NUM }">
									 </p>
									<p><a onClick="javascript:if(confirm('수정하시겠습니까?')==true){ location.href='adminQnaComModify.omc?BC_BCID=${comment.BC_BCID}&BC_NUM=${comment.BC_NUM }' } else{ return false; }"
										class="reply">수정</a></p>
									<p><a onClick="javascript:if(confirm('삭제하시겠습니까?')==true){ location.href='adminQnaComDelete.omc?BC_BCID=${comment.BC_BCID}&BC_NUM=${comment.BC_NUM }' } else{ return false; }"
										class="reply">삭제</a></p>
								</div>
						     </li>
						</ul>
					</c:forEach>
				</c:if>
				<p>댓글이 없습니다.</p>
			</div>
			
			<div class="col-md-12 comment-form-wrap">
				<form id="commentForm" action="adminQnaComWrite.omc?BD_NUM=${qnaMap.BD_NUM}" method="post"><hr>
				<b>댓글 쓰기</b><br><br>
					<input type="hidden" name="BC_NUM" value="${qnaMap.BD_NUM}">
					<input class="form-control input-sm" id="newReplyWriter" 
								name="COMMENTWRITER" type="hidden" value="관리자" readonly>
					<div class="row">
						<div class="form-group">
                    		<textarea id="newReplyText" name="COMMENTT" cols="120" rows="2" class="form-control"></textarea>
                 		</div> &nbsp;&nbsp;
						<div class="form-group">
							<input type="button" class="btn py-3 px-4 btn-primary btn-outline-primary" onClick="commentCheck()" value="등록">
						</div>
					</div>
				</form>
			</div>
	</div>
</section>
</body>
</html>