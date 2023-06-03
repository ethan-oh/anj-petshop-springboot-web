<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script	src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<style>
    body {
      font-family: Arial, sans-serif;
      margin: 0;
      padding: 0;
    }

    .main {
      padding: 20px;
    }

    select,
    input[type="text"],
    button {
      margin-right: 10px;
      padding: 5px;
    }

    table {
      width: 100%;
      border-collapse: collapse;
    }

    th,
    td {
      padding: 10px;
      text-align: center;
      border: 1px solid #ccc;
    }

    .low {
      margin-top: 20px;
      text-align: center;
    }

    #pagenum {
      list-style: none;
      padding: 0;
      display: inline-block;
    }

    #pagenum li {
      display: inline-block;
      margin-right: 5px;
      cursor: pointer;
    }
  </style>
</head>
<body>
	<div class="main">
		<select name="pagesize" id="pagesize">
			<option value="10" selected="selected">10</option>
			<option value="15">15</option>
			<option value="20">20</option>
			<option value="5">5</option>
			<option value="2">2</option>
		</select> <select id="selectType">
			<option value="pid" selected="selected">제품코드</option>
			<option value="pname">제품명</option>
			<option value="pcategory">카테고리</option>
			<option value="available">노출여부</option>
		</select> <input type="text" id="text">
		<button id="btn" onclick="selectAction()">검색</button>

		<table class="" border="1">
			<thead>
				<tr>
					<th style="width: 40px; text-align: center;">순서</th>
					<th style="width: 80px; text-align: center;">제품 이미지</th>
					<th style="width: 50px; text-align: center;">제품코드</th>
					<th style="width: 50px; text-align: center;">제품명</th>
					<th style="width: 80px; text-align: center;">카테고리</th>
					<th style="width: 130px; text-align: center;">가격</th>
					<th style="width: 90px; text-align: center;">재고</th>
					<th style="width: 200px; text-align: center;">노출 여부</th>
				</tr>
			</thead>
			<tbody class="table_body" style="text-align: center;">

			</tbody>
		</table>
		</div>
		<div class="low">
		<ul id="pagenum">
		</ul>
	</div>

</body>
<script>
	let pagesize = $("#pagesize option:selected").val(); // 화면에 나타낼 데이터갯수
	let pagenum = 1;
	let pagestart = 1;
	let pageend;
	let selectText = "";
	let selectType = "pid";

	function selectAction() {
		selectText = $("#text").val();
		selectType = $("#selectType").val()
		pagenum = 1;
		$("#pagenum li").empty();
		$.ajax({
			url : "selectChange.do",
			type : "post",
			dataType : "JSON",
			data : {
				"pagesize" : pagesize,
				"pagenum" : pagenum,
				"selectType" : selectType,
				"selectText" : selectText
			},
			success : function(data) {
				let str = '';
				pagestart = ((pagenum - 1) * pagesize) + 1;
				let item;

				$.each(data, function(i, currentItem) {
					item = currentItem;
					str += '<tr>';
					str += '<td>' + (pagestart++) + '</td>';
					str += '<td>' + item.pthumbnail + '</td>';
					str += '<td>' + item.pid + '</td>';
					str += '<td><a href="W_ProductUpdate.do?pid=' + item.pid
							+ '">' + item.pname + '</a></td>';
					str += '<td>' + item.pcategory + '</td>';
					str += '<td>' + item.pprice + '</td>';
					str += '<td>' + item.pstock + '</td>';
					str += '<td>' + item.available + '</td>';
					str += '</tr>';
				});
				$(".table_body").html(str);
				for (let i = 1; i <= item.count; i++) {
					$("#pagenum").append('<li value="' + i + '">' + i + '</li>');
				}
				if(item.count>pagenum){
					$("#pagenum").append('<li id="next">다음</li>');
					$("#pagenum").append('<li id="last">마지막</li>');
				}
				
				pageend=item.count;
			}
		});
	};

	// 페이지 숫자 클릭시
	$("#pagenum").on("click","li",	function(event) {
				if($(this).text()=="다음"){
					pagenum++;
				}else if($(this).text()=="이전"){
					pagenum--;
				}else if($(this).text()=="마지막"){
					pagenum = pageend;
				}else if($(this).text()=="처음"){
					pagenum = 1;
				
				}else{
				pagenum = $(this).val();
				};
				$("#pagenum li").empty();
				$.ajax({
					url : "selectChange.do",
					type : "post",
					dataType : "JSON",
					data : {
						"pagesize" : pagesize,
						"pagenum" : pagenum,
						"selectType" : selectType,
						"selectText" : selectText
					},
					success : function(data) {
						let str = '';
						pagestart = ((pagenum - 1) * pagesize) + 1;
						let item;
						if(pagenum>1){
							$("#pagenum").append('<li id="last">처음</li>');
							$("#pagenum").append('<li id="prev">이전</li>');
						}
						$.each(data, function(i, currentItem) {
							item = currentItem;
							str += '<tr>';
							str += '<td>' + (pagestart++) + '</td>';
							str += '<td>' + item.pthumbnail + '</td>';
							str += '<td>' + item.pid + '</td>';
							str += '<td>' + item.pname + '</td>';
							str += '<td>' + item.pcategory + '</td>';
							str += '<td>' + item.pprice + '</td>';
							str += '<td>' + item.pstock + '</td>';
							str += '<td>' + item.available + '</td>';
							str += '</tr>';
						});
						$(".table_body").html(str);
						for (let i = 1; i <= item.count; i++) {
							$("#pagenum").append(
									'<li value="' + i + '">' + i + '</li>');
						}
						if(item.count>pagenum){
							$("#pagenum").append('<li id="next">다음</li>');
							$("#pagenum").append('<li id="last">마지막</li>');
						}
						
						pageend=item.count;
					}
				});
				
			});

	// 처음 화면 리스트
	$(document).ready(
			function() {
				$("#pagenum li").empty();
				$.ajax({
					url : "selectChange.do",
					type : "post",
					dataType : "JSON",
					data : {
						"pagesize" : pagesize,
						"pagenum" : pagenum,
						"selectType" : selectType,
						"selectText" : selectText
					},
					success : function(data) {
						let str = '';
						pagestart = ((pagenum - 1) * pagesize) + 1;
						let item;

						$.each(data, function(i, currentItem) {
							item = currentItem;
							str += '<tr>';
							str += '<td>' + (pagestart++) + '</td>';
							str += '<td>' + item.pthumbnail + '</td>';
							str += '<td>' + item.pid + '</td>';
							str += '<td><a href="W_ProductUpdate.do?pid='+ item.pid + '">' + item.pname + '</a></td>';
							str += '<td>' + item.pcategory + '</td>';
							str += '<td>' + item.pprice + '</td>';
							str += '<td>' + item.pstock + '</td>';
							str += '<td>' + item.available + '</td>';
							str += '</tr>';
						});
						$(".table_body").html(str);
						for (let i = 1; i <= item.count; i++) {
							$("#pagenum").append(
									'<li value="' + i + '">' + i + '</li>');
						}
						if(item.count>1){
							$("#pagenum").append('<li id="next">다음</li>');
							$("#pagenum").append('<li id="last">마지막</li>');
						}
						pageend=item.count;
					}
				});
			});
	// 화면나타내는 수에따른 리스트
	$("#pagesize").change(
			function() {
				$("#pagenum li").empty();
				pagenum = 1;
				pagesize = $("#pagesize option:selected").val();
				$.ajax({
					url : "selectChange.do",
					type : "post",
					dataType : "JSON",
					data : {
						"pagesize" : pagesize,
						"pagenum" : pagenum,
						"selectType" : selectType,
						"selectText" : selectText
					},
					success : function(data) {
						let str = '';
						pagestart = ((pagenum - 1) * pagesize) + 1;
						let item;
						
						$.each(data, function(i, currentItem) {
							item = currentItem;
							str += '<tr>';
							str += '<td>' + (pagestart++) + '</td>';
							str += '<td>' + item.pthumbnail + '</td>';
							str += '<td>' + item.pid + '</td>';
							str += '<td><a href="W_ProductUpdate.do?pid='
									+ item.pid + '">' + item.pname
									+ '</a></td>';
							str += '<td>' + item.pcategory + '</td>';
							str += '<td>' + item.pprice + '</td>';
							str += '<td>' + item.pstock + '</td>';
							str += '<td>' + item.available + '</td>';
							str += '</tr>';
						});
						$(".table_body").html(str);
						
						for (let i = 1; i <= item.count; i++) {
							$("#pagenum").append(
									'<li value="' + i + '">' + i + '</li>');
						}
						
						if(item.count>1){
							$("#pagenum").append('<li id="next">다음</li>');
							$("#pagenum").append('<li id="last">마지막</li>');
						}
						pageend=item.count;
					}
				});

			});
</script>
</html>