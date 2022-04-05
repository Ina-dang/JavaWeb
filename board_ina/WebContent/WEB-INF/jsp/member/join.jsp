<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<jsp:include page="../common/head.jsp"/>        
</head>
<body>
    <jsp:include page="../common/nav.jsp"/>
<!-- 회원가입 -->
<div class="signup">
    <h3><label for="uname" >Maple Story ID 생성</label></h3>
    <h6>하나의 Maple Strory 아이디로 모든 서비스를 이용할 수 있습니다.</h6>
    <form method="post" id="form">
        <div>
            <div>
                <p>아이디</p>
                <span>
                    <label for="id"></label>
                    <input type="text" name="id" id="id" maxlength="20">
                    <button class="btn btn-dark btn-block" type="button" id="btnId"> 중복확인 </button>
                    <input type="hidden" value="1" id="chkId">
                </span>
                <!-- 스크립트로 구현할 에러메세지  -->
                <span class = "error-next-box"></span>
            </div>
            <div>
                <p>비밀번호</p>
                <span>
                    <label for="pw"></label>
                    <input type="password" name="pw" id="pw" maxlength="20">
                </span>
                <!-- 스크립트로 구현할 에러메세지  -->
                <span class = "error-next-box"></span>
            </div>
            <div>
                <p>비밀번호 확인</p>
                <span>
                    <label for="pw2"></label>
                    <input type="password" name="pw2" id="pw2" maxlength="20">
                </span>
                <!-- 스크립트로 구현할 에러메세지  -->
                <span class = "error-next-box"></span>
            </div>
            <div>
                <p>이름</p>
                <span>
                    <label for="name"></label>
                    <input type="text" name="name" id="name" maxlength="20">
                </span>
            </div>
            <div>
                <p>본인 확인 이메일</p>
                <span>
                    <label for="emal"></label>
                    <input type="email" name="email" id="email" >
                </span>
                <span>
                    <button class="btn btn-dark btn-block" type="button" id="btnEmail"> 중복확인 </button>
                    <input type="hidden" value="1" id="chkEmail">
                </span>
                <!-- 스크립트로 구현할 에러메세지  -->
                <span class = "error-next-box"></span>
            </div>
            
            <!-- 주소 -->
            <div>
                <p>주소</p>
                <span>
                    <input id="roadAddr" name="roadAddr" readonly/>
                    <input id="addrDetail" name="addrDetail" readonly/>
                </span>
                <span>
                    <input name="roadFullAddr" id="roadFullAddr" >
                </span>
                <span>
                	<button class="btn btn-dark btn-block" type="button" id="btnSearchAddr"> 주소검색 </button>
                </span>
                <input type="hidden" name="si" id="si">
                <input type="hidden" name="sgg" id="sgg">
                <input type="hidden" name="emd" id="emd">
                <input type="hidden" name="zipNo" id="zipNo">
                <input type="hidden" name="jibunAddr" id="jibunAddr">
            </div>
        </div>
        <!-- 가입하기버튼 -->
        <div>
         <button class="btn btn-dark btn-block btn-join">가입하기</button>
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
        const cp = '${cp}';
        
        //아이디 중복확인
        $('#btnId').click(function(){
            console.log("clicked!");
            var id = {id : $('#id').val()}
            $.ajax(cp + "/member/findMember", {
                data : id,
                method : "get",
                success : function(data){
          			// console.log(data);
                    $("#chkId").val(data);
                }
            });
        });
        
        //이메일 중복확인
        $('#btnEmail').click(function() {
            console.log("clicked!");
            var email = {email : $('#email').val()}
            $.ajax(cp + "/member/findMember", {
                data : email,
                method : "get",
                success : function (data) {
                    $("#chkEmail").val(data);
                }
            });
        });

        //인증 완료 후 값 변경 시 다시 되돌리기
        $('#id').change(function() {
            $('#chkId').val(1);
        })
        
        $('#email').change(function() {
            $('#chkEmail').val(1);
        })
        
        //중복체크 해라
        $('#form').submit(function() {
            if ($('#chkId').val()){
                alert("아이디 중복체크를 확인 해주세요");
                return false;
            }
            if ($('#chkEmail').val()){
                alert("이메일 중복체크를 확인 해주세요");
                return false;
            }
        });
    })

    //해야될 거
    /*중복확인 됐을 때 알려주기
      비밀번호 확인 일치, 불일치 여부 알려주기
    */
    
    
            
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
