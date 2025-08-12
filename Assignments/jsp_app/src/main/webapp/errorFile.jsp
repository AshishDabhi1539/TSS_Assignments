<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
String error = request.getParameter("error");
if ("underage".equals(error)) {
%>
<p style="color: red;">You must be 18 to 100 years older to proceed.</p>
<%
}
%>
<a href="main.jsp">Go back to form</a>
