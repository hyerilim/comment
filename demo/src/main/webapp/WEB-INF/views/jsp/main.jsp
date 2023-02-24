<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>메인화면</title>

<%-- <link rel="stylesheet"  href="<c:url value="../resources/css/bootstrap.css"/>"> --%>
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/bootstrap.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/style.css">

</head>
<body>
<!-- LocalStorage에 저장됨. SessionStorage로 변경? -->
<p>현재 시간</p>
<h2 id="clock">00:00:00</h2>

<form id="login-form" class="hidden">
    <input required maxlength="15" type="text" placeholder="what is your name?"/>
    <input type="submit" value="Log In"/>
</form>

<h1 id="greeting" class="hidden"></h1>

<form id="todo-form">
    <input required type="text" placeholder="할 일 입력 후 엔터!!"/>
</form>
<ul id="todo-list"></ul>

<div id="quote">
    <span></span>
    - <span></span>
</div>

<div>
<a class="" href="/question/list">질문리스트</a>
</div>



<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/bootstrap.js"></script>

    <script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/greetings.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/clock.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/quotes.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/background.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/todo.js"></script>

</body>
</html>