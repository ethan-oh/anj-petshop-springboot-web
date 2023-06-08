<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>	
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<header>
	</header>
	
	<main>
		<table class="mileage">
			<tr>
				<td> 사용 가능하신 적립금 	<!-- 총 제품 구매 적립금 - 사용한 적립금 --> </td>
				<td><fmt:formatNumber value="${userView.mileage }" pattern="#,###" /> 원 
				<form action="J_mileageHistoryPage.jsp" style="display: inline;">
					<input type="submit" value="조회"> </form></td>		<!-- **버튼 누르면 조회페이지로 넘어가도록!!! -->
				<td> 총 제품구매 적립금	<!-- 모든 적립금 내역들의 합계 --> </td>
				<td><fmt:formatNumber value="${orderMileage.totalMileage}" pattern="#,###" /> 원 </td>
				
			</tr>
			<tr>
				<td> 사용하신 적립금 </td>
				<td> <fmt:formatNumber value="${orderMileage.totalUsedMileage }" pattern="#,###" /> 원 </td>
				<td> 총 주문 금액  </td>
				<td>
				<fmt:formatNumber value="${orderMileage.totalPrice}" pattern="#,###" /> 원
				(<fmt:formatNumber value="${orderMileage.ordercount}" pattern="#,###" />회)
				</td>
			</tr>
		</table>
	</main>
	
	<footer>
	</footer>



</body>
</html>