<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>마이페이지</title>
</head>
<body>

[마이페이지]
오늘의 할 일 
<form id="todo-form">
    <input required type="text" placeholder="할 일 입력 후 엔터!!"/>
</form>
<ul id="todo-list"></ul>
내가 쓴 글, 추천
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/todo.js"></script>
</body>
</html>