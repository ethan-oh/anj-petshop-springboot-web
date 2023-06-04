<%@ page language="java" contentType="text/html; charset=EUC-KR" pageEncoding="EUC-KR"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<c:set var="index" value="0" scope="page" />
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>��������</title>

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
					<a href="O_Notice.do?page=1">��������</a>
					<a href="O_FAQ.do">FAQ</a>
				</h3>
			</div>
		</div>

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
						<td scope="col" class="th-left">���� : ��������</td>
					</tr>
					<tr>
						<td scope="col" class="th-left">�ۼ��� : ${NDetail.writedate}</td>
					</tr>
					<tr>
						<td>${NDetail.n_content}</td>

					</tr>
				</tbody>
			</table>
		</div>

		<div class="container">
			<br /> <span class="list-button"><a href="O_Notice.do?page=1">���</a></span>
		</div>
	</section>
	<footer>
		<ul>
			<li><a href="#">Brand Story</a></li>
			<li><a href="#">�����̿���</a></li>
			<li><a href="#">��������ó����ħ</a></li>
			<li><a href="#">���ڱ����ŷ����</a></li>
		</ul>
		<div>
			<p>
				<img src="LOGO.png" alt="Ǫ�ͷΰ�">
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