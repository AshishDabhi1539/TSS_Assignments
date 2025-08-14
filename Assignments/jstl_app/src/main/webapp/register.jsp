<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1" />
    <title>Register</title>
    <link rel="stylesheet" href="css/dashboard.css" />
    <link href="https://fonts.googleapis.com/css2?family=Inter:wght@400;500;600&display=swap" rel="stylesheet">
</head>
<body>
<div class="container narrow">
    <header class="page-header">
        <h1>Create Account</h1>
    </header>
    <main>
        <section class="panel">
            <form class="auth-form" action="register" method="post">
                <label for="name">Name</label>
                <input id="name" name="name" type="text" required placeholder="Enter your name"/>
                <label for="email">Email</label>
                <input id="email" name="email" type="email" required placeholder="Enter your email"/>
                <label for="password">Password</label>
                <input id="password" name="password" type="password" required placeholder="Enter password"/>
                <label for="jobTitle">Job Title</label>
                <input id="jobTitle" name="jobTitle" type="text" required placeholder="Your job title"/>
                <label for="deptNo">Department No</label>
                <input id="deptNo" name="deptNo" type="number" required placeholder="Department number"/>
                <button class="btn full" type="submit">Register</button>
            </form>
            <br>
            <p class="muted">Already registered? <a href="login.jsp">Sign in</a></p>
            <c:if test="${not empty errorMessage}">
                <p class="error-message">${errorMessage}</p>
            </c:if>
            <c:if test="${not empty successMessage}">
                <p class="success-message">${successMessage}</p>
            </c:if>
        </section>
    </main>
</div>
</body>
</html>