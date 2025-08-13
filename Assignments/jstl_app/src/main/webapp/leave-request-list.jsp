<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8" />
<meta name="viewport" content="width=device-width,initial-scale=1" />
<title>Leave Requests</title>
<link rel="stylesheet" href="css/dashboard.css" />
</head>
<body>
	<div class="container">
		<h2>All Leave Requests</h2>
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
						<td><fmt:formatDate value="${req.startDate}"
								pattern="yyyy-MM-dd" /></td>
						<td><fmt:formatDate value="${req.endDate}"
								pattern="yyyy-MM-dd" /></td>
						<td>${req.reason}</td>
						<td
							class="status ${not empty req.status ? fn:toLowerCase(req.status) : ''}">
							${req.status}</td>
					</tr>
				</c:forEach>
				<c:if test="${empty leaveRequests}">
					<tr>
						<td colspan="7" class="muted">No records</td>
					</tr>
				</c:if>
			</tbody>
		</table>
	</div>
</body>
</html>
