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
                <h2>${board.attachs}</h2>
                <div class="card mb-4">
                    <div class="card-body">
                        <form>
						  <div class="mb-3 mt-3">
						    <label for="bno" class="form-label"><i class="fas fa-list-ol"></i> bno</label>
						    <input type="text" class="form-control" id="bno" name="bno" value="${board.bno}" disabled>
						  </div>
						  <div class="mb-3">
						    <label for="title" class="form-label"><i class="fas fa-heading"></i> title</label>
						    <input type="text" class="form-control" id="title" name="title" value="${board.title}" disabled>
						  </div>
						  <div class="mb-3">
						    <label for="content" class="form-label"><i class="fas fa-align-justify"></i> content</label>
						    <textarea class="form-control" id="content" name="content" disabled>${board.content}</textarea>
						  </div>
						  <div class="mb-3">
						    <label for="regDate" class="form-label"><i class="far fa-clock"></i> regDate</label>
						    <input type="text" class="form-control" id="regDate" name="regDate" value="${board.regDate}" disabled>
						  </div>
						  <div class="mb-3">
						    <label for="writer" class="form-label"><i class="fas fa-user"></i> writer</label>
						    <input type="text" class="form-control" id="writer" name="writer" value="${board.writer}" disabled>
						  </div>
						  <div class="mb-3">
						    <label for="attach" class="form-label"><i class="fas fa-file-archive"></i> attach</label>
						  	<ul class="list-group">
						  	<c:forEach items="${board.attachs}" var="attach">
								<li class="list-group-item"><i class="fas fa-download"></i> <a href="${pageContext.request.contextPath}/download${attach.params}">${attach.origin}</a></li>
							</c:forEach>
							</ul>
						  </div>
						    <span class="form-label mb-4"><i class="fas fa-comments"></i> replies</span>
							<ul class="list-group my-3 list-group-flush my-3 small">
								<li class="list-group-item">
									<div class="list-group-item list-group-item-secondary small">
										<span> |  작성자  </span>
										<span class="small float-end">|  작성시간  </span>
									</div>
									<div class="list-group-item"> 
									댓글내용<br>
									댓글내용<br>
									댓글내용<br>
									댓글내용<br>								
									</div>
								</li>
								<li class="list-group-item">
									<div class="list-group-item list-group-item-secondary small">
										<span> |  작성자  </span>
										<span class="small float-end">|  작성시간  </span>
									</div>
									<div class="list-group-item"> 
									댓글내용<br>
									댓글내용<br>
									댓글내용<br>
									댓글내용<br>								
									</div>
								</li>
								<li class="list-group-item">
									<div class="list-group-item list-group-item-secondary small">
										<span> |  작성자  </span>
										<span class="small float-end">|  작성시간  </span>
									</div>
									<div class="list-group-item"> 
									댓글내용<br>
									댓글내용<br>
									댓글내용<br>
									댓글내용<br>								
									</div>
								</li>
								<li class="list-group-item">
									<div class="list-group-item list-group-item-secondary small">
										<span> |  작성자  </span>
										<span class="small float-end">|  작성시간  </span>
									</div>
									<div class="list-group-item"> 
									댓글내용<br>
									댓글내용<br>
									댓글내용<br>
									댓글내용<br>								
									</div>
								</li>
							</ul>
						  <a href="list${cri.params2}" class="btn btn-outline-secondary">list</a>
						  <c:if test="${not empty member && member.id == board.writer}">
						  <a href="modify${cri.params2}&bno=${board.bno}" class="btn btn-outline-warning">modify</a>
						  <a href="remove${cri.params2}&bno=${board.bno}" class="btn btn-outline-danger" onclick="return confirm('삭제하시겠습니까?')">remove</a>
						  </c:if>
						</form>
                    </div>
                </div>
            </div>
        </main>
		        <!-- The Modal -->
		<div class="modal" id="replyModal">
		  <div class="modal-dialog">
		    <div class="modal-content">
		
		      <!-- Modal Header -->
		      <div class="modal-header">
		        <h4 class="modal-title">Modal Heading</h4>
		        <button type="button" class="btn-close" data-bs-dismiss="modal"></button>
		      </div>
		
		      <!-- Modal body -->
		      <div class="modal-body">
		  			<div class="mb-3 mt-3">
				    <label for="rno" class="form-label"><i class="fas fa-list-ol"></i> rno</label>
				    <input type="text" class="form-control" id="rno" name="rno" >
				  </div>
				  <div class="mb-3">
				    <label for="replyContent" class="form-label"><i class="fas fa-heading"></i> content</label>
				    <textarea class="form-control" id="replyContent" name="replyContent"></textarea>
				  </div>
				  <div class="mb-3">
				    <label for="replyRegDate" class="form-label"><i class="far fa-clock"></i> regDate</label>
				    <input type="text" class="form-control" id="replyRegDate" name="replyRegDate">
				  </div>
				  <div class="mb-3">
				    <label for="replyWriter" class="form-label"><i class="fas fa-user"></i> writer</label>
				    <input type="text" class="form-control" id="replyWriter" name="replyWriter">
				  </div>
		      </div>
		
		      <!-- Modal footer -->
		      <div class="modal-footer">
		        <button type="button" class="btn btn-primary" >Register</button>
		        <button type="button" class="btn btn-warning" >Modify</button>
		        <button type="button" class="btn btn-danger" >Remove</button>
		      </div>
		
		    </div>
		  </div>
		</div>
        <%@ include file="../common/footer.jsp" %>
		<script>
		//모듈패턴
		var replyService = (function() {
			
			//단일조회목적
			function get(rno, callback, cp) {

			}
			return{ get : get }
		})();
		

		$(function(){

			var cp = '${pageContext.request.contextPath}';
			$("#replyModal").modal("show");
			var reply = replyService.get(21, function(){

			}, cp);
		});
		</script>
    </body>
</html>
