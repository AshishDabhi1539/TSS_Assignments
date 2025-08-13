<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>JSTL Demo - Index</title>
<style>
body {
	font-family: Arial, sans-serif;
	margin: 20px;
}

button {
	padding: 8px 15px;
	background: #007bff;
	color: white;
	border: none;
	border-radius: 4px;
	cursor: pointer;
}

button:hover {
	background: #0056b3;
}
</style>
</head>
<body>

	<h2>JSTL Demo Page</h2>

	<!-- Output number -->
	<p>
		Number:
		<c:out value="25" />
	</p>

	<!-- Set a variable -->
	<c:set var="name" value="Ashish" />

	<!-- Output variable -->
	<p>
		Your name:
		<c:out value="${name}" />
	</p>

	<!-- If condition -->
	<c:if test="${name == 'Ashish'}">
		<p>Hello Ashish</p>
	</c:if>

	<!-- Else condition with choose -->
	<c:choose>
		<c:when test="${name == 'Tushar'}">
			<p>Hello Tushar</p>
		</c:when>
		<c:otherwise>
			<p>Hello Guest</p>
		</c:otherwise>
	</c:choose>

	<!-- Remove variable -->
	<c:remove var="name" />
	<p>
		After removing 'name':
		<c:out value="${name}" default="No Name Found" />
	</p>

	<!-- Loop through a list (pure JSTL using fn:split) -->
	<h3>Fruit List</h3>
	<c:set var="fruits" value="${fn:split('Apple,Banana,Mango', ',')}" />
	<ul>
		<c:forEach var="fruit" items="${fruits}">
			<li>${fruit}</li>
		</c:forEach>
	</ul>

	<!-- Loop with index -->
	<h3>Numbers</h3>
	<c:forEach var="num" begin="1" end="5" step="1">
    ${num}<br>
	</c:forEach>

	<!-- URL building -->
	<h3>External Link</h3>
	<c:url var="googleUrl" value="https://www.google.com" />
	<a href="${googleUrl}" target="_blank">Go to Google</a>

	<hr>

	<!-- Add Data Button -->
	<form method="post">
		<button type="submit" name="action" value="add">Add Data</button>
	</form>

	<!-- Redirect when Add button clicked -->
	<c:if test="${param.action == 'add'}">
		<c:redirect url="success.jsp">
			<c:param name="status" value="added" />
		</c:redirect>
	</c:if>

</body>
</html>
