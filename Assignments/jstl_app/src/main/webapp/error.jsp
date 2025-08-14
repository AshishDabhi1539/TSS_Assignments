<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1" />
    <title>Error</title>
    <link rel="stylesheet" href="css/dashboard.css" />
    <link href="https://fonts.googleapis.com/css2?family=Inter:wght@400;500;600&display=swap" rel="stylesheet">
</head>
<body>
<div class="container narrow">
    <header class="page-header">
        <div>
            <h1>Error</h1>
            <p class="muted">Something went wrong</p>
        </div>
        <div class="header-actions">
            <a href="login.jsp" class="btn btn-ghost">Back to Login</a>
        </div>
    </header>
    <main>
        <section class="panel">
            <p class="error-message">${requestScope.errorMessage}</p>
        </section>
    </main>
</div>
</body>
</html>