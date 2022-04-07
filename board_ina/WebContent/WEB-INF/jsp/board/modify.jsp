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
    <main class="register">
    	<form method="post">
    		<div>
	        	<span>
		             <a href="list${cri.params2}" class="btn btn-primary float-end" id="btnGet" type="button">목록 </a>
		    	</span>
	    	</div>
			<section class = "regSection">
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
						<!-- 제목 -->
						<input type="text" maxlength="30" oninput="handleOnInput(this)" name="title" value="${board.title}"> 
					</p>
					<div>
						<ul>
							<li> <img src=" ${cp}images/ser1.png " > ${member.id} </li>
						</ul>
					</div>
					<!--본문-->
					<div>
						<textarea rows="10" cols="95" id="content" maxlength="3000" name="content"> ${board.content} </textarea>
						<p id="textCount"></p>
					</div>
				</div>
			</section>
			<div class="regBtnWrap">
			    <input type="hidden" name="amount" value="${cri.amount}">
			    <input type="hidden" name="category" value="${cri.category}">
			    <input type="hidden" name="pageNum" value="${cri.pageNum}">
			<button class="btn btn-outline-warning" >등록</button>
				<a href="list?${cri.params2}" class="btn btn-outline-danger" onclick="return confirm('취소하시겠습니까?')">취소</a>
			</div>
        </form>
    </main>
    <jsp:include page="../common/footer.jsp"/>
    <script>
    	function handleOnInput(e){
    		if (e.value.length > 30) {
				e.value = e.value.substr(0, 30);
				alert("제목을 30자 이내로 작성해주세요.");
			}
    	}
    	
    
    	
    	$('#content').keyup(function (e) {
    		let content = $(this).val();
    		
    		//글자수 세기
    		if (content.length == 0 || content == '' ) {
				$('#textCount').text('0자');
			} else {
				$('#textCount').text(content.length + '자');
			}
    		
    		//글자수 제한 => 알람이 안뜨네..
    		if (content.length > 3000 ){
    			$(this).val.substring(0,3000);
    			alert("내용을 3000자 이내로 작성해주세요.");
    		};
    	});
    </script>
</body>
</html>