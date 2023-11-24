<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

	<c:forEach items="${boardList}" var="boardData" varStatus="loop">
    <div class="accordion" id="accordionBoard${loop.index}">
        <!-- 게시물 1 -->
        <div class="card border-0">
            <div class="card-header border-0 d-flex justify-content-between bg-color p-0" id="heading${loop.index}">
                <div class="d-flex align-center justify-start">
                    <button class="btn bg-dark text-light mr-3" disabled>${boardData.boardSubCategory}</button>
                    <h4 class="mb-0 text-light justify-content mt-1">
                        ${boardData.boardTitle}
                    </h4>
                </div>
                <button class="btn outline" type="button" data-toggle="collapse" data-target="#collapse${loop.index}" aria-expanded="true" aria-controls="collapse${loop.index}">
                    <i class="fas fa-chevron-down" style="color: #0275d8;"></i>
                </button>
            </div>
            <div id="collapse${loop.index}" class="p-0 collapse bg-color" aria-labelledby="heading${loop.index}" data-parent="#accordionBoard${loop.index}">
                <div class="card-body">
                    ${boardData.boardContent}
                </div>
            </div>
        </div>   
        <hr class="board-line mt-4 mb-4">
    </div>
</c:forEach>
