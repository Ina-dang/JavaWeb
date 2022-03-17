<%@page import="java.net.URLDecoder"%>
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
	Cookie[] cookies = request.getCookies(); //배열타입
	for(Cookie cookie : cookies){
		out.println(cookie.getName() + "<br>");
		out.println(URLDecoder.decode(cookie.getValue(), "utf-8")+ "<br>");
	}
%>
</body>
</html>