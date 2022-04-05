<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<jsp:include page="../common/head.jsp"/>
<script>
	window.onload = function(){
		document.frm.onsubmit = function(){
			var id = document.frm.id;
			var pw = document.frm.pw;
			if(!id.value){
				alert("아이디를 8자리 이상 입력하세요");
				id.focus();
				return false; //서식 전송을 하지 않는다
			}
			else if(!pw.value){
				alert("비밀번호를 4자리 이상 입력하세요")
				pw.focus();
				return false;
			}
		}
	}

</script>
</head>
<body>
    <jsp:include page="../common/nav.jsp"/>
	 <!-- 로그인 -->
	 <div class="login">
	    <h3><label for="uname" class="signup1">Maple Story에 로그인하세요</label></h3>
	    <div>
		    <form class="form" name="frm" method="post">
		        <div>
		        	<ul>
			            <li><input type="text" class="input" name="id" id="userId" placeholder="아이디"/></li>
		            </ul>
		        </div>
		        <div>
		        	<ul>
		            	<li><input type="password" class="input" name="pw" id="userPw" placeholder="비밀번호"/></li>
		            </ul>
		        </div>
			    <div>
			   		<button class="btn btn-dark float-end">로그인</button>
			    </div>
	   		</form>
   		</div>
</div>
	<jsp:include page="../common/footer.jsp"/>
</body>
</html>