<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Welcome</title>
</head>
<body>
	<h2>
		Welcome,
		<%=request.getAttribute("username")%>!
	</h2>
	<p>
		Course:
		<%=request.getAttribute("course")%></p>
	<p>
		Login Time:
		<%=request.getAttribute("loginTime")%></p>
	<p>
		Greeting: <strong>Have a great day ahead!</strong>
	</p>
</body>
</html>