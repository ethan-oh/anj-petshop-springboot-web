<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<c:set var="index" value="0" scope="page" />
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>공지사항</title>

<link rel="stylesheet" href="CSS/O_NBoardStyle.css">
<link rel="stylesheet" href="A_heardCss.css">
<link rel="stylesheet" href="A_MainCss.css">
<link rel="stylesheet" href="CSS/O_Common.css">
<script src="JS/O_ScrollTop.js"></script>

</head>
<body>
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
     <br><br> <br> <br><br><hr>
	<!-- ---------------------- -->
	<div class="page-title">
		<br><br><br>
		<h3>COMMUNITY</h3>
		<br><br>
			<span class="selected"><a href="O_Notice.do">NOTICE</a></span>
			<a href="O_FAQ.do">FAQ</a> 
			<a href="O_QNA.do">Q&A</a> 
			<a href="O_Review.do">REVIEW</a> 
		<br><br>
	</div>
	<div class="page-title">
		<h4>공지사항 상세</h4>
	</div>

	<!-- board list area -->
	<div class="container">
		<form name="NDetail" method="post"> <!-- 유저용에서는 이 폼태그 빼고 제목을 input타입 빼고 그냥 적기, textarea readonly 속성 넣어주기 -->
			<input type="hidden" name="status" value="1">
			<input type="hidden" name="seq" value="${seq }">
			<table class="board-table">
				<thead>
					<tr>
						<th class="th-wnum">제목</th>
						<th scope="col" colspan="3"><input type="text" name="n_title" value="${NDetail.n_title}" readonly="readonly"></th>
					</tr>
				</thead>
				<tbody>
					<tr>
						<td class="th-wnum">구분</td>
						<td scope="col" class="th-left">공지</td>
					</tr>
					<tr>
						<td scope="col" class="th-wnum">작성일</td>
						<td scope="col" class="th-left">${NDetail.writedate}</td>
					</tr>
					<tr>
						<td>내용</td>
						<td>
							<textarea rows="25" cols="109" wrap="hard" name="n_content" readonly="readonly"><c:out value="${NDetail.n_content}" /></textarea>
						</td>
					</tr>
					<tr>
						<td></td>
						<td class="th-right"><span class="list-button"><a href="O_Notice.do">목록</a></span></td>
					</tr>
				</tbody>
			</table>
		</form>
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