<%@ page language="java" contentType="text/html; charset=EUC-KR"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/include/include-header.jspf"%>
<!DOCTYPE html>
<html lang="ko">

<head>

<script charset="utf-8"></script>
<script type="text/javascript">
if(${! empty msg}) {
	alert('${msg}');
}
location.href='${pageContext.request.contextPath}${url}';
</script>
</head>
</html>