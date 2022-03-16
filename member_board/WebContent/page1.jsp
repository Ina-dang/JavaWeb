<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>page 1</h1>
	<a href="page2.jsp">페이지2로 이동</a>
	<form action="page2.jsp">
		<button>submit</button>
	</form><!-- 서브밋을 통해 이동 -->
	<input type="button" value="클릭미" id="btn" onclick="location.href = 'page2.jsp'"> <!-- //자바스크립트 -->
</body>
</html>