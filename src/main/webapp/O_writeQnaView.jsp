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

<script type="text/javascript">
	function writeCheck(){
		const form = document.writeQnA
		const qna_title = form.qna_title.value
		const qna_content = form.qna_content.value
		
		if(qna_title == ""){
			alert("제목을 입력해 주세요.")
			return
		}
		if(qna_content == ""){
			alert("내용을 입력해 주세요.")
			return
		}
		form.action = "O_writeQnA.do";
		document.productinfo.submit();
	}
</script>
<script>
  function setQnaTitle() { // select의 옵션 선택하자마자 제목에 값을 넣어주는 함수.
    var qCategory = document.getElementsByName('qCategory')[0];
    var qnaTitle = document.getElementById('qna_title');

    var selectedCategory = qCategory.options[qCategory.selectedIndex].value;
    qnaTitle.value = selectedCategory + " 문의 드립니다.";
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
	<!----------------------------------------------------------------------------------------------------->
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
		<h4>QnA 등록</h4>
	</div>
	<!-- board list area -->
	<div class="container">
		<form name="writeQnA" method="post">
		  <input type="hidden" name="userid" value="osm1119"> <!-- 로그인 구현 시 session값으로 바꿔주기 -->
		  <table class="board-table">
		    <thead>
		      <tr>
		        <th scope="col">
		          <div class="qna">
		            <select name="qCategory" onchange="setQnaTitle()">
		              <option value="상품 관련">상품</option>
		              <option value="배송 관련">배송</option>
		              <option value="입금 관련">입금확인</option>
		              <option value="주문 취소">주문취소</option>
		              <option value="환불">환불</option>
		              <option value="기타">기타</option>
		            </select>
		          </div>
		        </th>
		        <th scope="col">
		          <span class="qna"><input type="text" name="qna_title" id="qna_title" value="상품 관련 문의 드립니다."></span>
		        </th>
		      </tr>
		    </thead>
		    <tbody>
		      <tr>
		        <td>내용</td>
		        <td><textarea rows="5" cols="100" wrap="hard" name="qna_content"></textarea></td>
		      </tr>
		    </tbody>
		  </table>
		  <div class="container" style="text-align: right;">
		    <br>
		    <input type="submit" class="list-button th-right" value="등록" onclick="writeCheck()">
		  </div>
</form>
	</div>
	<button class="top-button" onclick="scrollToTop()">top</button>
	<!----------------------------------------------------------------------------------------------------->
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