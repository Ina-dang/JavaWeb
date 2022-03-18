
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<!-- 액션태그사용. 액션태그 유즈빈 사용 -->
<!--           id 앞으로 불리어질 이름   /클래스 : 가리키는게 멤버클래스 가리킴 (패키지포함)-->
<!-- 스코프 지정안하면 페이지. 빈이생성된다고함 -->
<jsp:useBean id="member" class="domain.Member" scope = "request"/> <!-- usseBean거의 안쓴다함  -->
			<!--                domain.Member에있는거 / 값      -->
<jsp:setProperty name="member" property="id" value="babamba" />
<jsp:setProperty name="member" property="pw" value="1234" />
<jsp:setProperty name="member" property="name" value="바밤바" />

<!-- 바인딩에의해 리퀘스트 스코프에 들어가는 domain.Member 인스턴스 가져옴 -->
<h2>${member}</h2>
<h2>${member.id}</h2>
<!-- 지역변수로도 만들 수 있다 -->
<h2><%=member%></h2>
<h2><%=member.getId()%></h2> <!-- 얘는.id 안됨 -->
<h2><jsp:getProperty property="id" name="member"/></h2>
<!-- 4번줄 코어라이브러리 이용한 호출 -->
<h2><c:out value="${member.id}" ></c:out> </h2> <!-- 컨트롤 스페이스 하면 나오는 excapeXml 에 유용해서 EL다음으로 많이사용 -->

</body>
</html>