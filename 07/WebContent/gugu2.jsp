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
	int num = Integer.parseInt(request.getParameter("num"));
 	for (int i = 1 ; i <= 9 ; i++ ){
 		out.println("<tr>");
		out.println("<h1>" + num + "*" + i + "=" + num * i + "</h2>") ;
 		out.println("</tr>");
 	}
 %>
 
 

</body>
</html>