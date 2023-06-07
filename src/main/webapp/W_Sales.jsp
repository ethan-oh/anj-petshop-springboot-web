<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<html>
<head>
<c:set var="today" value="<%=new java.util.Date()%>" />
<!-- 현재날짜 -->
<c:set var="date">
	<fmt:formatDate value="${today}" pattern="yyyy년 MM월 dd일" />
</c:set>

<style type="text/css">
.rowdata {
	display: flex;
	background-color: f1f1f1;
	margin-top: 5%;
}

.sidebar {
	display: flex;
	flex-direction: column;
	width: 10%;
	background-color: f1f1f1;
	padding: 20px;
}

.sidebar input[type="submit"] {
	display: float;
	width: 100%;
	height: 50px;
	font-size: 20px;
}

.container {
	display: flex;
	flex-direction: column;
	justify-content: center;
	width: 100%;
	text-align: center;
}

.today {
	margin-left: 5%;
	text-align: left;
	margin-bottom: 5%;
}

.chartarea {
display: flex;
	flex-direction: low;
}

.table{
margin-left: 5%;
margin-right: 5%;
}
</style>

</head>




<link rel="stylesheet" href="W_Header.css">
<body style="height: 830px; background-color: #f1f1f1;">

	<header>
		<nav>
			<ul>
				<li><a href="W_AdminMain.jsp">HOME</a></li>
				<li><a href="W_ProductList.jsp">상품 관리</a>
					<ul>
						<li><a href="W_ProductList.jsp">상품 목록</a></li>
						<li><a href="W_ProductInsert.jsp">상품 등록</a></li>
					</ul></li>
				<li><a href="A_introduction.jsp">주문 관리</a></li>
				<li><a href="W_UserList.jsp">회원 관리</a></li>
				<li><a href="A_introduction.jsp">게시판 관리</a>
					<ul>
						<li><a href="A_ProductView.do">공지사항</a></li>
						<li><a href="A_ProductView.do">Q & A</a></li>
						<li><a href="A_ProductView.do">리뷰</a></li>
					</ul></li>
				<li><a href="W_Sales.jsp">매출현황</a></li>
			</ul>
		</nav>
	</header>
	
	<div class="rowdata">
		<div class="sidebar">
			<form action="W_ProductList.jsp">
				<input type="submit" value="일매출">
			</form>
			<br />
			<form action="W_ProductInsert.jsp">
				<input type="submit" value="월매출">
			</form>
			<br />
			<form action="W_ProductInsert.jsp">
				<input type="submit" value="상품별매출">
			</form>
			<br />
		</div>
		<!-- sidebar -->

		<div class="container">
			<div class="today">

				<h1 style="font-size: 40px">Today 리포트</h1>
				<br />
			</div> <!-- today -->
			<div class="chartarea">

				<div class="table">
					<table>
						<tr>
							<td><h4>오늘의 매출 현황<c:out value="${date}" /></h4></td>
						</tr>
						<tr>
							<td style="text-align: right;"><h3>964,200원</h3></td>
						</tr>
						<tr>
							<td>결제 금액</td>
							<td>123,123</td>
							<td>사용포인트</td>
							<td>123,123</td>
						</tr>
						<tr>
							<td>결제 건수</td>
							<td>12</td>
							<td>사용포인트</td>
							<td>123,123</td>
						</tr>
					</table>
				</div>
				<!-- table -->
				<div id="chart" style="width: 50%; height: 400px; align-self: center; background-color: f1f1f1;"></div>
			</div>
		</div>
		<!-- container -->
	</div>
	<!-- rowdata -->
</body>

<script type="text/javascript"
	src="https://www.gstatic.com/charts/loader.js"></script>
<script type="text/javascript">
	google.charts.load('current', {
		packages : [ 'corechart', 'bar' ]
	});
	google.charts.setOnLoadCallback(drawBasic);

	function drawBasic() {

		var data = google.visualization.arrayToDataTable([
				[ '', 'Population', {role : 'style'} ],
				[ '오늘', 3200000, 'gold' ], 
				[ '어제', 2000000, '#76A7FA' ],
				[ '최근 7일 평균', 2500000, 'purple' ]

		]);

		var options = {
			title : '일별 매출',
			chartArea : {
				width : '50%'
			},
			hAxis : {
				title : '단위 (원)',
				minValue : 0
			},
			vAxis : {
				title : ''
			}
		};

		var chart = new google.visualization.BarChart(document
				.getElementById('chart'));

		chart.draw(data, options);
	}
</script>
</html>
