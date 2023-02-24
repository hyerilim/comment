<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/bootstrap.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/snow.css">

</head>
<body style="padding-top: 95px;">
<header style="position:fixed; top: 10px; left: 0; right: 0; z-index:10;" class="navbar navbar-expand-lg navbar-dark bg-dark">
  <div class="container-fluid">
    <a class="navbar-brand" href="/main">Main</a>
    <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarColor01" aria-controls="navbarColor01" aria-expanded="false" aria-label="Toggle navigation">
      <span class="navbar-toggler-icon"></span>
    </button>
    <div class="collapse navbar-collapse" id="navbarColor01">
      <ul class="navbar-nav me-auto">
        <li class="nav-item">
          <a class="nav-link active" href="/question/list">질문리스트
            <span class="visually-hidden">(current)</span>
          </a>
        </li>
        <li class="nav-item">
          <a class="nav-link" href="#">자랑하기</a>
        </li>
        <li class="nav-item">
          <a class="nav-link" href="#">상점</a>
        </li>
        <li class="nav-item">
          <a class="nav-link" href="#">게임</a>
        </li>
        <!-- <li class="nav-item dropdown">
          <a class="nav-link dropdown-toggle" data-bs-toggle="dropdown" href="#" role="button" aria-haspopup="true" aria-expanded="false">Dropdown</a>
          <div class="dropdown-menu">
            <a class="dropdown-item" href="#">Action</a>
            <a class="dropdown-item" href="#">Another action</a>
            <a class="dropdown-item" href="#">Something else here</a>
            <div class="dropdown-divider"></div>
            <a class="dropdown-item" href="#">Separated link</a>
          </div>
        </li> -->
      </ul>
      
      	<sec:authorize access="hasAnyRole('ROLE_ADMIN')">
        		<a class="btn btn-outline-warning" href="#">관리자 페이지</a>
		</sec:authorize>
      
      	<div class="mx-3">
      	<sec:authorize access="isAnonymous()">
      		<a class="btn btn-outline-light btn-sm" href="/user/login">로그인</a>
          	<a class="btn btn-outline-light btn-sm" href="/user/signup">회원가입</a>
      	</sec:authorize>
      	
      	<sec:authorize access="isAuthenticated()">	
			<a class="btn btn-outline-light btn-sm" href="/user/logout">로그아웃</a>
			<a class="btn btn-outline-light btn-sm" href="/user/myPage">마이페이지</a>
		</sec:authorize>         
        </div>
      
      <!-- <form class="d-flex">
        <input class="form-control me-sm-2" type="search" placeholder="검색어를 입력하세요">
        <button class="btn btn-secondary my-2 my-sm-0" type="submit">Search</button>
      </form> -->
    </div>
  </div>
</header>

<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/bootstrap.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/snow.js"></script>
</body>
</html>