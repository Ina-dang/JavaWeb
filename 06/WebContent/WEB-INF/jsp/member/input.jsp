<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap/5.0.1/css/bootstrap.min.css" integrity="sha512-Ez0cGzNzHR1tYAv56860NLspgUGuQw16GiOOp/I2LuTmpSK9xDXlgJz3XN4cnpXWDmkNBKXR/VDMTCnAaEooxA==" crossorigin="anonymous" referrerpolicy="no-referrer" />
﻿<script src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap/5.0.1/js/bootstrap.min.js" integrity="sha512-EKWWs1ZcA2ZY9lbLISPz8aGR2+L7JVYqBAYTq5AXgBkSjRSuQEGqWx8R1zAX16KdXPaCjOCaKE8MCpU0wcHlHA==" crossorigin="anonymous" referrerpolicy="no-referrer"></script>﻿
</head>
<body>

<div class="container">
	<div class="col-5 mx-auto">
	
		<form name="frmInput" method="post" action="input">
		
		<label for="userId" class="form-label">아이디</label>
		<input type="text" name="user_id" class="form-control" placeholder="아이디를 입력하세요">
		<label for="userPw" class="form-label">비밀번호</label>
		<input type="password" name="user_pw" class="form-control " placeholder="비밀번호를 입력하세요">
		
		<label class="form-check-label"><input class="form-check-input" type="checkbox" name="subject" value="java" checked > 자바</label>
		<label class="form-check-label"><input class="form-check-input" type="checkbox" name="subject" value="C언어" checked > C언어</label>  
		<label class="form-check-label"><input class="form-check-input" type="checkbox" name="subject" value="JSP" checked> JSP</label>
		<label class="form-check-label"><input class="form-check-input" type="checkbox" name="subject" value="안드로이드" checked > 안드로이드</label>
		
		<div class="d-grid">
		<button class="btn btn-outline-primary" >로그인</button>
		</div>
		
		<div class="d-grid">
		<button type="reset" class="btn btn-outline-danger">다시입력</button>
		</div>
		
		</form>
		
	</div>
</div>

</body>
</html>