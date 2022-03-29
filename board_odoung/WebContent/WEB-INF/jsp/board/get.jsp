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
                <%-- <h2>${board.attachs}</h2> --%>
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
								<c:if test="${cri.category==3}">
									<div>
										<c:forEach items="${board.attachs}" var="attach">
											<c:if test="${attach.image}">
												<div class="text-center my-3">
													<img class="mx-100" src="${pageContext.request.contextPath}/display?uuid=${attach.uuid}&path=${attach.path}" alt="${attach.origin}">
												</div>
											</c:if>
										</c:forEach>
									</div>	
								</c:if>
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
						<button type="button" class="btn btn-primary btn-sm float-end" id="btnReplyReg">reply register</button>
						<ul class="list-group my-3 list-group-flush my-3 small replies"></ul>
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
			//모듈패턴 GET 
			const cp = '${pageContext.request.contextPath}';

			// replyService.get(24, function(data) {
			// 	console.log(data);
			// }, cp);

			// replyService.post({bno : 305, content:'get.jsp 내용', writer : 'inadang'}, function(data){
			// 	console.log(data);
			// }, cp);

			//댓글 & 모달버튼
			$(function(){

				// $("#replyModal").modal("show");
				const bno = '${board.bno}';
				showList(); 

				function showList(){
					replyService.list(bno, function(data){
						console.log(data);
						var str = "";
						for(var i in data){
							str+='				<li class="list-group-item" data-rno="' + data[i].rno +  '">'
							str+='					<div class="list-group-item list-group-item-secondary small">'
							str+='						<span>' + data[i].writer +  '</span>'
							str+='						<span class="small float-end">' + data[i].regDate + '</span>'
							str+='					</div>'
							str+='					<div class="list-group-item">' + data[i].content + '</div>'
							str+='				</li>'
						}
						$(".replies").html(str);
					}, cp);
				}

				//댓글 상세조회				
				//동적 li라 위임작업해야함 > on함수(이벤트바인딩함수(동작, 자손))
				$(".replies").on("click", "li", function(){
															//비동기처리후 성공함수 결과값
					replyService.get($(this).data("rno"), function(data) {
						console.log(data);
						
						//값부여
						$("#rno").val(data.rno);
						$("#replyContent").val(data.content);
						$("#replyRegDate").val(data.regDate);
						$("#replyWriter").val(data.writer);
						
						//댓글상세조회시보기
						$("#replyModal .modal-body div").show;

						//버튼
						$("#replyModal")
						.data("rno", data.rno)
							//작성버튼만 비활성화		  
							.find(".modal-footer button").hide()
								.filter(":gt(0)").show()
						//#replyModal로 되돌아감
							.end()	
						.end()					//불린속성에 대한 on off 상태 >> 스위치너낌
													//attr는 외 겟터셋터때 편함 (attr은 제거하려면 remove해야해서)
							.find("input, textarea").prop("disabled", false)
						.end().modal("show");
					}, cp);
				});

				//댓글 등록창 활성화 (모달 띄우기)
				$("#btnReplyReg").click(function(){
					$("#replyModal .modal-body div").eq(0).hide();
					$("#replyModal .modal-body div").eq(2).hide();
					

					$("#replyModal")
						.find(".modal-footer button").hide()
							// .eq(0).show()
							.filter(":eq(0)").show()
						.end()
					.end()
												//bno랑 시간, 작성자는 비활성화해야함 >> content만 활성화 되어야함
						.find("input, textarea").prop("disabled", false).val("")
							
					$("#replyModal .modal-body div").eq(3).find("input").prop("disabled", true).val("${member.id}")
					$("#replyModal").modal("show");
				});

				//등록 버튼 클릭 이벤트 (댓글 작성후 Register)
				$("#replyModal .modal-footer button:eq(0)").click(function(){
					var reply = {bno : bno, content:$("#replyContent").val(), writer:$("#replyWriter").val()}
					replyService.add(reply, function(data){
						alert("댓글 등록완료");
						showList();
						$("#replyModal").modal("hide")
					}, cp);
				});

				//수정 버튼 클릭 이벤트 (댓글 작성후 modify)
				$("#replyModal .modal-footer button:eq(1)").click(function(){
					var reply = {rno : $("#replyModal").data("rno"), content:$("#replyContent").val()}
					replyService.modify(reply, function(data){
						alert("댓글 수정완료");
						showList();
						$("#replyModal").modal("hide")
					}, cp);
				});

				//삭제 버튼 클릭 이벤트 (댓글 작성후 delete)
				$("#replyModal .modal-footer button:eq(2)").click(function(){
					var reply = {rno : $("#replyModal").data("rno")}
					replyService.remove(reply, function(data){
						alert("댓글 삭제완료");
						showList();
						$("#replyModal").modal("hide")
					}, cp);
				});
			});
		</script>
    </body>
</html>
