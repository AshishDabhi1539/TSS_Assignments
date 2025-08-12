<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<jsp:useBean id="user" class="com.tss.model.User" scope="request" />
<jsp:setProperty name="user" property="*" />

<%
String name = request.getParameter("name");
String ageStr = request.getParameter("age");

if (name != null && ageStr != null) {
	int age = Integer.parseInt(ageStr);

	if (age < 18 && age > 100) {
%>
<jsp:forward page="errorFile.jsp">
	<jsp:param name="error" value="underage" />
</jsp:forward>
<%
} else {
%>
<jsp:include page="header.jsp" />
Welcome,
<jsp:getProperty name="user" property="name" />
<br>
Your recorded age is:
<jsp:getProperty name="user" property="age" /><br>
From param: Name = ${param.name}, Age = ${param.age}
<%
}
} else {
%>
<form method="post" action="main.jsp">
	Name: <input type="text" name="name" required><br>
	<br> Age: <input type="number" name="age" required><br>
	<br> <input type="submit" value="Submit">
</form>
<%
}
%>
