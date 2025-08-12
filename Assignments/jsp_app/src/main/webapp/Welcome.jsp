<%@ page import="java.util.Date"%><%-- page directive --%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%-- <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>--%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>First JSP Page</title>
</head>
<body>

	<h1>
		<b>Welcome to JSP</b>
	</h1>

	<%
	int number1 = 10;
	int number2 = 20;

	Date date = new Date();
	%><%--scriptlet tag --%>

	<%=number1 + number2%><%--Expression tag --%>

	<br>
	<br>

	<b><%=date%></b>

	<%!double pi = 3.14;%><!-- declaration tag -->
	<br>
	<br>
	<strong><i><%=pi%></i></strong>

	<%@ include file="NewJSP.jsp"%><%-- include directive --%>

	<%-- just basic example
	<c:if test="${number1 < number2}">
		<p>Number1 is less than Number2</p>
	</c:if>
	 --%>

</body>
</html>