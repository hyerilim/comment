<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>질문 리스트</title>

<%-- <link rel="stylesheet"  href="<c:url value="../resources/css/bootstrap.css"/>"> --%>
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/bootstrap.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/snow.css">

</head>
<body>


<!-- 네비게이션 링크 -->
<jsp:include page="nav.jsp"></jsp:include>
<%-- <c:import url="main.jsp"></c:import> --%>


<div class="card shadow mb-2">
	<div class="card-header">
		<form action="/question/list" method="get" id="searchForm">
        <div style="float: right;" class="col-6">
            <div class="input-group">
             <select class="form-select" name="columns">
                    <option ${(columns=="subject")?"selected":""}  value="subject">질문 제목</option>
                    <option ${(columns=="content")?"selected":""}  value="content">질문 내용</option>
                </select> 
                <input type="text" class="form-control" name="keyword" value="${param.keyword}" placeholder="검색어를 입력하세요">
                <button class="btn btn-secondary" type="submit" id="btn_search">검색</button>
            </div>
        </div>
		</form>
	</div>
	<div class="card-body">
	<div class="table-responsive">
	
	 <div style="float: right;" class="">
		<a href="/question/create" class="btn btn-outline-light btn-sm">질문 등록하기</a>  
	</div>
	
	<table class="table table-hover" id="dataTable" width="100%" cellspacing="0">
		<thead>
			<tr class="table-primary">
	            <th width="10%">번호</th>
	            <th width="20%">글쓴이</th>
	            <th>제목</th>
	            <th width="20%">작성일시</th>
	        </tr>
	    </thead>
	<c:forEach var="question" items="${paging.content}" varStatus="status">
	    <tbody>
	        <tr class="table-active">
	            <td>${paging.totalElements - (paging.number * paging.size) - status.index}</td>
	            <td>${question.author.username}</td>
	            <td>
	            	<a href="detail/${question.id}">${question.subject}</a>
	            	<c:if test="${question.answerList.size() > 0}">
	            	<span class="text-warning small ms-2">
	            		${question.answerList.size()}
	            	</span>
	            	</c:if>
	            </td>
	            <%-- <fmt:formatDate pattern="yyyy-MM-dd HH:mm" value="${question.createDate}" var="da"}/> --%>
	            <td>
	            <c:if test="${empty question.modifyDate}">
	            	${question.createDate}
	            </c:if>
	            <c:if test="${not empty question.modifyDate}">
	            	${question.modifyDate} (수정 일시)
	            </c:if>
	            </td> 
	        </tr>
	    </tbody>
	</c:forEach> 
	</table>
<!-- 페이징 1 -->
<%-- <div class="">
<c:if test="${!empty paging}">
  <ul class="pagination justify-content-center">
    <li class="page-item  ${(paging.number-1 < 0)?'disabled':''}">
      <a class="page-link " href="?page=${paging.number-1}">&laquo;</a>
    </li>
    
    <c:forEach var="i" begin="0" end="${paging.totalPages-1}">
		<c:if test="${paging.number+i < paging.totalPages}">
    	<li class="page-item ${(paging.number == paging.number+i)?'active':''}">
      		<a class="page-link" href="?page=${paging.number+i}">${paging.number+i}</a>
    	</li>
   		</c:if>
	</c:forEach>   
    
    <li class="page-item ${(paging.number+1 >= paging.totalPages)?'disabled':''}">
      <a class="page-link" href="?page=${paging.number+1}">&raquo;</a>
    </li>
  </ul>
</c:if>
</div> --%>


<!-- Pagination-->
<c:set var="page" value="${(empty param.page)?1:param.page}"></c:set>
<c:set var="startNum" value="${page -(page-1) % 5}"></c:set>
<c:set var="lastNum" value="${paging.totalPages}"></c:set> 
  
        <div aria-label="Pagination">
            
            <ul class="pagination justify-content-center my-2">
                <li class="page-item ${(paging.number-1 < 0)?'disabled':''}">
				<a class="page-link" href="?page=1&columns=${param.columns}&keyword=${param.keyword}">
                    <span> 첫 페이지 </span>
                </a>
				</li>
                
                <c:if test="${startNum-1 > 0}">
                <li class="page-item"><a class="page-link" href="?page=${startNum - 1}&columns=${param.columns}&keyword=${param.keyword}">이전</a></li>
				</c:if>
				<c:if test="${startNum-1 <= 0}">
					<span class="page-item page-link" onclick="alert('이전 페이지가 없습니다.')">이전</span>
				</c:if>
				<c:forEach var="i" begin="0" end="4">
					<!-- 마지막 게시물이 있는 페이지까지만 표시 -->
					<c:if test="${(i+startNum) <= lastNum }">
						<!-- // 해당 페이지 인 경우, 스타일 지정 -->									
                <li class="page-item ${(page==(i+startNum))?'active':''}"><a class="page-link" href="?page=${i+startNum}&columns=${param.columns}&keyword=${param.keyword}">${i+startNum}</a></li>
				</c:if>
				</c:forEach>
            	<c:if test="${startNum + 4 < lastNum}">
            	<li class="page-item"><a class="page-link" href="?page=${startNum + 5}&columns=${param.columns}&keyword=${param.keyword}">다음</a></li>
				</c:if>
				<c:if test="${startNum + 4 >= lastNum}">
						<span class="page-item page-link" onclick="alert('다음 페이지가 없습니다.')">다음</span>
				</c:if>
				
				<li class="page-item ${(paging.number+1 >= paging.totalPages)?'disabled':''}">
				<a class="page-link" href="?page=${lastNum}&columns=${param.columns}&keyword=${param.keyword}">
                    <span> 마지막 페이지 </span>
                </a>
			</li>
				
            </ul>
        </div>   
</div>
</div>
</div>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/snow.js"></script>
 <%-- <script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/bootstrap.js"></script> --%>       
</body>
</html>