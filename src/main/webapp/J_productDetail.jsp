<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>${productDetail.pname }</title> 					<!--  상품 페이지에서 선택한 제품의 상세 페이지입니다. -->
<link rel="stylesheet" href="J_productDetail.css">

</head>
<body>
<%
	/* 상품 리스트에서 사용자가 선택한 제품 아이디 받아오기 */
	String pid = request.getParameter("pid");
	String USERID = (String) session.getAttribute("USERID");
	
%>
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
						<br/>
						<br/><br/>
	<main class="main">
		<div class="top-section">
			<div class="top-left"> 			<!-- 이미지 뜨는 섹션 -->
				<div class="slide">
					<img alt="제품 이미지를 준비중 입니다." src="${productDetail.pthumbnail }">
				</div>
				<div class="slide">
					<img alt="제품 이미지를 준비중 입니다." src="${productDetail.pth2 }">
				</div>
				<div class="slide">
					<img alt="제품 이미지를 준비중 입니다." src="${productDetail.pth3 }">
				</div>
			</div>
			<div class="top-right">			<!-- 상품 상세 정보 뜨는 섹션 -->
				<h1 style="text-align: center"> ${productDetail.pname }</h1> 
				<h2 style="text-align: center"><fmt:formatNumber value="${productDetail.pprice }" pattern="#,###" /> 원</h2><br/>
				<h5 style="text-align: center">최소 주문 수량 1개 이상 <br/>
				배송비 무료 </h5> <hr>
				
				수량을 선택해 주세요. <br/><br/>
				<input type="button" onclick="clickMinus()" value=" - " style="font-size: 23px; color: #477A7B; background-color: #DFE9E8; border: none; ">
				<input type="text" name="qty" size="2" id="quantity" value="1" min="0" max="${productDetail.pstock }" onchange="updateQuantity(this.value)" oninput="calcPrice(this.value)" style="text-align: center; font-size: 20px;border: none;">
				<input type="button" onclick="clickPlus()" value=" + " style="font-size: 23px; color: #477A7B; background-color: #DFE9E8; border: none; ">
				<input type="button" onclick="resetSelection()" value="♺" style="font-size: 19px; color: #477A7B; background-color: #DFE9E8; border: none; ">
				<h3 style="text-align: right;">TOTAL </h3>
				<h3 style="text-align: right;"> <span id="resultPrice"><fmt:formatNumber value="${productDetail.pprice }" pattern="#,###" /> 원</span></h3>
				<h5 style="text-align: right;"> <span id="resultRewards"> (적립금 : + ${productDetail.pprice * 0.01 } 원) </span></h5>
				<div style="display: flex;">
					<form action="<!-- 태영이 장바구니 넘기기.do -->" name="basket" method="get" style="display: inline; width: 50%;">
						<input type="hidden" name="userid" value="<%=USERID%>">
						<input type="hidden" name="pid" value="<%=pid%>">
						<input type="hidden" name="qty">
						<input type="button" value="장바구니 담기" onclick="sendToCart()" style="font-size: 25px; color: #477A7B; background-color: #DFE9E8; border: none; width: 100%; height: 60px">
					</form>
					<form action="<!-- 태영이 구매.do -->" name="purchase" method="get" style="display: inline; width: 50%;">
						<input type="button" value="즉시 구매하기" onclick="sendToPay()" style="font-size: 25px; color: white; background-color: #477A7B; border: none; width: 100%; height: 60px">
					</form>
				</div>
			</div>
		</div>
		<div class="bottom-section-wrapper">
			<div class="bottom-section">
				<img alt="제품 사진을 준비 중 입니다." src="${pdExplain.p_filename }"><br/>
				<img alt="제품 사진을 준비 중 입니다." src="${pdExplain.p_filename2 }"><br/>
				<img alt="제품 사진을 준비 중 입니다." src="${pdExplain.p_filename3 }"><br/>
				<img alt="제품 사진을 준비 중 입니다." src="${pdExplain.p_filename4 }"><br/>
				<img alt="제품 사진을 준비 중 입니다." src="${pdExplain.p_filename5 }"><br/>
				
			</div>
			
			<!-- 고정된 구매하기 버튼 --><%-- 
			<form action="<!-- 태영이 구매.do -->" name="purchase" method="get" class="fixedPurchaseBtn">
				<input type="button" value="구매하기" onclick="sendToPay()" style="font-size: 15px; color: white; background-color: #477A7B; border: none; width: 100%; height: 40px">
			</form> --%>
		</div>
		
		
	</main>
	<!-- <footer>
		<p>
			<span>TEL. 1877-3228<br/>
			E-MAIL. arrr@dongwon.com<br/>
			ADDRESS. 10th floor, 68, Mabangro, Seocho-gu, Seoul, 
			Republic of Korea, 06775<br/>
			BUSINESS NO. 703-88-01843</span>
		</p>
			여기에다가는 마지막에 추가하는것으로 
	</footer> -->

</body>
<script type="text/javascript" src="J_productDetail.js"></script>
<script type="text/javascript">
function clickPlus() {		/* 수량 파트 : (+) 클릭할 때 */
  	var tfQuantity = document.getElementById('quantity');		// 수량 텍스트 필드에 입력되어있는 값(문자).
    var currentValue = parseInt(tfQuantity.value);				// 현재 수량 텍스트 필드에 입력되어있는 값(정수).
    if (currentValue < ${productDetail.pstock}) {
    	tfQuantity.value = currentValue + 1;						// 현재 텍스트 필드 값에서 1 더한 값을 현재값으로 할당. 
	} else {
    	alert("죄송합니다. 주문 가능한 수량을 초과하였습니다. \n 해당 제품은 " + ${productDetail.pstock} + "개 까지만 주문 가능합니다.");
    }
	calcPrice();
	calcRewards();
  }
  
function calcPrice() {
	  var quantityField = document.getElementById('quantity');
	  var quantity = parseInt(quantityField.value);
	  var price = parseInt(${productDetail.pprice});
	  var result = quantity * price;
	  var resultElement = document.getElementById('resultPrice');
	  resultElement.textContent = result.toLocaleString() + '원';
	}

function calcRewards() {
	var quantityField = document.getElementById('quantity');
	  var quantity = parseInt(quantityField.value);
	  var price = parseInt(${productDetail.pprice});
	  var result = quantity * price * 0.01;
	  var resultElement = document.getElementById('resultRewards');
	  resultElement.textContent = '(적립금 : + ' + result.toLocaleString() + '원)';
	}
	
function resetSelection() {
	  document.getElementById("quantity").value = "1";
	  var resultElement = document.getElementById('resultPrice');
	  var resultElement1 = document.getElementById('resultRewards');
	    var price = parseInt(${productDetail.pprice});
	    var result = 1 * price; // 초기 수량(1)과 가격을 곱하여 초기 결과 계산
	    var result1 = result * 0.01; // 초기 결과에 대한 적립금 계산
	    resultElement.textContent = result.toLocaleString() + '원';
	  resultElement1.textContent = '(적립금 : + ' + result1.toLocaleString() + '원)';
	  
}
	
	function sendToCart() {			/* 사용자가 선택한 수량 넘겨주기 */
		const qty = document.selectedOption.qty.value;
		document.basket.qty.value = qty;
		document.basket.submit();
	}
	
	function sendToPay() { 			/* 사용자가 선택한 수량 넘겨주기 */
		const qty = document.selectedOption.qty.value;
		document.basket.qty.value = qty;
		document.basket.submit();
		
	}

	/* 사진 넘기는 효과주기 */
	const slides = document.querySelectorAll('.slide');
	let currentSlide = 0;

	function showSlide(n) {
	  slides[currentSlide].classList.remove('active');
	  slides[n].classList.add('active');
	  currentSlide = n;
	}

	function nextSlide() {
	  let next = currentSlide + 1;
	  if (next >= slides.length) {
	    next = 0;
	  }
	  showSlide(next);
	}

	function prevSlide() {
		let prev = currentSlide - 1;
		if (prev < 0) {
			prev = slides.length - 1;
		}
		showSlide(prev);
	}
	
	slides[currentSlide].classList.add('active');
	setInterval(nextSlide, 5000); // 5초마다 다음 슬라이드로 이동

	for (let i = 0; i < slides.length; i++) {
	  slides[i].addEventListener('click', (event) => {
		  const slideWidth = slides[i].offsetWidth;
		  const clickX = event.offsetX;
		  
		  if (clickX > slideWidth / 2) {
		    nextSlide();
		  } else {
			  prevSlide();
		  }
	  });
	}

	
	
	
	
	
	
	
	
</script>
</html>