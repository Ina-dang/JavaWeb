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
            var pw = document.frm.pw;
            var name = document.frm.name;
            var nick = document.frm.nick;
            var email = document.frm.email;

            if(!id.value){
                alert("아이디를 정확하게 입력하세요")
                id.focus();
                return false; 
            }
            else if(!pw.value){
                alert("비밀번호를 정확하게 입력하세요")
                pw.focus();
                return false;
            }
            else if(!name.value){
                alert("이름를 정확하게 입력하세요")
                name.focus();
                return false;
            }
            else if(!nick.value){
                alert("닉네임을 정확하게 입력하세요")
                nick.focus();
                return false;
            }
            else if(!email.value){
                alert("이메일을 정확하게 입력하세요")
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
            <h1>Sign up</h1>
            <form class="form" name="frm" method="post">
                <label for="userId" class="form-lable">id</label>
                <input type="text" name="id" id="userid" class="form-control">
                <label for="userPw" class="form-lable">password</label>
                <input type="password" name="pw" id="userPw" class="form-control">
                <label for="name" class="form-lable">name</label>
                <input type="text" name="name" id="name" class="form-control">
                <label for="nick" class="form-lable">nickname</label>
                <input type="text" name="nick" id="nick" class="form-control">
                <label for="email" class="form-lable">email</label>
                <input type="email" name="email" id="email" class="form-control">
            <div class="col mx-auto">
                <button type="submit" class="btn btn-primary my-3">회원가입</button>
                <button type="reset" class="btn btn-danger">다시입력</button>
            </div>
            </form>
        </div>
    </div>
</body>
</html>