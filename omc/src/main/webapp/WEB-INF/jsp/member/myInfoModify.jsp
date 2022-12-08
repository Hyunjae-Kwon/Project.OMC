<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>  

<html>
<head>
    <title>오늘의 메뉴 추천, 오메추</title>
</head>
<body> 

	<script>
   if(${! empty msg}) {
      alert('${msg}');
   }
    location.href='${pageContext.request.contextPath}${url}';
    </script>

</body>
</html>
