<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

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

<body>
	<header >
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

	<div class="page-title" style="background-color: #DFE9E8;">
		<br><br><br>
		<h3>COMMUNITY</h3>
		<br><br>
			<a href="O_Notice.do">NOTICE</a>
			<a href="O_FAQ.do">FAQ</a> 
			<span class="selected"><a href="O_QNA.do">Q&A</a></span>
			<a href="O_FAQ.do">REVIEW</a> 
		<br><br>
	</div>

	<div class="page-title">
		<h4>QnA 게시판</h4>
	</div>
	<!-- board seach area -->
	<div id="board-search">
		<div class="container">
			<div class="search-window">
				<form action="O_QNA.do" method="post">
					<div class="search-wrap">
						<select name="query">
							<option value="qna_title">제목</option>
							<option value="qna_content">내용</option>
							<option value="userid">작성자</option>
						</select>
						<input id="search" type="search" name="content" placeholder="검색어를 입력해주세요.">
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
					<th scope="col" class="th-date">작성자</th>
					<th scope="col" class="th-date">작성일</th>
				</tr>
			</thead>
			<tbody>
				<c:set var="count" value="${qnaList.size() + 1}" />
				<c:forEach items="${qnaList}" var="dto">
					<c:set var="count" value="${count -1 }" />
					<tr>
						<td>${count}</td>
						<td style="text-align: left;">
							<c:choose>
								<c:when test="${dto.seq == dto.parentseq }">
									<a href="O_getQnaDetail.do?seq=${dto.seq }">[${dto.category}] ${dto.qna_title}</a>
								</c:when>
								<c:otherwise>
									<span class="re-button">re</span> <a href="O_getQnaDetail.do?seq=${dto.seq }" style="font-weight: bold;">${dto.qna_title}</a>
								</c:otherwise>
							</c:choose>
						</td>
						<td>
							<c:choose>
								<c:when test="${dto.seq == dto.parentseq}">
									<c:set var="maskedUserId" value="${fn:substring(dto.userid, 0, 3)}****" />
									${maskedUserId}
								</c:when>
								<c:otherwise><strong>관리자</strong></c:otherwise>
							</c:choose>
						</td>
						<td>${dto.writedate}</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>
	<div class="container" style="text-align: right;">
		<br>
		<span class="list-button" ><a href="O_writeViewQnA.do">유저용글쓰기</a></span>
	</div>

	<div class="container pagination" style="text-align: center;">
		<script>
		let pageSize = ${p.pageSize}; // 한 페이지당 보여줄 최대 페이지 개수
		let itemsPerPage = ${p.itemsPerPage}; // 한 페이지당 보여줄 게시물의 수
		let totalCount = ${p.totalCount}; // 전체 게시물의 수
		let currentPage = ${p.currentPage}; // 현재 페이지
		let totalPages = ${p.totalPages}; // 전체 페이지의 수
		let calcPage = Math.floor((currentPage - 1) / pageSize) * pageSize + 1; // 현재 페이지에서 보여질 페이지의 시작값 계산

		// 이전 버튼
		if (currentPage > 1) {
		  document.write('<span><a href="O_QNA.do?page=' + 1 + '"><<</a></span>');
		  document.write('<span><a href="O_QNA.do?page=' + (currentPage - 1) + '"><</a></span>');
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
		      document.write('<span class="current"><a href="O_QNA.do?page=' + i + '">' + i + '</a></span>');
		    } else {
		      document.write('<span><a href="O_QNA.do?page=' + i + '">' + i + '</a></span>');
		    }
		  }
		}

		// 다음 버튼
		if (currentPage != totalPages && totalPages != 1) {
		  document.write('<span><a href="O_QNA.do?page=' + (currentPage + 1) + '">></a><span>');
		  document.write('<span><a href="O_QNA.do?page=' + totalPages + '">>></a><span>');
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