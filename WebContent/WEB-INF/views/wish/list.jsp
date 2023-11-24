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
					
					<div class="wish_list_wrap my-5 mx-auto row">
					
						<div class="wish_list_top_wrap w-100 d-flex">
							<h3 class="col-12 col-lg-6 mb-3 text-light font-weight-bold">
								<a href="${cp }/member/profile?memberNo=${member.memberNo}" class="d-inline-block mr-2 text-decoration-none text-primary">
									<c:if test="${member.memberImageVO ne null }">
			                			<img class="img-profile rounded-circle img-fluid w-custom-50 h-custom-50 object-fit-contain-custom border border-secondary border-width-custom-2" src="${cp }/resources/upload/${member.memberImageVO.storedFileName}">
		                			</c:if>
		                			<c:if test="${member.memberImageVO eq null }">
			                			<img class="img-profile rounded-circle img-fluid w-custom-50 h-custom-50 object-fit-contain-custom border border-secondary border-width-custom-2" src="${cp }/resources/img/profile-no-image.webp">
		                			</c:if>
									<span>${member.memberNickname }</span>
								</a><span class="h4">님의 위시리스트</span>
							</h3>
							
							<!-- (PC에서만 보임) 로그인했을 경우, 현재 보고 있는 유저가 나인 경우 여기서 갈 수도 있음 -->
							<c:if test="${mine == true }">
								<div class="d-none d-lg-flex col-6 mb-3 text-light align-items-center justify-content-end">
									<a href="${cp }/cart" class="text-secondary mr-3 font-larger-custom text-decoration-none">장바구니</a>
									<a href="${cp }/wish/list?memberNo=${loginMember.memberNo}" class="text-light font-weight-bold font-larger-custom text-decoration-none">위시리스트</a>
								</div>
							</c:if>
						</div>
						
						<hr class="col-12 hr border-color-custom border-width-custom mb-3 mt-1">
						
						<!-- ajax 로딩 시 보여줄 것 -->
						<div class="ajax-loading-spinner-wrap fixed-top position-fixed align-items-center d-none h-100 justify-content-center">
							<div class="spinner-border ajax-loading-spinner mt-5" style="width: 5rem; height: 5rem;" role="status">
								<span class="sr-only">Loading...</span>
							</div>
						</div>
						
						<!-- 위시리스트는 라이브러리와 비슷하게 할 것임 -->
						<div class="wish_list w-100 mt-3">
							
							<c:if test="${wishList.size() == 0 }">
								<h5>위시리스트가 비어있습니다</h5>
							</c:if>
						
							<c:if test="${wishList.size() > 0 }">
								<c:forEach items="${wishList }" var="wish">
								
									<li class="wish row m-0 bg-custom-gray mb-3 p-3 px-2 d-flex align-items-center" data-wishno="${wish.wishNo }">
										<div class="<c:if test="${mine != true }">w-100</c:if><c:if test="${mine == true }">w-100-50-custom</c:if> pr-3">
											<a href="${cp}/game/view?gameNo=${wish.gameVO.gameNo}" class="w-100 d-flex align-items-center text-decoration-none text-light font-weight-bold">
												<div class="wish_game_info_wrap w-100 d-flex align-items-center">
													<img src="${cp }/resources/upload/${wish.gameVO.gameImageList[0].storedFileName}" class="w-custom-100 mr-3 d-none d-sm-inline-block" />
													<span class="wish_game_name one-line-ellipsis">${wish.gameVO.gameName }</span>
												</div>
									
												<div class="wish_price_wrap m-lg-auto w-50 text-lg-right text-secondary pr-1">
													<h6 class="font-weight-bold one-line-ellipsis text-right m-0 <c:if test="${not empty wish.gameVO.saleVO}">line-through-custom text-dark mb-1</c:if>">
														<fmt:formatNumber value="${wish.gameVO.gamePrice}"
															pattern="#,###" />
														원
													</h6>

													<c:if test="${not empty wish.gameVO.saleVO}">
														<div class="d-flex float-right">
															<span class="badge badge-danger"> <c:out
																	value="${wish.gameVO.saleVO.saleRate }%"></c:out>
															</span>
															<h6
																class="font-weight-bold one-line-ellipsis text-right m-0 ml-2 text-danger">
																<fmt:formatNumber
																	value="${wish.gameVO.discountedPrice}"
																	pattern="#,###" />
																원
															</h6>
														</div>
													</c:if>
												</div>
											</a>
										</div>
										<!-- 버튼은 본인만 보임 -->
										<c:if test="${mine == true }">
											<div class="w-custom-50 d-flex align-items-center h-100">
												<a href="javascript:;" id="deleteWishButton" class="w-100 h-custom-50 d-inline-block text-center align-items-center d-flex text-decoration-none text-secondary">
													<i class="fas fa-times w-100 fa-lg"></i>
												</a>
											</div>
										</c:if>
									</li>
									
								</c:forEach>
							</c:if>
							
						</div>
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
								
								return formattedDate = moment(dateObject).format('YYYY-MM-DD HH:mm:ss');
							}
							
							// 위시리스트 삭제 버튼 클릭
							$(".wish_list_wrap").on("click", "#deleteWishButton", function(){
								const wishNo = $(this).closest("li").data("wishno");
								
								console.log(wishNo);
								
								deleteWish(wishNo);
							});

							// 위시리스트 삭제
							function deleteWish(wishNo){
								$.ajax({
									url: "${cp}/wish/delete",
									type: "POST",
									data: {
										memberNo: '${member.memberNo}',
										wishNo: wishNo,
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
										// 굳이 다시 불러오지 않고 그냥 해당 요소만 지우도록 하겠다.
										if(res.success === true){
											console.log(res);
											$(".wish[data-wishno='" + wishNo + "']").remove();

											if($(".wish").length == 0){
												$(".wish_list").html("<h5>위시리스트가 비어있습니다</h5>");
											}
												
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
