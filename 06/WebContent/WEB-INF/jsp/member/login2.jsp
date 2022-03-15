<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script>
    //유효성 검증 예정
    window.onload = function(){
    	//객체.객체.메서드 >> 이벤트바인딩
        document.frm.onsubmit = function(){
            console.log(document.frm.id.value);
            console.log(document.frm.pw.value);
            console.log(document.frm.pwChk.value);
    		if(document.frm.id.value.length < 5){
    			alert("아이디를 입력하세요");
                return false; //서식전송을 하지 않는다
    		}
            else if(document.frm.pw.value !== document.frm.pwChk.value){
                alert("비밀번호를 입력하세요");
                return false;
            }
        }
    }
</script>
</head>
<body> 
    <!-- 다 확인 후에 포스트로 -->
	<form name="frm" method="post">
        <p>아이디 : <input name="id"></p>
        <p>비밀번호 : <input type="password" name="pw"></p>
        <p>비밀번호 확인 : <input type="password" name="pwChk"></p>
        <button>login</button>
    </form>
</body>
</html>