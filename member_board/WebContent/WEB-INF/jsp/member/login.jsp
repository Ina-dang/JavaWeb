<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap/5.0.1/css/bootstrap.min.css" integrity="sha512-Ez0cGzNzHR1tYAv56860NLspgUGuQw16GiOOp/I2LuTmpSK9xDXlgJz3XN4cnpXWDmkNBKXR/VDMTCnAaEooxA==" crossorigin="anonymous" referrerpolicy="no-referrer" />
<script src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap/5.0.1/js/bootstrap.min.js" integrity="sha512-EKWWs1ZcA2ZY9lbLISPz8aGR2+L7JVYqBAYTq5AXgBkSjRSuQEGqWx8R1zAX16KdXPaCjOCaKE8MCpU0wcHlHA==" crossorigin="anonymous" referrerpolicy="no-referrer"></script>
<script>

	window.onload = function(){
		document.frm.onsubmit = function(){
 			var id = document.frm.id;
 			var pwd = document.frm.pwd;
 			if(!id.value){
				alert("아이디를 8자리 이상 입력하세요")
				id.focus();
				return false; //서식전송을 하지 않는다
			}
			else if(!pwd.value){
				alert("비밀번호를 4자리 이상 입력하세요")
				pwd.focus();
				return false;
			}
		}
	}

</script>
</head>
<body>
	<div class="container">
		<div class="col-6 mx-auto">
		<h1>로그인</h1>
		<form class="form" name="frm" method="post" >
			<label for="userId" class="form-label">아이디</label>
			<input type = "text" name="id" id="userId" class="form-control">
			<label for="userPw" class="form-label">비밀번호</label>
			<input type = "password" name="pwd" id="userPw" class="form-control">
		
		<div class="d-grid">
			<button type="submit" class="btn btn-primary btn-block my-3">로그인</button>
		</div>
		<div class="d-grid">
			<button type="reset" class="btn btn-danger btn-block right">다시입력</button>
		</div>
<!-- 		<div class="d-grid">
			<input type="hidden" name="command" value="addMember">
		</div> -->
		</form>
		</div>
	</div>

</body>
</html>