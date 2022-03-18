<%@page import="java.util.Map"%>
<%@page import="java.util.HashMap"%>
<%@page import="com.google.gson.Gson"%>
<%@page import="domain.Member" %>
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
	Member member = new Member("javaman", "1234", "자바맨", "javaman@gmail.com");
/*EL쓰러면 바인딩 해야됨  */
	request.setAttribute("mem", member);

/* {"id" : "javaman", "pw" : "1234", "name" : "자바맨", "email" : "javaman@gmail.com"} */
	Gson gson = new Gson();
	//제이슨 스트링으로 반환
	String jsonStr = gson.toJson(member);
	System.out.println(jsonStr);
	//다시 멤버타입으로
	Member member2 = gson.fromJson(jsonStr, Member.class);
	System.out.println(member);
	Map<String, Object> map = gson.fromJson(jsonStr, HashMap.class);
	System.out.println(map);

	//json사용처 >> 비동기구현
	//제이슨 스트링으로 비동기형식 반환 | 레스트요청 
	String jsonStr1 = gson.toJson(map);
	System.out.println(jsonStr1);
	
%>
<h2>${mem}</h2>
<h2>${mem.id}</h2>
<h2>${mem.pw}</h2>
<h2>${mem.name}</h2>
<h2>${mem.email}</h2>


</body>
</html>