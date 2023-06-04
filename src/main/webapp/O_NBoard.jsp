<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<c:set var="index" value="0" scope="page" />

<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>공지사항</title>

<link rel="stylesheet" href="O_NBoardStyle.css">
<link rel="stylesheet" href="A_heardCss.css">
<link rel="stylesheet" href="A_MainCss.css">
<link rel="stylesheet" href="O_PageStyle.css">

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


	<section class="notice">
		<div class="page-title">
			<div class="container">
				<h3>
					<a href="O_Notice.do?page=1">공지사항</a>
					<a href="O_FAQ.do">FAQ</a>
				</h3>
			</div>
		</div>

		<!-- board seach area -->
		<div id="board-search">
			<div class="container">
				<div class="search-window">
					<form action="">
						<div class="search-wrap">
							<label for="search" class="blind">공지사항 내용 검색</label> <input id="search" type="search" name="" placeholder="검색어를 입력해주세요." value="">
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
		</div>

		<div class="container pagination" style="text-align: center;">
			<script>
				let pageSize = ${p.pageSize} // 한 페이지당 보여줄 최대 페이지 개수
				let itemsPerPage = ${p.itemsPerPage} // 한 페이지당 보여줄 게시물의 수
				let totalCount = ${p.totalCount} // 전체 게시물의 수
				let currentPage = ${p.currentPage} // 현재 페이지
				let totalPages = ${p.totalPages} // 전체 페이지의 수
				let calcPage = Math.floor((currentPage - 1) / pageSize) * pageSize + 1; // 현재 페이지에서 보여질 페이지의 시작값 계산
				
				
				
				if(currentPage > 1){ // 이전 버튼
					document.write('<a href="O_Notice.do?page=' + (currentPage - 1) + '">이전</a>')
				}
				
				if(totalPages != 1 ){ // 페이지 번호
					for(let i = calcPage; i <= calcPage + pageSize -1 && i <= totalPages; i++){
						document.write('<a href="O_Notice.do?page=' + i + '"> ' + i + ' </a>')
					}
				}
				
				if((currentPage != totalPages) && totalPages != 1){ // 다음 버튼
					document.write('<a href="O_Notice.do?page=' + (currentPage + 1) + '"> 다음</a>')
				}
				
			</script>
		</div>
		<div class="container" style="text-align: right;">
			<span class="list-button" ><a href="O_WriteNoticeView.do">글쓰기</a></span>
		</div>

	</section>
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