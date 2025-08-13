<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8" />
<meta name="viewport" content="width=device-width,initial-scale=1" />
<title>Leave Balance</title>
<link rel="stylesheet" href="css/dashboard.css" />
</head>
<body>
	<div class="container narrow">
		<h2>Leave Balance</h2>

		<c:choose>
			<c:when test="${not empty leaveBalance}">
				<p>
					<strong>Employee:</strong> ${leaveBalance.empId}
				</p>
				<p>
					<strong>Total leaves:</strong> ${leaveBalance.totalLeaves}
				</p>
				<p>
					<strong>Leaves taken:</strong> ${leaveBalance.leavesTaken}
				</p>
				<p>
					<strong>Remaining:</strong> ${leaveBalance.remainingLeaves}
				</p>
			</c:when>
			<c:otherwise>
				<p class="muted">No leave balance found for this employee.</p>
			</c:otherwise>
		</c:choose>

		<a class="btn" href="UserDashboardServlet">Back</a>
	</div>
</body>
</html>
