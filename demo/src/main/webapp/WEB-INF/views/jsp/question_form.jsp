<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>질문 등록</title>

<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/bootstrap.css">

</head>
<body>

<jsp:include page="nav.jsp"></jsp:include>

<div class="container">
    <h5 class="my-3 border-bottom pb-2">질문등록</h5>
    <form method="post" name="register" enctype="multipart/form-data">
        <div class="mb-2">
            <label for="subject" class="form-label">제목</label>
            <input type="text" name="subject" id="subject" class="form-control" maxlength="200" value="${subject}">
        </div>
      <a href="#" class="btn btn-sm btn-secondary" onclick="return setInnerHTML()">첨부파일 추가하기</a> 
       <div class="mb-2">
  			<label for="formFile" class="form-label">이미지 선택</label>
  			<input class="form-control" type="file" id="images" name="images" multiple="multiple"/>
		</div>
		
		<div id="imageSelector"></div>

		<c:choose>
			<c:when test="${empty images}">
			</c:when>
			<c:otherwise>
            	<a href="#!"><img src="/resources/save/${images}"></a>
            </c:otherwise>
         </c:choose>
		
        <div class="mb-0">
            <label for="content" class="form-label">내용</label>
            <textarea name="content" id="content" class="form-control" rows="10">${content}</textarea>
        </div>
        <input type="submit" value="저장하기" class="btn btn-primary my-2" onclick="return check()">
    </form>
</div>

<script type="text/javascript">
function check(){
	if(document.register.subject.value.length === 0){
		alert('제목을 입력해주세요');
		register.subject.focus();
		return false;
	}
	if(document.register.content.value.length === 0){
		alert('내용을 입력해주세요');
		register.content.focus();
		return false;
	}
	return true;
}

   
// 첨부파일 선택 추가
function setInnerHTML()  {
  const element = document.getElementById('imageSelector');
	element.innerHTML = element.innerHTML +
		"<div id='my_div' class='mb-2'><label for='formFile' class='form-label'>이미지 선택</label><input class='form-control' type='file' id='images' name= 'images' multiple='multiple'/><input type='button' value='첨부파일 삭제' onclick='deleteDiv()'/></div>";
}

function deleteDiv() {
	  const div = document.getElementById('my_div');
	  
	  div.remove();
	} 

</script>

</body>
</html>