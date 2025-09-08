use banking_system;

CREATE TABLE users (
    user_id INT AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(50) UNIQUE NOT NULL,
    password VARCHAR(255) NOT NULL,
    role ENUM('ADMIN','CUSTOMER') NOT NULL DEFAULT 'CUSTOMER',
    status ENUM('ACTIVE','BLOCKED') DEFAULT 'ACTIVE',
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE customers (
    customer_id INT AUTO_INCREMENT PRIMARY KEY,
    user_id INT NOT NULL,
    full_name VARCHAR(100) NOT NULL,
    email VARCHAR(100) UNIQUE NOT NULL,
    phone VARCHAR(15) UNIQUE NOT NULL,
    address VARCHAR(255),
    aadhar_no VARCHAR(20) UNIQUE,
    pan_no VARCHAR(20) UNIQUE,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (user_id) REFERENCES users(user_id) ON DELETE CASCADE
);

CREATE TABLE accounts (
    account_id INT AUTO_INCREMENT PRIMARY KEY,
    customer_id INT NOT NULL,
    account_number VARCHAR(20) UNIQUE NOT NULL,
    account_type ENUM('SAVINGS','CURRENT') NOT NULL,
    balance DECIMAL(15,2) DEFAULT 0.00,
    status ENUM('ACTIVE','CLOSED','BLOCKED') DEFAULT 'ACTIVE',
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (customer_id) REFERENCES customers(customer_id) ON DELETE CASCADE
);

CREATE TABLE transactions (
    transaction_id INT AUTO_INCREMENT PRIMARY KEY,
    account_id INT NOT NULL,
    type ENUM('DEPOSIT','WITHDRAW','TRANSFER') NOT NULL,
    amount DECIMAL(15,2) NOT NULL,
    transaction_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    status ENUM('SUCCESS','FAILED','PENDING') DEFAULT 'SUCCESS',
    reference_account VARCHAR(20), -- for transfers
    FOREIGN KEY (account_id) REFERENCES accounts(account_id) ON DELETE CASCADE
);

CREATE TABLE loans (
    loan_id INT AUTO_INCREMENT PRIMARY KEY,
    customer_id INT NOT NULL,
    loan_type ENUM('PERSONAL','HOME','CAR','EDUCATION') NOT NULL,
    amount DECIMAL(15,2) NOT NULL,
    interest_rate DECIMAL(5,2) NOT NULL,
    tenure_months INT NOT NULL,
    status ENUM('PENDING','APPROVED','REJECTED','CLOSED') DEFAULT 'PENDING',
    applied_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (customer_id) REFERENCES customers(customer_id) ON DELETE CASCADE
);

CREATE TABLE loan_repayments (
    repayment_id INT AUTO_INCREMENT PRIMARY KEY,
    loan_id INT NOT NULL,
    amount_paid DECIMAL(15,2) NOT NULL,
    repayment_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (loan_id) REFERENCES loans(loan_id) ON DELETE CASCADE
);

CREATE TABLE complaints (
    complaint_id INT AUTO_INCREMENT PRIMARY KEY,
    customer_id INT NOT NULL,
    subject VARCHAR(100) NOT NULL,
    message TEXT NOT NULL,
    status ENUM('OPEN','IN_PROGRESS','RESOLVED') DEFAULT 'OPEN',
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (customer_id) REFERENCES customers(customer_id) ON DELETE CASCADE
);

CREATE TABLE activity_logs (
    log_id INT AUTO_INCREMENT PRIMARY KEY,
    user_id INT NOT NULL,
    action VARCHAR(255) NOT NULL,
    log_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (user_id) REFERENCES users(user_id) ON DELETE CASCADE
);

INSERT INTO users (username, password, role, status)
VALUES ('admin', '$2a$10$WlYdcq2r/mua8Vp/EGi1huAtFaYRxm9zh9L.sdQyWHREJe8ntWvOe', 'ADMIN', 'ACTIVE');

-- Step 1: check if column already exists
DESCRIBE loans;

-- Step 2: If no account_id column, then add it safely
ALTER TABLE loans
ADD COLUMN account_id INT NULL AFTER customer_id;

-- Step 3: Make sure both tables are InnoDB
ALTER TABLE loans ENGINE=InnoDB;
ALTER TABLE accounts ENGINE=InnoDB;

-- Step 4: Add the foreign key constraint
ALTER TABLE loans
ADD CONSTRAINT fk_loans_account
FOREIGN KEY (account_id) REFERENCES accounts(account_id)
ON DELETE SET NULL;

ALTER TABLE customers ADD COLUMN status VARCHAR(20) DEFAULT 'ACTIVE';

