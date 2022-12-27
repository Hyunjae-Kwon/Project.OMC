<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/include/include-header.jspf" %>
<!DOCTYPE html>
<html>
<head>


</head>
<body>

<!-- title -->
<div align="center">
   <h3><b>글 수정</b></h3> 
</div>
<div style="height: 30px;"></div>

<!-- body -->
<form id="frm" name="frm" enctype="multipart/form-data">
<div class="container" id="frm" style="width:60%;">
   <div style="margin-bottom:15px;">
      <!-- 카테고리 -->
      <c:forEach var="board" items="${board}">
      <div style="display:inline-block; width:19%;">
           <select class="form-control" id="BD_TYPE" name="BD_TYPE">
            <option>${board.BD_TYPE}</option>
            <option value="C">COMMUNITY</option>
            <option value="R">Review</option>
            <option value="Q">Q&A</option>
         </select>
      </div>
      <input type="hidden" name="BD_NUM" id="BD_NUM" value="${board.BD_NUM}"/>
      <!-- 제목 -->
      <div style="display:inline-block; width:80%; float:right;">
         <input type="text" id="BD_TITLE" name="BD_TITLE" class="form-control" value="${board.BD_TITLE}" style="width:100%;">
         <%-- <input type="hidden" name="BOARD_NUM" id="BOARD_NUM" value="${map.BOARD_NUM}"/> --%>
      </div>
   </div>
   <!-- 내용 -->
   <div style="margin-bottom:15px;">
      <textarea class="form-control" id="BD_CONTENT" name="BD_CONTENT" rows="12">${board.BD_CONTENT}</textarea> 
   </div>
  <!--   첨부파일 
   <div class="custom-file" style="margin-bottom:15px;">
     <input type="file" class="custom-file-input" id="customFile">
     <label class="custom-file-label" for="customFile">첨부 파일</label>
   </div> -->
   <!-- 버튼 -->
</c:forEach>
   <div align="center" style="height:100px;">
      <input type="button" class="btn btn-primary" onClick="return fn_updateBoard()" value="글 수정"></a>
      <input type="button" class="btn btn-primary" onClick="return fn_deleteBoard()" value="글 삭제">
      <input type="button" class="btn btn-outline-primary" value="취소" onClick="javascript:history.go(-1)">
   </div>
</div>
</form>
<form id="commonForm" name="commonForm"></form>
<script type="text/javascript">
   
  /*  
   $("#write").on("click", function(e){
         e.preventDefault();
         fn_updateBoard();
      });
 */

function fn_updateBoard(){

	   var comSubmit = new ComSubmit("frm");
	      comSubmit.setUrl("/updateBoard.omc");
		var BD_TYPE = document.getElementById("BD_TYPE").value;
		var BD_TITLE = document.getElementById("BD_TITLE").value;
		var BD_CONTENT = document.getElementById("BD_CONTENT").value;
		
			if (!$("#BD_TYPE").val() || $("#BD_TYPE").val() == '카테고리 선택') {
				alert("카테고리를 선택하세요.");
				$("#BD_TYPE").focus();
				return false;
			}

			if (!$("#BD_TITLE").val()) {
				alert("제목을 입력하세요.");
				$("#BD_TITLE").focus();
				return false;
			}

			if (!$("#BD_CONTENT").val()) {
				alert("내용을 입력하세요.");
				$("#BD_CONTENT").focus();
				return false;
			}

			alert("게시판이 정상적으로 수정 되었습니다.");
			comSubmit.submit();
}
 
 function fn_deleteBoard(){
     var comSubmit = new ComSubmit();
     var CONFIRM = confirm("정말로 삭제하시겠습니까?");
     if(CONFIRM==true){
     comSubmit.setUrl("/boardDelete.omc");
     comSubmit.addParam("BD_NUM", $("#BD_NUM").val());
     comSubmit.submit();
     alert("삭제가 완료되었습니다.");
     }
 }
</script>
 
</body>
</html>