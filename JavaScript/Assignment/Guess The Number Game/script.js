const validNumbers = [12, 23, 34, 45, 56, 67, 78, 89, 90, 100];
let correctNumber = validNumbers[Math.floor(Math.random() * validNumbers.length)];
console.log(`Correct number (for debugging): ${correctNumber}`);
let attemptsLeft = 3;
let guessCount = 0;

const guessInput = document.querySelector('.guessInput');
const guessBtn = document.querySelector('.guessBtn');
const resultDiv = document.querySelector('.result');
const chancesDiv = document.querySelector('.chances');
const guessesDisplay = document.querySelector('.guesses');
const resetBtn = document.querySelector('.resetBtn');

chancesDiv.innerText = `Remaining chances: ${attemptsLeft}`;
resetBtn.style.display = 'none';

guessBtn.addEventListener('click', handleGuess);
resetBtn.addEventListener('click', resetGame);

function handleGuess() {
  const userGuess = parseInt(guessInput.value);

  if (isNaN(userGuess)) {
    resultDiv.textContent = 'Please enter a number!';
    return;
  }

  if (!validNumbers.includes(userGuess)) {
    resultDiv.textContent = 'Invalid guess! Use one of the valid numbers.';
    return;
  }

  guessCount++;
  attemptsLeft--;
  guessesDisplay.textContent += userGuess + ' ';

  if (userGuess === correctNumber) {
    resultDiv.textContent = `You guessed right in ${guessCount} attempt(s)!`;
    endGame();
  } else if (attemptsLeft === 0) {
    resultDiv.textContent = `Game over! The correct number was ${correctNumber}.`;
    endGame();
  } else if (userGuess < correctNumber) {
    resultDiv.textContent = 'Too low! Try again.';
    chancesDiv.textContent = `Remaining chances: ${attemptsLeft}`;
  } else {
    resultDiv.textContent = 'Too high! Try again.';
    chancesDiv.textContent = `Remaining chances: ${attemptsLeft}`;
  }

  guessInput.value = '';
  guessInput.focus();
}

function endGame() {
  guessInput.disabled = true;
  guessBtn.disabled = true;
  resetBtn.style.display = 'block';
}

function resetGame() {
  correctNumber = validNumbers[Math.floor(Math.random() * validNumbers.length)];
  attemptsLeft = 3;
  guessCount = 0;

  guessInput.disabled = false;
  guessBtn.disabled = false;
  guessInput.value = '';
  resultDiv.textContent = '';
  chancesDiv.textContent = `Remaining chances: ${attemptsLeft}`;
  guessesDisplay.textContent = '';
  resetBtn.style.display = 'none';
  guessInput.focus();
}
