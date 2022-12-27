<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>오늘의 메뉴 추천, 오메추</title>
<style type="text/css">
.name {
  width        : 225px;     /* 너비는 변경될수 있습니다. */
  text-overflow: ellipsis;  /* 위에 설정한 너비보다 길면 말줄임표처럼 표시합니다. */
  white-space  : nowrap;    /* 줄바꿈을 하지 않습니다. */
  overflow     : hidden;    /* 내용이 길면 감춤니다 */
  display      : block;     /* ie6이상 현재요소를 블럭처리합니다. */
  color   : black;
}
</style>
<script>
function deleteCheckAjax(BD_NUM, index) {
	if(confirm("삭제하시겠습니까?") == true) {
		
		$.ajax({
			url			: "adminNoticeDeleteAjax.omc",
			data		: {"BD_NUM" : BD_NUM},
			contentType	: "application/json",
			success		: function(data) {
				alert("삭제하였습니다.");
				$("#not"+index).remove();
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
</head>
<body>
<section class="ftco-section ftco-cart">
	<div style="text-align:center">
		<h2> 공지사항 </h2>
	</div>
	<br>

		<div class="container">
			<div class="row">
				<div class="col-md-12 ftco-animate">
				
					<div class="cart-list">
					
						<div style="text-align:right;">
							<button class="btn btn-primary py-2 px-3" style="height:55px;"
								onclick="location.href='adminNoticeWriteForm.omc'">글쓰기</button>
						</div>
						<br>
				
						<table class="table">
							<thead class="thead-primary">
								<tr class="text-center">
									<th>번호</th>
									<th>제목</th>
									<th>작성자</th>
									<th>작성일</th>
									<th>&nbsp;</th>
								</tr>
							</thead>
							
							<tbody>
								<c:forEach var="notice" items="${noticeListArr}"  varStatus="status"> 
								<tr id="not${status.index}">
									<td>${notice.BD_NUM}</td>
								
									<td><a href="adminNoticeDetail.omc?BD_NUM=${notice.BD_NUM}">${notice.BD_TITLE}</a></td>
									<td class="product-name">
										${notice.BD_ID}
									</td>
									<td ${notice.BD_REGDATE }>
           								 <fmt:formatDate value="${notice.BD_REGDATE}" pattern="yyyy-MM-dd"/>   
          							  </td>
									<td>
										<button class="btn btn-light py-2 px-3"
											onClick="deleteCheckAjax(${notice.BD_NUM}, ${status.index});">삭제</button>
									</td>
								</tr><!-- END TR-->
								</c:forEach>
							</tbody>
						</table>
					</div> <!-- end cart-list div -->
					<br>

					<br>
					${paging.pageHtml}
				</div>			
			</div>
		</div>
	</section>
</body>
<br><br>
</html>