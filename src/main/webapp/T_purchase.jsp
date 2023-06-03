<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<link rel="stylesheet" href="T_cartCss.css">

<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>주문/결제</title>
</head>

<style type="text/css">
@import
	url(https://fonts.googleapis.com/css?family=Advent+Pro:100,300,600);

@import url(http://fonts.googleapis.com/earlyaccess/nanumpenscript.css);

@import
	url(http://fonts.googleapis.com/earlyaccess/nanumbrushscript.css);

@import url(http://fonts.googleapis.com/earlyaccess/nanumgothic.css);

@import url(http://fonts.googleapis.com/earlyaccess/hanna.css);

@import url(http://fonts.googleapis.com/earlyaccess/notosanskr.css);

@import url(http://fonts.googleapis.com/earlyaccess/nanummyeongjo.css);

@import
	url(http://fonts.googleapis.com/earlyaccess/nanumgothiccoding.css);
</style>

<!-- 주소api -->
<script
	src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>

<script>
	function sample6_execDaumPostcode() {
		new daum.Postcode(
				{
					oncomplete : function(data) {
						// 팝업에서 검색결과 항목을 클릭했을때 실행할 코드를 작성하는 부분.

						// 각 주소의 노출 규칙에 따라 주소를 조합한다.
						// 내려오는 변수가 값이 없는 경우엔 공백('')값을 가지므로, 이를 참고하여 분기 한다.
						var addr = ''; // 주소 변수
						var extraAddr = ''; // 참고항목 변수

						//사용자가 선택한 주소 타입에 따라 해당 주소 값을 가져온다.
						if (data.userSelectedType === 'R') { // 사용자가 도로명 주소를 선택했을 경우
							addr = data.roadAddress;
						} else { // 사용자가 지번 주소를 선택했을 경우(J)
							addr = data.jibunAddress;
						}

						// 사용자가 선택한 주소가 도로명 타입일때 참고항목을 조합한다.
						if (data.userSelectedType === 'R') {
							// 법정동명이 있을 경우 추가한다. (법정리는 제외)
							// 법정동의 경우 마지막 문자가 "동/로/가"로 끝난다.
							if (data.bname !== ''
									&& /[동|로|가]$/g.test(data.bname)) {
								extraAddr += data.bname;
							}
							// 건물명이 있고, 공동주택일 경우 추가한다.
							if (data.buildingName !== ''
									&& data.apartment === 'Y') {
								extraAddr += (extraAddr !== '' ? ', '
										+ data.buildingName : data.buildingName);
							}
							// 표시할 참고항목이 있을 경우, 괄호까지 추가한 최종 문자열을 만든다.
							if (extraAddr !== '') {
								extraAddr = ' (' + extraAddr + ')';
							}
							// 조합된 참고항목을 해당 필드에 넣는다.
							//document.getElementById("sample6_extraAddress").value = extraAddr;

						} else {
							//document.getElementById("sample6_extraAddress").value = '';
						}

						// 우편번호와 주소 정보를 해당 필드에 넣는다.
						document.getElementById('sample6_postcode').value = data.zonecode;
						document.getElementById("sample6_address").value = addr;
						// 커서를 상세주소 필드로 이동한다.
						document.getElementById("sample6_detailAddress")
								.focus();
					}
				}).open();
	}
</script>

<script>
	// 숫자 단위정리
	function numberWithCommas(x) {
		return x.toString().replace(/\B(?=(\d{3})+(?!\d))/g, ",");
	}

	// 변경된 총 주문금액 재계산
	function calculateTotalPrice(selectElement, rowId) {
		var selectedCount = selectElement.value;
		var originalCount = selectElement.getAttribute("data-original-count");
		if (selectedCount !== originalCount) {
			var priceText = selectElement.getAttribute("data-price");
			var price = parseInt(priceText);
			var newTotalPrice = selectedCount * price;
			document.getElementById(rowId).textContent = numberWithCommas(newTotalPrice)
					+ "원";
			calculateTotalAmount(); // 주문금액 변경 시 총 주문금액 재계산
		}
	}

	// 합계구하기
	function submitForm(pid, selectedCount) {
		var form = document.getElementById("form_" + pid);
		form.elements["count_" + pid].value = selectedCount;
		form.submit();
	}

	// 총 주문금액 계산하기
	function calculateTotalAmount() {
		var totalAmount = 0;
		var totalPriceElements = document.querySelectorAll("[id^='total_']");

		totalPriceElements.forEach(function(element) {
			var priceText = element.textContent.replace(/[^0-9]/g, "");
			var price = parseInt(priceText);

			if (!isNaN(price)) {
				totalAmount += price;
			}
		});

		var totalAmountElement = document.getElementById("totalAmount");
		totalAmountElement.textContent = numberWithCommas(totalAmount) + "원";
	}

	// 페이지 로드 시 총 주문금액 초기화
	window.addEventListener("load", calculateTotalAmount);

	// 서버에서 전달된 JSON 값
	// 체크박스 클릭 시 데이터 채우기
	var serverData = {
		username : "${delivery_View.username}",
		userpostcode : "${delivery_View.userpostcode}",
		useraddress : "${delivery_View.useraddress}",
		userdetailaddress : "${delivery_View.userdetailaddress}",
		usertel : "${delivery_View.usertel}",
		useremail : "${delivery_View.useremail}"
	};

	//페이지 로드 시 자동으로 데이터 비우기
	fillUserInfo(document.getElementById("checkboxId"));

	function fillUserInfo(checkbox) {
		var usernameInput = document.getElementsByName("username")[0];
		var userpostcodeInput = document.getElementsByName("userpostcode")[0];
		var useraddressInput = document.getElementsByName("useraddress")[0];
		var userdetailaddressInput = document.getElementsByName("userdetailaddress")[0];
		var usertelInput1 = document.getElementsByName("phone1")[0];
		var usertelInput2 = document.getElementsByName("phone2")[0];
		var usertelInput3 = document.getElementsByName("phone3")[0];
		var useremailInput = document.getElementsByName("useremail")[0];

		if (checkbox.checked) {
			// 체크박스가 선택된 경우 데이터 채우기
			usernameInput.value = serverData.username;
			userpostcodeInput.value = serverData.userpostcode;
			useraddressInput.value = serverData.useraddress;
			userdetailaddressInput.value = serverData.userdetailaddress;
			usertelInput1.value = serverData.usertel.substring(0, 3);
			usertelInput2.value = serverData.usertel.substring(4, 8);
			usertelInput3.value = serverData.usertel.substring(9);
			useremailInput.value = serverData.useremail;
		} else {
			// 체크박스가 선택되지 않은 경우 데이터 비우기
			usernameInput.value = "";
			userpostcodeInput.value = "";
			useraddressInput.value = "";
			userdetailaddressInput.value = "";
			usertelInput1.value = "";
			usertelInput2.value = "";
			usertelInput3.value = "";
			useremailInput.value = "";
		}
	}

	function submitForm() {
		var form = document.createElement("form");
		form.action = "order.do";
		form.method = "post";

		var pidInputs = document.getElementsByName("pid");
		var countInputs = document.getElementsByName("count");

		for (var i = 0; i < pidInputs.length; i++) {
			var pidInput = document.createElement("input");
			pidInput.type = "hidden";
			pidInput.name = "pid";
			pidInput.value = pidInputs[i].value;
			form.appendChild(pidInput);

			var countInput = document.createElement("input");
			countInput.type = "hidden";
			countInput.name = "count";
			countInput.value = countInputs[i].value;
			form.appendChild(countInput);
		}

		document.body.appendChild(form);
		form.submit();
	}

	function confirmPurchase() {
		var confirmed = confirm("구매하시겠습니까?");
		if (confirmed) {
			alert("구매가 완료되었습니다.");
			submitForm(); // 구매를 확인한 경우에만 submitForm() 함수 호출
		}
	}
</script>

<body>

	<!-- <header>
		<nav>
			<ul>
				<li><a href="A_mainView.do">SHOP</a></li>
				<li><a href="A_ProductView.do">ANJLIFE</a></li>
				<li><a href="A_introduction.jsp">COMMUNITY</a></li>
				<li><a href="A_introduction.jsp">CART</a></li>
			</ul>
		</nav>
	</header> -->

	<main class="main">

		<h1 style="font-family: 'font-family: ' Nanum Pen Script ', cursive;">ORDER</h1>
		<br> <br> <br> <br> <br> <br> <br>
		<br>

	</main>

	<form id="orderForm" method="post">
		<hr width="80%" color="#477a7b" size="2">
		<table border="0">
			<tr>
				<td
					style="height: 10px; text-align: left; border-bottom: 1px solid #E8E8E8;; color: #477a7b;">주문상품<br>
				<br></td>
			</tr>
		</table>
		<c:forEach items="${list}" var="dto">
			<table border="0" style="border-top: 1px solid #E8E8E8;">
				<tr>
					<td style="width: 200px; height: 100px; text-align: left;">image</td>
					<td style="width: 180px; text-align: left;"><span class="pid">${dto.pid}</span><br>
						<span class="pname">${dto.pname}</span><br> <fmt:formatNumber
							value="${dto.pprice}" pattern="#,##0" />원</td>
					<td style="width: 130px;">${dto.count}개</td>
					<td id="total_${dto.pid}" style="width: 100px;"><fmt:formatNumber
							value="${dto.pprice * dto.count}" pattern="#,##0" />원</td>
				</tr>
			</table>

			<input type="hidden" name="count" value="${dto.count}">
			<input type="hidden" name="pid" value="${dto.pid}">
		</c:forEach>

		<table border="0">
			<tr>
				<td style="text-align: left;">총 주문금액 :</td>
				<td style="text-align: right;"><span id="totalAmount"> <fmt:formatNumber
							value="${totalAmount}" pattern="#,##0" />
				</span></td>
			</tr>
		</table>
		<br>

		

	</form>


	<br>
	<br>
	<hr width="80%" color="#477a7b" size="1">

	<!-- 주문자 정보 -->
	<!-- 배송지 정보 -->
	<form>
		<table border="1" id="userDeliveryTable">
    <tr>
        <td style="height: 10px; font-size: 15px; text-align: left; color: #477a7b;">배송정보<br><br></td>
    </tr>
    <tr>
        <td style="height: 10px; font-size: 15px; text-align: left;">배송지 선택
            <input type="checkbox" name="checkbox" style="position: absolute; left: 380px;" onchange="fillUserInfo(this)">
            <label for="checkbox" style="position: absolute; left: 400px;">회원 정보와 동일</label> <br>
        </td>
    </tr>
    <tr>
        <td style="font-size: 15px; order: bottom: 1px solid black; text-align: left;"><p>받으시는 분</p></td>
        <td><input type="text" name="username" size="20" dir="ltr" style="font-size: 15px;"></td>
    </tr>
    <tr>
        <td style="font-size: 15px; text-align: left;"><p>배송지 주소</p></td>
        <td >
            <input type="text" id="sample6_postcode" placeholder="우편번호" name="userpostcode">
            <input type="button" onclick="sample6_execDaumPostcode()" value="우편번호 찾기"><br>
            <input type="text" id="sample6_address" placeholder="주소" name="useraddress"><br>
            <input type="text" id="sample6_detailAddress" placeholder="상세주소" name="userdetailaddress"><br>
            <p style="font-size: 7px; text-align: left;">※상세주소(아파트 동, 호수) 꼭 기재 부탁드립니다.</p>
        </td>
    </tr>
    <tr>
        <td style="font-size: 15px; order: bottom: 1px solid black; text-align: left;"><p>휴대전화</p></td>
        <td>
            <select name="phone1" style="text-align: left;">
                <option value="010">010</option>
                <option value="011">011</option>
                <option value="016">016</option>
                <option value="017">017</option>
                <option value="019">019</option>
            </select>
            -
            <input type="text" maxlength="4" size="4" name="phone2" style="text-align: left;">
            -
            <input type="text" maxlength="4" size="4" name="phone3" style="text-align: left;">
        </td>
    </tr>
    <tr>
        <td style="font-size: 15px; text-align: left;"><p>이메일</p></td>
        <td><input type="text" name="useremail" size="50" style="font-size: 15px;"><br>
        <p style="font-size: 7px; text-align: left;">※이메일을 통해 주문처리과정을 보내드립니다.<br></p>
        <p style="font-size: 7px; text-align: left;">※이메일 주소란에는 반드시 수신가능한 이메일주소를 입력해 주세요</p>
        </td>
    </tr>
</table>
		<br> <br>
	</form>
	
	
	
	
	
	
	
	<button type="button" class="submit-button"
			style="display: block; position: absolute; right: 305px; width: 120px; height: 30px; background-color: black; color: white;"
			onclick="confirmPurchase()">주문하기</button>
			
			<br> <br><br> <br>
</body>
</html>