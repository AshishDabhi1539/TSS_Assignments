CREATE DATABASE startersql;

use startersql;

CREATE TABLE USERS(
id INT auto_increment PRIMARY KEY,
name VARCHAR(25) NOT NULL,
email VARCHAR(25) NOT NULL,
gender ENUM('Male','Female','Other'),
date_of_birth DATE,
created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP);

DESC USERS;

select * from USERS;
select name, email from USERS;

RENAME TABLE USERS TO CUSTOMER;
RENAME TABLE CUSTOMER TO USERS;

ALTER TABLE users ADD COLUMN is_active BOOLEAN DEFAULT TRUE;
ALTER TABLE users DROP COLUMN is_active;
ALTER TABLE users MODIFY COLUMN name VARCHAR(150);
ALTER TABLE users MODIFY COLUMN email VARCHAR(100) FIRST;
ALTER TABLE users MODIFY COLUMN gender ENUM('Male', 'Female', 'Other') AFTER name;

INSERT INTO USERS VALUES
('ashish@gmail.com', 1, 'Ashish', 'Male', '2001-05-14', DEFAULT);

INSERT INTO USERS (email, name, gender, date_of_birth) VALUES
('chirag@gmail.com', 'Chirag', 'Male', '2000-07-13'),
('kishan@gmail.com', 'Kishan', 'Male', '2004-03-01');

ALTER TABLE users DROP COLUMN created_at;

select * from USERS;