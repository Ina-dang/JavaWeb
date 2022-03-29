<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<jsp:include page="../common/head.jsp"/>        
</head>
<body>
    <jsp:include page="../common/nav.jsp"/>
    <!-- 회원가입 -->
    <div class="signup">
        <h3><label for="uname" >Maple Story ID 생성</label></h3>
        <h6>하나의 Maple Strory 아이디로 모든 커뮤니티 서비스를 이용할 수 있습니다.</h6>
        <form action="file:///G:/%EB%82%B4%20%EB%93%9C%EB%9D%BC%EC%9D%B4%EB%B8%8C/workspace-ina-web/report/index.html" method="post">
            <ul>
                <li><label for="uname" class="input"></label></li>
                <input type="text" class="input" name="uname" id="uname" placeholder="아이디를 입력하세요" minlength="4" maxlength="10">
                <span aria-hidden="true" ></span>
            </ul>
            <ul>
                <li><label for="pw-check" class="input"></label></li>
                <input type="text" class="input" name="pw-check" id="pw-check" placeholder="비밀번호를 입력하세요" minlength="8" maxlength="12">
            </ul>
            <ul>
                <li><label for="pw" class="input"></label></li>
                <input type="password" class="input" name="pw" id="pw" placeholder="비밀번호 확인을 입력하세요" minlength="8" maxlength="12">
            </ul>
            <ul>
                <li><label for="name" class="input"></label></li>
                <input type="text" class="input" name="name" id="name" placeholder="이름을 입력하세요" minlength="1" maxlength="5">
            </ul>
        </form>
        <div class="btn-login">
            <a href="${cp}index">가입하기</a>
        </div>
    </div>
    	<jsp:include page="../common/footer.jsp"/>
</body>
</html>