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

<link rel="stylesheet" href="CSS/O_FBoardStyle.css">
<link rel="stylesheet" href="CSS/O_NBoardStyle.css">
<link rel="stylesheet" href="A_heardCss.css">
<link rel="stylesheet" href="CSS/O_PageStyle.css">
<link rel="stylesheet" href="CSS/O_Common.css">
<script src="JS/O_ScrollTop.js"></script>

<script type="text/javascript">
	function writeCheck(){
		const form = document.writeFAQ
		const n_title = form.n_title.value
		const n_content = form.n_content.value
		
		if(n_title == ""){
			alert("제목을 입력해 주세요.")
			return
		}
		if(n_content == ""){
			alert("내용을 입력해 주세요.")
			return
		}
		form.action = "O_WriteFAQ.do";
		document.productinfo.submit();
	}
</script>
<script type="text/javascript">
	$(document).ready(function() {
		$(".hidden_row").hide(); // 페이지 로드 시 숨겨진 행 숨기기
		$(".toggle-icon").text("+"); // 초기에 토글 아이콘 "+"로 표시
	});

	function showHiddenRow(row) {
		let $targetRow = $("#" + row);
		let $toggleIcon = $targetRow.prev("tr").find(".toggle-icon");

		if ($targetRow.is(":visible")) {
			$targetRow.hide();
			$toggleIcon.text("+");
		} else {
			$(".hidden_row").hide();
			$(".toggle-icon").text("+");
			$targetRow.show();
			$toggleIcon.text("-");
		}
	}
</script>
</head>
<body>
<!------------------------- /header ---------------------------------->
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
<!------------------------- /header ---------------------------------->
<!------------------------- body ---------------------------------->

	<div class="page-title" style="background-color: #DFE9E8;">
		<br><br><br>
		<h3>CUMMUNITY</h3>
		<br><br>
			<a href="O_Notice.do">NOTICE</a> 
			<a href="O_FAQ.do">FAQ</a> 
			<a href="O_FAQ.do">Q&A</a> 
			<a href="O_FAQ.do">REVIEW</a> 
		<br><br>
	</div>
	<div class="page-title">
			<a href="O_FAQ.do">목록</a> 
			<a href="">등록</a> 
			<a href="">수정</a>
			<a href="">삭제</a>
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
						<td class="custom-padding th-left" data-padding="20">${dto.n_title}
							<span class="toggle-icon-wrapper">
								<span class="toggle-icon" style="float: right;"></span>
							</span>
						</td>
					</tr>
					<!-- id를 게시글의 seq로 부여 -->
					<tr id="row_${dto.seq}" class="hidden_row">
						<td class="custom-padding th-left" data-padding="60"><pre>${dto.n_content}</pre></td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
		<br><br><br>
	</div>
	
	<div class="page-title">
		<h4>FAQ 신규 등록</h4>
	</div>
	
	<div class="container">
		<form  name="writeFAQ" method="post">
			<input type="hidden" name="adminid" value="admin">
			<table class="board-table" border="1">
				<thead>
					<tr>
						<th scope="col">
							<div class="qna">
								<select name="qCategory">
									<option value="[회원]">회원</option>
									<option value="[배송]">배송</option>
									<option value="[주문/결제]">주문/결제</option>
									<option value="[교환/반품]">교환/반품</option>
								</select>
							</div>
						</th>
						<th scope="col">
							<span class="qna"><input type="text" name="n_title" placeholder="제목을 입력하세요."></span>
						</th>
					</tr>
				</thead>
				<tbody>
					<tr>
						<td>내용</td>
						<td><textarea rows="5" cols="100" wrap="hard" name="n_content" placeholder="내용을 입력하세요."></textarea></td>
					</tr>
				</tbody>
			</table>
			<div class="container" style="text-align: right;">
				<br>
				<input type="submit" class="list-button th-right" value="등록" onclick="writeCheck()">
			</div>
		</form>
	</div>
	
	<div class="page-title">
		<h4>삭제된 FAQ 복구</h4>
	</div>

	<div class="container">
		<form action="O_changeFAQStatus.do" method="post">
		<input type="hidden" name="status" value="0">
			<table class="board-table" border="1">
				<thead>
					<tr>
						<th scope="col">
							<div class="qna longSelect">
								<select name="seq">
									<c:forEach items="${DeletedFAQList}" var="dto">
										<option value="${dto.seq}">${dto.n_title}</option>
									</c:forEach>
								</select>
							</div>
						</th>
					</tr>
				</thead>
			</table>
			<div class="container" style="text-align: right;">
				<br>
				<input type="submit" class="list-button th-right" value="복구">
			</div>
		</form>
	</div>
	
	<!-- <div class="page-title">
		<h4>FAQ 수정</h4>
	</div>
	
	<div class="container">
		<form  name="writeFAQ" method="post">
			<input type="hidden" name="adminid" value="admin">
			<table class="board-table" border="1">
				<thead>
					<tr>
						<th scope="col">
							<div class="qna">
								<select name="qCategory">
									<option value="[회원]">회원</option>
									<option value="[배송]">배송</option>
									<option value="[주문/결제]">주문/결제</option>
									<option value="[교환/반품]">교환/반품</option>
								</select>
							</div>
						</th>
						<th scope="col">
							<span class="qna"><input type="text" name="n_title" placeholder="제목을 입력하세요."></span>
						</th>
					</tr>
				</thead>
				<tbody>
					<tr>
						<td>내용</td>
						<td><textarea rows="5" cols="100" wrap="hard" name="n_content" placeholder="내용을 입력하세요."></textarea></td>
					</tr>
				</tbody>
			</table>
			<div class="container" style="text-align: right;">
				<br>
				<input type="submit" class="list-button th-right" value="등록" onclick="writeCheck()">
			</div>
		</form>
	</div> -->
	
	<div class="page-title">
		<h4>FAQ 삭제</h4>
	</div>

	<div class="container">
		<form action="O_changeFAQStatus.do" method="post">
			<input type="hidden" name="status" value="1">
			<table class="board-table" border="1">
				<thead>
					<tr>
						<th scope="col">
							<div class="qna longSelect">
								<select name="seq">
									<c:forEach items="${FAQList}" var="dto">
										<option value="${dto.seq}">${dto.n_title}</option>
									</c:forEach>
								</select>
							</div>
						</th>
					</tr>
				</thead>
			</table>
			<div class="container" style="text-align: right;">
				<br>
				<input type="submit" class="list-button th-right" value="삭제">
			</div>
		</form>
	</div>
	
	<button class="top-button" onclick="scrollToTop()">top</button>
	<!------------------------- /body ---------------------------------->
	<!------------------------- footer ---------------------------------->
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
	<!------------------------- /footer ---------------------------------->
</body>
</html>