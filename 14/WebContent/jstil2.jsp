<%@page import="java.io.BufferedReader"%>
<%@page import="java.io.FileReader"%>
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
<%
/* 	int c;
	StringBuffer sb = new StringBuffer();
	while((c = br.read()) != -1){
		//System.out.printf("%c %d %n", c, c);
		sb.append((char)c);
	}
	//System.out.println(sb.toString());
	
	str = sb.toString();
	//System.out.println(str); */
	
	BufferedReader br = new BufferedReader(new FileReader("G:\\내 드라이브\\교재\\자바관련\\fruits.csv"));
	String s;
	String str = "";
	while((s = br.readLine()) != null) {
		str += s + ";";
	};
	
	String strs = "가, 나, 다, 라";

	request.setAttribute("strs", strs);
	request.setAttribute("str", str);
	System.out.println(str);
%>

			<!--아이템즈 : 순회대상 / 딜림즈 : 구분자   -->
<c:forTokens items="${strs}" delims=", " var="s">
	<h3>${s}</h3>
</c:forTokens>

<table border="1">
<c:forTokens items="${str}" delims=";" var="v">
	<tr>
	<c:forTokens items="${v}" delims="," var="v2">
		<td>${v2}</td>
	</c:forTokens>
	</tr>
</c:forTokens>
</table>

</body>
</html>