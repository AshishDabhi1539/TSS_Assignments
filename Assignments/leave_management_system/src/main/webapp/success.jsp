<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Success</title>
<style>
body {
    font-family: Arial, sans-serif;
    background: linear-gradient(135deg, #c3ec52, #0ba29d);
    display: flex;
    justify-content: center;
    align-items: center;
    height: 100vh;
    margin: 0;
}
.success-container {
    background: white;
    padding: 30px;
    border-radius: 10px;
    box-shadow: 0 2px 15px rgba(0,0,0,0.2);
    width: 400px;
    text-align: center;
}
.success-container h2 {
    color: #333;
    margin-bottom: 20px;
}
.success-container a {
    display: inline-block;
    margin-top: 15px;
    text-decoration: none;
    color: #2196F3;
    font-weight: bold;
}
.success-container a:hover {
    text-decoration: underline;
}
</style>
</head>
<body>
<div class="success-container">
    <h2>Leave Request Submitted Successfully!</h2>
    <a href="employeeDashboard">Back to Dashboard</a>
</div>
</body>
</html>
