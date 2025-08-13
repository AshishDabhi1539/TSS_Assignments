<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8" />
<meta name="viewport" content="width=device-width,initial-scale=1" />
<title>Login</title>
<link rel="stylesheet" href="css/dashboard.css" />
</head>
<body>
	<div class="container narrow">
		<h2>Sign in</h2>

		<form action="login" method="post" class="auth-form">
			<label for="email">Email / Username</label> <input id="email"
				name="email" type="text" required /> <label for="password">Password</label>
			<input id="password" name="password" type="password" required />

			<button class="btn full" type="submit">Login</button>
		</form>

		<p class="muted">
			New user? <a href="register.jsp">Register here</a>
		</p>

		<c:if test="${not empty errorMessage}">
			<p class="error-message">${errorMessage}</p>
		</c:if>
	</div>
</body>
</html>
