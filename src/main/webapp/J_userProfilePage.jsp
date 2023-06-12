<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>어제보다 더 나은, ANJ Shop</title>
<link rel="stylesheet" href="J_userProfilePage.css">
	<!-- 주소api -->
	<script src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
	
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
		document.getElementById("sample6_detailAddress").placeholder = ""; // 상세주소 필드 초기화
		document.getElementById("sample6_detailAddress").focus(); // 상세주소 필드에 포커스를 준다
	}
	
	/* 이메일 입력받기 */
	function selectedEmail(){
		var emailDomain = document.getElementById("domainSelect");
		var customDomain = document.getElementById("customDomain");
		
		if(emailDomain.value === "custom") {
			customDomain.style.display = "inline-block" ;
		} else {
			customDomain.style.display = "none";
		} 
	}
	
	
</script>
		
	
</head>
<body>
	<header>
	
	</header>



	<main class="main">
	
		<h1 id="pageTitle">회원 정보 수정</h1><br/>
		<div id="requiredInfo">
			<p style="font-size: 20px; text-align: left;">기본 정보</p>
			<p style="font-size: 15px; text-align: right;">* 표시는 필수 입력 사항</p>
		</div>
		
		<form action="j_userUpdate.do">
		<hr>
		<table id="userInfoUpdate">
			<tr>
				<td id="infoUpdate" style="background-color: #FFFFF0;"> 아이디 <sup>*</sup> &nbsp;&nbsp;</td>
				<td>&nbsp;&nbsp;<input type="text" name="userid" id="tfUserid" value="${userView.userid }" maxlength="15" readonly="readonly" disabled="disabled" >&nbsp;&nbsp;(영문소문자/숫자,유효성 검사 재원오빠랑 확인! )</td>
			</tr>
			<tr>
				<td id="infoUpdate" style="background-color: #FFFFF0;"> 비밀번호 <sup>*</sup> &nbsp;&nbsp; </td>
				<td>&nbsp;&nbsp;<input type="password" name="userpasswd" id="pfUserpw" maxlength="30">&nbsp;&nbsp; (비밀번호 유효성 조건 주절주절)</td>
			</tr>
			<tr>
				<td id="infoUpdate"> 비밀번호 확인 <sup>*</sup> &nbsp;&nbsp; </td>
				<td>&nbsp;&nbsp;<input type="password" id="pfUserpw" maxlength="30">&nbsp;&nbsp; </td>
			</tr>
			<tr>
				<td id="infoUpdate"> 이름 <sup>*</sup> &nbsp;&nbsp;</td>
				<td>&nbsp;&nbsp;<input type="text" id="pfUserpw" value="${userView.username }" maxlength="15" readonly="readonly" disabled="disabled">&nbsp;&nbsp;(영문소문자/숫자,유효성 검사 재원오빠랑 확인! )</td>
			</tr>
			<tr>
				<td id="infoUpdate"> 배송지 주소  &nbsp;&nbsp;</td>
				<td style="padding: 18px;">
					<input type="text" id="sample6_postcode" placeholder="${userView.userpostcode }" disabled="disabled" name="userpostcode">
					<input type="button" onclick="sample6_execDaumPostcode()" value="우편번호 찾기"><br>
					<input type="text" id="sample6_address" placeholder="${userView.useraddress }" disabled="disabled" name="useraddress">
					 기본 주소<br> 
					<input type="text"	id="sample6_detailAddress" placeholder="${userView.userdetailaddress }" name="userdetailaddress">
					 나머지 주소 (선택 입력 가능)<br>
					<p style="font-size: 12px; text-align: left;"> ※ 상세주소(아파트 동, 호수) 꼭 기재 부탁드립니다.</p> 
				</td>
			</tr>
			<tr>
				<td id="infoUpdate"> 휴대전화 <sup>*</sup> &nbsp;&nbsp;</td>
				<td> &nbsp;&nbsp;
					<select name="phone1" id="tfUserPhone" >
						<option value="010">010</option>
						<option value="011">011</option>
						<option value="016">016</option>
						<option value="017">017</option>
						<option value="018">018</option>
						<option value="019">019</option>
					</select> - <input type="text" maxlength="4" size="4" name="phone2" id="tfUserPhone">
					- <input type="text" maxlength="4" size="4" name="phone3" id="tfUserPhone">
				</td>
			</tr>
			<tr>
				<td id="infoUpdate"> 이메일 <sup>*</sup> &nbsp;&nbsp;</td>
				<td>
					<input type="text" name="useremail" id="inputEmail">
					@<input type="text" name="userdomain" id="customDomain" placeholder="직접 입력해주세요" style="display: none;">
					<select id="domainSelect" name="userdomain" onchange="selectedEmail()">
						<option value="">-- 선택하세요 -- </option>
						<option value="naver.com">naver.com</option>
						<option value="daum.net">daum.net</option>
						<option value="gmail.com">gmail.com</option>
						<option value="hotmail.com">hotmail.com</option>
						<option value="custom" >직접입력</option>
					</select>	
				</td>
			</tr>
		</table><br/>
		<input type="submit" id="btnUpdateInfo" value="회원정보 수정">&nbsp;&nbsp;
		</form>
		
		<form action="j_userUpdateCancel.do"> 		<!-- 취소버튼 클릭시 메인페이지로 보내기 -->
			<input type="submit" id="btnCancel" value="취소">  <br/>
		</form>
		
		<!-- 확인창 메시지가 안떠ㅠㅠㅠ그냥 탈퇴되어버린다. -->
		<form id="deleteForm" action="j_userDelete.do">
			<input type="submit" id="btnDeleteInfo" value="회원 탈퇴">
		</form>
	
	
	
	
	</main>
	
	
	
	<footer>
	
	</footer>

</body>
<script type="text/javascript">
/* 회원 탈퇴 클릭시 경고창 띄우기 */
document.getElementById('deleteForm').addEventListener('submit', function(e) {
 e.preventDefault(); // 버튼의 기본 동작(폼 전송)을 막습니다.
 
 var confirmation = confirm('정말로 회원을 탈퇴하시겠습니까?');
 if (confirmation === true) {
   // 확인 버튼 클릭 시
   alert('회원 탈퇴가 정상적으로 처리되었습니다.');
	this.submit();
 } else {
   // 취소 버튼 클릭 시
   alert('회원 탈퇴가 취소되었습니다.');
   return false;
 }
});
</script>
</html>