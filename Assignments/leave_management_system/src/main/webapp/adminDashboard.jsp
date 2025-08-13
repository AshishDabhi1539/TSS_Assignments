<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Admin Dashboard</title>
<style>
body {
    font-family: Arial, sans-serif;
    background: linear-gradient(135deg, #f6d365, #fda085);
    margin: 0;
    padding: 0;
}
header {
    background: #ff5722;
    color: white;
    padding: 15px;
    text-align: center;
    font-size: 24px;
}
.container {
    width: 90%;
    max-width: 1000px;
    margin: 20px auto;
}
table {
    width: 100%;
    border-collapse: collapse;
    background: white;
    border-radius: 10px;
    overflow: hidden;
    box-shadow: 0 2px 10px rgba(0,0,0,0.1);
}
th, td {
    padding: 12px;
    text-align: left;
    border-bottom: 1px solid #ddd;
}
th { background: #ff5722; color: white; }
form { display: inline; }
button {
    padding: 5px 10px;
    border: none;
    border-radius: 5px;
    cursor: pointer;
}
.approve { background: #4CAF50; color: white; }
.reject { background: #f44336; color: white; }
a.logout { display: inline-block; margin-top: 10px; color: #2196F3; text-decoration: none; }
a.logout:hover { text-decoration: underline; }
.error { color: red; margin-top: 10px; }
</style>
</head>
<body>
<header>Admin Dashboard</header>
<div class="container">
    <table>
        <tr>
            <th>ID</th>
            <th>Employee ID</th>
            <th>Leave Type</th>
            <th>Start Date</th>
            <th>End Date</th>
            <th>Reason</th>
            <th>Action</th>
        </tr>
        <c:forEach var="req" items="${requests}">
            <tr>
                <td>${req.id}</td>
                <td>${req.employeeId}</td>
                <td>${req.typeName}</td>
                <td><fmt:formatDate value="${req.startDate}" pattern="yyyy-MM-dd"/></td>
                <td><fmt:formatDate value="${req.endDate}" pattern="yyyy-MM-dd"/></td>
                <td>${req.reason}</td>
                <td>
                    <form action="adminDashboard" method="post">
                        <input type="hidden" name="id" value="${req.id}">
                        <input type="hidden" name="action" value="approved">
                        <button type="submit" class="approve">Approve</button>
                    </form>
                    <form action="adminDashboard" method="post">
                        <input type="hidden" name="id" value="${req.id}">
                        <input type="hidden" name="action" value="rejected">
                        <button type="submit" class="reject">Reject</button>
                    </form>
                </td>
            </tr>
        </c:forEach>
    </table>
    <c:if test="${param.error == 'error'}">
        <p class="error">An error occurred. Please try again!</p>
    </c:if>
    <a href="logout" class="logout">Logout</a>
</div>
</body>
</html>
