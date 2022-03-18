<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<%-- <%
	request.setAttribute("request", request);
%> --%>
<!-- jstl에서 두겟 두포스트 관리 -->
<form method="post" name="form">
	<button formmethod="get" name="get">GET전송</button>
	<button name="post">전송</button>
</form>
<!-- 리퀘스트 객체의 겟메서드 -->
<h2><%=request.getMethod()%></h2>
<h2>${empty request}</h2>
<h2>${request.getMethod()}</h2>
<h2>${request.method}</h2>
<h2>${pageContext.request.method}</h2>
<!-- c:if를 이용해 GET > 겟 출력 POST 포스트 출력 -->
<%
	if(request.getMethod().equals("GET")){
		out.println("<h2>겟</h2>");
	}
	if(request.getMethod().equals("POST")){
		out.println("<h2>포스트</h2>");
	}
%>
<c:if test="${pageContext.request.method == 'GET'}">
	<h2>겟 줘</h2>
</c:if>
<c:if test="${pageContext.request.method == 'POST'}">
	<h2>포스트 줘</h2>
</c:if>
<!-- c:choose를 이용해 GET > 겟 출력 POST 포스트 출력 -->
<c:choose>
	<c:when test="${pageContext.request.method == 'GET' }">
		<h2>겟줘</h2>
	</c:when>
	<c:when test="${pageContext.request.method == 'POST' }">
		<h2>포스트줘</h2>
	</c:when>
</c:choose>

</body>
</html>