<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<jsp:include page="../common/head.jsp"/>        
</head>
<body>
    <body class="body">
    <jsp:include page="../common/nav.jsp"/>

    <!-- 게시판글쓰기 -->
    <div class="notice">
        <h1> 자유 게시판</h1>
        <div class="card-header">
            <a class="btn btn-primary float-end" href="register">
            <!-- <i class="fas fa-table me-1"></i> -->
            <i class="fas fa-edit"></i>
            글 작성
            </a>
        </div>
        <table class="table">
            <tr>
                <th class="col1">글번호</th>
                <th class="col2">제목</th>
                <th class="col4">작성자</th>
                <th class="col5">조회수</th>
                <th class="col3">작성일</th>
            </tr>
            <c:forEach items="${boards}" var="board">
            <tr>
                <td>${board.bno}</td>
                <td><a href="get?bno=${board.bno}">${board.title}</a></td>
                <td>${board.writer}</td>
                <td>${board.hitcount}</td>
                <td>${board.regDate}</td>
            </tr>
            </c:forEach>
        </table>
        <div class="btn-notice">
            <a href="${cp}register" >글쓰기</a>
        </div>
    </div>
   	<jsp:include page="../common/footer.jsp"/>
   	<script>

    </script>
</body>
</html>