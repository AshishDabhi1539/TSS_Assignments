<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Quiz Result</title>
<link href="styles.css" rel="stylesheet">
</head>
<body>
<c:if test="${empty sessionScope.user}">
    <c:redirect url="login.html"/>
</c:if>
<div class="container">
    <h2>Quiz Completed</h2>
    <p>Hello, <strong>${user.username}</strong>!</p>
    <p>Your Score: <strong>${score}</strong></p>
    <p>Time: <strong>${result.quizDate}</strong></p>
    <form action="logout" method="post">
        <button type="submit">Logout</button>
    </form>
</div>
</body>
</html>
