<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<%
	int num = 10;
%>
	<h2>el의 변수 활용</h2>
	<h3>EL : ${num}</h3>
	<h3>EL(page) : ${pageScope.num}</h3>
	<h3>EL(request) : ${requestScope.num}</h3>
	<h3>EL(session) : ${sessionScope.num}</h3>
	<h3>EL(application) : ${applicationScope.num}</h3>
	<h3>EL(empty) : ${test}</h3>
	<h3>EL(empty) : ${empty test}</h3>
	<h3>EL(num) : ${empty num}</h3>
	<h3>expression : <%=num%></h3>
</body>
</html>