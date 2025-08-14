<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:if test="${sessionScope.role != 'USER'}">
    <c:redirect url="login.jsp" />
</c:if>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1" />
    <title>Success</title>
    <link rel="stylesheet" href="css/dashboard.css" />
    <link href="https://fonts.googleapis.com/css2?family=Inter:wght@400;500;600&display=swap" rel="stylesheet">
</head>
<body>
<div class="container narrow">
    <header class="page-header">
        <div>
            <h1>Success</h1>
            <p class="muted">Welcome, ${sessionScope.name != null ? sessionScope.name : 'User'}</p>
        </div>
    </header>
    <main>
        <section class="panel">
            <p class="success-message">Leave applied successfully.</p>
            <div class="form-actions">
                <a class="btn" href="UserDashboardServlet">Back</a>
            </div>
        </section>
    </main>
</div>
</body>
</html>