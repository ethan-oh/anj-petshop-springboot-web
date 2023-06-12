<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link rel="stylesheet" href="T_cartCss.css">
<meta charset="UTF-8">
<title>구매완료</title>

</head>
<body>
<main class="main">

    <h1 style="font-family: 'font-family: 'Nanum Pen Script', cursive;">고객님의 주문이 완료 되었습니다.</h1>
    <br> <br> <br> <br> <br> <br> <br>
</main>
				<c:set var="ordernum" value="${content_View.ordernum}" />
<table>
    <tr>
        <td>
            주문번호 : ${ordernum}<br>
        </td>
    </tr>
    <tr>    
        <td>
            주문날짜 : ${content_View.orderdate}<br>
        </td>
    </tr>
</table>
    
    <br><br><br>

    <hr width="80%" color="#477a7b" size="2">
	<table>
		<tr>
			<td>
				결제정보
			</td>
		</tr>
		<tr>
			<td>
				전체주문금액
			</td>
			<td>
				${content_View.orderprice}원<br>
			</td>
		</tr>
		<tr>
			<td>
				사용 마일리지
			</td>
			<td>
				${content_View.usedmileage}<br>
			</td>
		</tr>
		<tr>
			<td>
				최종결제금액
			</td>
			<td>
				${content_View.usedmileage}<br>
			</td>
		</tr>
		<tr>
			<td>
				결제수단
			</td>
			<td>
				${content_View.payment}<br>
			</td>
		</tr>
	</table>
	<hr width="80%" color="#477a7b" size="2">
	<table>
		<tr>
			<td>
				주문 상품 정보
			</td>
		</tr>
	</table>
		<%-- <c:forEach var="dto" items="${orderproduct}"> --%>
   <c:forEach var="product" items="${orderList}">
    <table>
        <tr>
            <td style="width: 180px; text-align: left;">
                <span class="pid">${product.pid}</span><br>
                <span class="pname">${product.pname}</span><br>
                <fmt:formatNumber value="${product.orderprice}" pattern="#,##0" />원
            </td>
            <td style="width: 130px;">${product.count}</td>
        </tr>
    </table>
</c:forEach>

<%-- </c:forEach> --%>

<%-- ${dto.pid} 
${dto.pname} 
${dto.orderprice} 
${dto.count}  --%>

	<hr width="80%" color="#477a7b" size="2">
    <table>
    	<tr>
			<td>
				배송지정보
			</td>
		</tr>
    	<tr>
    		<td>
    			받으시는분
    		</td>
    		<td>
    			${content_View.username}<br>
    		</td>
    	</tr>
    	<tr>
    		<td>
    			우편번호
    		</td>
    		<td>
    			 ${content_View.userpostcode}<br>
    		</td>
    	</tr>
    	<tr>
    		<td>
    			주소
    		</td>
    		<td>
    			${content_View.shipaddress}<br>
    		</td>
    	</tr>
    	<tr>
    		<td>
    			휴대전화
    		</td>
    		<td>
    			${content_View.usertel}<br>
    		</td>
    	</tr>
    	<tr>
    		<td>
    			배송메시지
    		</td>
    		<td>
    			${content_View.ordermessage}<br>
    		</td>
    	</tr>
    </table>
   
	<br>
	<br>
	<br>
	<br>

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
				<strong>Corporation ANJ.industry</strong> <br> Gangnam-gu,
				Seoul (Yeoksam-dong The Joy Computer Academy) <br> CEO: Ahn
				Jae-won <br> Business registration number: 240-81-87676
				Business information confirmation <br> Mail-order business
				report: Gangnam 10238 Fax: 02-000-1234
			</p>
			<p>
				<strong>customer service center</strong> <br> Tel :
				Representative number 1234-5678 (Weekdays 09:00~18:00) <br>
				Dedicated to the future: 1522-5700 (365 days 09:00-18:00) <br>
				Gangnam-gu, Seoul (Yeoksam-dong The Joy Computer Academy) <br>
				Fax : 02-000-1234 | Mail : ajw0376@gmail.com <br>
			</p>
		</div>
	</footer>
</body>
</html>
