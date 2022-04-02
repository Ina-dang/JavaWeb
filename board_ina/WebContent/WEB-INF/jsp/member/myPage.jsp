<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<jsp:include page="../common/head.jsp"/>        
</head>
<body>
<jsp:include page="../common/nav.jsp"/>
<!-- 회원정보관리 -->
<div class="signup">
    <h3><label for="uname" >회원정보 조회/수정</label></h3>
    <h6>소중한 내 정보를 최신으로 관리하세요</h6>
    <form method="post" id="form">
        <div>
            <div>
                <p>아이디</p>
                <span>
                    <label for="id"></label>
                    <input type="text" name="id" id="id" value="${memberInfo.id}" readonly>
                </span>
            </div>
            <div>
                <p>비밀번호</p>
                <span>
                    <label for="pw"></label>
                    <input type="password" name="pw" id="pw" >
                </span>
            </div>
            <div>
                <p>비밀번호 확인</p>
                <span>
                    <label for="pw2"></label>
                    <input type="password" name="pw2" id="pw2" >
                </span>
            </div>
            <div>
                <p>이름</p>
                <span>
                    <label for="name"></label>
                    <input type="text" name="name" id="name" value="${memberInfo.name}" readonly>
                </span>
            </div>
            <div>
                <p>이메일</p>
                <span>
                    <label for="emal"></label>
                    <input type="email" name="email" id="email" value="${memberInfo.email}" readonly>
                </span>
                <span>
                    <c:if test="${memberInfo.auth == 0 }">
                    <button class="btn btn-danger" type="button" id="btnEmail">이메일인증</button>
                    </c:if>
                    <c:if test="${memberInfo.auth == 1 }">
                    <button class="btn btn-success" type="button" id="btnEmail" disabled>인증된 이메일</button>
                    </c:if>
                </span>
            </div>
            <!-- 주소  -->
            <div>
                <p>주소</p>
                <span>
                    <input id="roadAddr" name="roadAddr" value="${memberInfo.roadAddr}" readonly/>
                    <input id="addrDetail" name="addrDetail" value="${memberInfo.addrDetail}"readonly/>
                </span>
                <span>
                    <input name="roadFullAddr" id="roadFullAddr" value="${memberInfo.roadFullAddr}" >
                </span>
                <span>
                	<button class="btn btn-secondary btn-block" type="button" id="btnSearchAddr"> 주소검색 </button>
                </span>
                <input type="hidden" name="si" id="si">
                <input type="hidden" name="sgg" id="sgg">
                <input type="hidden" name="emd" id="emd">
                <input type="hidden" name="zipNo" id="zipNo">
                <input type="hidden" name="jibunAddr" id="jibunAddr">
            </div>
        </div>
        <div>
	         <button class="btn btn-warning btn-block">회원정보 수정</button>
	         <button class="btn btn-danger btn-block" formaction="secession">회원 탈퇴</button>
        </div>
    </form>
</div>
<div>
    <jsp:include page="../common/footer.jsp"/>
</div>
<script>
    $(function(){
    	
    
        //주소찾기
        $('#btnSearchAddr').click(function(){
            var pop = window.open("${cp}/juso", "pop", "width=570, height=420, scrollbars=yes, resizable=yes");
        });
        
        //경로지정
        var cp = '${pageContext.request.contextPath}';
        
		//이메일 인증
		$('#btnEmail').click(function(){
			var $btnEmail = $(this);
			var str = '<img src="https://i.stack.imgur.com/qq8AE.gif" width="20">';
			console.log("clicked!");
			var data = {email : $("#email").val(), id : $("#id").val()};
			console.log(data)
            $.ajax(cp + "/member/memberAuth", {
                data : data,
                method : "get",
                beforeSend : function(){
                	$btnEmail.prop("disabled", true).html(str + " 발송 중");
                },
                success : function(data){
                    $btnEmail.prop("disabled", false).html("발송 완료");
                    console.log(data);
                }
			}); //비동기 끝
		}); //이메일 인증 끝
    });//스코프종료

    //해야될 거
	// 비밀번호 일치해야 회원정보 수정되게 
    
    
            
    function jusoCallBack(roadFullAddr,roadAddrPart1,addrDetail,roadAddrPart2,engAddr, jibunAddr,
    zipNo, admCd, rnMgtSn, bdMgtSn,detBdNmList,bdNm,bdKdcd,siNm,sggNm,emdNm,liNm,rn,udrtYn,
    buldMnnm,buldSlno,mtYn,lnbrMnnm,lnbrSlno,emdNo){
    $("#si").val(siNm);
    $("#sgg").val(sggNm);
    $("#emd").val(emdNm);
    $("#roadAddr").val(roadAddrPart1);
    $("#addrDetail").val(addrDetail);
    $("#zipNo").val(zipNo);
    $("#roadFullAddr").val(roadFullAddr);
    $("#jibunAddr").val(jibunAddr);

    // 팝업페이지에서 주소입력한 정보를 받아서, 현 페이지에 정보를 등록합니다.
    // document.form.roadFullAddr.value = roadFullAddr;
    // document.form.roadAddrPart1.value = roadAddrPart1;
    // document.form.roadAddrPart2.value = roadAddrPart2;
    // document.form.addrDetail.value = addrDetail;
    // document.form.engAddr.value = engAddr;
    // document.form.jibunAddr.value = jibunAddr;
    // document.form.zipNo.value = zipNo;
    // document.form.admCd.value = admCd;
    // document.form.rnMgtSn.value = rnMgtSn;
    // document.form.bdMgtSn.value = bdMgtSn;
    // document.form.detBdNmList.value = detBdNmList;
    // /** 2017년 2월 추가제공 **/
    // document.form.bdNm.value = bdNm;
    // document.form.bdKdcd.value = bdKdcd;
    // document.form.siNm.value = siNm;
    // document.form.sggNm.value = sggNm;
    // document.form.emdNm.value = emdNm;
    // document.form.liNm.value = liNm;
    // document.form.rn.value = rn;
    // document.form.udrtYn.value = udrtYn;
    // document.form.buldMnnm.value = buldMnnm;
    // document.form.buldSlno.value = buldSlno;
    // document.form.mtYn.value = mtYn;
    // document.form.lnbrMnnm.value = lnbrMnnm;
    // document.form.lnbrSlno.value = lnbrSlno;
    // /** 2017년 3월 추가제공 **/
    // document.form.emdNo.value = emdNo;
        } 
</script>
</body>
</html>


