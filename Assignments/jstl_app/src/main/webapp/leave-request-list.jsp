<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<c:if test="${sessionScope.role != 'USER'}">
    <c:redirect url="login.jsp" />
</c:if>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1" />
    <title>Leave Requests</title>
    <link rel="stylesheet" href="css/dashboard.css" />
    <link href="https://fonts.googleapis.com/css2?family=Inter:wght@400;500;600&display=swap" rel="stylesheet">
</head>
<body>
<div class="container">
    <header class="page-header">
        <div>
            <h1>Leave Requests</h1>
            <p class="muted">Welcome, ${sessionScope.name != null ? sessionScope.name : 'User'}</p>
        </div>
    </header>
    <main>
        <section class="panel">
            <h2>Your Leave Requests</h2>
            <c:if test="${not empty leaveRequests}">
                <table class="styled-table">
                    <thead>
                        <tr>
                            <th>Request ID</th>
                            <th>Emp ID</th>
                            <th>Type</th>
                            <th>From</th>
                            <th>To</th>
                            <th>Reason</th>
                            <th>Status</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach var="req" items="${leaveRequests}">
                            <tr>
                                <td>${req.requestId}</td>
                                <td>${req.empId}</td>
                                <td>${req.leaveType}</td>
                                <td><fmt:formatDate value="${req.startDate}" pattern="yyyy-MM-dd"/></td>
                                <td><fmt:formatDate value="${req.endDate}" pattern="yyyy-MM-dd"/></td>
                                <td>${req.reason}</td>
                                <td>
                                    <span class="status-badge ${not empty req.status ? fn:toLowerCase(req.status) : ''}">
                                        <span class="dot"></span>
                                        ${req.status}
                                    </span>
                                </td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>
            </c:if>
            <c:if test="${empty leaveRequests}">
                <p class="muted">No leave requests found.</p>
            </c:if>
        </section>
    </main>
</div>
</body>
</html>