<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<c:set var="index" value="0" scope="page" />
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>공지사항</title>

<link rel="stylesheet" href="O_NBoardStyle.css">
<link rel="stylesheet" href="A_heardCss.css">
<link rel="stylesheet" href="A_MainCss.css">

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
	<!-- ---------------------- -->
	<section class="notice">
		<div class="page-title">
			<div class="container">
				<h3>
					임시 헤더
					<a href="O_Notice.do?page=1">공지사항</a>
					<a href="O_FAQ.do">FAQ</a>
				</h3><br/>
				<h3>공지사항 작성</h3>
			</div>
		</div>

		<!-- board list area -->
		<div class="container">
			<form action="O_WriteNotice.do" method="post">
				<input type="hidden" name="adminid" value="admin">
				<table class="board-table">
					<thead>
						<tr>
							<th scope="col" class="th-wnum">제목</th>
							<th scope="col" class="th-num" style="text-align: left;" >
								<input type="text" name="n_title" placeholder="제목을 입력하세요.">
							</th>
						</tr>
						<tr>
							<th scope="col" >구분</th>
							<th scope="col" class="th-left">공지사항</th>
						</tr>
					</thead>
					<tbody>
						
						<tr>
							<td>
								내용
							</td>
							<td style="text-align: left;">
								<textarea rows="30" cols="109" wrap="hard" name="n_content" placeholder="내용을 입력하세요."></textarea>
							</td>
						</tr>
						<tr style="text-align: right;">
							<td>
								<input type="submit" class="list-button" value="등록">
							</td>
						</tr>
					</tbody>
				</table>
			</form>
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