<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Quiz Question</title>
<link href="styles.css" rel="stylesheet">
</head>
<body>
	<c:if test="${empty sessionScope.user}">
		<c:redirect url="login.html" />
	</c:if>
	<div class="container">
		<h2>Question ${currentQuestion}</h2>
		<p>${question.questionText}</p>
		<div class="error" id="errorMessage"></div>
		<form action="question" method="post"
			onsubmit="return validateQuestionForm()">
			<input type="hidden" name="questionId" value="${question.id}" />
			<div class="option">
				<input type="radio" name="answer" value="1" id="option1" required />
				<label for="option1">${question.option1}</label>
			</div>
			<div class="option">
				<input type="radio" name="answer" value="2" id="option2" /> <label
					for="option2">${question.option2}</label>
			</div>
			<div class="option">
				<input type="radio" name="answer" value="3" id="option3" /> <label
					for="option3">${question.option3}</label>
			</div>
			<div class="option">
				<input type="radio" name="answer" value="4" id="option4" /> <label
					for="option4">${question.option4}</label>
			</div>
			<button type="submit">Next</button>
		</form>
	</div>
	<script>
        function validateQuestionForm() {
            const options = document.getElementsByName("answer");
            const errorDiv = document.getElementById("errorMessage");
            let selected = false;
            for (const option of options) {
                if (option.checked) {
                    selected = true;
                    break;
                }
            }
            if (!selected) {
                errorDiv.textContent = "Please select an option.";
                errorDiv.style.display = "block";
                return false;
            }
            return true;
        }
    </script>
</body>
</html>