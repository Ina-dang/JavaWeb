<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<jsp:include page="../common/head.jsp"/>        
</head>
<body class="body">
    <jsp:include page="../common/nav.jsp"/>
    <!-- 게시판상세 화면 -->
    <main class="write">
        <section class="page">
            <div class="write-title">
                <textarea class="write-title-textarea" placeholder="제목을 입력해 주세요"></textarea>
                <div class="date"> 2022 - 05 - 14</div>
            </div>
            <div class="write-question">
                <textarea class="write-title-textarea1" placeholder="본문을 입력해 주세요"></textarea>
            </div>

            <div class="btn-write">
                <a href="${cp}list">수정</a>
                <a href="${cp}list">취소</a>
            </div>
        </section>
    </main>
    <jsp:include page="../common/footer.jsp"/>
</body>
</html>