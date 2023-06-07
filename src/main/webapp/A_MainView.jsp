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
<script type="text/javascript">
$(function(){
	   var $firstmenu = $('nav > ul > li'),
	       $header = $('header');
	    $firstmenu.mouseenter(function(){
	       $header.stop().animate({height:'300px'},200);
	    })
	    .mouseleave(function(){
	        $header.stop().animate({height:'50px'},200);
	    })
	    
	    
	});
</script>
<body>


			 <!-- <header>
				  <nav>
				    <ul>
				      <li><a href="A_MainView.do"><img src="LOGO.png" alt="logo"></a></li>
				      <li><a href="A_ProductView.do">SHOP</a></li>
				      <li><a href="#">ANJLIFE</a></li>
				      <li><a href="#">COMMUNITY</a></li>
				      <li><a href="#">NOTICE</a></li>
				      <li>	<form action="A_ProductView.do" class="center" method="get">
						<select name="query">
							<option value="pname" selected="selected">Product</option>
							<option value="pprice">price</option>
						</select> 
						      <input type="text" name="content" size="60"> 
							  <input type="submit" value="serach"> </li>
				      <li class="right-align">
				        <button class="btn-login">Abandoned dog</button>
				        <button class="btn-login">Login</button>
				        <button class="btn-new">New</button>
				      </li>
				    </ul>
				  </nav>
				</header>
				<br><br><br><br/><hr>
 -->

			 <header>
		        <div class="head-wrap">
		            <div class="head-wrap-inner">
		               <a href="A_MainView.do"><img class="head-logo" src="LOGO.png"></a>  
		            	</div>
		           	 <div class="head-wrap-sub">
		           	  <h3>ANJ PET SHOP</h3>
		                <nav class="head-menu-main-nav">
		                    <ul>
		                        <li class="main-nav01"><a href="A_ProductView.do">SHOP</a></li>
		                        <li class="main-nav02"><a href="#">ANJLIFE</a></li>
		                        <li class="main-nav03"><a href="#">COMMUNITY</a></li>
		                        <li class="main-nav04"><a href="#">NOTICE</a></li>         
		                        <li class="main-nav04"><a href="#">CART</a></li>         
		                        <li class="right-align">
						        <button class="btn-login">Abandoned dog</button>
						        <button class="btn-login">Login</button>
						        <button class="btn-new">New MEMBERS</button>
						      </li>
		                    </ul>
			            </nav>
			            </div>
		       		 </div>
   	 			</header>
     <br><br> <br> <br><br><hr>
				<!-- 아래부터 본문---------------------------------------------------------------->
		
		 				
				<!-- (0)  메인화면 이미지 ( 추후 상품상세페이지와 연결할거야 ) ----------------------->
	
						<ul class="slides">
						    <input type="radio" name="radio-btn" id="img-1" checked />
						    <li class="slide-container">
						    <div class="slide">
						      <img src="image_10.png" />
						        </div>
						    <div class="nav">
						      <label for="img-6" class="prev">&#x2039;</label>
						      <label for="img-2" class="next">&#x203a;</label>
						    </div>
						    </li>
						
						    <input type="radio" name="radio-btn" id="img-2" />
						    <li class="slide-container">
						        <div class="slide">
						          <img src="image_11.png" />
						        </div>
						    <div class="nav">
						      <label for="img-1" class="prev">&#x2039;</label>
						      <label for="img-3" class="next">&#x203a;</label>
						    </div>
						    </li>
						   
						    <input type="radio" name="radio-btn" id="img-3" />
						    <li class="slide-container">
						        <div class="slide">
						          <img src="image_12.png" />
						        </div>
						    <div class="nav">
						      <label for="img-1" class="prev">&#x2039;</label>
						      <label for="img-2" class="next">&#x203a;</label>
						      <label for="img-3" class="next">&#x203a;</label>
						    </div>
						    </li>

						    <li class="nav-dots">
						      <label for="img-1" class="nav-dot" id="img-dot-1"></label>
						      <label for="img-2" class="nav-dot" id="img-dot-2"></label>
						      <label for="img-3" class="nav-dot" id="img-dot-3"></label>
						    </li>
						</ul>
							<br/><br/><br/>
	
	
				
					
				<!--(2) 판매량 순으로 데이터 베이스 가져오기 -------------------------------------------->

						<div style="text-align: center;">
						  <input type="button" value="BEST PRODUCT" style="font-size: 20px; color: white; background-color: black; border: none; width: 20%; height: 50px">
						</div><br/><br/>
						
						
				
						<div class="product-grid" id="ranking-product-grid">
						  <c:forEach items="${A_MainView2}" var="dto">
						    <div class="product-item">	
						      <img src="images/thumbnail/${dto.pthumbnail}.png" alt="Product Thumbnail">
						      <h3>${dto.pname}</h3>
						      <p>Price: ${dto.pprice}원</p>
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
				
		<%-- 		<hr>
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
						 --%>
			
				


				<!-- (3) 랜덤으로 데이터 베이스 가져오기 ------------------------------------------------>
				
				<hr><br/>
				<div style="text-align: center;">
						  <input type="button" value="Recommendation of the day" style="font-size: 20px; color: white; background-color: black; border: none; width: 20%; height: 50px">
						</div><br/><br/>
				
				
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