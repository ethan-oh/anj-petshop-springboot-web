<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<div>
	
	<form action="">
	제품코드 <input type="text" name="pid"> 제품명<input type="text" name="pname"><br/>
	카테고리 <select name="pcategory">
	<option value="food">food</option>
	<option value="living">living</option>
	<option value="clean">clean</option>
	</select>
	상품가격<input type="number" name="pprice"> 입고량<input type="number" name="pstock"><br/>
	<img id="preview"><img id="preview2"><img id="preview3"><br/>
	이미지 선택<input type="file" name="pthumbnail" onchange="readURL(this)"> 
	2번째<input type="file" name="pthumbnail2" onchange="readURL2(this)">
	 3번<input type="file" name="pthumbnail3" onchange="readURL3(this)">
	노출여부<select name="available">
	<option value="1">Y</option>
	<option value="0">N</option>
	</select>
	<br/>
	<input type="submit" value="상품입력">

	</form>
</div>


</body>
<script type="text/javascript">
function readURL(input) {
	  if (input.files && input.files[0]) {
	    var reader = new FileReader();
	    reader.onload = function(e) {
	      document.getElementById('preview').src = e.target.result;
	    };
	    reader.readAsDataURL(input.files[0]);
	  } else {
	    document.getElementById('preview').src = "";
	  }
	}
function readURL2(input) {
	  if (input.files && input.files[0]) {
	    var reader = new FileReader();
	    reader.onload = function(e) {
	      document.getElementById('preview2').src = e.target.result;
	    };
	    reader.readAsDataURL(input.files[0]);
	  } else {
	    document.getElementById('preview2').src = "";
	  }
	}
function readURL3(input) {
	  if (input.files && input.files[0]) {
	    var reader = new FileReader();
	    reader.onload = function(e) {
	      document.getElementById('preview3').src = e.target.result;
	    };
	    reader.readAsDataURL(input.files[0]);
	  } else {
	    document.getElementById('preview3').src = "";
	  }
	}
</script>
</html>