const validGuesses = [5, 12, 27, 29, 30, 31, 35, 37, 90, 100]; // Array of possible numbers to guess
const maxAttempts = 5; // Maximum number of attempts allowed
let attemptsLeft = maxAttempts; // Tracks remaining attempts

// Shuffle the validGuesses array using Fisher-Yates shuffle
function shuffleArray(arr) { // Function to shuffle an array
    for (let i = arr.length - 1; i > 0; i--) { // Loop from end to start
        const j = Math.floor(Math.random() * (i + 1)); // Pick a random index
        [arr[i], arr[j]] = [arr[j], arr[i]]; // Swap elements at i and j
    }
    return arr; // Return the shuffled array
}

const shuffledGuesses = shuffleArray([...validGuesses]); // Make a shuffled copy of validGuesses
const randomNumber = shuffledGuesses[Math.floor(Math.random() * shuffledGuesses.length)]; // Pick a random number from shuffledGuesses

document.getElementById("targetNumber").textContent = randomNumber; // Display the target number in the HTML element with id 'targetNumber'
const message = document.getElementById("message"); // Get the message element for feedback
const remaining = document.getElementById("remaining"); // Get the element to show remaining attempts
const buttonContainer = document.getElementById("buttonContainer"); // Get the container for number buttons

remaining.textContent = `Attempts Left: ${attemptsLeft}`; // Show initial attempts left

// Create buttons with hidden numbers in shuffled order
shuffledGuesses.forEach(number => { // Loop through each number in shuffledGuesses
    const btn = document.createElement("button"); // Create a new button element
    btn.textContent = number; // Set button text to the number
    btn.className = "number-button"; // Add a class for styling
    btn.setAttribute("data-number", number); // Store the number as a data attribute
    btn.onclick = () => checkGuess(parseInt(number), btn); // Set click handler to checkGuess function
    buttonContainer.appendChild(btn); // Add the button to the container
});

function checkGuess(guess, btn) { // Function to handle button click and check guess
    btn.style.color = "white"; // Reveal number on click

    if (guess === randomNumber) { // If guess is correct
        message.textContent = "Correct."; // Show correct message
        btn.classList.add("correct"); // Add correct class for styling
        disableAllButtons(); // Disable all buttons
    } else { // If guess is wrong
        attemptsLeft--; // Decrement attempts left
        btn.classList.add("wrong"); // Add wrong class for styling
        btn.disabled = true; // Disable the clicked button

        if (attemptsLeft === 0) { // If no attempts left
            message.textContent = `Game Over! The correct number was ${randomNumber}.`; // Show game over message
            revealCorrectButton(); // Reveal the correct button
            disableAllButtons(); // Disable all buttons
        } else {
            message.textContent = "Wrong button. Try again."; // Show wrong guess message
        }

        remaining.textContent = `Attempts Left: ${attemptsLeft}`; // Update attempts left display
    }
}

function disableAllButtons() { // Function to disable all number buttons
    const buttons = document.querySelectorAll(".number-button"); // Select all number buttons
    buttons.forEach(btn => btn.disabled = true); // Disable each button
}

function revealCorrectButton() { // Function to reveal the correct button
    const buttons = document.querySelectorAll(".number-button"); // Select all number buttons
    buttons.forEach(btn => { // Loop through each button
        if (parseInt(btn.getAttribute("data-number")) === randomNumber) { // If button's number is the correct one
            btn.style.color = "white"; // Reveal the number
            btn.classList.add("correct"); // Add correct class for styling
        }
    });
}
