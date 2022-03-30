<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <!-- header -->
    <header class="all-header">
        <div class="header-width">
            <div class="bg-logo">
                <a href="${cp}/common/index"><img src="${cp}images/MapleStory-logo.png" alt="메이플스토리 로고"></a>
            </div>
        </div>
    </header>
    <nav class="all-nav"> 
        <!-- main-banner 안에 공지사항이랑 자유게시판 링크-->
        <ul>
            <li><a href="${cp}board/list?category=1">공지사항</a></li>
            <li><a href="${cp}board/list?category=2">자유게시판</a></li>
            <li><a href="${cp}board/list?category=3">갤러리</a></li>
        </ul>
    </nav>