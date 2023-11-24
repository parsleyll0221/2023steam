<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<!DOCTYPE html>
<html lang="ko">

<head>
<!-- head 부분 분리 -->
<jsp:include page="../common/head.jsp"></jsp:include>
</head>

<body id="page-top">

	<!-- 전체 Wrapper 시작 -->
	<div id="wrapper">

		<!-- 사이드바 분리 -->
		<jsp:include page="../common/sidebar.jsp"></jsp:include>

		<!-- 컨텐츠 Wrapper 시작 -->
		<div id="content-wrapper" class="d-flex flex-column bg-custom-blue">

			<!-- 메인 컨텐츠 시작 -->
			<div id="content">

				<!-- 탑바 분리 -->
				<jsp:include page="../common/topbar.jsp"></jsp:include>

				<!-- 페이지 컨텐츠 시작 -->
				<div class="container">

					<!-- 여기 내부 부분을 작업하면 됩니다 -->
					
					<div class="library_list_wrap my-5 mx-auto row">
						<div class="library_list_top_wrap w-100 d-flex">
							<h3 class="col-12 col-lg-6 mb-3 text-light font-weight-bold">
								<a href="${cp }/member/profile?memberNo=${member.memberNo}" class="d-inline-block mr-2 text-decoration-none text-primary">
									<c:if test="${member.memberImageVO ne null }">
			                			<img class="img-profile rounded-circle img-fluid w-custom-50 h-custom-50 object-fit-contain-custom border border-secondary border-width-custom-2" src="${cp }/resources/upload/${member.memberImageVO.storedFileName}">
		                			</c:if>
		                			<c:if test="${member.memberImageVO eq null }">
			                			<img class="img-profile rounded-circle img-fluid w-custom-50 h-custom-50 object-fit-contain-custom border border-secondary border-width-custom-2" src="${cp }/resources/img/profile-no-image.webp">
		                			</c:if>
									<span>${member.memberNickname }</span>
								</a><span class="h4">님의 라이브러리</span>
							</h3>
						
							<!-- (PC에서만 보임) 로그인했을 경우, 현재 보고 있는 유저가 나인 경우 여기서 갈 수도 있음 -->
							<c:if test="${mine == true }">
								<div class="d-none d-lg-flex col-6 mb-3 text-light align-items-center justify-content-end">
									<a href="${cp }/library/list?memberNo=${loginMember.memberNo}" class="text-light font-weight-bold mr-3 font-larger-custom text-decoration-none">라이브러리</a>
									<a href="${cp }/purchase/list" class="text-secondary font-larger-custom text-decoration-none">구매내역</a>
								</div>
							</c:if>
						</div>
						
						<hr class="col-12 hr border-color-custom border-width-custom mb-3 mt-1">
				
						<div class="library_filter_wrap mt-3">
							<a href="javascript:;" data-filter="all" class="library_filter_button text-light text-decoration-none p-2 px-3 bg-primary mr-2 d-inline-block rounded">
								전체
							</a>
							<a href="javascript:;" data-filter="favorite" class="library_filter_button text-secondary text-decoration-none p-2 px-3 bg-custom-gray mr-2 d-inline-block rounded">
								<i class="fas fa-bookmark w-100 font-large-custom"></i>
							</a>
							<!-- 숨김은 본인만 보임 -->
							<c:if test="${mine == true }">
								<a href="javascript:;" data-filter="hidden" class="library_filter_button text-secondary text-decoration-none p-2 px-3 bg-custom-gray mr-2 d-inline-block rounded">
									<i class="fas fa-ghost w-100 font-large-custom"></i>
								</a>
							</c:if>
						</div>
						
						<!-- ajax 로딩 시 보여줄 것 -->
						<div class="ajax-loading-spinner-wrap fixed-top position-fixed align-items-center d-none h-100 justify-content-center">
							<div class="spinner-border ajax-loading-spinner mt-5" style="width: 5rem; height: 5rem;" role="status">
								<span class="sr-only">Loading...</span>
							</div>
						</div>
						
						<input type="hidden" name="nowFilter" id="nowFilter" value="all">
				
						<ul class="library_list p-0 list-unstyled w-100">
						
							<c:if test="${libraryList.size() == 0 }">
								<h5 class="mt-4">라이브러리에 게임이 없습니다</h5>
							</c:if>
						
							<c:if test="${libraryList.size() > 0 }">
								<c:forEach items="${libraryList }" var="library">
								
									<!-- 자기 자신 것이 아닐 땐 숨김인 것은 보이지 않게 함 -->
									<c:if test="${not (mine != true and library.isHidden() == true) }">
									
										<c:if test="${library.isFavorite() != true && library.isHidden() != true }">
											<c:set var="bgClass" value="bg-custom-gray"/>
											<c:set var="textClass" value="text-secondary"/>
											<c:set var="favoriteClass" value="text-secondary"/>
											<c:set var="hiddenClass" value="text-secondary"/>
										</c:if>
										<c:if test="${library.isFavorite() == true }">
											<c:set var="bgClass" value="bg-primary"/>
											<c:set var="textClass" value="text-light"/>
											<c:set var="favoriteClass" value="text-warning"/>
											<c:set var="hiddenClass" value="text-light"/>
										</c:if>
										<c:if test="${library.isHidden() == true }">
											<c:set var="bgClass" value="bg-dark"/>
											<c:set var="textClass" value="text-secondary"/>
											<c:set var="favoriteClass" value="text-secondary"/>
											<c:set var="hiddenClass" value="text-info"/>
										</c:if>
										
										<li class="library row m-0 ${bgClass } mt-3 p-3 px-2 d-flex align-items-center" data-libraryno="${library.libraryNo }">
											<div class="<c:if test="${mine != true }">w-100</c:if><c:if test="${mine == true }">w-100-100-custom</c:if> pr-3">
												<a href="${cp}/game/view?gameNo=${library.gameVO.gameNo}" class="w-100 d-lg-flex align-items-center text-decoration-none text-light font-weight-bold">
													<div class="library_game_info_wrap w-100 d-flex align-items-center">
														<img src="${cp }/resources/upload/${library.gameVO.gameImageList[0].storedFileName}" class="w-custom-100 mr-3 d-none d-sm-inline-block" />
														<span class="library_game_name one-line-ellipsis">${library.gameVO.gameName }</span>
													</div>
										
													<span class="library_reg_date m-lg-auto w-100 d-block d-lg-inline w-lg-25 text-lg-right ${textClass} pr-1 mt-3 mt-lg-0">
														<fmt:formatDate value="${library.regDate}" pattern="yyyy. M. d. 등록됨"></fmt:formatDate>
													</span>
												</a>
											</div>
											<!-- 버튼은 본인만 보임 -->
											<c:if test="${mine == true }">
												<div class="w-custom-100 d-flex align-items-center h-100">
													<a href="javascript:;" data-modify="favorite" class="library_modify_button w-50 h-custom-50 d-inline-block text-center align-items-center d-flex text-decoration-none ${favoriteClass}">
														<i class="fas fa-bookmark w-100 fa-lg"></i>
													</a>
													<!-- 숨긴 건 다른 사람한테 안 보임 -->
													<a href="javascript:;" data-modify="hidden" class="library_modify_button w-50 h-custom-50 d-inline-block text-center align-items-center d-flex text-decoration-none ${hiddenClass}">
														<i class="fas fa-ghost w-100 fa-lg"></i>
													</a>
												</div>
											</c:if>
										</li>
									</c:if>
								</c:forEach>
							</c:if>
							
						</ul>
					</div>
					<!-- wish_list_wrap 끝 -->
					
					<script>
					
						// IIFE
						$(function(){
							var contextPath = '${cp}'; // ContextPath
							var loadMoreData = true; // 더 불러올 데이터 있는지 여부
							var nowLoading = false; // 현재 로딩 중 여부
							
							moment.locale('ko'); // moment 라이브러리 로케일을 한국어로 설정
							
							// 패턴에 따라 날짜 형식화
							function formatDate(dateString) {
								const dateObject = new Date(dateString);
								
								return formattedDate = moment(dateObject).format('YYYY. M. D.');
							}
							
							// 필터 전체, 숨김, 즐겨찾기 버튼 누름
							$(".library_list_wrap").on("click", ".library_filter_button", function(){
								const filterType = $(this).data("filter");
								
								console.log(filterType);
								getLibraryList(filterType);
							});
							
							// 숨김 버튼, 즐겨찾기 버튼 누름
							$(".library_list_wrap").on("click", ".library_modify_button", function(){
								const modifyType = $(this).data("modify");
								const libraryNo = $(this).closest("li").data("libraryno");
								
								updateLibrary(modifyType, libraryNo);
							});
							
							// 숨김 버튼, 즐겨찾기 버튼 처리
							function updateLibrary(modifyType, libraryNo){
								// 숨김/숨김해제, 즐겨찾기/즐겨찾기해제 하기
								
								const filterType = $("#nowFilter").val();
								
								$.ajax({
									url: "${cp}/library/modify",
									type: "POST",
									data: {
										// 로그인한 회원 정보는 서버에서 체크
										memberNo: '${member.memberNo}',
										libraryNo: libraryNo,
										modifyType: modifyType,
									},
									beforeSend: function(){
										$(".ajax-loading-spinner-wrap").removeClass("d-none").addClass("d-flex");
										// nowLoading = true;
									},
									complete: function(){
										// $(".ajax-loading-spinner-wrap").addClass("d-none").removeClass("d-flex");
										// nowLoading = false;
									},
									success: function(res){
										if(res.success === true){
											// 숨김이나 즐겨찾기 후 리스트 항상 새로 불러옴 (단 전체 필터로)
											console.log(res);
											
											getLibraryList(filterType);
										}else{
											alert(res.message);
										}
										
									},
									error: function(err){
										console.error(err);
									}
								});
							}
							
							// 라이브러리 리스트 불러옴
							function getLibraryList(filterType){
								// 즐겨찾기가 먼저 오도록 한다
								// order by 즐겨찾기 desc, 라이브러리번호 desc
								
								// filterType은 all, hidden, favorite 중 하나
								if(!filterType){
									filterType = "all";
								}
								
								// 로딩 중인 경우 돌아감
								if(nowLoading === true){
									return;
								}
								
								$.ajax({
									url: "${cp}/library/list",
									type: "POST",
									data: {
										// 로그인한 회원 정보는 서버에서 체크
										memberNo: '${member.memberNo}',
										filterType: filterType,
									},
									beforeSend: function(){
										$(".ajax-loading-spinner-wrap").removeClass("d-none").addClass("d-flex");
										nowLoading = true;
									},
									complete: function(){
										$(".ajax-loading-spinner-wrap").addClass("d-none").removeClass("d-flex");
										nowLoading = false;
									},
									success: function(res){
										if(res.success === true){
											console.log(res);
											
											switch(filterType){
												case 'all':
													$(".library_filter_wrap a").removeClass("text-light").addClass("text-secondary").removeClass("bg-primary").addClass("bg-custom-gray");
													$(".library_filter_wrap a[data-filter='all']").removeClass("text-secondary").addClass("text-light").removeClass("bg-custom-gray").addClass("bg-primary");
													break;
												case 'hidden':
													$(".library_filter_wrap a").removeClass("text-light").addClass("text-secondary").removeClass("bg-primary").addClass("bg-custom-gray");
													$(".library_filter_wrap a[data-filter='hidden']").removeClass("text-secondary").addClass("text-light").removeClass("bg-custom-gray").addClass("bg-primary");
													break;
												case 'favorite':
													$(".library_filter_wrap a").removeClass("text-light").addClass("text-secondary").removeClass("bg-primary").addClass("bg-custom-gray");
													$(".library_filter_wrap a[data-filter='favorite']").removeClass("text-secondary").addClass("text-light").removeClass("bg-custom-gray").addClass("bg-primary");
													break;
												default:
													break;
											}
											
											let libraryListStr = "";
											
											let resDataMine = res.data.mine;
											
											res.data.libraryList.forEach(library => {
												
												<c:if test="${library.isFavorite() != true && library.isHidden() != true }">
													<c:set var="bgClass" value="bg-custom-gray"/>
													<c:set var="textClass" value="text-secondary"/>
													<c:set var="favoriteClass" value="text-secondary"/>
													<c:set var="hiddenClass" value="text-secondary"/>
												</c:if>
												<c:if test="${library.isFavorite() == true }">
													<c:set var="bgClass" value="bg-primary"/>
													<c:set var="textClass" value="text-light"/>
													<c:set var="favoriteClass" value="text-warning"/>
													<c:set var="hiddenClass" value="text-light"/>
												</c:if>
												<c:if test="${library.isHidden() == true }">
													<c:set var="bgClass" value="bg-dark"/>
													<c:set var="textClass" value="text-secondary"/>
													<c:set var="favoriteClass" value="text-secondary"/>
													<c:set var="hiddenClass" value="text-info"/>
												</c:if>

												let bgClass = "";
												let textClass = "";
												let favoriteClass = "";
												let hiddenClass = "";
												
												if(library.isFavorite === true){
													bgClass = 'bg-primary';
													textClass = 'text-light';
													favoriteClass = 'text-warning';
													hiddenClass = textClass;
												}else if(library.isHidden === true){
													bgClass = 'bg-dark';
													textClass = 'text-secondary';
													favoriteClass = textClass;
													hiddenClass = 'text-info';
												}else{
													bgClass = 'bg-custom-gray';
													textClass = 'text-secondary';
													favoriteClass = textClass;
													hiddenClass = textClass;
												}
												
												let libraryButtonStr = "";

												if(resDataMine === true){
													libraryButtonStr += `
														<div class="w-custom-100 d-flex align-items-center h-100">
															<a href="javascript:;" data-modify="favorite" class="library_modify_button w-50 h-custom-50 d-inline-block text-center align-items-center d-flex text-decoration-none \${favoriteClass}">
																<i class="fas fa-bookmark w-100 fa-lg"></i>
															</a>
															<a href="javascript:;" data-modify="hidden" class="library_modify_button w-50 h-custom-50 d-inline-block text-center align-items-center d-flex text-decoration-none \${hiddenClass}">
																<i class="fas fa-ghost w-100 fa-lg"></i>
															</a>
														</div>
													`;
												}
												
												libraryListStr += `
													<li class="library row m-0 \${bgClass } mt-3 p-3 px-2 d-flex align-items-center" data-libraryno="\${library.libraryNo }">
													<div class="\${resDataMine !== true ? 'w-100' : 'w-100-100-custom' } pr-3">
														<a href="${cp}/game/view?gameNo=\${library.gameVO.gameNo}" class="w-100 d-lg-flex align-items-center text-decoration-none text-light font-weight-bold">
															<div class="library_game_info_wrap w-100 d-flex align-items-center">
																<img src="${cp }/resources/upload/\${library.gameVO.gameImageList[0].storedFileName}" class="w-custom-100 mr-3 d-none d-sm-inline-block" />
																<span class="library_game_name one-line-ellipsis">\${library.gameVO.gameName }</span>
															</div>
															
															<span class="library_reg_date m-lg-auto w-100 d-block d-lg-inline w-lg-25 text-lg-right \${textClass} pr-1 mt-3 mt-lg-0">
																\${formatDate(library.regDate)} 등록됨
															</span>
														</a>
													</div>
													<!-- 버튼은 본인만 보임 -->
													\${libraryButtonStr}
												</li>
												`;
											});
											
											$(".library_list").html(libraryListStr);
											$("#nowFilter").val(filterType);
											
										}else{
											alert(res.message);
										}
										
									},
									error: function(err){
										console.error(err);
									}
								});
							}
							
						}); // IIFE END
					
						
					</script>

				</div>
				<!-- 페이지 컨텐츠 끝 -->

			</div>
			<!-- 메인 컨텐츠 끝 -->

		</div>
		<!-- 컨텐츠 Wrapper 끝 -->

	</div>
	<!-- 전체 Wrapper 끝 -->

	<!-- 맨 위로 스크롤 버튼 -->
	<a class="scroll-to-top rounded" href="#page-top"> <i class="fas fa-angle-up"></i>
	</a>

	<!-- 모달 JSP 삽입 -->
	<jsp:include page="../common/modal.jsp"></jsp:include>

</body>

</html>
