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
	        <div class="boardList">
		        <h1> 자유 게시판
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
                        		<span><a href="get${page.cri.params2}&bno=${board.bno}">${board.title}</span>
                        	</a>
                        </p>
                        <div>
                            <span><img src="${cp}images/ser1.png"> ${board.writer}</span>
                            <ul>
                                <li><img src="${cp}images/eye_new.png"> ${board.regDate}</li>
                                <li><img src="${cp}images/sub_date_new.png">${board.hitcount}</li>
                            </ul>
                        </div>
                    </li>
                    </c:forEach>
                </ul>
	            <div class="btnGet">
	                <button class="btn btn-primary float-end" id="btnGet">
	                <i class="fas fa-edit"></i>글 작성
	                </button>
	                <input type="hidden" value="1" id="chkId"> 
	            </div>
                <div>
                    <ul class="pagination justify-content-center">
                    <c:if test="${page.prev}">
                        <li class="page-item ${p == page.cri.pageNum }"><a class="page-link" href="list${page.cri.params}&pageNum=${page.cri.pageNum-1}">prev</a></li>
                    </c:if>
                    <c:forEach begin="${page.start}" end="${page.end}" var="p">
                        <li class="page-item ${p == page.cri.pageNum ? 'active' : ''}"><a class="page-link" href="list${page.cri.params}&pageNum=${p}">${p}</a></li>
                    </c:forEach>
                    <c:if test="${page.next}">
                        <li class="page-item ${p == page.cri.pageNum }"><a class="page-link" href="list${page.cri.params}&pageNum=${page.cri.pageNum+1}">next</a></li>
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
	
	$('#btnGet').click(function(){
		console.log("click!");
		if ($("#chkId").val) {
			alert("로그인이 필요합니다")
			window.location( cp + "/member/login");
		}
	});
</script>
</body>
</html>