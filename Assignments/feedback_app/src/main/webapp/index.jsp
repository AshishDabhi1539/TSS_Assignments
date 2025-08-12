<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Feedback App</title>
<link rel="stylesheet" href="css/style.css">
</head>
<body>
	<jsp:include page="header.jsp" />

	<div class="container">
		<h1 class="glow">Feedback App</h1>

		<form action="submitFeedback" method="post" class="feedback-form"
			autocomplete="off">
			<div class="field">
				<label>Name</label> <input type="text" name="name" required/>
			</div>

			<div class="field">
				<label>Session Date</label> <input type="date" name="session_date" />
			</div>

			<div class="field">
				<label>Feedback</label>
				<textarea name="feedback_text" rows="4"></textarea>
			</div>

			<div class="grid">
				<div class="rating">
					<label>Query Resolution (1-10)</label> <input type="number"
						name="query_resolution" min="1" max="10"/>
				</div>
				<div class="rating">
					<label>Interactivity (1-10)</label> <input type="number"
						name="interactivity" min="1" max="10"/>
				</div>
				<div class="rating">
					<label>Impactful learning (1-10)</label> <input type="number"
						name="impactful_learning" min="1" max="10"/>
				</div>
				<div class="rating">
					<label>Content Delivery (1-10)</label> <input type="number"
						name="content_delivery" min="1" max="10"/>
				</div>
			</div>

			<div class="submit-wrap">
				<button type="submit" class="btn">Submit Feedback</button>
			</div>
		</form>
	</div>

	<jsp:include page="footer.jsp" />
</body>
</html>
