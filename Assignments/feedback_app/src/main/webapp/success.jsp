<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Feedback Submitted</title>
<link rel="stylesheet" href="css/style.css">
</head>
<body>
	<jsp:include page="header.jsp" />
	<div class="container success">
		<div class="confetti">
			<h1>✔ Submitted Successfully</h1>
			<p class="big">
				Thank you, <strong><%=request.getAttribute("name")%></strong>!
			</p>
			<p>Your feedback has been recorded. Keep learning & shining</p>
			<br><br>
			<a href="index.jsp" class="btn">Give Another Feedback</a>
		</div>
	</div>
	<jsp:include page="footer.jsp" />
</body>
</html>
