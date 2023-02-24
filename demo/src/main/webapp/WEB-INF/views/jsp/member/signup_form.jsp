<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원가입</title>

<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/bootstrap.css">

</head>
<body>

<jsp:include page="../nav.jsp"></jsp:include>

<div class="container">
    <h5 class="my-3 border-bottom pb-2">회원가입</h5>
    <form action="/user/signup" method="post" name="register">
        <div class="mb-2">
            <label for="username" class="form-label">사용자 ID</label>
            <input type="text" name="username" id="username" class="form-control" value="${username}">
        </div>
        
        <div class="mb-2">
            <label for="password1" class="form-label">비밀번호</label>
            <input type="password" name="password1" id="password1" class="form-control" value="${password1}">
        </div>
        
        <div class="mb-2">
            <label for="password2" class="form-label">비밀번호 확인</label>
            <input type="password" name="password2" id="password2" class="form-control" value="${password2}">
        </div>
        
        <div class="mb-2">
            <label for="email" class="form-label">이메일</label>
            <input type="email" name="email" id="email" class="form-control" value="${email}">
        </div>

        <input type="submit" value="회원가입" class="btn btn-primary my-2" onclick="">
    </form>
</div>

<script type="text/javascript">

</script>

</body>
</html>