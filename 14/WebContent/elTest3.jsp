<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
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
	pageContext.setAttribute("num", 20);
	request.setAttribute("num", 30);
	session.setAttribute("num", 40);
	application.setAttribute("num", 50);
	List<String> list = new ArrayList<>();
	list.add("가");
	list.add("나");
	list.add("다");
	list.add("라");
	request.setAttribute("list", list);
%>
	<h2>el의 변수 활용</h2>
	<h3>EL : ${num}</h3>
	<h3>EL(page) : ${pageScope.num}</h3>
	<h3>EL(request) : ${requestScope.num}</h3>
	<h3>EL(session) : ${sessionScope.num}</h3>
	<h3>EL(application) : ${applicationScope.num}</h3>
	<h3>EL(application) : ${applicationScope['num']}</h3> <!-- 연관배열 -->
	<h3>EL(application[연관배열]) : ${applicationScope['num']}</h3>
	<h3>expression : <%=num%></h3>
	<hr>
	<h3>list : ${list}</h3>
	<h3>list.get(0) : ${list.get(0)}</h3>
	<h3>list[0] : ${list[0]}</h3>
	<h3>list[0].concat("아아아") : ${list[0].concat("아아아")}</h3>
	
	<%-- <jsp:forward page="elTest3_2.jsp"></jsp:forward> --%>
</body>
</html>