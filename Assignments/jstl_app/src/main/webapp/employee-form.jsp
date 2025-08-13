<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8" />
<meta name="viewport" content="width=device-width,initial-scale=1" />
<title>Add Employee</title>
<link rel="stylesheet" href="css/dashboard.css" />
</head>
<body>
	<div class="container narrow">
		<h2>Add Employee</h2>
		<form action="employees" method="post" class="auth-form">
			<label for="name">Name</label> <input id="name" name="name" required />

			<label for="email">Email</label> <input id="email" name="email"
				type="email" required /> <label for="password">Password</label> <input
				id="password" name="password" type="password" required /> <label
				for="jobTitle">Job Title</label> <input id="jobTitle"
				name="jobTitle" required /> <label for="deptNo">Department
				No</label> <input id="deptNo" name="deptNo" type="number" required /> <label
				for="role">Role</label> <select id="role" name="role" required>
				<option value="EMPLOYEE">EMPLOYEE</option>
				<option value="ADMIN">ADMIN</option>
			</select>

			<button class="btn full" type="submit">Add</button>
		</form>
	</div>
</body>
</html>
