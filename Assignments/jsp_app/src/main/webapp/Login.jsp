<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Login Page</title>
</head>
<body>
	<h2>Login Page</h2>
	<form action="LoginServlet" method="post">
		<label>Username:</label> <input type="text" name="username" required><br>
		<br> <label>Password:</label> <input type="password"
			name="password" required><br>
		<br> <input type="submit" value="Login">
	</form>
</body>
</html>
