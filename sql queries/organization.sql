use organization;

CREATE TABLE employee (
    emp_id INT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(100) NOT NULL,
    email VARCHAR(100) UNIQUE NOT NULL,
    password VARCHAR(255) NOT NULL, -- store hashed passwords
    job_title VARCHAR(50),
    dept_no INT,
    role ENUM('EMPLOYEE', 'ADMIN') NOT NULL DEFAULT 'EMPLOYEE'
);

CREATE TABLE leave_balance (
    emp_id INT PRIMARY KEY,
    total_leaves INT NOT NULL DEFAULT 20,
    leaves_taken INT NOT NULL DEFAULT 0,
    FOREIGN KEY (emp_id) REFERENCES employee(emp_id) ON DELETE CASCADE
);

CREATE TABLE leave_request (
    request_id INT PRIMARY KEY AUTO_INCREMENT,
    emp_id INT NOT NULL,
    leave_type VARCHAR(50) NOT NULL,
    start_date DATE NOT NULL,
    end_date DATE NOT NULL,
    reason TEXT,
    status ENUM('Pending', 'Approved', 'Rejected') DEFAULT 'Pending',
    request_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (emp_id) REFERENCES employee(emp_id) ON DELETE CASCADE
);

ALTER TABLE leave_request ADD rejection_reason VARCHAR(255);

select * from employee;
select * from leave_balance;
select * from leave_request;

SET FOREIGN_KEY_CHECKS = 0;
TRUNCATE TABLE leave_request;
TRUNCATE TABLE leave_balance;
TRUNCATE TABLE employee;
SET FOREIGN_KEY_CHECKS = 1;
