<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>ANJ MY PAGE</title>
<link rel="stylesheet" href="J_userMyPage.css">
<link rel="stylesheet" href="A_heardCss.css">
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
	

	<main class="main">

	<p></p>
	<h1 style="text-align: center; color : #477A7B">MY PAGE</h1><br/><br/>
	
	<div class="userExpense"> 		<!-- 사용자 마일리지 및 총 구매 금액 보여주는 테이블 -->
		<table class="mileage">
			<tr>
				<td>
				사용 가능하신 적립금 	<!-- 총 제품 구매 적립금 - 사용 제품 구매 적립금 -->&nbsp;&nbsp;&nbsp;
				${userPage.point } 원 &nbsp;&nbsp;&nbsp;
				<input type="button" value="조회"> <br/><br/>			<!-- **버튼 누르면 조회페이지로 넘어가도록!!! -->
				사용하신 적립금 &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				($$$$) 원 
				</td>
				<td>
				총 제품구매 적립금   &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 	<!-- 모든 적립금 내역들의 합계 -->
				($$$$) 원 &nbsp;&nbsp;&nbsp; <br/><br/>
				총 주문 &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; ($$$$$)원 ($회)
				</td>
			</tr>
		</table>
	</div>
	<br/><br/>
	<div class="userOperations">
		<table class="operations">
			<tr>
				<td> 
				<img src="J_userPageIcon/J_orderIcon.gif" style="width: 3px; height: auto; border: 1px solid black;"> 
				<h4>ORDER </h4> 
				<h5>주문내역 조회 </h5>
				<h6>고객님께서 주문하신 상품의 주문내역을 확인하실 수 있습니다. </h6>  
				</td>

				<td>
				<img src="J_userPageIcon/J_profileIcon.gif" style="width: 3px; height: auto;"> 
				<h4> PROFILE </h4> 
				<h5> 회원 정보 </h5>
				<h6> 회원이신 고객님의 개인 정보를 관리하는 공간입니다. </h6>  
				</td>

				<td>			<!-- 여기는 찜 기능 넣으면 하고 안넣으면 안하고 기억이 안나서 일단 넣는다. (or 최근 본 상품으로 대체) -->
				<img src="J_userPageIcon/J_wishlistIcon.gif" style="width: 3px; height: auto; /* border: 1px solid black; */"> 
				<h4> WISHLIST </h4> 
				<h5> 관심 상품 </h5>
				<h6> 관심상품으로 등록하신 상품의 목록을 보여드립니다. </h6>  
				</td> 
			</tr>
			<tr>			
				<td> 
				<img src="J_userPageIcon/J_mileageIcon.gif" style="width: 3px; height: auto; /* border: 1px solid black; */"> 
				<h4> MILEAGE </h4> 
				<h5> 제품구매 적립금 </h5>
				<h6> 제품구매 적립금은 상품 구매 시 사용하실 수 있습니다. </h6>  
				</td>
				
				<td>
				<img src="J_userPageIcon/J_boardIcon.gif" style="width: 3px; height: auto; /* border: 1px solid black; */"> 
				<h4> BOARD </h4> 
				<h5> 게시물 관리 </h5>
				<h6> 고객님께서 작성하신 게시물을 관리하는 공간입니다. </h6>  
				</td>
				
				<td>
				<img src="J_userPageIcon/J_customercenterIcon.gif" style="width: 3px; height: auto; /* border: 1px solid black; */"> 
				<h4> 고객센터 </h4> 
				<h5> 1577 - 5462 </h5>
				<h6> 매일 AM 09:00 ~ PM 17:00 <br/>
					 Lunch PM 12:00 ~ 13:00 <br/>
					 Sat, Sun, Holiday OFF </h6>  
				</td>
			</tr>
		</table>
	</div>
		
	</main>
	
	<!-- <footer>
		<p>
			<span>TEL. 1877-3228<br/>
			E-MAIL. arrr@dongwon.com<br/>
			ADDRESS. 10th floor, 68, Mabangro, Seocho-gu, Seoul,
			Republic of Korea, 06775<br/>
			BUSINESS NO. 703-88-01843 </span>
		</p>
	</footer> -->

</body>
</html>