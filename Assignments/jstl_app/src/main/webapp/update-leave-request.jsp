<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:if test="${sessionScope.role != 'USER'}">
    <c:redirect url="login.jsp" />
</c:if>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1" />
    <title>Update Leave Request</title>
    <link rel="stylesheet" href="css/dashboard.css" />
    <link href="https://fonts.googleapis.com/css2?family=Inter:wght@400;500;600&display=swap" rel="stylesheet">
    <script>
        window.onload = function() {
            var today = new Date().toISOString().split('T')[0];
            document.getElementById('fromDate').setAttribute('min', today);
            document.getElementById('toDate').setAttribute('min', today);
        };
    </script>
</head>
<body>
<div class="container narrow">
    <header class="page-header">
        <div>
            <h1>Update Leave Request</h1>
            <p class="muted">Welcome, ${sessionScope.name != null ? sessionScope.name : 'User'}</p>
        </div>
    </header>
    <main>
        <section class="panel">
            <form action="updateLeaveRequest" method="post" class="stack-form">
                <input type="hidden" name="requestId" value="${leaveRequest.requestId}" />
                <label for="leaveType">Leave Type</label>
                <select id="leaveType" name="leaveType" required>
                    <option value="">--Select--</option>
                    <option value="Casual" <c:if test="${leaveRequest.leaveType == 'Casual'}">selected</c:if>>Casual</option>
                    <option value="Sick" <c:if test="${leaveRequest.leaveType == 'Sick'}">selected</c:if>>Sick</option>
                    <option value="Earned" <c:if test="${leaveRequest.leaveType == 'Earned'}">selected</c:if>>Earned</option>
                </select>
                <div class="grid-2">
                    <div>
                        <label for="fromDate">From</label>
                        <input id="fromDate" type="date" name="fromDate" value="${leaveRequest.startDateFormatted}" required />
                    </div>
                    <div>
                        <label for="toDate">To</label>
                        <input id="toDate" type="date" name="toDate" value="${leaveRequest.endDateFormatted}" required />
                    </div>
                </div>
                <label for="reason">Reason (optional)</label>
                <textarea id="reason" name="reason" rows="3" placeholder="Reason...">${leaveRequest.reason}</textarea>
                <div class="form-actions">
                    <button class="btn" type="submit">Update</button>
                    <button style="border: none"><a href="UserDashboardServlet" class="btn btn-ghost">Cancel</a></button>
                </div>
            </form>
        </section>
    </main>
</div>
</body>
</html>