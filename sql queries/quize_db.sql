CREATE DATABASE quiz_db;

USE quiz_db;

CREATE TABLE users (
    id INT AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(50) NOT NULL UNIQUE,
    password VARCHAR(255) NOT NULL,
    email VARCHAR(100) NOT NULL
);

CREATE TABLE questions (
    id INT AUTO_INCREMENT PRIMARY KEY,
    question_text TEXT NOT NULL,
    option1 VARCHAR(100) NOT NULL,
    option2 VARCHAR(100) NOT NULL,
    option3 VARCHAR(100) NOT NULL,
    option4 VARCHAR(100) NOT NULL,
    correct_option INT NOT NULL
);

CREATE TABLE results (
    id INT AUTO_INCREMENT PRIMARY KEY,
    user_id INT NOT NULL,
    score INT NOT NULL,
    quiz_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (user_id) REFERENCES users(id)
);

INSERT INTO questions (question_text, option1, option2, option3, option4, correct_option) VALUES
('What is the capital of France?', 'Paris', 'London', 'Berlin', 'Madrid', 1),
('What is 2 + 2?', '3', '4', '5', '6', 2),
('Which planet is known as the Red Planet?', 'Jupiter', 'Mars', 'Venus', 'Mercury', 2);

select * from questions;

delete from questions where id between 4 and 6;