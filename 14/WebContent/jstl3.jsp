<%@page import="domain.Member"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<%
	List<Member> members = new ArrayList<>();
	members.add(new Member("javaman", "1234", "자바맨", "email@e.mail"));
	members.add(new Member("babamba", "1234", "바밤바", "email@e.mail"));
	members.add(new Member("amanna", "1234", "아맛나", "email@e.mail"));

	request.setAttribute("members", members);
%>
<c:forEach var="i" begin="5" end="15" varStatus="stat" step="2">
		<h2>${i} / ${stat.index} / ${stat.count} </h2>
</c:forEach>

<c:forEach var="mem" items="${members}" step="2" varStatus="stat">
	<h3>${stat.index} / ${mem} / ${stat.first} / ${stat.last}</h3>
</c:forEach>
</body>
</html>