<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<%--
<% %> : 스크립틀릿
<%! %> : 선언문
<%= %> : 표현식
 --%>
 
 <%
     int num = 10;
 	run();
 %>
 
 <%!
     String str = "가나다라";
 	public void run(){
 		System.out.println("가나다라");
 	}
 %>
 
 <h1><%=num%></h1> <!--  표현식은 세미콜론 안됨 수식이니까 30번줄같은뜻임-->
 <h1><%=str%></h1>
 <%-- <h1><%out.print(num;)%></h1> --%>
 <h1><%out.print(num);%></h1>
</body>
</html>