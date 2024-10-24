<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<html>
<head>
    <title>로그인 페이지</title>
    <!-- 스타일과 스크립트 추가 가능 -->
</head>

<body>
    <div class="login-form">
        <!-- 시스템 이름 -->
        <h1>DaeKuen16</h1>

        <!-- 로그인 폼 -->
        <h2>Login</h2>
        
        <!-- 에러 메시지 출력 -->
        <c:if test="${not empty error}">
            <p class="error-message">${error}</p>
        </c:if>

        <form action="login" method="post">
            <input type="text" name="username" placeholder="Username" required>
            <input type="password" name="password" placeholder="Password" required>
            <input type="submit" value="Login">
        </form>
    </div>
</body>
</html>
