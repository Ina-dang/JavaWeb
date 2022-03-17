<%@page import="java.io.PrintWriter"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" isErrorPage="true"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<!-- //에러전용페이지 2번줄에 isErrorPage해주면 익셉션생김-->
	<h1>에러발생! <%=exception.getMessage() %></h1>
<%
	PrintWriter pw = response.getWriter();
	exception.printStackTrace(pw);
%>
	
</body>
</html>