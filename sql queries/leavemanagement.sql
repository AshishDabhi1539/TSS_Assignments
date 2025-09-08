CREATE TABLE employees (
    id INT AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(50) NOT NULL UNIQUE,
    password VARCHAR(100) NOT NULL,
    role ENUM('employee', 'admin') DEFAULT 'employee',
    leave_balance INT DEFAULT 10
);

CREATE TABLE leave_requests (
    id INT AUTO_INCREMENT PRIMARY KEY,
    employee_id INT NOT NULL,
    start_date DATE NOT NULL,
    end_date DATE NOT NULL,
    status ENUM('pending', 'approved', 'rejected') DEFAULT 'pending',
    reason VARCHAR(255),
    FOREIGN KEY (employee_id) REFERENCES employees(id) ON DELETE CASCADE
);

INSERT INTO employees (username, password, role, leave_balance) VALUES
('rajesh.sharma', 'pass123', 'employee', 10),
('priya.mehta', 'pass456', 'employee', 10),
('vikram.singh', 'pass789', 'employee', 10),
('anita.desai', 'pass101', 'admin', 15),
('arjun.patel', 'pass202', 'employee', 10);

INSERT INTO leave_requests (employee_id, start_date, end_date, status, reason) VALUES
(1, '2025-08-15', '2025-08-20', 'pending', 'Family wedding'),
(2, '2025-08-10', '2025-08-12', 'approved', 'Medical checkup'),
(3, '2025-08-25', '2025-08-27', 'rejected', 'Insufficient leave balance'),
(4, '2025-08-18', '2025-08-19', 'pending', 'Personal leave'),
(5, '2025-08-22', '2025-08-24', 'approved', 'Festival celebration');

CREATE TABLE leave_types (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(50) UNIQUE NOT NULL,
    default_days INT NOT NULL
);

-- Insert default leave types
INSERT INTO leave_types (name, default_days) VALUES
('Casual Leave', 10),
('Sick Leave', 8),
('Earned Leave', 15);

ALTER TABLE leave_requests
ADD COLUMN type_id INT AFTER employee_id,
ADD COLUMN applied_on DATETIME DEFAULT CURRENT_TIMESTAMP;

ALTER TABLE leave_requests
ADD FOREIGN KEY (type_id) REFERENCES leave_types(id) ON DELETE CASCADE;

CREATE TABLE audit_logs (
    id INT AUTO_INCREMENT PRIMARY KEY,
    admin_id INT NOT NULL,
    action VARCHAR(50) NOT NULL,
    leave_id INT NOT NULL,
    action_time DATETIME DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (admin_id) REFERENCES employees(id) ON DELETE CASCADE
);

ALTER TABLE employees MODIFY password VARCHAR(255) NOT NULL;

select * from employees;
use leavemanagement;