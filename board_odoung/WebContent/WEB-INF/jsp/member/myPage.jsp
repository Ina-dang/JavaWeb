<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <!DOCTYPE html>
    <html lang="ko">
    <head>
    	<jsp:include page="../common/head.jsp" />
    </head>
    <body class="bg-primary">
    <%@ include file="../common/nav.jsp" %>
        <div id="layoutAuthentication">
            <div id="layoutAuthentication_content">
                <main>
                    <div class="container">
                        <div class="row justify-content-center">
                            <div class="col-lg-7">
                                <div class="card shadow-lg border-0 rounded-lg mt-5">
                                    <div class="card-header"><h3 class="text-center font-weight-light my-4">My Account</h3></div>
                                    	${memberInfo}
                                        <div class="card-body">
                                            <form method="post" id="form">
                                            <div class="form-floating mb-3 input-group">
                                                <input class="form-control" id="id" type="text" name="id" placeholder="enter" value="${memberInfo.id}" readonly/>
                                                <label for="id">ID</label>
                                            </div>
                                            <div class="row mb-3">
                                                <div class="col-md-6">
                                                    <div class="form-floating mb-3 mb-md-0">
                                                        <input class="form-control" id="inputPassword" name="pw" type="password" placeholder="Create a password" />
                                                        <label for="inputPassword">Password</label>
                                                    </div>
                                                </div>
                                                <div class="col-md-6">
                                                    <div class="form-floating mb-3 mb-md-0">
                                                        <input class="form-control" id="inputPasswordConfirm" name="pw2" type="password" placeholder="Confirm password" />
                                                        <label for="inputPasswordConfirm">Confirm Password</label>
                                                    </div>
                                                </div>
                                            </div>
                                            <div class="form-floating mb-3 ">
                                                <input class="form-control" id="name" name="name" type="text" placeholder="enter name" value="${memberInfo.name}" readonly/>
                                                <label for="name">name</label>
                                            </div>
                                            <div class="form-floating mb-3 input-group">
                                                <input class="form-control" id="email" name="email" type="email" placeholder="enter your email" value="${memberInfo.email}" readonly/>
                                                <label for="email">email</label>
                                                <c:if test="${memberInfo.auth == 0 }">
                                                <button class="btn btn-danger" type="button" id="btnEmail">이메일인증</button>
                                                </c:if>
                                                <c:if test="${memberInfo.auth == 1 }">
                                                <button class="btn btn-success" type="button" id="btnEmail" disabled>인증된 이메일</button>
                                                </c:if>
                                                <input type="hidden" value="1" id="chkEmail">
                                            </div>
                                            <hr>
                                            <button type="button" id="btnSearchAddr" class="mb-3 btn btn-sm btn-secondary">주소검색</button>
                                                
                                            <!-- 주소 -->
                                            <div class="row mb-3">
                                                <div class="col-md-8">
                                                    <div class=" mb-3 mb-md-0">
                                                        <input class="form-control" id="roadAddr" name="roadAddr" readonly/>
                                                    </div>
                                                </div>
                                                <div class="col-md-4">
                                                    <div class=" mb-3 mb-md-0">
                                                        <input class="form-control" id="addrDetail" name="addrDetail" readonly/>
                                                    </div>
                                                </div>
                                                <input type="hidden" name="si" id="si">
                                                <input type="hidden" name="sgg" id="sgg">
                                                <input type="hidden" name="emd" id="emd">
                                                <input type="hidden" name="zipNo" id="zipNo">
                                                <input type="hidden" name="roadFullAddr" id="roadFullAddr">
                                                <input type="hidden" name="jibunAddr" id="jibunAddr">
                                            </div>
                                            <div class="mt-4 mb-0">
                                                <div class="d-grid"><button class="btn btn-primary btn-block" >Create Account</button></div>
                                            </div>
                                            </form>
                                        </div>
                                    <div class="card-footer text-center py-3">
                                        <div class="small"><a href="login.html">Have an account? Go to login</a></div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </main>
            </div>
            <div id="layoutAuthentication_footer">
            	<jsp:include page="../common/footer.jsp"/>
            </div>
        </div>
        <script>
        $(function(){
        	//컨텍스트패쓰 경로지정
            var cp = '${pageContext.request.contextPath}';
            
            //주소찾기
            $("#btnSearchAddr").click(function(){

                var pop = window.open("${pageContext.request.contextPath}/juso","pop","width=570,height=420, scrollbars=yes, resizable=yes");
            });


            //이메일 인증
           $('#btnEmail').click(function(){
        	   var $btnEmail = $(this);
        	   var str = '<img src="https://i.stack.imgur.com/qq8AE.gif" width="20">';
            	console.log("clicked!");
                var data = {email : $("#email").val(), id : $("#id").val()}
                $.ajax(cp + "/member/memberAuth", {
                    data : data,
                    method : "get",
                    //발송전까지 버튼 비활성화
                    beforeSend : function(){
                    	$btnEmail.prop("disabled", true).html(str + " 발송중");
                    },
                    success : function(data){
                        $btnEmail.prop("disabled", false).html("이메일인증");
                        console.log(data);
                    }
                });
            });
            
            //form태그 가 가진 버튼의 submit 막기
            $("#form").submit(function(){
            	if ($("#chkId").val()){
            		alert("id 중복체크 ㄱ");
	                return false;
            	}
            	if ($("#chkEmail").val()){
            		alert("Email 중복체크 ㄱ");
	                return false;
            	}
            })
        });
        function jusoCallBack(roadFullAddr,roadAddrPart1,addrDetail,roadAddrPart2,engAddr, jibunAddr, zipNo, admCd, rnMgtSn, bdMgtSn,detBdNmList,bdNm,bdKdcd,siNm,sggNm,emdNm,liNm,rn,udrtYn,buldMnnm,buldSlno,mtYn,lnbrMnnm,lnbrSlno,emdNo){
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