<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>page 2</h1>
	
	<!-- redirect로 자동이동 -->
	
<%
	//리다이렉트          절대경로:프로토콜 또는 상대경로 입력
	//response.sendRedirect("https://www.naver.com"); //url값도 바뀜
	//response.sendRedirect("page3.jsp");
	
	//포워드
	request.getRequestDispatcher("page3.jsp").forward(request, response);
%>
</body>
</html>