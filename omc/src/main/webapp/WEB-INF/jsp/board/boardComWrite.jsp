<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>오늘의 메뉴 추천, 오메추</title>
</head>
<body>
	<script>
   if(${! empty msg}) {
      alert('${msg}');
   }
    location.href='${pageContext.request.contextPath}${url}';
    </script>
<br>
<a href="/boardList.omc">커뮤니ㅌㅣ 상세 페이지로 이동</a><br>  <!-- 나중에바꾸기@@@ -->
</body>
</html>