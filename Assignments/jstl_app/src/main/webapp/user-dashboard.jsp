<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<c:if test="${sessionScope.role != 'USER'}">
	<c:redirect url="login.jsp" />
</c:if>

<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8" />
<meta name="viewport" content="width=device-width,initial-scale=1" />
<title>User Dashboard</title>
<link rel="stylesheet" href="css/dashboard.css" />
</head>
<body>
	<div class="container">
		<header class="page-header">
			<div>
				<h1>User Dashboard</h1>
				<p class="muted">Welcome, ${sessionScope.name}</p>
			</div>
			<div class="header-actions">
				<a href="logout" class="btn btn-ghost">Logout</a>
			</div>
		</header>

		<main>
			<section class="panel small">
				<h2>Your Leave Balance</h2>
				<p class="big">
					<strong> <c:choose>
							<c:when test="${not empty leaveBalance}">
                                ${leaveBalance}
                            </c:when>
							<c:otherwise>0</c:otherwise>
						</c:choose>
					</strong> days remaining
				</p>
			</section>

			<section class="panel">
				<h2>Apply for Leave</h2>
				<form action="applyLeave" method="post" class="stack-form">
					<label for="leaveType">Leave Type</label> <select id="leaveType"
						name="leaveType" required>
						<option value="">--Select--</option>
						<option value="Casual">Casual</option>
						<option value="Sick">Sick</option>
						<option value="Earned">Earned</option>
					</select>

					<div class="grid-2">
						<div>
							<label for="fromDate">From</label> <input id="fromDate"
								type="date" name="fromDate" required />
						</div>
						<div>
							<label for="toDate">To</label> <input id="toDate" type="date"
								name="toDate" required />
						</div>
					</div>

					<label for="reason">Reason (optional)</label>
					<textarea id="reason" name="reason" rows="3"
						placeholder="Reason..."></textarea>

					<div class="form-actions">
						<button class="btn" type="submit">Apply</button>
					</div>

					<c:if test="${not empty errorMessage}">
						<p class="error-message">${errorMessage}</p>
					</c:if>
					<c:if test="${not empty successMessage}">
						<p class="success-message">${successMessage}</p>
					</c:if>
				</form>
			</section>

			<section class="panel">
				<h2>Your Leave History</h2>
				<table class="styled-table">
					<thead>
						<tr>
							<th>Leave ID</th>
							<th>Type</th>
							<th>From</th>
							<th>To</th>
							<th>Status</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach var="lr" items="${myLeaves}">
							<tr>
								<td>${lr.requestId}</td>
								<td>${lr.leaveType}</td>
								<td>${lr.startDateFormatted}</td>
								<td>${lr.endDateFormatted}</td>
								<td
									class="status ${not empty lr.status ? fn:toLowerCase(lr.status) : ''}">
									${lr.status}</td>
							</tr>
						</c:forEach>
						<c:if test="${empty myLeaves}">
							<tr>
								<td colspan="5" class="muted">No leave requests yet.</td>
							</tr>
						</c:if>
					</tbody>
				</table>
			</section>
		</main>
	</div>
</body>
</html>
