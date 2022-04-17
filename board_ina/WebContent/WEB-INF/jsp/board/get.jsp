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
    <main class="get">
    	<form>
    		<div>
	        	<span>
		             <a href="list${cri.params2}" class="btn btn-primary float-end" id="btnReg" type="button">목록 </a>
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
					<div class="getInfo">
						<ul>
							<li> <img src=" ${cp}images/ser1.png "> ${board.writer} </li>
							<li> <img src=" ${cp}images/eye_new.png "> ${board.regDate}</li>
							<li> <img src=" ${cp}images/sub_date_new.png "> ${board.hitcount}</li>
						</ul>
						<!-- 주소복사 넣을 수 있으면 여기 넣기 -->
					</div>
					<!--본문-->
					<div class="getContent">
						${board.content}
					</div>
					<!-- 파일첨부 -->
					<div>
						<label for="attach" class="form-label"><i class="fas fa-file-archive"></i> 첨부파일</label>
						<c:if test="${cri.category == 3}">
						<div>
							<c:forEach items="${board.attachs}" var="attach">
								<c:if test="${attach.image}">
										<img class="mx-100" src="${pageContext.request.contextPath}/display?uuid=${attach.uuid}&path=${attach.path}" alt="${attach.origin}">
								</c:if>
							</c:forEach>
						</div>	
						</c:if>
 					  	<c:forEach items="${board.attachs}" var="attach">
							<li class="list-group-item"><i class="fas fa-download"></i> <a href="${pageContext.request.contextPath}/download${attach.params}">${attach.origin}</a></li>
						</c:forEach>
					</div>
					<!-- 댓글처리 -->
						<div class="clearfix">
						<span class="form-label mb-4">
						<i class="fas fa-comments"></i> 댓글 
						<%-- <span class="text-secondary small">[${board.replyCnt}]</span> --%>
						</span> 
						</div>
						<ul class="list-group my-3 list-group-flush my-3 small replies" id="replytitle">
						
						</ul>
						<!-- 댓글 -->
						<div class="row p-4">
							<div class="col-10 "> 
								<textarea class="w-100" id="replyContent" placeholder="댓글을 입력해주세요."></textarea>
							</div>
							<div class="col-2"> 
								<button class="btn btn-primary" id="btnReplyReg" type="button"> 글등록 </button> 
							</div>
						</div>
				</div>
			</section>
			<c:if test="${board.writer == member.id && not empty member}">
			<div class="regBtnWrap">
				<a href="modify${cri.params2}&bno=${board.bno}" class="btn btn-outline-warning">수정</a>
				<a href="remove${cri.params2}&bno=${board.bno}" class="btn btn-outline-danger" onclick="return confirm('삭제하시겠습니까?')">삭제</a>
			</div>
			</c:if>
        </form>
    </main>
    <jsp:include page="../common/footer.jsp"/>
		<script>
			//모듈패턴 GET 
			const cp = '${pageContext.request.contextPath}';


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
							str+='						<span class="float-end mx-2 btnReplyRemove"><i class="fas fa-minus-circle text-danger" style="cursor:pointer"></i></span>'
							str+='					</div>'
							str+='					<div class="list-group-item">' + data[i].content + '</div>'
							str+='				</li>'
						}
						$(".replies").html(str);
					}, cp);
				}

				//댓글 삭제		
				$(".replies").on("click", ".btnReplyRemove", function(){
					var rno = $(this).closest("li").data("rno");
					var reply = {"rno" : rno}
					replyService.remove(reply, function(data){
						alert("댓글 삭제완료");
						showList();
					}, cp);
				});

				
				//비동기댓글등록
				$("#btnReplyReg").click(function(){
					var reply = {bno : bno, content:$("#replyContent").val(), writer:'${member.id}'}
					replyService.add(reply, function(data){
						showList();
						$("#replyContent").val("");
						alert("댓글이 등록되었습니다");
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