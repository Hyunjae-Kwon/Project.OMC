<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ include file="/WEB-INF/include/include-header.jspf" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
    <section class="ftco-section">

    	<div class="container">
				<div class="row justify-content-center mb-3 pb-3">
          <div class="col-md-12 heading-section text-center ftco-animate">
          	<span class="subheading">판매중인 상품</span>
            <h2 class="mb-4">신상품</h2>
            </div>
        </div>   		
        </div>
    	<form action="newGoodsList.omc" method="GET">
    	
    	<div class="container">
    	<div class="row">
    		
    		<div class="container" align="center">
	    		<div class="row">
	    			<c:forEach var="goods" items="${newGoodsList}">
	    			<div class="col-md-6 col-lg-3 ftco-animate" style="float:left; width:33%; padding:10px;">
	    				<div class="goods">
	    					<a href="/goodsDetail.omc?GD_GID=${goods.GD_GID}" class="img-prod"><img class="img-fluid" src="resources/img/goods/${goods.GD_IMAGE}" style="height:250px;"></a>
	    					<div class="text py-3 pb-4 px-3 text-center">
	    						<h5><a href="/goodsDetail.omc?GD_GID=${goods.GD_GID}">${goods.GD_GNAME}</a></h5>
	    						<div class="pricing">
									<c:choose>
										<c:when test="${goods.GD_DCPRICE==goods.GD_PRICE}">
											<p class="dcPrice">
											<fmt:formatNumber value="${goods.GD_DCPRICE}" pattern="#,###"/>원
											</p>
											</c:when>
										<c:otherwise>
											<p class="price" style="color: gray;">
											<strike><fmt:formatNumber value="${goods.GD_PRICE}" pattern="#,###"/>원</strike>
											</p>
											<p class="dcPrice">
											<fmt:formatNumber value="${goods.GD_DCPRICE}" pattern="#,###"/>원
											</p>
										</c:otherwise>
									</c:choose>
								</div>
	    					</div>
	    				</div>
	    			</div>
	    			</c:forEach>
					
    			</div>
    		</div>    		
    	</div>
    </div>
    </form>
</section>
</body>
</html>

