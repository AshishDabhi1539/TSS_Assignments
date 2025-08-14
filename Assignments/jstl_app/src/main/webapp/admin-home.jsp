<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<c:if test="${sessionScope.role != 'ADMIN'}">
    <c:redirect url="login.jsp" />
</c:if>

<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8" />
<meta name="viewport" content="width=device-width, initial-scale=1" />
<title>Admin Home</title>
<link rel="stylesheet" href="css/dashboard.css" />
<link href="https://fonts.googleapis.com/css2?family=Inter:wght@400;500;600&display=swap" rel="stylesheet">
</head>
<body>
<div class="container">
    <header class="page-header">
        <div>
            <h1>Admin</h1>
            <p class="muted">Welcome, ${sessionScope.name != null ? sessionScope.name : 'Admin'}</p>
        </div>
        <div class="header-actions">
            <a href="logout" class="btn btn-ghost">Logout</a>
        </div>
    </header>

    <main>
        <section class="panel">
            <h2>What would you like to manage?</h2>
            <div class="tile-grid">
                <a class="tile" href="AdminDashboardServlet">
                    <div class="tile-icon">📅</div>
                    <div class="tile-title">Manage Leave Requests</div>
                    <div class="tile-sub">Approve / Reject / Filter</div>
                </a>

                <a class="tile" href="employees">
                    <div class="tile-icon">👥</div>
                    <div class="tile-title">View Employees</div>
                    <div class="tile-sub">Directory & roles</div>
                </a>
            </div>
        </section>
    </main>
</div>
</body>
</html>
