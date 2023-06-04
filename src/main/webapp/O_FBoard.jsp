<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>공지사항</title>

<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>

<link rel="stylesheet" href="O_FBoardStyle.css">
<link rel="stylesheet" href="A_heardCss.css">
<link rel="stylesheet" href="A_MainCss.css">
<link rel="stylesheet" href="O_PageStyle.css">

<script type="text/javascript">
	$(document).ready(function() {
		$(".hidden_row").hide(); // 페이지 로드 시 숨겨진 행 숨기기
	});

	function showHiddenRow(row) {
		// 다른 토글 클릭 시 기존 토글 접히게 설정
		$(".hidden_row").not("#" + row).hide();
		$(".toggle-icon").removeClass("active");

		$("#" + row).toggle();
		let icon = $("#" + row).prev("tr").find(".toggle-icon");
		icon.toggleClass("active"); // 토글 아이콘의 상태 변경
	}
</script>
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


		<!-- board list area -->
		<div class="container">
			<table class="board-table">
				<thead>
					<tr>
						<th scope="col" colspan="1">내용</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${FAQList}" var="dto">
						<!-- 제목 클릭 시 hidden_row 토글 -->
						<tr class="tr-background" onclick="showHiddenRow('row_${dto.seq}');">
							<td class="custom-padding" data-padding="20">${dto.n_title}
								<span class="toggle-icon-wrapper">
									<span class="toggle-icon" style="float: right;"></span>
								</span>
							</td>
						</tr>
						<!-- id를 게시글의 seq로 부여 -->
						<tr id="row_${dto.seq}" class="hidden_row">
							<td class="custom-padding" data-padding="60">${dto.n_content}</td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
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