<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>오늘의 메뉴 추천, 오메추</title>
<script>
function deleteCheck() {
	var BD_NUM = document.getElementById('BD_NUM').value;
	if(confirm("삭제하시겠습니까?") == true) {
		location.href="adminNoticeDelete.omc?BD_NUM=" + BD_NUM;
	}
}
</script>

<style type="text/css">
p { word-break: break-all;}
</style>

</head>
<body>
<div style="text-align:center">
	<h5>공지사항</h5>
	<input type="hidden" id="BD_NUM" name="BD_NUM" value="${noticeMap.BD_NUM }">
	<hr width="80%">
</div>

<section class="ftco-section ftco-degree-bg">
	<div class="container">
		<div class="row">
			<div class="blog-entry align-self-stretch d-md-flex">
				<div class="text d-block pl-md-4">
					<h3><p style="width: 100%;">${noticeMap.BD_TITLE}</p></h3>
					<div class="meta mb-3">
						<div>${noticeMap.BD_ID}</div>
						<div>${noticeMap.BD_REGDATE}</div>
					</div><hr>
      				<p class="mt-5" style="font-size:large; width: 100%;">${noticeMap.BD_CONTENT}</p>
    			</div>
			</div>
		</div><hr>
	</div>
</section>

<p style="text-align:center;">
<button class="btn btn-black py-2 px-3" onclick="location.href='adminNoticeModifyForm.omc?BD_NUM=${noticeMap.BD_NUM}'">수정</button>
<button class="btn btn-light py-2 px-3" onclick="return deleteCheck()">삭제</button>
</p><br><br>
</body>
</html>