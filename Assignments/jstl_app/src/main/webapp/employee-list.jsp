<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8" />
<meta name="viewport" content="width=device-width,initial-scale=1" />
<title>Employees</title>
<link rel="stylesheet" href="css/dashboard.css" />
</head>
<body>
	<div class="container">
		<header class="page-header">
			<h1>Employees</h1> 
			<a class="btn btn-ghost" href="AdminDashboardServlet">Back</a>
		</header>

		<table class="styled-table">
			<thead>
				<tr>
					<th>Emp ID</th>
					<th>Name</th>
					<th>Job Title</th>
					<th>Dept No</th>
					<th>Role</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach var="emp" items="${employees}">
					<tr>
						<td>${emp.empId}</td>
						<td>${emp.name}</td>
						<td>${emp.jobTitle}</td>
						<td>${emp.deptNo}</td>
						<td>${emp.role}</td>
					</tr>
				</c:forEach>
				<c:if test="${empty employees}">
					<tr>
						<td colspan="5" class="muted">No employees found.</td>
					</tr>
				</c:if>
			</tbody>
		</table>
	</div>
</body>
</html>
