<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:if test="${sessionScope.role != 'ADMIN'}">
    <c:redirect url="login.jsp" />
</c:if>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1" />
    <title>Employees</title>
    <link rel="stylesheet" href="css/dashboard.css" />
    <link href="https://fonts.googleapis.com/css2?family=Inter:wght@400;500;600&display=swap" rel="stylesheet">
</head>
<body>
<div class="container">
    <header class="page-header">
        <div>
            <h1>Employees</h1>
            <p class="muted">Welcome, ${sessionScope.name != null ? sessionScope.name : 'Admin'}</p>
        </div>
        <div class="header-actions">
            <a href="AdminHomeServlet" class="btn btn-ghost">Home</a>
        </div>
    </header>
    <main>
        <section class="panel">
            <h2>Employee Directory</h2>
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
        </section>
    </main>
</div>
</body>
</html>