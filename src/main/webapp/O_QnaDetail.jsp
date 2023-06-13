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
<script type="text/javascript">
	function updateCheck(){
		const form = document.QnaDetail
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
		
		// 나중에 본인 게시물인 경우에만 수정, 삭제 가능하게 구현해야함.
		// 그렇지 않으면 alert로 '본인의 게시글만 수정/삭제가 가능합니다.' 메시지 띄워주기!
			
		if(confirm("수정하시겠습니까?") == true){
		form.action = "O_updateQnA.do";
		form.submit();
		}
	}
	
	function deleteCheck(){
		const form = document.QnaDetail
		const seq = form.seq.value
		const parentseq = form.parentseq.value
		var result = 0
		
		if (seq == parentseq){ // 질문글일때
			result = 1
		}else{ // 답변글일때
			result = 2
		}
		
		if(confirm("정말 삭제하시겠습니까?") == true && result == 1){
			form.action = "O_deleteQuestion.do?seq=" + seq;
			form.submit();
			return;
			}else{
				return; // 취소 눌러도 return함
			}
		
		if(confirm("정말 삭제하시겠습니까?") == true && result == 2){
			form.action = "O_deleteAnswer.do?seq=" + seq;
			form.submit();
			return;
			}else{
				return;
			}
	}
	
	function writeAction(){
		const form = document.QnaDetail
		const seq = form.seq.value
		form.action = "O_writeAnswerView.do?seq=" + seq;
		form.submit();
		}
</script>
<script type="text/javascript">
// 드롭다운
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
										<a href="#">NOTICE</a>
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
	<!-- ---------------------- -->
	<div class="page-title">
		<br><br><br>
		<h3>COMMUNITY</h3>
		<br><br>
			<a href="O_Notice.do">NOTICE</a>
			<a href="O_FAQ.do">FAQ</a> 
			<span class="selected"><a href="O_QNA.do">Q&A</a></span>
			<a href="O_Review.do">REVIEW</a>
		<br><br>
	</div>
	<div class="page-title">
		<h4>QnA 상세</h4>
	</div>

	<!-- board list area -->
	<div class="container">
		<form name="QnaDetail" method="post"> <!-- 유저용에서는 이 폼태그 빼고 제목을 input타입 빼고 그냥 적기, textarea readonly 속성 넣어주기 -->
			<input type="hidden" name="status" value="1">
			<input type="hidden" name="seq" value="${qnaDetail.seq }">
			<input type="hidden" name="parentseq" value="${qnaDetail.parentseq}">
			<table class="board-table">
				<thead>
					<tr>
						<th class="th-wnum">제목</th>
						<th scope="col" colspan="3"><input type="text" name="qna_title" value="${qnaDetail.qna_title}" readonly="readonly"></th>
					</tr>
				</thead>
				<tbody>
					<tr>
						<td class="th-wnum">구분</td>
						<td scope="col" class="th-left">${qnaDetail.category }</td>
					</tr>
					<tr>
						<td scope="col" class="th-wnum">작성일</td>
						<td scope="col" class="th-left">${qnaDetail.writedate}</td>
					</tr>
					<tr>
						<td scope="col" class="th-wnum">작성자</td>
						<td scope="col" class="th-left">${qnaDetail.userid}</td>
					</tr>
					<tr>
						<td>내용</td>
						<td>
							<textarea rows="25" cols="109" wrap="hard" name="qna_content" readonly="readonly"><c:out value="${qnaDetail.qna_content}" /></textarea>
						</td>
					</tr>
					<tr>
						<td></td>
						<td class="th-right"><span class="list-button"><a href="O_QNA.do">목록</a></span></td>
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