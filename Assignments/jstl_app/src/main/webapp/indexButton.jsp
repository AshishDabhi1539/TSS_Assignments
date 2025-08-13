<%@ page contentType="text/html;charset=UTF-8"%>
<html>
<head>
<title>Employee Portal</title>
<style>
/* Background Animation */
body {
	margin: 0;
	font-family: 'Segoe UI', sans-serif;
	background: linear-gradient(270deg, #6a11cb, #2575fc);
	background-size: 400% 400%;
	animation: gradientBG 8s ease infinite;
	color: white;
}

/* Center container with animation */
.container {
	text-align: center;
	margin-top: 20vh;
	animation: fadeIn 1.5s ease-in-out;
}

/* Heading Style */
h2 {
	font-size: 2.5em;
	letter-spacing: 2px;
	margin-bottom: 30px;
	animation: slideDown 1.2s ease-out;
}

/* Fancy Button */
button {
	padding: 15px 35px;
	font-size: 18px;
	cursor: pointer;
	border: none;
	border-radius: 30px;
	background: linear-gradient(45deg, #ff512f, #dd2476);
	color: white;
	font-weight: bold;
	box-shadow: 0 8px 15px rgba(0, 0, 0, 0.3);
	transition: all 0.4s ease;
}

button:hover {
	transform: translateY(-3px) scale(1.05);
	box-shadow: 0 12px 20px rgba(0, 0, 0, 0.4);
	background: linear-gradient(45deg, #dd2476, #ff512f);
}

/* Background Gradient Animation Keyframes */
@
keyframes gradientBG { 0% {
	background-position: 0% 50%;
}

50
%
{
background-position
:
100%
50%;
}
100
%
{
background-position
:
0%
50%;
}
}

/* Fade-in Animation */
@
keyframes fadeIn {
	from {opacity: 0;
}

to {
	opacity: 1;
}

}

/* Slide-down Animation */
@
keyframes slideDown {
	from {transform: translateY(-20px);
	opacity: 0;
}

to {
	transform: translateY(0);
	opacity: 1;
}
}
</style>
</head>
<body>
	<div class="container">
		<h2>Welcome to Employee Portal</h2>
		<form action="employees" method="post">
			<button type="submit">Show All Employees</button>
		</form>
	</div>
</body>
</html>
