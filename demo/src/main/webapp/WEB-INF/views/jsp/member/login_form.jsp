<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>로그인</title>

<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/bootstrap.css">

</head>
<body>

<jsp:include page="../nav.jsp"></jsp:include>

<div class="container">
    <h5 class="my-3 border-bottom pb-2">로그인</h5>
    <form action="/user/login" method="post" name="register">
        <div class="mb-2">
            <label for="username" class="form-label">사용자 ID</label>
            <input type="text" name="username" id="username" class="form-control" value="${username}">
        </div>
        
        <div class="mb-2">
            <label for="password" class="form-label">비밀번호</label>
            <input type="password" name="password" id="password" class="form-control" value="${password}">
        </div>

        <input type="submit" value="로그인" class="btn btn-primary my-2" >
    </form>
</div>

</body>
</html>