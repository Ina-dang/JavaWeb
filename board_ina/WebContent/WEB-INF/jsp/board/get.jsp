<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"  %>
<!DOCTYPE html>
<html>
<head>
<jsp:include page="../common/head.jsp"/>        
</head>
<body class="body">
    <jsp:include page="../common/nav.jsp"/>
    <!-- 게시판상세 화면 -->
    <main class="write">
    	<form>
    		<div>
	        	<span>
		             <a href="list${cri.params2}" class="btn btn-primary float-end" id="btnGet" type="button">목록 </a>
		    	</span>
	    	</div>
			<section class = "getSection">
				<div>
					<h1> 
						<c:if test="${page.cri.category == 1}">
						자유게시판
						</c:if>
						<c:if test="${page.cri.category == 2}">
						공지사항
						</c:if>
						<c:if test="${page.cri.category == 3}">
						갤러리
						</c:if>
					</h1>
					<p>
						<!-- 서버도...자동으로.. -->
						<span>${board.title}</span> 
					</p>
					<div>
						<ul>
							<li> <img src=" ${cp}images/ser1.png "> ${board.writer} </li>
							<li> <img src=" ${cp}images/eye_new.png "> ${board.regDate}</li>
							<li> <img src=" ${cp}images/sub_date_new.png "> ${board.hitcount}</li>
						</ul>
						<!-- 주소복사 넣을 수 있으면 여기 넣기 -->
					</div>
					<!--본문-->
					<div>
						<textarea rows="9" cols="40" ></textarea>
					</div>
				</div>
			</section>
			<c:if test="${board.writer == member.id && not empty member}">
			<div class="getBtnWrap">
				<a href="modify${cri.params2}&bno=${board.bno}" class="btn btn-outline-warning">수정</a>
				<a href="remove${cri.params2}&bno=${board.bno}" class="btn btn-outline-danger" onclick="return confirm('삭제하시겠습니까?')">삭제</a>
			</div>
			</c:if>
        </form>
    </main>
    <jsp:include page="../common/footer.jsp"/>
</body>
</html>