<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.util.Date"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Student Portal</title>
</head>
<body>

	<h1><%="Welcome to Student Portal"%></h1>

	<p>
		<strong>Current Date & Time:</strong>
		<%=new Date()%></p>

	<%!String studentName = "Ashish Dabhi";
	String course = "Computer Engineering";
	int marks = 87;
	String grade;%>

	<%
	if (marks >= 90) {
		grade = "A";
	} else if (marks >= 75 && marks < 90) {
		grade = "B";
	} else if (marks >= 50 && marks < 75) {
		grade = "C";
	} else {
		grade = "F";
	}
	%>

	<h3>Student Information:</h3>
	<p>
		<strong>Name:</strong>
		<%=studentName%></p>
	<p>
		<strong>Course:</strong>
		<%=course%></p>
	<p>
		<strong>Marks:</strong>
		<%=marks%></p>
	<p>
		<strong>Grade:</strong>
		<%=grade%></p>

</body>
</html>
