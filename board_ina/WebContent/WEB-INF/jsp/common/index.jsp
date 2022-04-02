<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
<jsp:include page="../common/head.jsp"/>        
</head>
<body class="mainbody"> 
<jsp:include page="../common/nav.jsp"/>
    <div class="commu-wrap">
        <!-- commu 1,2,3 left -->
        <div class="commu-wrap-left">
            <div class="commu-wrap1">
                <!-- 이미지 , h5, 글 -->
                <div class="commu-wrap1-image">
                    <a href="https://maplestory.nexon.com/News/Event/543"><image src="${cp}images/events1.png" alt="이벤트이미지"></image></a>
                </div>
                <div>
                    <h4> '별빛 심포니 이벤트' 2021년 12월 30일 점검 후 시작</h4>
                    <hr>
                    <p>1. 화면 왼쪽의 이벤트 알림이를 통해'[별빛 심포니] 별에 깃든 음악' 퀘스트를 완료합니다.</p>
                    <p>2. 퀘스트를 완료하면 별빛 심포니로 이동합니다</p>
                    <p>3. 별빛 심포니에 위치한 npc가 제공하는 다양한 이벤트에 참여해 보세요.</p>
                </div>
            </div>
            <div class="commu-wrap2">
                    <!-- commu2 사진이랑 ㅇㅇㅇ흥으에-->
                <p>메이플 코디</p>
                <p>(닉네임 클릭 시 .gg로 이동합니다)</p>
                <div class="cody-wrap">
                    <ul>
                        <li class="char-left">
                            <a href="${cp}board/list?category=3"><img src="${cp}images/riyemoo.png" alt="캐릭터"></a>
                        </li>
                        <li class="char-center">
                             <a href="${cp}board/list?category=3"><img src="${cp}images/boyeon.png" alt="캐릭터"></a>
                        </li> 
                        <li class="char-right">
                            <a href="${cp}board/list?category=3"><img src="${cp}images/char12.png" alt="캐릭터"></a>
                        </li>
                    </ul>
                    <ul>
                        <li class="nick-left">
                            <span><img src="${cp}images/ser1.png"><a href="https://maple.gg/u/%EB%A6%AC%EC%98%88%EB%AC%B4">리예무</a></span>
                        </li>
                        <li class="nick-center">
                            <span><img src="${cp}images/ser1.png"><a href="https://maple.gg/u/%EB%B3%B4%EC%97%B0%EC%95%84%EB%8D%B8">보연아델</a></span>
                        </li>
                        <li class="nick-right">
                            <span><img src="${cp}images/ser2.png#"><a href="https://maple.gg/u/%EC%A7%B9%EC%82%A5">짹삥</a></span>
                        </li>
                    </ul>
                </div>
            </div>
            <div class="commu-wrap3">
                <!-- commu3 이미지1 -->
                <a href="https://maplestory.nexon.com/Guide/GameInformation/SpecialContents/PartyQuest"><div class="party"></div></a>
                <div>
                <h1> 파티퀘스트 </h1>
                <p>이번 달의 파티퀘스트 일정 정복하기! 용사님과 함께 파티퀘스트를 즐기는 방법</p>
                </div>
                <!-- commu3 이미지 -->
                <a href="https://maplestory.nexon.com/Guide/GameInformation/Skill/4thPromotionAndHyperSkill"><div class="character"></div></a>
                <div>
                <h1> 캐릭터 & 스킬 </h1>
                <p>모든 직업과 스킬을 한눈에! 메이플 스토리의 캐릭터, 스킬 정보 확인하기</p>
                </div>
                <!-- commu3 이미지 -->
                <a href="https://maplestory.nexon.com/Guide/GameInformation/Security/UOTPSettings"><div class="security"></div></a>
                <div>
                <h1> 보안강화하기 </h1>
                <p> 편리와 보안을 한방에! 안전하고 편하게 메이플 스토리 즐기기</p>
                </div>
            </div>
        </div>
        <!-- commu login, 회원가입, id/pw찾기 right  -->
        <div class="commu-wrap-right">
        	<form method="post">
            <!-- 로그인 회원가입 찾기 -->
            <c:if test="${not empty member}">
            <div class="main-btn-login"><a href="#!">${member.name}</a></div>
            <div class="main-btn-login1">
                <div class="main-btn-login2"><a href="${cp}member/myPage">회원정보</a></div>
                <div class="main-btn-login2"><a href="${cp}member/logout">로그아웃</a></div>
            </div>
            </c:if>
            <c:if test="${empty member}">
            <div class="main-btn-login"><a href="${cp}member/login">login</a></div>
            <div class="main-btn-login1">
                <div class="main-btn-login2"><a href="${cp}member/contract">sign up</a></div>
                <div class="main-btn-login2"><a href="#!">find</a></div>
            </div>
            <p>메이플을 더 안전하고 편리하게 이용하세요</p>
            </c:if>
            </form>
        </div>
    </div>
<jsp:include page="../common/footer.jsp"/>
</body>
</html>