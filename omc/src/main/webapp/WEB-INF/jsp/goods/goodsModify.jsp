<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%
	response.setHeader("Cache-Control","no-cache");
	response.setHeader("Pragma","no-cache");
	response.setDateHeader("Expires",0);
%>
<html>
<head>
<meta charset="UTF-8">
<title>주모</title>
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