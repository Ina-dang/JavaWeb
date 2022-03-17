<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
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

            if(!id.value){r
                alert("아이디를 정확하게 입력하세요")
                id.focus();
                return false; 
            }
            else if(!pw.value){
                alert("비밀번호를 정확하게 입력하세요")
                pw.focus();
                return false;
            }
        }
    }
</script>
</head>
<body>
	<div class="container">
        <div class="col-6 mx-auto">
            <h1>Sign in</h1>
            <form class="form" name="frm" method="post">
                <label for="userId" class="form-lable">id</label>
                <input type="text" name="id" id="userid" class="form-control">
                <label for="userPw" class="form-lable">password</label>
                <input type="password" name="pw" id="userPw" class="form-control">
            <div class="col mx-auto">
                <button type="submit" class="btn btn-primary my-3">로그인</button>
                <button type="reset" class="btn btn-danger">다시입력</button>
            </div>
            </form>
        </div>
    </div>
	
</body>
</html>