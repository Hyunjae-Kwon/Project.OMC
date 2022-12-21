<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/include/include-header.jspf" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
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
			<col width="13%"/>
			<col width="13%"/>
			<col width="13%"/>
			<col width="13%"/>
			<col width="12%"/>
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
            <c:if test="${boardDetail.BD_TYPE eq 'Q' }">FAQ</c:if>
            </td>
            <th scope="row">작성시간</th>
            <td>${boardDetail.BD_REGDATE }</td>
            <th scope="row">조회수</th>
            <td>${boardDetail.BD_COUNT}</td>
			</tr>
			<tr>
				<th scope="row">제목</th>
				<td colspan="5">${boardDetail.BD_TITLE}</td>
			</tr>
		
			
			<tr>
				<th scope="row">내용</th>
				<td colspan="6">${fn:replace(boardDetail.BD_CONTENT, replaceChar,"<br/>") }</td>
				
			</tr>
		
	</table>
<!--  댓글 시작 -->
		<div class="col-md-12"><hr>
		 	<form action="boardComWrite.omc?BD_NUM=${boardDetail.BD_NUM}" name="frm" id="frm" method="post" enctype="multipart/form-data">
		
		    <b>댓글</b><br><br>
		    <div><button class="reply-content-toggle">댓글 열기</button></div>
		    	<c:if test="${comListCount!=0}">
		    	
		<c:forEach var="comment" items="${comList}" varStatus="status">
		<ul class="comment-list">
		<li class="comment">
		<div class="vcard bio">
        <h5 style="color:#82ae46;">${comment.BC_ID }</h5><!-- COMMENTWRITER로 하면 ADMIN으로 떠서 OMC로 입력 -->
        </div>
		<div class="comment-body">
		<div class="meta">${comment.BC_REGDATE}</div><!-- 답변 작성 날짜 -->
		<p style="font-size:middle;">
		 ${comment.BC_COMMENT}
		<input type="hidden" id="BC_BCID" name="BC_BCID" value="${comment.BC_BCID }">
		<input type="hidden" id="BC_NUM" name="BC_NUM" value="${comment.BC_NUM }">
		</p>
		<a onClick="javascript:if(confirm('삭제하시겠습니까?')==true){ location.href='commentDelete.omc?BC_BCID=${comment.BC_BCID}&BC_NUM=${comment.BC_NUM }' } else{ return false; }">
		<span style="font-size:9pt; color:gray; padding-right:10px; float:left;">삭제</span></a>
		<button type="button" id="modifyComment" name="modifyComment" onclick="modifyComment(this.id)"
		style="font-size:9pt; color:gray; padding-right:10px; float:left;">수정</button>
		</a>
									
								</div>
						
						     </li>
						</ul>
					</c:forEach>
					
				</c:if>
				
			</div>

   
   <input type="hidden" name="BC_NUM" id="BD_NUM" value="${boardDetail.BD_NUM}">
   <input type="hidden" id="MEM_ID" name="MEM_ID" value="${MEM_ID}"/>
   <div style="display:inline-block; width:88%;">
   <textarea name="BC_COMMENT" id="BC_COMMENT" class="form-control" width="100%" placeholder="댓글을 입력해주세요."></textarea>
   </div>
   <div style="display:inline-block; float:right; width:10%;">
	<input type="button" value="댓글쓰기" onclick="commentCheck()" class="btn btn-primary py-2 px-2" ></a> 
   
   </div>
    </form> 
 <!--  댓글 끝 -->  
   
<!--  글 수정 /목록 버튼  -->
	<br/>
	<hr>
	  <div align="center" style="height:100px;"> 
		<input type="button" value="목록" class="btn btn-primary py-2 px-2" style="height:55px;" onClick="window.history.back()">
	
		<input type="button" value="수정" class="btn btn-primary py-2 px-2" style="height:55px;" onClick="location.href='updateBoardForm.omc?BD_NUM=${boardDetail.BD_NUM}'">
    </div>
   
	</c:forEach>	
		</tbody>
   
</div>

</body>
<script type = "text/javascript">

$("#modifyComment").one("click", function(e){ //수정하기 버튼
    e.preventDefault();
    fn_modifyComment($(this));
 });
 
function fn_modifyComment(obj){
	   var str = "<input type = 'text' name='BC_COMMENT' id='BC_COMMENT'>"
	   + "<a href='#this' class='btn' name='updateComment'>작성</a>";
	   $(obj.parent()).append(str);
	   $("a[name='updateComment']").on("click", function(e){
		   e.preventDefault();
		   fn_updateComment($(this));
	   });
	}
function fn_updateComment(obj){		
	var comSubmit = new ComSubmit();		
	comSubmit.setUrl("<c:url value='/board/commentModify' />");
	comSubmit.addParam("BC_BCID", obj.parent().find("#BC_BCID").val());
	comSubmit.addParam("BC_COMMENT", obj.parent().find("#BC_COMMENT").val());
	comSubmit.addParam("BD_NUM",$("input[name='BD_NUM']").val());
	comSubmit.addParam("ID",$("input[name='B_ID']").val());
	comSubmit.submit();	 
}
/* function fn_commentWrite(){
    var comSubmit = new ComSubmit("frm");
       comSubmit.setUrl("/insertBoardComment.omc");

       if (!$("#BC_COMMENT").val()) {
          alert("내용을 입력하세요.");
          $("#BC_COMMENT").focus();
          return false;
       }
     alert("댓글이 정상적으로 등록 되었습니다.");
    comSubmit.submit();
    } */
    

    function commentCheck() {
    	var frm = document.getElementById('frm');
    	var BD_NUM = document.getElementById('BD_NUM');
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



</script>


</html>