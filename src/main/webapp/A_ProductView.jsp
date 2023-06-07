
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%@ page import="com.javalec.dto.A_dto"%>
<%@ page import="com.javalec.dao.A_dao"%>



<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>
</style>
<link rel="stylesheet" href="A_heardCss.css">
<link rel="stylesheet" href="A_pageCss.css">
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
<!-- 
				 <header>
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
				</header> -->
				
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
											
			
				
							<img src="image_05.png" alt="My Image">
							<br />
							<br />


				<!-- 검색 및 상품정렬 -->
			
						<div style="text-align: right;">
							<form action="A_ProductView.do" class="center" method="get">
								<select name="query">
									<option value="pname" selected="selected">Product</option>
									<option value="pprice">price</option>
								</select> 
								      <input type="text" name="content" size="30"> 
									  <input type="submit" value="serach"> 
									<select id="sortOrder" name="sortOrder" onchange="handleSortOrderChange()">
									<option value="">가격순 정렬</option>
									<option value="highprice">높은 가격순</option>
									<option value="lowprice">낮은 가격순</option>
								</select>
							</form>
						</div><br/>
						

							 <li class="center-align">
						            <button class="btn-login btn-dog">DOG</button>
						             <button class="btn-login btn-new">CAT</button>
                                     <button class="btn-login">ALL</button>
                           </li><br><br>
				
				<!-- 전체 상품목록 -->
							<h1 style="text-align: center; color: #477A7B;">ANJ's Pick</h1>
						
							<div class="product-grid">
								<c:forEach items="${A_ProductView}" var="dto">
									<div class="product-item">
										<a href="j_productClicked.do?pid=${dto.pid }"><img
											src="images/thumbnail/${dto.pthumbnail}.png"
											alt="Product Thumbnail"></a>
										<h3>${dto.pname}</h3>
										<p>Price: ${dto.pprice}</p>
									</div>
								</c:forEach>
							</div><br/><br/><br/>
				
				
				

			  
			  
						 <div class="pagination">
						    <button onclick="goToPreviousPage()">Previous</button>
						    <div id="pageNumbers"></div>
						    <button onclick="goToNextPage()">Next</button>
						 </div>
			
			
						<script>
						  // 한 페이지에 보여줄 상품의 개수
						  var itemsPerPage = 8;
						
						  // 상품 목록 컨테이너 요소
						  var productGrid = document.querySelector('.product-grid');
						
						  // 상품 아이템 요소들
						  var productItems = productGrid.querySelectorAll('.product-item');
						
						  // 상품 아이템 개수
						  var itemCount = productItems.length;
						
						  // 현재 페이지 번호
						  var currentPage = 1;
						
						  // 전체 페이지 개수
						  var totalPages = Math.ceil(itemCount / itemsPerPage);
						
						  // 페이지를 업데이트하는 함수
						  function updatePage() {
						   
							  // 모든 상품 아이템을 숨김
						    productItems.forEach(function(item) {
						      item.style.display = 'none';
						    });
						
						    // 현재 페이지에 해당하는 상품 아이템만 보여줌
						    var startIndex = (currentPage - 1) * itemsPerPage;
						    var endIndex = startIndex + itemsPerPage;
						    for (var i = startIndex; i < endIndex && i < itemCount; i++) {
						      productItems[i].style.display = 'block';
						    }
						  }
						
						  // 초기 페이지 업데이트
						  updatePage();
						
						  // 페이지 번호를 클릭했을 때 해당 페이지로 이동
						  function goToPage(page) {
						    if (page < 1 || page > totalPages) {
						      return;
						    }
						    currentPage = page;
						    updatePage();
						  }
						
						  // 이전 페이지로 이동하는 함수
						  function goToPreviousPage() {
						    if (currentPage > 1) {
						      currentPage--;
						      updatePage();
						    }
						  }
						
						  // 다음 페이지로 이동하는 함수
						  function goToNextPage() {
						    if (currentPage < totalPages) {
						      currentPage++;
						      updatePage();
						    }
						  }
						  
						</script>

						<footer>
							<ul>
								<li><a href="#">Brand Story</a></li>
								<li><a href="#">서비스이용약관</a></li>
								<li><a href="#">개인정보처리방침</a></li>
								<li><a href="#">전자금융거래약관</a></li>
							</ul>
							<div>
								<p>
									<strong>Corporation ANJ.industry</strong> <br> Gangnam-gu,
									Seoul (Yeoksam-dong The Joy Computer Academy) <br> CEO: Ahn
									Jae-won <br> Business registration number: 240-81-87676
									Business information confirmation <br> Mail-order business
									report: Gangnam 10238 Fax: 02-000-1234
								</p>
								<p>
									<strong>customer service center</strong> <br> Tel:
									Representative number 1234-5678 (Weekdays 09:00~18:00) <br>
									Dedicated to the future: 1522-5700 (365 days 09:00-18:00) <br>
									Gangnam-gu, Seoul (Yeoksam-dong The Joy Computer Academy) <br>
									Fax: 02-000-1234 | Mail: ajw0376@gmail.com <br>
								</p>
							</div>
						</footer>
</html>