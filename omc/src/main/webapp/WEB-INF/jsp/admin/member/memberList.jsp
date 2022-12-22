<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ include file="/WEB-INF/include/include-header.jspf" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>오늘의 메뉴 추천, 오메추</title>
</head>

<body>
	
<section class="ftco-section ftco-cart">
	<div style="text-align:center"><h2>회원 정보</h2></div>
	<br/>
		<div class="container">
			<div class="row">

				<form action="memberList.omc">
				<div class="form-group d-flex">
					<select name="condition" id="condition">
						<option value="MEM_ID" <c:if test="${condition=='MEM_ID'}">selected</c:if>>아 이 디</option>
						<option value="MEM_NAME" <c:if test="${condition=='MEM_NAME'}">selected</c:if>>이 &emsp; 름</option>
						<option value="MEM_PHONE" <c:if test="${condition=='MEM_NAME'}">selected</c:if>>휴대폰번호</option>
					</select>
					<input type="text" name="keyword" id="keyword" class="form-control" style="width:250px;"
						placeholder="검색어 ..." value="${keyword}"/>
					<button type="submit" class="btn-black px-4">검색</button>
				</div>
				</form><!-- condition이라는 파라미터 명으로 넘어간다. -->
				<div>
				</div>
				<br>
				<div class="container" style="padding-right:70px;">				
					<div class="col-md-12 ftco-animate">
						<div class="cart-list">
							<table class="table">
							<thead class="thead-primary">
								<tr class="text-center">
									<th>아이디</th>
									<th>이름</th>
									<th>핸드폰번호</th>
									<th>주소</th>
								</tr>
							</thead>
							<tbody>
							
								<c:forEach var="member" items="${memberBeanList}"> 
								<tr class="text-center">
									<td><a href="memberDetail.omc?MEM_ID=${member.MEM_ID}">${member.MEM_ID}</a></td>
									<td class="product-name">
										${member.MEM_NAME}
									</td>
									<td>${member.MEM_PHONE}</td>
									<td>${member.MEM_ADD1} / ${member.MEM_ADD2}</td>
								</tr><!-- END TR-->
								</c:forEach>
							
							</tbody>
							</table>
						</div>
						
						${paging.pageHtml}
					</div>			
				</div>
		</div>
	</div>
</section>	

</body>
</html>