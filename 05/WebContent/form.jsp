<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<!-- 서식전송태그 -->
	<form action="test2">
		<input name="id">
		<button>get 전송</button>
	</form>
	<form action="test2" method="post">
		<input name="id">
		<button>post전송</button>
	</form>
</body>
</html>