<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<c:if test="${sessionScope.role != 'ADMIN'}">
	<c:redirect url="login.jsp" />
</c:if>

<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8" />
<meta name="viewport" content="width=device-width,initial-scale=1" />
<title>Admin Dashboard</title>
<link rel="stylesheet" href="css/dashboard.css" />
</head>
<body>
	<div class="container">
		<header class="page-header">
			<div>
				<h1>Admin Dashboard</h1>
				<p class="muted">Welcome, Admin</p>
			</div>
			<div class="header-actions">
				<a href="employees" class="btn">Employees</a> <a href="logout"
					class="btn btn-ghost">Logout</a>
			</div>
		</header>

		<main>
			<section class="panel">
				<h2>Leave Requests</h2>
				<table class="styled-table">
					<thead>
						<tr>
							<th>Leave ID</th>
							<th>Emp ID</th>
							<th>Type</th>
							<th>From</th>
							<th>To</th>
							<th>Reason</th>
							<th>Status</th>
							<th>Action</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach var="lr" items="${leaveRequests}">
							<tr>
								<td>${lr.requestId}</td>
								<td>${lr.empId}</td>
								<td>${lr.leaveType}</td>
								<td>${lr.startDateFormatted}</td>
								<td>${lr.endDateFormatted}</td>
								<td>${lr.reason}</td>
								<td
									class="status ${not empty lr.status ? fn:toLowerCase(lr.status) : ''}">
									${lr.status}</td>
								<td>
									<form action="updateLeaveStatus" method="post"
										class="inline-form">
										<input type="hidden" name="leaveId" value="${lr.requestId}" />
										<select name="status" required>
											<option value="">--</option>
											<option value="APPROVED">Approve</option>
											<option value="REJECTED">Reject</option>
										</select>
										<button type="submit" class="btn small-btn">Update</button>
									</form>
								</td>
							</tr>
						</c:forEach>
						<c:if test="${empty leaveRequests}">
							<tr>
								<td colspan="8" class="muted">No leave requests found.</td>
							</tr>
						</c:if>
					</tbody>
				</table>
			</section>
		</main>
	</div>
</body>
</html>