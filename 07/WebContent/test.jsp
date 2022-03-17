<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
 <h1>test.jsp</h1>
 <%@ include file="test2.jsp" %>
<jsp:include page="test2.jsp">
	<jsp:param value="100" name="val"/>
</jsp:include>
 <jsp:include page="test2.jsp"/> <!-- 엶과 동시에 닫음 -->
<%-- <jsp:forward page="test2.jsp"></jsp:forward> 포워드는 서블릿에서--%>
 <!--jsp:include 같은 태그들은 반드시 닫는태그 있어야함  -->
 <h2>${param.val}</h2>
 <h2><%=request.getParameter("val") %></h2>
 <iframe src="test2.jsp"> </iframe>
<iframe width="560" height="315" src="https://www.youtube.com/embed/_zpEuB9FmMI" title="YouTube video player" frameborder="0" allow="accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture" allowfullscreen></iframe>
<input />
</body>
</html>