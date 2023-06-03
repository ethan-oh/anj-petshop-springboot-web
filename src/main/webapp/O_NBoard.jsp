<%@ page language="java" contentType="text/html; charset=EUC-KR" pageEncoding="EUC-KR"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<c:set var="index" value="0" scope="page" />

<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>��������</title>

<link rel="stylesheet" href="O_BoardStyle.css">
<link rel="stylesheet" href="A_heardCss.css">
<link rel="stylesheet" href="A_MainCss.css">
<link rel="stylesheet" href="O_PageStyle.css">

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
	<section class="notice">
		<div class="page-title">
			<div class="container">
				<h3>��������</h3>
			</div>
		</div>

		<!-- board seach area -->
		<div id="board-search">
			<div class="container">
				<div class="search-window">
					<form action="">
						<div class="search-wrap">
							<label for="search" class="blind">�������� ���� �˻�</label> <input id="search" type="search" name="" placeholder="�˻�� �Է����ּ���." value="">
							<button type="submit" class="btn btn-dark">�˻�</button>
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
						<th scope="col" class="th-num">��ȣ</th>
						<th scope="col" class="th-title">����</th>
						<th scope="col" class="th-writer">�ۼ���</th>
						<th scope="col" class="th-date">�ۼ�����</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${noticeList}" var="dto">
						<tr>
							<td><span class="notice-button">����</span></td>
							<td><a href="O_NDetail.do?seq=${dto.seq}">${dto.n_title}</a></td>
							<td>${dto.adminid}</td>
							<td>${dto.writedate}</td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</div>

		<div class="container pagination"  style="text-align: center;">
			<script>
				let pageSize = ${p.pageSize} // �� �������� ������ �ִ� ������ ����
				let itemsPerPage = ${p.itemsPerPage} // �� �������� ������ �Խù��� ��
				let totalCount = ${p.totalCount} // ��ü �Խù��� ��
				let currentPage = ${p.currentPage} // ���� ������
				let totalPages = ${p.totalPages} // ��ü �������� ��
				let calcPage = Math.floor((currentPage - 1) / pageSize) * pageSize + 1; // ���� ���������� ������ �������� ���۰� ���
				
				
				
				if(currentPage > 1){
					document.write('<a href="O_Notice.do?page=' + (currentPage - 1) + '">����</a>')
				}
				
				if(totalPages != 1 ){
					for(var i = calcPage; i <= calcPage + pageSize -1 && i <= totalPages; i++){
						document.write('<a href="O_Notice.do?page=' + i + '"> ' + i + ' </a>')
					}
				}
				
				if((currentPage != totalPages) && totalPages != 1){
					document.write('<a href="O_Notice.do?page=' + (currentPage + 1) + '"> ����</a>')
				}
				
			</script>
		</div>


	</section>
</body>
<footer>
	<p>
		<span> TEL. 1877-3228<br /> E-MAIL. arrr@dongwon.com<br /> ADDRESS. 10th floor, 68, Mabangro, Seocho-gu, Seoul, Republic of Korea, 06775<br /> BUSINESS NO. 703-88-01843
		</span>
	</p>
</footer>

</html>