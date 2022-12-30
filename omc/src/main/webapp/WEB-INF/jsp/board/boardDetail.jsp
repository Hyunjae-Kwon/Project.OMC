<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/include/include-header.jspf" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<% pageContext.setAttribute("replaceChar","\n"); %>
<!DOCTYPE html>
<html lang="ko">
<head>

<meta charset="utf-8">
<title>오늘의 메뉴 추천, 오메추</title>

<body>
<div class="container">
<table class="board_view">
		<colgroup>
			<col width="12%"/>
			<col width="13%"/>
			<col width="12%"/>
			<col width="13%"/>
			<col width="12%"/>
			<col width="13%"/>
			<col width="12%"/>
			<col width="13%"/>
			
			<col width="25%"/>
			<col width="40%"/>
		</colgroup>
		
		<tbody>
			<c:forEach var="boardDetail" items="${boardDetail}">
			 <tr>
            <th scope="row">글 번호</th>
            <td>${boardDetail.BD_NUM }</td>
            <th scope="row">카테고리</th>
            <td>
            <c:if test="${boardDetail.BD_TYPE eq 'C' }">커뮤니티</c:if>
            <c:if test="${boardDetail.BD_TYPE eq 'N' }">공지사항</c:if>
            <c:if test="${boardDetail.BD_TYPE eq 'F' }">FAQ</c:if>
            <c:if test="${boardDetail.BD_TYPE eq 'Q' }">Q&A</c:if>
            </td>
            <th scope="row">작성시간</th>
            <td ${boardDetail.BD_REGDATE }>
            <fmt:formatDate value="${boardDetail.BD_REGDATE}" pattern="yyyy-MM-dd"/>   
            </td>
            <th scope="row">조회수</th>
            <td>${boardDetail.BD_COUNT}</td>
			</tr>
			<tr>
				<th scope="row">제목</th>
				<td colspan="8" style="font-size:25px">${boardDetail.BD_TITLE}</td>
			</tr>		
			<tr height="200">
				<th scope="row">내용</th>
				<td colspan="6">${fn:replace(boardDetail.BD_CONTENT, replaceChar,"<br/>") }</td>
				
			</tr>		
	</table>

		<div class="col-md-12"><hr>
		    <b>댓글</b><br><br>
		    	<c:if test="${comListCount!=0}">
		    	
					<c:forEach var="comment" items="${comList}" varStatus="status">
					    <ul class="comment-list">
							<li class="comment">
								<div class="vcard bio">
                    				<h5 style="color:#82ae46;">${comment.BC_ID }</h5><!-- COMMENTWRITER로 하면 ADMIN으로 떠서 Jumo로 입력 -->
                  				</div>
								<div class="comment-body">
									<div class="meta">${comment.BC_REGDATE}</div><!-- 답변 작성 날짜 -->
									<p style="font-size:middle;">
									 	${comment.BC_COMMENT}
									 	<input type="hidden" id="BC_BCID" name="BC_BCID" value="${comment.BC_BCID }">
										<input type="hidden" id="BC_NUM" name="BC_NUM" value="${comment.BC_NUM }">
									 </p>
									 <c:if test="${MEM_ID eq comment.BC_ID}">
									<p><a onClick="deleteCheck1()" class="reply" style="cursor:pointer">삭제</a>
									</c:if>
								</div>
						     </li>
						</ul>
					</c:forEach>
				</c:if>
			</div>
	 
 	<form action="boardComWrite.omc?BD_NUM=${boardDetail.BD_NUM}" name="frm" id="frm" method="post" enctype="multipart/form-data">
   
   <input type="hidden" name="BC_NUM" id="BD_NUM" value="${boardDetail.BD_NUM}">
   <input type="hidden" id="MEM_ID" name="MEM_ID" value="${MEM_ID}"/>
   <div style="display:inline-block; width:88%;">
   <textarea name="BC_COMMENT" id="BC_COMMENT" class="form-control" width="100%" placeholder="댓글을 입력해주세요."></textarea>
   </div>
   <div style="display:inline-block; float:right; width:10%;">
	<input type="button" value="댓글쓰기" onclick="fn_commentCheck()" class="btn btn-primary py-2 px-2" ></a> 
   
   </div>
    </form> 
	<br/>
	<hr>
	  <div align="center" style="height:100px;"> 
		<c:if test="${boardDetail.BD_TYPE eq 'C' }">
			<input type="button" value="이전" class="btn btn-primary py-2 px-2" style="height:55px;" onClick="location.href='boardList.omc'">
		</c:if>
		<c:if test="${boardDetail.BD_TYPE eq 'N' }">
			<input type="button" value="이전" class="btn btn-primary py-2 px-2" style="height:55px;" onClick="location.href='noticeList.omc'">
		</c:if>
		<c:if test="${boardDetail.BD_TYPE eq 'F' }">
			<input type="button" value="이전" class="btn btn-primary py-2 px-2" style="height:55px;" onClick="location.href='faqList.omc'">
		</c:if>
		<c:if test="${MEM_ID eq boardDetail.BD_ID}">
			<input type="button" value="수정" class="btn btn-primary py-2 px-2" style="height:55px;" onClick="location.href='updateBoardForm.omc?BD_NUM=${boardDetail.BD_NUM}'">
			<input type="button" value="삭제" class="btn btn-primary py-2 px-2" style="height:55px;" onClick="return deleteCheck()">
		</c:if>
    </div>
   
	</c:forEach>	
		</tbody>
   
</div>

</body>
<script>

    <!-- 댓글 입력 -->
    function fn_commentCheck() {
    	var frm = document.getElementById('frm');
    	var BD_NUM = document.getElementById('BD_NUM');
    	
    	if (!$("#BC_COMMENT").val()) {
            alert("내용을 입력하세요.");
            $("#BC_COMMENT").focus();
            return false;
         }
      /*  alert("댓글이 정상적으로 등록 되었습니다."); */
    	//commentForm.action = 
    	frm.submit();
    }
  

function deleteCheck1() {
	var BC_BCID = document.getElementById('BC_BCID').value;
	var BC_NUM = document.getElementById('BC_NUM').value;
	if(confirm("삭제하시겠습니까?") == true) {
		location.href="commentDelete.omc?BC_BCID=" + BC_BCID +"&BC_NUM="+ BC_NUM;
	}/* adminQnaComDelete.al?COMMENTIDX=${comment.COMMENTIDX}&ARTICLEIDX=${comment.ARTICLEIDX } */
}

<!-- 수정 유효성검사 -->

function deleteCheck2() {
	var BC_BCID = document.getElementById('BC_BCID').value;
	var BC_NUM = document.getElementById('BC_NUM').value;
	if(confirm("수정하시겠습니까?") == true) {
		location.href="commentModify.omc?BC_BCID=" + BC_BCID +"&BC_NUM="+ BC_NUM;
	}
}	


function commentWrite(){
   var comSubmit = new ComSubmit("frm");
      comSubmit.setUrl("<c:url value='/commentUpdate.omc' />");
      if (!$("#BC_COMMENT").val()) {
         alert("내용을 입력하세요.");
         $("#BC_COMMENT").focus();
         return false;
      }
    alert("댓글이 정상적으로 수정 되었습니다.");
   comSubmit.submit();
   }

<!-- 댓글 수정 -->
$(function (){
	$('input[type="button"][id="commentModify"]').on('click', function(){
	var etcChk = $('input[type=button][id="commentModify"]:checked').val();
		if(etcChk=='Modify'){
		$('#comModify').css('display','block');
		} 
	});
});
</script>

<script>
function deleteCheck() {
	var BD_NUM = document.getElementById('BD_NUM').value;
	if(confirm("삭제하시겠습니까?") == true) {
		location.href="boardDelete.omc?BD_NUM=" + BD_NUM;
	}
}
</script>


</html>