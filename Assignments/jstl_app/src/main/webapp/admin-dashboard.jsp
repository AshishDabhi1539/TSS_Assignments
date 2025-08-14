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
<meta name="viewport" content="width=device-width, initial-scale=1" />
<title>Admin Dashboard</title>
<link rel="stylesheet" href="css/dashboard.css" />
<link href="https://fonts.googleapis.com/css2?family=Inter:wght@400;500;600&display=swap" rel="stylesheet">

<script>
document.addEventListener('DOMContentLoaded', function () {
    const rows = document.querySelectorAll('tbody tr');

    rows.forEach(row => {
        const statusSelect = row.querySelector('select[name="status"]');
        const rejectionReasonSelect = row.querySelector('select[name="rejectionReason"]');
        const customReasonInput = row.querySelector('input[name="customReason"]');
        const updateButton = row.querySelector('button[type="submit"]');

        if (!statusSelect || !rejectionReasonSelect || !updateButton) return;

        const refreshButtonState = () => {
            const statusVal = statusSelect.value;
            const reasonVal = rejectionReasonSelect.value;

            if (statusVal === 'APPROVED') {
                updateButton.disabled = false;
            } else if (statusVal === 'REJECTED') {
                if (!reasonVal) {
                    updateButton.disabled = true;
                } else if (reasonVal === 'Other') {
                    updateButton.disabled = !customReasonInput.value.trim();
                } else {
                    updateButton.disabled = false;
                }
            } else {
                updateButton.disabled = true;
            }
        };

        // init
        rejectionReasonSelect.disabled = true;
        customReasonInput.disabled = true;
        customReasonInput.style.display = 'none';
        updateButton.disabled = true;

        statusSelect.addEventListener('change', function () {
            if (this.value === 'APPROVED') {
                rejectionReasonSelect.disabled = true;
                rejectionReasonSelect.value = '';
                customReasonInput.value = '';
                customReasonInput.disabled = true;
                customReasonInput.style.display = 'none';
            } else if (this.value === 'REJECTED') {
                rejectionReasonSelect.disabled = false;
                if (rejectionReasonSelect.value !== 'Other') {
                    customReasonInput.disabled = true;
                    customReasonInput.style.display = 'none';
                    customReasonInput.value = '';
                }
            } else {
                rejectionReasonSelect.disabled = true;
                rejectionReasonSelect.value = '';
                customReasonInput.value = '';
                customReasonInput.disabled = true;
                customReasonInput.style.display = 'none';
            }
            refreshButtonState();
        });

        rejectionReasonSelect.addEventListener('change', function () {
            if (statusSelect.value === 'REJECTED') {
                if (this.value === 'Other') {
                    customReasonInput.disabled = false;
                    customReasonInput.style.display = 'inline';
                } else {
                    customReasonInput.disabled = true;
                    customReasonInput.style.display = 'none';
                    customReasonInput.value = '';
                }
            }
            refreshButtonState();
        });

        customReasonInput.addEventListener('input', refreshButtonState);
    });
});
</script>
</head>
<body>
<div class="container">
    <header class="page-header">
        <div>
            <h1>Admin Dashboard</h1>
            <p class="muted">Welcome, Admin</p>
        </div>
        <div class="header-actions">
            <a href="admin-home.jsp" class="btn btn-ghost">Home</a>
        </div>
    </header>

    <main>
        <section class="panel">
            <h2>Leave Requests</h2>

            <!-- horizontal toolbar -->
            <form action="AdminDashboardServlet" method="get" class="toolbar">
                <div class="field">
                    <label for="startDate">From</label>
                    <input id="startDate" type="date" name="startDate" value="${param.startDate}" />
                </div>
                <div class="field">
                    <label for="endDate">To</label>
                    <input id="endDate" type="date" name="endDate" value="${param.endDate}" />
                </div>
                <div class="field">
                    <label for="status">Status</label>
                    <select id="status" name="status">
                        <option value="">All</option>
                        <option value="Pending"  <c:if test="${param.status == 'Pending'}">selected</c:if>>Pending</option>
                        <option value="Approved" <c:if test="${param.status == 'Approved'}">selected</c:if>>Approved</option>
                        <option value="Rejected" <c:if test="${param.status == 'Rejected'}">selected</c:if>>Rejected</option>
                    </select>
                </div>
                <div class="field field-button">
                    <button class="btn" type="submit">Search</button>
                </div>
            </form>

            <c:if test="${not empty leaveRequests}">
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
                            <th>Rejection Reason</th>
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
                                <!-- prettier status badge, same text -->
                                <td>
                                    <span class="status-badge ${not empty lr.status ? fn:toLowerCase(lr.status) : ''}">
                                        <span class="dot"></span>
                                        ${lr.status}
                                    </span>
                                </td>
                                <td>${lr.rejectionReason}</td>
                                <td>
                                    <c:choose>
                                        <c:when test="${lr.status == 'Pending'}">
                                            <form action="updateLeaveStatus" method="post" class="inline-form">
                                                <input type="hidden" name="leaveId" value="${lr.requestId}" />
                                                <select name="status" required>
                                                    <option value="">--</option>
                                                    <option value="APPROVED">Approve</option>
                                                    <option value="REJECTED">Reject</option>
                                                </select>
                                                <select name="rejectionReason" id="rejectionReason_${lr.requestId}" required>
                                                    <option value="">Select Reason</option>
                                                    <option value="Insufficient Leave Balance">Insufficient Leave Balance</option>
                                                    <option value="Project Deadline Conflict">Project Deadline Conflict</option>
                                                    <option value="Team Understaffed">Team Understaffed</option>
                                                    <option value="Other">Other</option>
                                                </select>
                                                <input type="text" name="customReason" placeholder="Custom reason" style="display:none;" id="customReason_${lr.requestId}" />
                                                <button type="submit" class="btn small-btn">Update</button>
                                            </form>
                                        </c:when>
                                        <c:otherwise>
                                            <span class="muted">Action Locked</span>
                                        </c:otherwise>
                                    </c:choose>
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
