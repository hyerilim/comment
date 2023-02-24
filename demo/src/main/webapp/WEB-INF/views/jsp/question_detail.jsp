<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>질문 상세 페이지</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/bootstrap.css">
</head>
<body>
<!-- 네비게이션 링크 -->
<jsp:include page="nav.jsp"></jsp:include>
<!-- Page content-->
<div class="container py-4" id="up_up">
    <div class="row">
        <div class="col-lg-8">
            <div class="card mb-4">
            <div class="small text-muted">${question.createDate}</div>
            <div class="small text-muted">글쓴이 : ${question.author.username}</div>
            <div class="card-body">
    
            <h2 class="card-title">${question.subject}</h2>
        
            <p class="card-text">${question.content}</p>
            <div class=""> 
            <c:choose>
				<c:when test="${empty question.images}">
					<!-- <a href="#!"><img src="/resources/save/noimage.jpg"></a> -->
				</c:when>
				<c:otherwise>
            		<a href="#!">이전코드error<img src="/resources/save/${question.images}"></a>
            	</c:otherwise>
            </c:choose>
           
           
           <div id="imageInsert">
           </div> 
           </div>
<%--             <resources mapping="/save/**" location="file:///C:/work/test5/demo/src/main/webapp/WEB-INF/views/save" />
            1<img src="<spring:url value ='/save/${question.images}'/>">
            2<img src="C:\work\test5\demo\src\main\webapp\WEB-INF\views\save\TEST.png">
            3<img src="/save/${question.images}">
            4<a href="#!"><img src="<%= session.getServletContext().getRealPath("/WEB-INF/views/save")%>/${question.images}"></a>
            5<img src="./TEST.png">
            6<img src="save/TEST.png">
            7<img src="/demo/src/main/webapp/WEB-INF/views/save/noimage.jpg">
            8<img src="/resource/static/save/noimage.jpg">
            이미지명: ${question.images}
            9<img src="${pageContext.request.contextPath}/resources/static/save/noimage.jpg">
            10<img src="<%=request.getContextPath()%>/WEB-INF/views/save/${question.images}">
            11<img src="${pageContext.request.contextPath}/WEB-INF/views/save/${question.images}">
            12<img src="/resources/save/${question.images}">
            <a href="#!">정답!!<img src="/resources/save/noimage.jpg"></a>
            13<img src="/resources/save/${question.images}">
            14<img src="/static/save/${question.images}"> --%>
            
            <!-- <a href="#!"><img class="card-img-top" src="https://dummyimage.com/850x350/dee2e6/6c757d.jpg" alt="..." /></a> -->
        </div>
    </div>
    <sec:authorize access="isAuthenticated()">
    <sec:authentication property="principal" var="principal"/> 
    	<c:if test="${principal.username == question.author.username}">
     	<div class="">
            <a href="/question/modify/${question.id}" class="btn btn-sm btn-secondary">질문 수정</a>
            <button class="btn btn-sm btn-secondary" onclick="deleteQuestionConfirm()">질문 삭제</button>
   		</div>
    	</c:if>      
   	</sec:authorize>
   	</div>

 
         <!-- Side widgets-->
        <div class="col-lg-4">          
            <!-- Categories widget-->
            <div class="card mb-4">
                <div class="card-header">카테고리</div>
                <div class="card-body">
                    <div class="row">
                        <div class="col-sm-6">
                            <ul class="list-unstyled mb-0">
                                <li><a href="#!">Web Design</a></li>
                                <li><a href="#!">HTML</a></li>
                                <li><a href="#!">Freebies</a></li>
                            </ul>
                        </div>
                        <div class="col-sm-6">
                            <ul class="list-unstyled mb-0">
                                <li><a href="#!">JavaScript</a></li>
                                <li><a href="#!">CSS</a></li>
                                <li><a href="#!">Tutorials</a></li>
                            </ul>
                        </div>
                    </div>
                </div>
            </div>
            <!-- Side widget-->
            <div class="card mb-4">
                <div class="card-header">관련 질문</div>
                <div class="card-body">
                	<a href="#">관련 질문 1</a>
                </div>
            </div>
            
            <form action="/question/list" method="get" id="searchForm">
            <div class="card mb-4">
                <div class="card-header">게시물 검색</div>
                <div class="card-body">
                    <div class="input-group"> 
              		<select class="form-select" name="columns">
                    	<option ${(columns=="subject")?"selected":""}  value="subject">제목</option>
                    	<option ${(columns=="content")?"selected":""}  value="content">내용</option>
                	</select> 
                	<input type="text" class="form-control" name="keyword" value="${param.keyword}" placeholder="검색어">
                	<button class="btn btn-secondary" type="submit" id="btn_search">검색</button>
					
                    </div>
                </div>
            </div>
            </form>
        </div>
    </div>
</div>
 

 <!-- Comments section-->
<div class="container mb-5">
    <div class="card bg-light">
        <div class="card-body">
            <!-- Comment form-->        
   		<form class="mb-4" action="/answer/create/${question.id}" method="post">
			
		<sec:authorize access="isAnonymous()">
			<textarea textarea class="form-control" rows="5" placeholder="로그인 후 답변을 입력해주세요" name="content" id="content" disabled></textarea>
      	</sec:authorize>
      	
      	<sec:authorize access="isAuthenticated()">
			<textarea textarea class="form-control" rows="5" placeholder="답변을 입력해주세요" name="content" id="content"></textarea>
			<input class="btn btn-primary my-2" type="submit" value="답변등록">
		</sec:authorize>
			
		</form>
            
            <div>${question.answerList.size()}개의 답변이 있습니다.</div>
            <!-- comment-->
            <c:forEach var="answer" items="${question.answerList}" varStatus="status">
            <div class="d-flex py-3">
                <div class="flex-shrink-0"><img class="rounded-circle" src="https://dummyimage.com/50x50/ced4da/6c757d.jpg" alt="..." /></div>
                <div class="ms-3">
                    <div class="fw-bold">${answer.author.username}   
                    <div>
                 		<c:if test="${empty answer.modifyDate}">
	            		${answer.createDate}
	            		</c:if>
	            		<c:if test="${not empty answer.modifyDate}">
	            		${answer.modifyDate} (수정 일시)
	            		</c:if>
                    </div>
                    </div>
                    ${answer.content}
    <sec:authorize access="isAuthenticated()">
    <sec:authentication property="principal" var="principal"/> 
    	<c:if test="${principal.username == answer.author.username}">
					<a href="/answer/modify/${answer.id}" class="btn btn-sm btn-outline-secondary">수정</a>
					<%-- <a href="/answer/delete/${answer.id}" class="btn btn-sm btn-outline-secondary">삭제</a> --%>
					<input type="hidden" id="answerId_${answer.id}" value="${answer.id}">
					<button class="btn btn-sm btn-secondary" onclick="deleteAnswerConfirm('${answer.id}')">삭제</button>
        </c:if>
            		<a href="/comment/create/answer/${answer.id}" class="small text-warning"><small>.. 댓글 추가 ..</small></a>
        </sec:authorize>
            
            
            <c:forEach var="comment" items="${answer.commentList}">
            <div class="d-flex mt-1">
            <div class="flex-shrink-0"><img class="rounded-circle" src="https://dummyimage.com/50x50/ced4da/6c757d.jpg" alt="..." /></div>
            <div class="ms-3 small">
           			<div class="fw-bold">${comment.author.username} 
            			<c:if test="${empty comment.modifyDate}">
	            		${comment.createDate}
	            		</c:if>
	            		<c:if test="${not empty comment.modifyDate}">
	            		${comment.modifyDate} (수정 일시)
	            		</c:if>
	            	</div>
            	${comment.content}
            
    	<sec:authorize access="isAuthenticated()">
    	<sec:authentication property="principal" var="principal"/> 
    	<c:if test="${principal.username == comment.author.username}">
            	<a href="/comment/modify/${comment.id}" class="small text-warning">수정 /</a>
            	<a href="/comment/delete/${comment.id}" class="small text-warning">삭제</a>
        </c:if>
    	</sec:authorize>
            </div>
            </div>
            </c:forEach>
                
                </div> 
            </div>
            <!-- 답변 앵커--><a id="answer_${answer.id}"></a>               
            </c:forEach>           
        </div>
    </div>
	<a href="#up_up">위로 이동하기</a>
</div>

<script type='text/javascript'> 
// 질문 삭제 확인
function deleteQuestionConfirm(){
	if ( confirm("삭제?") ) { 
		location.href = "/question/delete/${question.id}";
	}
}

// 답변 삭제 확인 현재 주석처리함.
function deleteAnswerConfirm(id){
	let answerId = document.getElementById("answerId_"+id).value;
	if ( confirm("삭제?") ) { 
		//console.log(answerId);
		location.href = "/answer/delete/"+answerId;
	}
}

window.onload = function(){
	let str ='${question.images}';
	let str2 = str.replace(/]$/, '');
	let newStr = str2.replace('[', '');
	let arr = newStr.split(', ');
	
	for (let i = 0; i < arr.length; i++) {
		console.log(arr[i]);

		let img = arr[i];
		const element = document.getElementById('imageInsert');
		const imgIn = document.createElement("img");
		
		imgIn.src = '/resources/save/'+img;
		
		element.appendChild(imgIn);
	}
}
</script>

</body>
</html>