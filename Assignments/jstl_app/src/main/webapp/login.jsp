<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1" />
    <title>Login</title>
    <link rel="stylesheet" href="css/dashboard.css" />
    <link href="https://fonts.googleapis.com/css2?family=Inter:wght@400;500;600&display=swap" rel="stylesheet">
</head>
<body>
<div class="container narrow">
    <header class="page-header">
        <h1>Sign In</h1>
    </header>
    <main>
        <section class="panel">
            <form action="login" method="post" class="auth-form">
                <label for="email">Email / Username</label>
                <input id="email" name="email" type="text" required placeholder="Enter your username"/>
                <label for="password">Password</label>
                <input id="password" name="password" type="password" required placeholder="Enter your password"/>
                <button class="btn full" type="submit">Login</button>
            </form>
            <p class="muted">New user? <a href="register.jsp">Register here</a></p>
            <c:if test="${not empty errorMessage}">
                <p class="error-message">${errorMessage}</p>
            </c:if>
        </section>
    </main>
</div>
</body>
</html>