<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="A_heardCss.css">
</head>
<script type="text/javascript">
	
	
	// (1) 검색 및 가격정렬-------------------------------------------
	
	function handleSortOrderChange() {
		var selectElement = document.getElementById("sortOrder");
		var selectedValue = selectElement.value;

		// 현재 페이지 URL에서 정렬 파라미터 제거
		var url = removeSortOrderParameter(window.location.href);

		// 선택된 정렬 파라미터 추가
		if (selectedValue !== '') {
			url += url.indexOf('?') === -1 ? '?sortOrder=' + selectedValue
					: '&sortOrder=' + selectedValue;
		}

		// 페이지 리로드
		window.location.href = url;
	}

	function removeSortOrderParameter(url) {
		var urlParts = url.split('?');
		if (urlParts.length > 1) {
			var queryParams = urlParts[1].split('&');
			var updatedQueryParams = queryParams.filter(function(param) {
				return !param.startsWith('sortOrder=');
			});
			return urlParts[0] + '?' + updatedQueryParams.join('&');
		}
		return url;
	}
</script>
<body>

			<header>
				  <nav>
				    <ul>
				      <li><a href="A_MainView.do"><img src="LOGO.png" alt="logo"></a></li>
				      <li><a href="A_ProductView.do">SHOP</a></li>
				      <li><a href="#">ANJLIFE</a></li>
				      <li><a href="#">COMMUNITY</a></li>
				      <li><a href="#">CART</a></li>
				      <li class="right-align"><a href="A_introduction.jsp">Login</a></li>
				      <li class="right-align"><a href="A_introduction.jsp">New</a></li>
				    </ul>
				  </nav>
			</header>
				<br/>
				
				
				<img src="image_05.png" alt="My Image"><br/><br/>
				
				
				<!-- 검색 및 상품정렬 -->
				
				<div style="text-align: right;">
		<form action="A_ProductView.do" class="center" method="get">
			<select name="query">
				<option value="pname" selected="selected">상품</option>
				<option value="pprice">금액</option>
			</select> <input type="text" name="content" size="30"> <input
				type="submit" value="검색"> <select id="sortOrder"
				name="sortOrder" onchange="handleSortOrderChange()">
				<option value="">가격순 정렬</option>
				<option value="highprice">높은 가격순</option>
				<option value="lowprice">낮은 가격순</option>
			</select>
		</form>
		</div>



			<!-- 전체 상품목록 -->
			<h1 style="text-align: center; color: #477A7B;">ALL Product</h1>
					
							<div class="product-grid">
						  <c:forEach items="${A_ProductView}" var="dto">
						   <div class="product-item">
							<img src="images/thumbnail/${dto.pthumbnail}.png" alt="Product Thumbnail">
						      <h3>${dto.pname}</h3>
						      <p>Price: ${dto.pprice}</p>
						   </div>
						  </c:forEach>
						  </div><br/><br/><br/>
						  
						  
						  
						  
				<footer>
		            <ul>
		                <li><a href="#">Brand Story</a></li>
		                <li><a href="#">서비스이용약관</a></li>
		                <li><a href="#">개인정보처리방침</a></li>
		                <li><a href="#">전자금융거래약관</a></li>
		            </ul>
		            <div>
		                <p><img src="LOGO.png" alt="푸터로고"></p>
		                <p>
		                    <strong>Corporation ANJ.industry</strong>
		                    <br>
		                    Gangnam-gu, Seoul (Yeoksam-dong The Joy Computer Academy)
		                    <br>
		                    CEO: Ahn Jae-won
		                    <br>   
							Business registration number: 240-81-87676 Business information confirmation
		                    <br>
		                    Mail-order business report: Gangnam 10238 Fax: 02-000-1234
		                </p>
		                <p>
		                    <strong>customer service center</strong>
		                    <br>
		                    Tel : Representative number 1234-5678 (Weekdays 09:00~18:00)
		                    <br>
		                    Dedicated to the future: 1522-5700 (365 days 09:00-18:00)
		                    <br>
		                    Gangnam-gu, Seoul (Yeoksam-dong The Joy Computer Academy)
		                    <br>
		                    Fax : 02-000-1234 | Mail : ajw0376@gmail.com
		                    <br>
		                </p>
		            </div>
		        </footer>
</body>
</html>