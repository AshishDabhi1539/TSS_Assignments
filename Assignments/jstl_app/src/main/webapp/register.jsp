<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8" />
<meta name="viewport" content="width=device-width,initial-scale=1" />
<title>Register</title>
<link rel="stylesheet" href="css/dashboard.css" />
</head>
<body>
	<div class="container narrow">
		<h2>Create account</h2>

		<form class="auth-form" action="register" method="post">
			<label for="name">Name</label> <input id="name" name="name"
				type="text" required /> <label for="email">Email</label> <input
				id="email" name="email" type="email" required /> <label
				for="password">Password</label> <input id="password" name="password"
				type="password" required /> <label for="jobTitle">Job Title</label>
			<input id="jobTitle" name="jobTitle" type="text" required /> <label
				for="deptNo">Department No</label> <input id="deptNo" name="deptNo"
				type="number" required />

			<button class="btn full" type="submit">Register</button>
		</form>

		<p class="muted">
			Already registered? <a href="login.jsp">Sign in</a>
		</p>

		<c:if test="${not empty errorMessage}">
			<p class="error-message">${errorMessage}</p>
		</c:if>
		<c:if test="${not empty successMessage}">
			<p class="success-message">${successMessage}</p>
		</c:if>
	</div>
</body>
</html>
