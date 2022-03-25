<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <!DOCTYPE html>
    <html lang="ko">
    <head>
    	<jsp:include page="../common/head.jsp" />
    </head>
    <body class="bg-primary">
        <div id="layoutAuthentication">
            <div id="layoutAuthentication_content">
                <main>
                    <div class="container">
                        <div class="row justify-content-center">
                            <div class="col-lg-7">
                                <div class="card shadow-lg border-0 rounded-lg mt-5">
                                    <div class="card-header"><h3 class="text-center font-weight-light my-4">Create Account</h3></div>
                                    <div class="card-body">
                                        <form method="post">
                                            <div class="form-floating mb-3 input-group">
                                                <input class="form-control" id="id" type="text" name="id" placeholder="enter"/>
                                                <button class="btn btn-success" type="button">중복확인</button>
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
                                                <input class="form-control" id="name" name="name" type="text" placeholder="enter name" />
                                                <label for="name">name</label>
                                            </div>
                                            <div class="form-floating mb-3 input-group">
                                                <input class="form-control" id="email" name="email" type="email" placeholder="enter your email" />
                                                <button class="btn btn-danger" type="button">인증메일 발송</button>
                                                <!-- <button class="btn btn-primary" type="button">인증</button> -->
                                                <!-- <button class="btn btn-success" type="button">인증완료</button> -->
                                                <label for="email">email</label>
                                            </div>
                                            <div class="form-floating mb-3">
                                                <input class="form-control" id="code" name="code" type="email" placeholder="enter your email" />
                                                <label for="email">인증코드</label>
                                            </div>
                                            <hr>
                                           	<button type="button" id="btnSearchAddr" class="mb-3 btn btn-sm btn-secondary">주소검색</button>
                                            	
                                               <div class="row mb-3">
                                                <div class="col-md-4">
                                                    <div class=" mb-3 mb-md-0">
                                                        <input class="form-control" id="addr1" name="addr1" readonly/>
                                                    </div>
                                                </div>
                                                <div class="col-md-4">
                                                    <div class=" mb-3 mb-md-0">
                                                        <input class="form-control" id="addr2" name="addr2" readonly/>
                                                    </div>
                                                </div>
                                                <div class="col-md-4">
                                                    <div class=" mb-3 mb-md-0">
                                                        <input class="form-control" id="addr3" name="addr3" readonly/>
                                                    </div>
                                                </div>
                                            </div>
                                            <div class="form-floating mb-3">
                                                <input class="form-control" id="addr" name="addr" type="text" placeholder="enter address" />
                                                <label for="name">상세주소</label>
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
            $("#btnSearchAddr").click(function(){
                var pop = window.open("${pageContext.request.contextPath}/juso","pop","width=570,height=420, scrollbars=yes, resizable=yes");
            });
        });
        function jusoCallBack(roadFullAddr,roadAddrPart1,addrDetail,roadAddrPart2,engAddr, jibunAddr, zipNo, admCd, rnMgtSn, bdMgtSn,detBdNmList,bdNm,bdKdcd,siNm,sggNm,emdNm,liNm,rn,udrtYn,buldMnnm,buldSlno,mtYn,lnbrMnnm,lnbrSlno,emdNo){
            $("#addr1").val(siNm);
            $("#addr2").val(sggNm);
            $("#addr3").val(emdNm);
            $("#addr").val(addrDetail);
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