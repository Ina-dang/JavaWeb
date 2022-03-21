<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="ko">
    <head>
        <%@ include file="../common/head.jsp" %>
    </head>
    <body class="sb-nav-fixed">
        <%@ include file="../common/nav.jsp" %>
        <main class="mt-5 pt-5">
            <div class="container-fluid px-4">
                <h1 class="mt-4">Board</h1>
                <div class="card mb-4">
                    <div class="card-header">
                    	<a class="btn btn-primary float-end" href="register">
                        <!-- <i class="fas fa-table me-1"></i> -->
                        <i class="fas fa-edit"></i>
                        글 작성
                        </a>
                        <div class="col-2 ">
                        <!--  			//부트스트랩 .. 내가준쿼리(아래<script>		-->
		                    <select class="form-select form-amount">
								<option ${page.cri.amount == 5 ? 'selected' : ''} value="5"> 5개씩 보기</option>
								<option ${page.cri.amount == 10 ? 'selected' : '' } value="10">10개씩 보기</option>
								<option ${page.cri.amount == 25 ? 'selected' : '' } value="25">25개씩 보기</option>
								<option ${page.cri.amount == 50 ? 'selected' : '' } value="50">50개씩 보기</option>
							</select>
						</div>
                    </div>
                    <div class="card-body">
                        <table class="table table-striped table-hover">
                            <thead>
                                <tr>
									<th>글번호</th>
									<th>제목</th>
									<th>작성자</th>
									<th>조회수</th>
									<th>등록일</th>
								</tr>
                            </thead>
                            <tbody>
                            <c:forEach items="${boards}" var="board">
								<tr>
									<td>${board.bno}</td>
									<!-- 페이지 따라감 -->
									<td><a href="get${page.cri.params2}&bno=${board.bno}">${board.title}</a></td>
									<td>${board.writer}</td>
									<td>${board.hitcount}</td>
									<td>${board.regDate}</td>
								</tr>
							</c:forEach>
                            </tbody>
                        </table>
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
                </div>
            </div>
        </main>
        <%@ include file="../common/footer.jsp" %>
        <script>
        $(function(){
        	var pageNum = '${page.cri.pageNum}'; //따옴표로 문자열만듦 오류 방지 
        	var category = '${page.cri.category}';
       		$(".form-amount").change(function(){
       			location.href = 'list?amount=' + $(this).val() + "&category=${page.cri.category}&pageNum=${page.cri.pageNum}";
       		});
        });
        </script>
    </body>
</html>
