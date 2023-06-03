<%@ page language="java" contentType="text/html; charset=EUC-KR" pageEncoding="EUC-KR"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<c:set var="index" value="0" scope="page" />
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>공지사항</title>

<link rel="stylesheet" href="O_BoardStyle.css">
<link rel="stylesheet" href="A_heardCss.css">
<link rel="stylesheet" href="A_MainCss.css">

</head>

<!-- <header>
	<nav>
		<ul>
			<li><a href="A_mainView.do">SHOP</a></li>
			<li><a href="A_ProductView.do">ANJLIFE</a></li>
			<li><a href="O_Notice.do">COMMUNITY</a></li>
			<li><a href="A_introduction.jsp">CART</a></li>
		</ul>
	</nav>
</header> -->
<body>
	<!-- ---------------------- -->
	<section class="notice">

		<!-- board list area -->
		<div class="container">
			<table class="board-table">
				<thead>
					<tr>
						<th scope="col">${NDetail.n_title}</th>
					</tr>
				</thead>
				<tbody>
					<tr>
						<td scope="col" class="th-left">구분 : 공지사항</td>
					</tr>
					<tr>
						<td scope="col" class="th-left">작성일 : ${NDetail.writedate}</td>
					</tr>
					<tr>
						<td>${NDetail.n_content}</td>

					</tr>
				</tbody>
			</table>
		</div>

		<div class="container">
			<br />
				<span class="list-button" style="text-align: right;"><a href="O_Notice.do?page=1">목록</a></span>>
		</div>
	</section>
</body>
<footer>
	<p>
		<span>TEL. 1877-3228<br /> E-MAIL. arrr@dongwon.com<br /> ADDRESS. 10th floor, 68, Mabangro, Seocho-gu, Seoul, Republic of Korea, 06775<br /> BUSINESS NO. 703-88-01843
		</span>
	</p>
</footer>

</html>