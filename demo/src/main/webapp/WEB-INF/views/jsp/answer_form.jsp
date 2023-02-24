<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>답변 수정</title>

<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/bootstrap.css">

</head>
<body>

<jsp:include page="nav.jsp"></jsp:include>

<div class="container">
    <h5 class="my-3 border-bottom pb-2">답변 수정</h5>
    <form method="post" name="register">

        <div class="mb-0">
            <label for="content" class="form-label">내용</label>
            <textarea name="content" id="content" class="form-control" rows="10">${content}</textarea>
        </div>
        <input type="submit" value="저장하기" class="btn btn-primary my-2" onclick="return check()">
    </form>
</div>

<script type="text/javascript">
function check(){
	if(document.register.content.value.length === 0){
		alert('내용을 입력해주세요');
		register.content.focus();
		return false;
	}
	return true;
}
</script>

</body>
</html>