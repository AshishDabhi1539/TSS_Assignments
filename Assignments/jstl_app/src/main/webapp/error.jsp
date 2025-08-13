<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8" />
<title>Error</title>
<link rel="stylesheet" href="css/dashboard.css" />
</head>
<body>
	<div class="container narrow">
		<h2>Oops — something went wrong</h2>
		<p class="error-message">${requestScope.errorMessage}</p>
		<a class="btn" href="login.jsp">Back to login</a>
	</div>
</body>
</html>
