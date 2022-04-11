<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<jsp:include page="../common/head.jsp"/>        
</head>
<body>
<jsp:include page="../common/nav.jsp"/>
    <!-- 게시판 -->
    <div class="board">
        <form>
        	<h3>${page.cri.category}</h3>
        	
	        <div class="boardList">
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
   			        <span>
	    				<select class="form-select form-amount">
							<option ${page.cri.amount == 5 ? 'selected' : ''} value="5"> 5개씩 보기</option>
							<option ${page.cri.amount == 10 ? 'selected' : '' } value="10">10개씩 보기</option>
							<option ${page.cri.amount == 25 ? 'selected' : '' } value="25">25개씩 보기</option>
							<option ${page.cri.amount == 50 ? 'selected' : '' } value="50">50개씩 보기</option>
						</select>
			        </span>
		        </h1>

                <ul>
           	        <c:forEach items="${boards}" var="board">
                    <li>
                        <p>
                        	<a href="#!">
                        		<!-- <span>regDate (하루전이면 n뜨게)</span> -->
                        		<span>[엘리시움]</span>
                        		<span><a href="${cp}board/get${cri.getparams2}&bno=${board.bno}">${board.title}</a></span>
                        	</a>
                        </p>
                        <div>
                            <span> <img src=" ${cp}images/ser1.png"> ${board.writer}</span>
                            <ul>
                                <li> <img src=" ${cp}images/eye_new.png"> ${board.regDate}</li>
                                <li> <img src=" ${cp}images/sub_date_new.png">${board.hitcount}</li>
                            </ul>
                        </div>
                    </li>
                    </c:forEach>
                </ul>
	            <div class="btnGet">
	                <a href="register${page.cri.params2}" class="btn btn-primary float-end" id="btnGet" type="button">
	                글 작성
	                </a>
	                <input type="hidden" value="1" id="chkId"> 
	            </div>                       
				<div>
                  	<ul class="pagination justify-content-center">
                  	<c:if test="${page.prev}">
                  		<li class="page-item ${p == page.cri.pageNum}"><a class="page-link" href="list${page.cri.params}&pageNum=${page.cri.pageNum-1}">prev</a></li>
                 		</c:if>
                  	<c:forEach begin="${page.start}" end="${page.end}" var="p">
                  		<li class="page-item ${p == page.cri.pageNum ? 'active': ''}"><a class="page-link" href="list${page.cri.params}&pageNum=${p}">${p}</a></li>
                  	</c:forEach>
                  	<c:if test="${page.next}">
                  		<li class="page-item ${p == page.cri.pageNum}"><a class="page-link" href="list${page.cri.params}&pageNum=${page.cri.pageNum+1}">next</a></li>
               		</c:if>
					</ul>
	            </div>
	        </div>
        </form>
    </div>
<jsp:include page="../common/footer.jsp"/>
<script>
	//컨텍스트패쓰 경로지정
	var cp = '${pageContext.request.contextPath}';
	var id = '${member.id}';
	$('#btnGet').click(function(){
		console.log("click!");
		if ( id == '') {
			alert("로그인이 필요합니다")
			location.href = cp + "/member/login";
		} else {
			location.href = cp + "/board/register";
		}
	});
	
	//페이징처리
	$(function() {
		var pageNum = '${page.cri.pageNum}';
		var category = '${page.cri.category}';
		$(".form-amount").change(function(){
			location.href = 'list?amount=' + $(this).val() + "&category=${page.cri.category}&pageNum=${page.cri.pageNum}";
		})
	})
</script>
</body>
</html>