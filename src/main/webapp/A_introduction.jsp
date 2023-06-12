<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="A_heardCss.css">
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
		

$(document).ready(function() {
	$(".dropdown").hover(
		function() {
			$(this).find(".dropdown-content").css("display", "block");
			$("header").addClass("fixed-header"); // 헤더에 fixed-header 클래스 추가
		},
		function() {
			$(this).find(".dropdown-content").css("display", "none");
			$("header").removeClass("fixed-header"); // 헤더에서 fixed-header 클래스 제거
		}
	);
});
</script>
<body>
<header>
				<div class="head-wrap">
					<div class="head-wrap-inner">
						<a href="A_MainView.do"><img class="head-logo" src="LOGO.png"></a>  
					</div>
					<div class="head-wrap-sub">
						<nav class="head-menu-main-nav">
							<ul> 
							
								<li class="main-nav02 dropdown">
									<a href="#">ANJLIFE</a>
											<div class="dropdown-content">
												<a href="#">introduction</a>
												<a href="#">BRAND</a>
												<a href="#">Part</a>
											</div>
								</li>
								<li class="main-nav01"><a href="A_ProductView.do">SHOP</a></li>
									<li class="main-nav02 dropdown">
										<a href="#">COMMUNITY</a>
											<div class="dropdown-content">
												<a href="#">review</a>
												<a href="#">Q&A</a>
											<!-- <a href="#">Part</a> -->
											</div>
								</li>
								<li class="main-nav04"><a href="#">NOTICE</a></li>         
								<li class="main-nav04"><a href="#">CART</a></li>        
								<li class="right-align" id="loginContainer">
									<li><button class="btn-login btn-dog" onclick="location.href='A_loginView.jsp'">Login</button></li>
									<li><button class="btn-login btn-dog" onclick="location.href='A_JoinView.jsp'">New</button></li>
									<li style="font-size: 11px; margin-top: 10px;">${sessionScope.USERID}님</li>
								</li>
							</ul>
						</nav>
					</div>
				</div>
			</header><br/><br/><br/>
			
			
			
			


</body>
</html>