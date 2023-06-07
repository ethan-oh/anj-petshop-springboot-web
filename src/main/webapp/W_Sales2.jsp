<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<html>
  <head>
 
  <style type="text/css">
  
        .rowdata{
	
	 display: flex;
	 background-color: f1f1f1;
	  margin-top: 5%;
	}
	.sidebar{
	  display: flex;
	flex-direction: column;
	width: 10%;
	background-color: f1f1f1;
	padding: 20px;
	
	}
	.sidebar input[type="submit"]{
	display: float;
	width: 100%;
	height:50px;
	font-size: 20px;
    }	
    .container{
    display: flex;
    flex-direction: column;
    justify-content: center;
    width: 100%;
    text-align: center;
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
					      	</ul>
					      </li>
					      <li><a href="A_introduction.jsp">주문 관리</a></li>
					      <li><a href="W_UserList.jsp">회원 관리</a></li>
					      <li><a href="A_introduction.jsp">게시판 관리</a>
					      	<ul>
					      		<li><a href="A_ProductView.do">공지사항</a></li>
					      		<li><a href="A_ProductView.do">Q & A</a></li>
					      		<li><a href="A_ProductView.do">리뷰</a></li>
					      	</ul>
					      	</li>
					      <li><a href="W_Sales.jsp">매출현황</a></li>
					    </ul>
					  </nav>
				</header>
  <div class="rowdata">
  	<div class="sidebar">
			<form action="W_ProductList.jsp"><input type="submit" value="일매출"></form>
			<br />
			<form action="W_ProductInsert.jsp"><input type="submit" value="월매출"></form>
			<br />
			<form action="W_ProductInsert.jsp"><input type="submit" value="상품별매출"></form>
			<br />
		</div> <!-- sidebar -->
		
		<div class="container">
  <h1 style="font-size: 50px">일일 매출 현황</h1><br/>
  
      <div id="chart" style="width: 50%; height: 400px; align-self: center;"></div>
    <table>
    	<tr>
    		<th>순번</th>
    		<th>구매자ID</th>
    		<th>제품코드</th>
    		<th>상품명</th>
    		<th>상품가격</th>
    		<th>판매량</th>
    		<th>사용포인트</th>
    		<th>순이익</th>
    		<th>  <input type="date"></th>
    	</tr>
    </table>
  
 	</div> <!-- container -->
</div> <!-- rowdata -->
  </body>
  
  <script type="text/javascript" src="https://www.gstatic.com/charts/loader.js"></script>
    <script type="text/javascript">
    google.charts.load('current', {packages: ['corechart', 'bar']});
    google.charts.setOnLoadCallback(drawBasic);

    function drawBasic() {

          var data = google.visualization.arrayToDataTable([
            ['ㄴㄴㄴㄴ', 'Population',{ role: 'style' }],
            ['오늘', 3200000,'gold'],
            ['어제', 2000000, '#76A7FA'],
            ['최근 7일 평균', 2500000, 'purple']
      
          ]);

          var options = {
            title: '일별 매출',
            chartArea: {width: '50%'},
            hAxis: {
              title: '단위 (원)',
              minValue: 0
            },
            vAxis: {
              title: ''
            }
          };

          var chart = new google.visualization.BarChart(document.getElementById('chart'));

          chart.draw(data, options);
        }
    </script>
</html>
