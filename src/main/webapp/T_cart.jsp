<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<!DOCTYPE html>
<html>
<link rel="stylesheet" href="A_heardCss.css">
<link rel="stylesheet" href="T_cartCss.css">

<head>
<meta charset="utf-8">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>장바구니</title>
</head>

<script>
	/* // 발자국 찍기
	src="footprint.js" */
	
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
        document.getElementById(rowId).value = newTotalPrice;
      }
    }

    // 합계구하기
    function submitForm(pid, selectedCount) {
      var form = document.getElementById("form_" + pid);
      form.elements["count_" + pid].value = selectedCount;
      form.submit();
    }

 // 체크박스 체크 여부에 따라 총 주문금액 계산
    function calculateTotalAmount() {
      var totalAmount = 0;
      var totalPriceElements = document.querySelectorAll("[id^='totalPrice_']");
      var checkboxes = document.getElementsByName('check'); // 체크박스 요소 가져오기

      totalPriceElements.forEach(function(element, index) {
        var priceText = element.textContent.replace(/[^0-9]/g, "");
        var price = parseInt(priceText);
        var checkbox = checkboxes[index]; // 해당 인덱스의 체크박스 가져오기

        if (checkbox.checked) { // 체크박스가 체크된 경우에만 계산
          if (!isNaN(price)) {
            totalAmount += price;
          }
        }
      });

      var totalAmountElement = document.getElementById("totalAmount");
      totalAmountElement.textContent = numberWithCommas(totalAmount);
    }

 // 페이지 로드 시 총 주문금액 초기화
    window.addEventListener("load", calculateTotalAmount);

    // 변경 여부 확인 후 폼 제출
    function confirmSubmitForm(pid, selectedCount) {
      var confirmation = confirm("변경하시겠습니까?");
      if (confirmation) {
        calculateTotalAmount(); // 확인 버튼을 누를 때 총 주문 금액 계산
        submitForm(pid, selectedCount);
      }
    }

    function deleteSelectedItems() {
      var checkboxes = document.getElementsByName('check');
      var selectedItems = [];

      // 선택된 체크박스의 값을 배열에 추가
      for (var i = 0; i < checkboxes.length; i++) {
        if (checkboxes[i].checked) {
          var formId = checkboxes[i].parentNode.parentNode.parentNode.parentNode.id;
          var pid = formId.substring(formId.indexOf('_') + 1);
          selectedItems.push(pid);
        }
      }

      // 선택된 항목이 있을 경우 서버로 삭제 요청 전송
      if (selectedItems.length > 0) {
        var confirmation = confirm('선택한 항목을 삭제하시겠습니까?');
        if (confirmation) {
          for (var j = 0; j < selectedItems.length; j++) {
            deleteItem(selectedItems[j]);
          }
        }
      }
    }

    function deleteItem(itemId) {
      var form = document.createElement('form');
      form.method = 'POST';
      form.action = 'delete.do';

      var input = document.createElement('input');
      input.type = 'hidden';
      input.name = 'seq';
      input.value = itemId;

      form.appendChild(input);

      document.body.appendChild(form);
      form.submit();
    }
    
 // 주문하기 버튼 클릭 시 선택된 상품들의 폼을 제출
    function submitSelectedItems() {
      var checkboxes = document.getElementsByName('check');
      var selectedItems = [];

      // 선택된 체크박스의 값을 배열에 추가
      for (var i = 0; i < checkboxes.length; i++) {
        if (checkboxes[i].checked) {
          var formId = checkboxes[i].parentNode.parentNode.parentNode.parentNode.id;
          var pid = formId.substring(formId.indexOf('_') + 1);
          selectedItems.push(pid);
        }
      }

      // 선택된 항목이 있을 경우 폼 제출
      if (selectedItems.length > 0) {
        var form = document.createElement('form');
        form.method = 'POST';
        form.action = 'purchase.do';

        // 선택된 상품들의 pid 값을 폼에 추가
        for (var j = 0; j < selectedItems.length; j++) {
          var input = document.createElement('input');
          input.type = 'hidden';
          input.name = 'pid';
          input.value = selectedItems[j];
          form.appendChild(input);
        }

        document.body.appendChild(form);
        form.submit();
      }
    }
 
	 
</script>

<body>

	<!-- <header>
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
				<br/><br/><br/><br/><br/>
				<img src="image_08.png" alt="My Image"> -->


	<main class="main">
		
		<h1 style="font-family: 'font-family: ' Nanum Pen Script ', cursive; position: absolute;
        top: 40%;
    	left: 50%;
    	transform: translate(-50%, -50%);
        height: 300px; /* 원하는 높이 값으로 변경 */  
        color: #477a7b;   /* 글씨색깔 */" >CART</h1>
		<br>
		
	


		<!-- <hr width="85%" color=#E8E8E8 size="2" style="position: absolute; top: 25%;"> -->

		<!-- 	<table border="0" style="position: absolute; height: 20px; top: 30%; border-top: 2px solid #E8E8E8; border-bottom: 2px solid #E8E8E8;">
    <tr>
      <th style="width: 500px;">상품정보</th>
      <th style="width: 130px;">수량</th>
      <th style="width: 100px;">주문금액</th>
    </tr>
  </table> -->

		<!-- border-bottom: 2px solid #E8E8E8; -->
	</main>
	<c:forEach items="${list}" var="dto">
		<form id="form_${dto.pid}" action="T_cart.do?pid=${dto.pid}"
			method="get">
			<table style="border-top: 2px solid #E8E8E8;">
				<tr>
					<td colspan="2" style="width: 10px;"><input type="checkbox" name="check" checked="checked" onchange="calculateTotalAmount()"></td>
					<!-- 이미지 -->
					<td style="width: 200px; height: 100px; text-align: left;">image</td>
					<!-- 상품정보 -->
					<td style="width: 300px; height: 100px; text-align: left;"><span
						class="pid" style="font-size: 25px;">${dto.pid}</span><br> <span
						class="pname" style="font-size: 25px;">${dto.pname}</span><br>
						<fmt:formatNumber value="${dto.pprice}" pattern="#,##0" />원<br>
					</td>
					<!-- 수량 -->
					 <td style="width: 130px; text-align: center;">
				      <select name="count_${dto.pid}" data-original-count="${dto.count}" data-price="${dto.pprice}" onchange="calculateTotalPrice(this, '${dto.pid}')">
				        <c:forEach begin="1" end="10" var="i">
				          <option value="${i}" ${i eq dto.count ? 'selected' : ''}>${i}</option>
				        </c:forEach>
				      </select>
				    </td>
					<!-- 주문금액 -->
					<td id="total_${dto.pid}" style="width: 100px;">
			            <span id="totalPrice_${dto.pid}" data-total-price="${dto.pprice * dto.count}">
			              <fmt:formatNumber value="${dto.pprice * dto.count}" pattern="#,##0"/>
			            </span>원
			          </td>
					<!-- 변경 버튼 -->
					<td style="width: 100px; text-align: center;"><a href="#"
						onclick="confirmSubmitForm('${dto.pid}', document.getElementsByName('count_${dto.pid}')[0].value)">변경</a>
						<input type="hidden" name="pid" value="${dto.pid}" /></td>
				</tr>
			</table>
		</form>
	</c:forEach>


	<!--  -->
	<form action="purchase.do" method="get">
		<table>
			<tr>
				<td colspan="2"
					style="text-align: right; border-top: 2px solid #E8E8E8;"><br>
				<input type="button"  value="   삭제    "
				    style="width: 80px; height: 30px; color: #477A7B; border-color: #477A7B;"
				    onclick="deleteSelectedItems()">
				<input type="submit" value="주문하기"
					style="width: 80px; height: 30px; background-color: #477A7B; color: white; border-color: #477A7B;"
					class="submit-button"><br>
				<br>
				<br></td>
			</tr>
			<tr>
				<td
					style="width: 900px; text-align: left; border-bottom: 2px solid #E8E8E8;">주문금액
					<br>배송비
				</td>
				<td style="width: 150px; text-align: right; border-bottom: 2px solid #E8E8E8;">
					<span id="totalAmount"> <fmt:formatNumber
							value="${totalAmount}" pattern="#,##0" />
				</span>원<br>0원
				</td>
			</tr>
			<tr>
				<td style="width: 600px; text-align: left;">합계</td>
				<td style="width: 150px; text-align: right;">
				  <span id="totalAmount"> <fmt:formatNumber
							value="${totalAmount}" pattern="#,##0" />
				</span>원
				</td>
			</tr>
		</table>
	</form> 
			  





	 <footer>
            <ul>
                <li><a href="#">Brand Story</a></li>
                <li><a href="#">서비스이용약관</a></li>
                <li><a href="#">개인정보처리방침</a></li>
                <li><a href="#">전자금융거래약관</a></li>
            </ul>
            <div>
                <p><img src="LOGO.png" alt="푸터로고"></p>
                <p>
                    <strong>Corporation ANJ.industry</strong>
                    <br>
                    Gangnam-gu, Seoul (Yeoksam-dong The Joy Computer Academy)
                    <br>
                    CEO: Ahn Jae-won
                    <br>   
					Business registration number: 240-81-87676 Business information confirmation
                    <br>
                    Mail-order business report: Gangnam 10238 Fax: 02-000-1234
                </p>
                <p>
                    <strong>customer service center</strong>
                    <br>
                    Tel : Representative number 1234-5678 (Weekdays 09:00~18:00)
                    <br>
                    Dedicated to the future: 1522-5700 (365 days 09:00-18:00)
                    <br>
                    Gangnam-gu, Seoul (Yeoksam-dong The Joy Computer Academy)
                    <br>
                    Fax : 02-000-1234 | Mail : ajw0376@gmail.com
                    <br>
                </p>
            </div>
        </footer>
</body>
</html>





