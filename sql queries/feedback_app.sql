-- create database
CREATE DATABASE feedback_app CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;

USE feedback_app;

-- create table
CREATE TABLE feedback (
  id INT AUTO_INCREMENT PRIMARY KEY,
  name VARCHAR(150) NOT NULL,
  session_date DATE NOT NULL,
  feedback_text TEXT,
  query_resolution INT NOT NULL,
  interactivity INT NOT NULL,
  impactful_learning INT NOT NULL,
  content_delivery INT NOT NULL,
  created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- optional: create a user (run as privileged user)
-- replace 'fb_user' and 'fb_password' with secure values
CREATE USER 'fb_user'@'localhost' IDENTIFIED BY 'fb_password';
GRANT ALL PRIVILEGES ON feedback_app.* TO 'fb_user'@'localhost';
FLUSH PRIVILEGES;


select * from feedback;
