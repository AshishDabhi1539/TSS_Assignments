<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Employee Dashboard</title>
<style>
body {
    font-family: Arial, sans-serif;
    background: linear-gradient(135deg, #74ebd5, #acb6e5);
    margin: 0;
    padding: 0;
}
header {
    background: #4CAF50;
    color: white;
    padding: 15px;
    text-align: center;
    font-size: 24px;
}
.container {
    width: 90%;
    max-width: 900px;
    margin: 20px auto;
}
.card {
    background: white;
    border-radius: 10px;
    padding: 20px;
    margin-bottom: 20px;
    box-shadow: 0 2px 10px rgba(0,0,0,0.1);
}
form label {
    display: block;
    margin: 10px 0 5px;
}
input, select, button {
    width: 100%;
    padding: 10px;
    margin-bottom: 10px;
    border-radius: 5px;
    border: 1px solid #ccc;
    box-sizing: border-box;
}
button {
    background: #4CAF50;
    color: white;
    border: none;
    cursor: pointer;
}
button:hover {
    background: #45a049;
}
table {
    width: 100%;
    border-collapse: collapse;
}
th, td {
    padding: 10px;
    border-bottom: 1px solid #ddd;
    text-align: left;
}
th {
    background: #4CAF50;
    color: white;
}
.status-pending { color: orange; font-weight: bold; }
.status-approved { color: green; font-weight: bold; }
.status-rejected { color: red; font-weight: bold; }
a.logout { display: inline-block; margin-top: 10px; color: #2196F3; text-decoration: none; }
a.logout:hover { text-decoration: underline; }
.error { color: red; margin-top: 10px; }
</style>
</head>
<body>
<header>Employee Dashboard</header>
<div class="container">

    <!-- Leave Application Card -->
    <div class="card">
        <h3>Apply for Leave</h3>
        <form action="employeeDashboard" method="post">
            <label>Leave Type:</label>
            <select name="typeId" required>
                <c:forEach var="type" items="${leaveTypes}">
                    <option value="${type.id}">${type.name}</option>
                </c:forEach>
            </select>

            <label>Start Date:</label>
            <input type="date" name="startDate" required>
            <label>End Date:</label>
            <input type="date" name="endDate" required>
            <label>Reason:</label>
            <input type="text" name="reason" placeholder="Reason for leave" required>
            <button type="submit">Submit Leave</button>
        </form>
        <c:if test="${param.error == 'error'}">
            <p class="error">An error occurred. Please try again!</p>
        </c:if>
    </div>

    <!-- Leave History Card -->
    <div class="card">
        <h3>Your Leave History</h3>
        <table>
            <tr>
                <th>Type</th>
                <th>Start Date</th>
                <th>End Date</th>
                <th>Status</th>
                <th>Reason</th>
            </tr>
            <c:forEach var="leave" items="${leaveHistory}">
                <tr>
                    <td>${leave.typeName}</td>
                    <td><fmt:formatDate value="${leave.startDate}" pattern="yyyy-MM-dd" /></td>
                    <td><fmt:formatDate value="${leave.endDate}" pattern="yyyy-MM-dd" /></td>
                    <td class="status-${leave.status}">${leave.status}</td>
                    <td>${leave.reason}</td>
                </tr>
            </c:forEach>
        </table>
    </div>

    <a href="logout" class="logout">Logout</a>
</div>
</body>
</html>
