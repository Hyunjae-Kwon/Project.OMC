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
<a href="/admin/community/adminQnaList.omc">고객센터 리스트</a><br>
</body>
</html>