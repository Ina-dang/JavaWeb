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
/*  			console.log(document.frmMember.id.value);
			console.log(document.frmMember.pw.value);
			console.log(document.frmMember.name.value);
			console.log(document.frmMember.email.value); */
		
 			var id = document.frm.id;
 			var pwd = document.frm.pwd;
 			var name = document.frm.name;
 			var email = document.frm.email;
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
			else if(!name.value){
				alert("이름은 필수입니다.")
				name.focus();
				return false;
			}
			else if(!email.value){
				alert("이메일은 필수입니다.")
				email.focus();
				return false;
			}
		}
	}

</script>
</head>
<body>
	<div class="container">
		<div class="col-6 mx-auto">
		<h1>회원 가입창</h1>
		<form class="form" name="frm" method="post" >
			<label for="userId" class="form-label">아이디</label>
			<input type = "text" name="id" id="userId" class="form-control">
			<label for="userPw" class="form-label">비밀번호</label>
			<input type = "password" name="pwd" id="userPw" class="form-control">
			<label for="name" class="form-label">이름</label>
			<input type = "text" name="name" id="name" class="form-control">
			<label for="email" class="form-label">이메일</label>
			<input type = "email" name="email" id="email" class="form-control">
		
		<div class="d-grid">
			<button type="submit" class="btn btn-primary btn-block my-3">가입하기</button>
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