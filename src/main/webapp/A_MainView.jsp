<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
  <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>MainView</title>
<link rel="stylesheet" href="A_heardCss.css">
<link rel="stylesheet" href="A_MainCss.css">
<link rel="stylesheet" href="exampleCss.css">
<style>
</style>
</head>
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
				<br/><br/><br/><br/><br/>
				
				<!-- 아래부터 본문---------------------------------------------------------------->
		
		
				<!-- (0) 헤더 및 메인화면 이미지 ( 추후 상품상세페이지와 연결할거야 ) ----------------------->
  			
  				<img src="image_08.png" alt="My Image">
				<hr>

				<!--(2) 판매량 순으로 데이터 베이스 가져오기 -------------------------------------------->

					<h1 style="text-align: center; color: #477A7B;">Ranking Product</h1>
				
						<div class="product-grid" id="ranking-product-grid">
						  <c:forEach items="${A_MainView2}" var="dto">
						    <div class="product-item">	
						      <img src="images/thumbnail/${dto.pthumbnail}.png" alt="Product Thumbnail">
						      <h3>${dto.pname}</h3>
						      <p>Price: ${dto.pprice}</p>
						    </div>
						  </c:forEach>
						</div>
						
						<style> /*랭킹 애니메이션 효과 */
						#ranking-product-grid {
						  overflow: hidden;
						}
						
						#ranking-product-grid .product-item {
						  display: inline-block;
						  animation: scrollLeft 10s linear infinite;
						}
						
						@keyframes scrollLeft {
						  0% { transform: translateX(0); }
						  100% { transform: translateX(-100%); }
						}
						</style>
			
  				<!-- (1) 전체상품 목록 데이터 베이스 가져오기  ------------------------------------------>
				
				<hr>
				<h1 style="text-align: center; color: #477A7B;">ALL Product</h1>
					
						<div class="product-grid">
						  <c:forEach items="${A_MainView}" var="dto">
						   <div class="product-item">
							<img src="images/thumbnail/${dto.pthumbnail}.png" alt="Product Thumbnail">
						      <h3>${dto.pname}</h3>
						      <p>Price: ${dto.pprice}</p>
						   </div>
						  </c:forEach>
						</div><br/><br/><br/><br/>
						
					
				


				<!--(3) 랜덤으로 데이터 베이스 가져오기 ------------------------------------------------>
				
				<hr>
				<h1 style="text-align: center; color: #477A7B;">Recommendation of the day</h1>
				
					<div class="product-grid">
						  <c:forEach items="${A_MainView3}" var="dto">
						    <div class="product-item">
						     <img src="images/thumbnail/${dto.pthumbnail}.png" alt="Product Thumbnail">	
						     <h3>${dto.pname}</h3>
						      <p>Price: ${dto.pprice}</p>
						    </div>
						  </c:forEach>
						</div>
					
			
				
				
				
				
				
				
				
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