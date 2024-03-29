<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%-- <%
session.setAttribute("USERID", "osm1119");
session.setAttribute("ADMINID", "admin");
%> --%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>공지사항</title>

<link rel="stylesheet" href="A_heardCss.css">
<link rel="stylesheet" href="CSS/O_NBoardStyle.css">
<link rel="stylesheet" href="CSS/O_PageStyle.css">
<link rel="stylesheet" href="CSS/O_Common.css">
<script src="JS/O_ScrollTop.js"></script>
</head>
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
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
						<a href="A_MainView.do?id=${sessionScope.USERID}"><img class="head-logo" src="LOGO.png"></a>  
					</div>
					<div class="head-wrap-sub">
						<nav class="head-menu-main-nav">
							<ul> 
								<li class="main-nav02 dropdown">
									<a href="#">ANJLIFE</a>
											<div class="dropdown-content">
												<a href="A_introduction.jsp">introduction</a>
												<a href="A_Part.jsp">Part</a>
											</div>
								</li>
								<li class="main-nav01"><a href="A_ProductView.do">SHOP</a></li>
									<li class="main-nav02 dropdown">
										<a href="#">COMMUNITY</a>
											<div class="dropdown-content">
												<a href="O_Review.do">review</a>
												<a href="O_QNA.do">Q&A</a>
											</div>
								  </li>
								<li class="main-nav02 dropdown">
										<a href="cart.do">NOTICE</a>
								      <div class="dropdown-content">
								      <a href="O_FAQ.do">FAQ</a>
									  <a href="O_Notice.do">Notice</a>
									 </div>
								        
								<li class="main-nav04"><a href="#">CART</a></li>        
								<li class="right-align" id="loginContainer">
									<c:choose>
										  <c:when test="${empty sessionScope.USERID}">
										    <!-- 세션 값이 비어있을 때 -->
										    <li><button class="btn-login btn-dog" onclick="location.href='A_loginView.jsp'">Login</button></li>
										    <li><button class="btn-login btn-dog" onclick="location.href='A_JoinView.jsp'">New</button></li>
										  </c:when>
										  <c:otherwise>
										    <!-- 세션 값이 있을 때 -->
										    <li><button class="btn-login btn-dog" onclick="location.href='A_logout.do'">Logout</button></li>
										    <li><button class="btn-login btn-dog" onclick="location.href='j_userpage.do'">MyPage</button></li>
										  </c:otherwise>
										</c:choose>
									<li style="font-size: 11px; margin-top: 10px;">${sessionScope.USERID}님</li>
								</li>
							</ul>
						</nav>
					</div>
				</div>
			</header>

	<div class="page-title">
		<br>
		<br>
		<br>
		<h3>COMMUNITY</h3>
		<br>
		<br> <span class="selected"><a href="O_Notice.do">NOTICE</a></span> <a href="O_FAQ.do">FAQ</a> <a href="O_QNA.do">Q&A</a> <a href="O_Review.do">REVIEW</a> <br>
		<br>
	</div>

	<div class="page-title">
		<h4>공지사항</h4>
	</div>
	<!-- board seach area -->
	<div id="board-search">
		<div class="container">
			<div class="search-window">
				<form action="O_Notice.do" method="post">
					<div class="search-wrap">
						<select name="query">
							<option value="n_title">제목</option>
							<option value="n_content">내용</option>
						</select> <input id="search" type="search" name="content" placeholder="검색어를 입력해주세요.">
						<button type="submit" class="btn btn-dark">검색</button>
					</div>
				</form>
			</div>
		</div>
	</div>

	<!-- board list area -->
	<div class="container">
		<table class="board-table">
			<thead>
				<tr>
					<th scope="col" class="th-num">번호</th>
					<th scope="col" class="th-title">제목</th>
					<th scope="col" class="th-writer">작성자</th>
					<th scope="col" class="th-date">작성일</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${noticeList}" var="dto">
					<tr>
						<td><span class="notice-button">공지</span></td>
						<td><a href="O_NDetail.do?seq=${dto.seq}">${dto.n_title}</a></td>
						<td>${dto.adminid}</td>
						<td>${dto.writedate}</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
		<br><br>
	</div>

	<div class="container pagination" style="text-align: center;">
		<script>
    let pageSize = ${p.pageSize}; // 한 페이지당 보여줄 최대 페이지 개수
    let itemsPerPage = ${p.itemsPerPage}; // 한 페이지당 보여줄 게시물의 수
    let totalCount = ${p.totalCount}; // 전체 게시물의 수
    let currentPage = ${p.currentPage}; // 현재 페이지
    let totalPages = ${p.totalPages}; // 전체 페이지의 수
    let calcPage = Math.floor((currentPage - 1) / pageSize) * pageSize + 1; // 현재 페이지에서 보여질 페이지의 시작값 계산

    // query가 null일 때 query를 n_content로 설정
    let query = "${query}";
    if (!query) {
      query = "n_title";
    }

    // 이전 버튼
    if (currentPage > 1) {
      document.write('<span><a href="O_Notice.do?page=' + 1 + '&query=' + query + '&content=${content}"><<</a></span>');
      document.write('<span><a href="O_Notice.do?page=' + (currentPage - 1) + '&query=' + query + '&content=${content}"><</a></span>');
    } else {
      document.write('<span class="empty"><a><<</a></span>');
      document.write('<span class="empty"><a><</a></span>');
    }

    // 페이지 번호
    if (totalPages != 1) {
      let numPagesToShow = Math.min(pageSize, totalPages); // 보여줄 페이지 번호 개수 (pageSize와 totalPages 중 작은 값 선택)
      let startPage = calcPage; // 시작 페이지

      // 시작 페이지 조정
      if (startPage + numPagesToShow - 1 > totalPages) {
        startPage = Math.max(totalPages - numPagesToShow + 1, 1);
      }

      for (let i = startPage; i <= startPage + numPagesToShow - 1; i++) {
        if (i === currentPage) {
          document.write('<span class="current"><a href="O_Notice.do?page=' + i + '&query=' + query + '&content=${content}">' + i + '</a></span>');
        } else {
          document.write('<span><a href="O_Notice.do?page=' + i + '&query=' + query + '&content=${content}">' + i + '</a></span>');
        }
      }
    }

    // 다음 버튼
    if (currentPage != totalPages && totalPages != 1) {
      document.write('<span><a href="O_Notice.do?page=' + (currentPage + 1) + '&query=' + query + '&content=${content}">></a><span>');
      document.write('<span><a href="O_Notice.do?page=' + totalPages + '&query=' + query + '&content=${content}">>></a><span>');
    } else {
      document.write('<span class="empty"><a>></a><span>');
      document.write('<span class="empty"><a>>></a><span>');
    }
  </script>
	</div>


	<button class="top-button" onclick="scrollToTop()">top</button>

	<footer>
		<ul>
			<li><a href="#">Brand Story</a></li>
			<li><a href="#">서비스이용약관</a></li>
			<li><a href="#">개인정보처리방침</a></li>
			<li><a href="#">전자금융거래약관</a></li>
		</ul>
		<div>
			<p>
				<img src="LOGO.png" alt="푸터로고">
			</p>
			<p>
				<strong>Corporation ANJ.industry</strong> <br> Gangnam-gu, Seoul (Yeoksam-dong The Joy Computer Academy) <br> CEO: Ahn Jae-won <br> Business registration number: 240-81-87676 Business information confirmation <br> Mail-order business report: Gangnam 10238 Fax: 02-000-1234
			</p>
			<p>
				<strong>customer service center</strong> <br> Tel : Representative number 1234-5678 (Weekdays 09:00~18:00) <br> Dedicated to the future: 1522-5700 (365 days 09:00-18:00) <br> Gangnam-gu, Seoul (Yeoksam-dong The Joy Computer Academy) <br> Fax : 02-000-1234 | Mail : ajw0376@gmail.com <br>
			</p>
		</div>
	</footer>
</body>
</html>