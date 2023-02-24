<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>댓글 등록</title>

<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/bootstrap.css">

</head>
<body>

<jsp:include page="nav.jsp"></jsp:include>

<div class="container">
    <form method="post" name="register">
        <div class="mb-0">
            <label for="content" class="form-label">댓글 내용 작성</label>
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