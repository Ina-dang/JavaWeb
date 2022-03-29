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
                <textarea class="write-title-textarea" readonly>패파살려 다 도망간다</textarea>
                <div class="date"><b> 2022 - 05 - 14 </b></div>
            </div>
            <div class="write-question">
                <textarea class="write-title-textarea1" readonly> 
나도 도망갈까?
일단 만들긴했어…
                    
3분극딜/레템 딜압축 보스유도/블레스트 최종뎀감소 삭제/블디 후딜개선/블래스트 범위감소 롤백/사출기보스 우선타격/트리플 임팩트 알아서 만족하게 개선/트랜지션 텔포화/아스트라 무적기화/ 레이븐 온오프화/부스터 패시브/배리어,언바운드 문양고정
                </textarea>
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