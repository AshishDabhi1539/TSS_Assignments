<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Error</title>
<link rel="stylesheet" href="css/style.css">
</head>
<body>
	<jsp:include page="header.jsp" />
	<div class="container error">
		<h1>Oops!</h1>
		<p style="color: #f66;"><%=request.getAttribute("error") != null ? request.getAttribute("error") : "Feedback not submitted"%></p>
		<br>
		<a href="index.jsp" class="btn">Back to Form</a>
	</div>
	<jsp:include page="footer.jsp" />
</body>
</html>
