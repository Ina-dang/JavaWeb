<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>여러가지 산술 연산자</h1>
	<h2>
	\{10+10} : ${10+10} <br>
	\{3.14+1} : ${3.14+1} <br>
	\{20-10} : ${20-10} <br>
	\{10*10} : ${10*10} <br>
	\{100/9} : ${100/9} <br>
	\{100/0} : ${100/0} <br>
	\{0/0} : ${0/0} <br>
	<%-- \{100div9} : ${100div9} <br> --%>
	\{100 % 9} : ${100 % 9} <br>
	\{100 mod 9} : ${100 mod 9} <br>
	</h2>
	
	<h1>여러가지 비교 연산자</h1>
	<h2>
	\{10 eq 10} : ${10 eq 10}<br>
	<%-- \{'10' eq 10} : ${'10' eq 10}<br> --%>
	</h2>
	
	<h2>여러가지 논리 연산자</h2>
	<h3>
	\{not true} : ${not true}<br>
	</h3>
	
	
</body>
</html>