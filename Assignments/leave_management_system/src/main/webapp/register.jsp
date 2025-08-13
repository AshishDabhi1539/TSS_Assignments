<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Register</title>
<style>
body {
	font-family: Arial, sans-serif;
	background: linear-gradient(135deg, #74ebd5, #acb6e5);
	display: flex;
	justify-content: center;
	align-items: center;
	height: 100vh;
	margin: 0;
}

.register-container {
	background: white;
	padding: 30px;
	border-radius: 10px;
	box-shadow: 0 2px 15px rgba(0, 0, 0, 0.2);
	width: 350px;
	text-align: center;
}

.register-container h2 {
	margin-bottom: 20px;
	color: #333;
}

input {
	width: 100%;
	padding: 12px;
	margin: 10px 0;
	border-radius: 5px;
	border: 1px solid #ccc;
	box-sizing: border-box;
}

button {
	width: 100%;
	padding: 12px;
	background: #4CAF50;
	color: white;
	border: none;
	border-radius: 5px;
	cursor: pointer;
	font-size: 16px;
}

button:hover {
	background: #45a049;
}

.error {
	color: red;
	margin-top: 10px;
	font-size: 14px;
}

.success {
	color: green;
	margin-top: 10px;
	font-size: 14px;
}

.login-link {
	margin-top: 15px;
	display: block;
	color: #2196F3;
	text-decoration: none;
}

.login-link:hover {
	text-decoration: underline;
}
</style>
</head>
<body>
	<div class="register-container">
		<h2>Register</h2>
		<form action="register" method="post">
			<input type="text" name="username" placeholder="Username" required>
			<input type="password" name="password" placeholder="Password"
				required> <input type="password" name="confirmPassword"
				placeholder="Confirm Password" required>
			<button type="submit">Register</button>
		</form>

		<c:if test="${param.error == 'nomatch'}">
			<p class="error">Passwords do not match!</p>
		</c:if>
		<c:if test="${param.error == 'exists'}">
			<p class="error">Username already exists!</p>
		</c:if>
		<c:if test="${param.error == 'error'}">
			<p class="error">An error occurred. Please try again!</p>
		</c:if>
		<c:if test="${param.success == 'registered'}">
			<p class="success">Registration successful! Please login.</p>
		</c:if>

		<a href="login" class="login-link">Already have an account? Login
			here</a>
	</div>
</body>
</html>
