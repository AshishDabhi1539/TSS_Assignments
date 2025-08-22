CREATE DATABASE banking_system;
USE banking_system;

-- =========================
-- Admin Table
-- =========================
CREATE TABLE admin (
    admin_id INT PRIMARY KEY AUTO_INCREMENT,
    username VARCHAR(50) UNIQUE NOT NULL,
    password VARCHAR(255) NOT NULL, -- store hashed password
    full_name VARCHAR(100) NOT NULL,
    email VARCHAR(100) UNIQUE NOT NULL,
    phone VARCHAR(15),
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);

-- =========================
-- Branch Management
-- =========================
CREATE TABLE branch (
    branch_id INT PRIMARY KEY AUTO_INCREMENT,
    branch_name VARCHAR(100) NOT NULL,
    branch_code VARCHAR(20) UNIQUE NOT NULL,
    address TEXT NOT NULL,
    phone VARCHAR(15),
    manager_name VARCHAR(100)
);

-- =========================
-- Customer Table
-- =========================
CREATE TABLE customer (
    customer_id INT PRIMARY KEY AUTO_INCREMENT,
    branch_id INT NOT NULL,
    username VARCHAR(50) UNIQUE NOT NULL,
    password VARCHAR(255) NOT NULL, -- store hashed password
    full_name VARCHAR(100) NOT NULL,
    email VARCHAR(100) UNIQUE NOT NULL,
    phone VARCHAR(15) NOT NULL,
    address TEXT NOT NULL,
    date_of_birth DATE NOT NULL,
    pan_number VARCHAR(10) UNIQUE NOT NULL,
    aadhar_number VARCHAR(12) UNIQUE NOT NULL,
    account_status ENUM('ACTIVE', 'INACTIVE', 'SUSPENDED') DEFAULT 'ACTIVE',
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    FOREIGN KEY (branch_id) REFERENCES branch(branch_id)
);

-- =========================
-- KYC Documents
-- =========================
CREATE TABLE kyc_document (
    doc_id INT PRIMARY KEY AUTO_INCREMENT,
    customer_id INT NOT NULL,
    document_type ENUM('PAN', 'AADHAR', 'PASSPORT', 'DRIVING_LICENSE') NOT NULL,
    document_number VARCHAR(50) UNIQUE NOT NULL,
    file_path VARCHAR(255),
    verification_status ENUM('PENDING', 'VERIFIED', 'REJECTED') DEFAULT 'PENDING',
    uploaded_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (customer_id) REFERENCES customer(customer_id) ON DELETE CASCADE
);

-- =========================
-- Account Types
-- =========================
CREATE TABLE account_type (
    type_id INT PRIMARY KEY AUTO_INCREMENT,
    type_name VARCHAR(50) NOT NULL,
    minimum_balance DECIMAL(10,2) NOT NULL,
    interest_rate DECIMAL(5,2) NOT NULL,
    description TEXT
);

-- =========================
-- Accounts
-- =========================
CREATE TABLE account (
    account_id INT PRIMARY KEY AUTO_INCREMENT,
    account_number VARCHAR(20) UNIQUE NOT NULL,
    customer_id INT NOT NULL,
    account_type_id INT NOT NULL,
    balance DECIMAL(15,2) DEFAULT 0.00,
    status ENUM('ACTIVE', 'INACTIVE', 'CLOSED') DEFAULT 'ACTIVE',
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    FOREIGN KEY (customer_id) REFERENCES customer(customer_id) ON DELETE CASCADE,
    FOREIGN KEY (account_type_id) REFERENCES account_type(type_id)
);

-- =========================
-- Transaction Types
-- =========================
CREATE TABLE transaction_type (
    type_id INT PRIMARY KEY AUTO_INCREMENT,
    type_name VARCHAR(50) NOT NULL,
    description TEXT
);

-- =========================
-- Beneficiaries (moved BEFORE transactions to fix FK dependency)
-- =========================
CREATE TABLE beneficiary (
    beneficiary_id INT PRIMARY KEY AUTO_INCREMENT,
    customer_id INT NOT NULL,
    beneficiary_name VARCHAR(100) NOT NULL,
    account_number VARCHAR(20) NOT NULL,
    ifsc_code VARCHAR(11) NOT NULL,
    bank_name VARCHAR(100) NOT NULL,
    nickname VARCHAR(50),
    is_active BOOLEAN DEFAULT TRUE,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (customer_id) REFERENCES customer(customer_id) ON DELETE CASCADE
);

-- =========================
-- Transactions
-- =========================
CREATE TABLE transactions (
    transaction_id INT PRIMARY KEY AUTO_INCREMENT,
    account_id INT NOT NULL,
    transaction_type_id INT NOT NULL,
    amount DECIMAL(15,2) NOT NULL,
    balance_after DECIMAL(15,2) NOT NULL,
    description TEXT,
    reference_number VARCHAR(50) UNIQUE,
    to_account_id INT NULL,
    beneficiary_id INT NULL,
    status ENUM('SUCCESS', 'PENDING', 'FAILED') DEFAULT 'SUCCESS',
    transaction_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (account_id) REFERENCES account(account_id) ON DELETE CASCADE,
    FOREIGN KEY (transaction_type_id) REFERENCES transaction_type(type_id),
    FOREIGN KEY (to_account_id) REFERENCES account(account_id),
    FOREIGN KEY (beneficiary_id) REFERENCES beneficiary(beneficiary_id)
);

-- =========================
-- Loan Types
-- =========================
CREATE TABLE loan_type (
    type_id INT PRIMARY KEY AUTO_INCREMENT,
    type_name VARCHAR(50) NOT NULL,
    interest_rate DECIMAL(5,2) NOT NULL,
    max_amount DECIMAL(15,2) NOT NULL,
    max_tenure_months INT NOT NULL,
    description TEXT
);

-- =========================
-- Loans
-- =========================
CREATE TABLE loan (
    loan_id INT PRIMARY KEY AUTO_INCREMENT,
    loan_number VARCHAR(20) UNIQUE NOT NULL,
    customer_id INT NOT NULL,
    account_id INT NOT NULL,
    loan_type_id INT NOT NULL,
    principal_amount DECIMAL(15,2) NOT NULL,
    interest_rate DECIMAL(5,2) NOT NULL,
    tenure_months INT NOT NULL,
    monthly_emi DECIMAL(10,2) NOT NULL,
    outstanding_amount DECIMAL(15,2) NOT NULL,
    status ENUM('PENDING', 'APPROVED', 'ACTIVE', 'CLOSED', 'REJECTED') DEFAULT 'PENDING',
    application_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    approval_date TIMESTAMP NULL,
    disbursement_date TIMESTAMP NULL,
    FOREIGN KEY (customer_id) REFERENCES customer(customer_id) ON DELETE CASCADE,
    FOREIGN KEY (account_id) REFERENCES account(account_id) ON DELETE CASCADE,
    FOREIGN KEY (loan_type_id) REFERENCES loan_type(type_id)
);

-- =========================
-- Loan Payments
-- =========================
CREATE TABLE loan_payment (
    payment_id INT PRIMARY KEY AUTO_INCREMENT,
    loan_id INT NOT NULL,
    amount DECIMAL(10,2) NOT NULL,
    payment_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    principal_component DECIMAL(10,2) NOT NULL,
    interest_component DECIMAL(10,2) NOT NULL,
    outstanding_after DECIMAL(15,2) NOT NULL,
    status ENUM('SUCCESS', 'FAILED') DEFAULT 'SUCCESS',
    FOREIGN KEY (loan_id) REFERENCES loan(loan_id) ON DELETE CASCADE
);

-- =========================
-- Fixed Deposits
-- =========================
CREATE TABLE fixed_deposit (
    fd_id INT PRIMARY KEY AUTO_INCREMENT,
    fd_number VARCHAR(20) UNIQUE NOT NULL,
    customer_id INT NOT NULL,
    principal_amount DECIMAL(15,2) NOT NULL,
    interest_rate DECIMAL(5,2) NOT NULL,
    tenure_months INT NOT NULL,
    maturity_amount DECIMAL(15,2) NOT NULL,
    start_date DATE NOT NULL,
    maturity_date DATE NOT NULL,
    status ENUM('ACTIVE', 'MATURED', 'PREMATURE_CLOSED') DEFAULT 'ACTIVE',
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (customer_id) REFERENCES customer(customer_id) ON DELETE CASCADE
);

-- =========================
-- Recurring Deposits
-- =========================
CREATE TABLE recurring_deposit (
    rd_id INT PRIMARY KEY AUTO_INCREMENT,
    rd_number VARCHAR(20) UNIQUE NOT NULL,
    customer_id INT NOT NULL,
    monthly_amount DECIMAL(15,2) NOT NULL,
    interest_rate DECIMAL(5,2) NOT NULL,
    tenure_months INT NOT NULL,
    maturity_amount DECIMAL(15,2) NOT NULL,
    start_date DATE NOT NULL,
    maturity_date DATE NOT NULL,
    status ENUM('ACTIVE', 'MATURED', 'PREMATURE_CLOSED') DEFAULT 'ACTIVE',
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (customer_id) REFERENCES customer(customer_id) ON DELETE CASCADE
);

-- =========================
-- Cards
-- =========================
CREATE TABLE card (
    card_id INT PRIMARY KEY AUTO_INCREMENT,
    customer_id INT NOT NULL,
    account_id INT NOT NULL,
    card_number VARCHAR(16) UNIQUE NOT NULL,
    card_type ENUM('DEBIT', 'CREDIT') NOT NULL,
    expiry_date DATE NOT NULL,
    cvv VARCHAR(3) NOT NULL,
    status ENUM('ACTIVE', 'BLOCKED', 'EXPIRED') DEFAULT 'ACTIVE',
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (customer_id) REFERENCES customer(customer_id) ON DELETE CASCADE,
    FOREIGN KEY (account_id) REFERENCES account(account_id) ON DELETE CASCADE
);

-- =========================
-- Staff Management
-- =========================
CREATE TABLE staff (
    staff_id INT PRIMARY KEY AUTO_INCREMENT,
    branch_id INT NOT NULL,
    full_name VARCHAR(100) NOT NULL,
    role ENUM('MANAGER', 'CASHIER', 'CLERK') NOT NULL,
    email VARCHAR(100) UNIQUE NOT NULL,
    phone VARCHAR(15),
    hire_date DATE NOT NULL,
    status ENUM('ACTIVE', 'INACTIVE') DEFAULT 'ACTIVE',
    FOREIGN KEY (branch_id) REFERENCES branch(branch_id)
);

-- =========================
-- Notifications
-- =========================
CREATE TABLE notification (
    notification_id INT PRIMARY KEY AUTO_INCREMENT,
    customer_id INT NOT NULL,
    message TEXT NOT NULL,
    type ENUM('TRANSACTION', 'SECURITY', 'GENERAL') DEFAULT 'GENERAL',
    is_read BOOLEAN DEFAULT FALSE,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (customer_id) REFERENCES customer(customer_id) ON DELETE CASCADE
);

-- =========================
-- Login Audit
-- =========================
CREATE TABLE login_audit (
    audit_id INT PRIMARY KEY AUTO_INCREMENT,
    user_type ENUM('ADMIN', 'CUSTOMER') NOT NULL,
    user_id INT NOT NULL,
    login_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    logout_time TIMESTAMP NULL,
    ip_address VARCHAR(45),
    device_info VARCHAR(255),
    status ENUM('SUCCESS', 'FAILED') DEFAULT 'SUCCESS'
);

-- =========================
-- Audit Logs
-- =========================
CREATE TABLE audit_log (
    log_id INT PRIMARY KEY AUTO_INCREMENT,
    user_type ENUM('ADMIN', 'CUSTOMER') NOT NULL,
    user_id INT NOT NULL,
    action VARCHAR(100) NOT NULL,
    details TEXT,
    ip_address VARCHAR(45),
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- =========================
-- Cheque Book Requests
-- =========================
CREATE TABLE cheque_request (
    request_id INT PRIMARY KEY AUTO_INCREMENT,
    account_id INT NOT NULL,
    no_of_leaves INT NOT NULL,
    status ENUM('PENDING', 'APPROVED', 'REJECTED', 'ISSUED') DEFAULT 'PENDING',
    request_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (account_id) REFERENCES account(account_id) ON DELETE CASCADE
);

-- =========================
-- Service Requests
-- =========================
CREATE TABLE service_request (
    request_id INT PRIMARY KEY AUTO_INCREMENT,
    customer_id INT NOT NULL,
    category ENUM('COMPLAINT', 'SERVICE', 'QUERY') NOT NULL,
    subject VARCHAR(200) NOT NULL,
    description TEXT,
    status ENUM('PENDING', 'IN_PROGRESS', 'RESOLVED', 'CLOSED') DEFAULT 'PENDING',
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (customer_id) REFERENCES customer(customer_id) ON DELETE CASCADE
);

-- =========================
-- Account Statements
-- =========================
CREATE TABLE account_statement (
    statement_id INT PRIMARY KEY AUTO_INCREMENT,
    account_id INT NOT NULL,
    period_start DATE NOT NULL,
    period_end DATE NOT NULL,
    generated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    file_path VARCHAR(255),
    FOREIGN KEY (account_id) REFERENCES account(account_id) ON DELETE CASCADE
);
SET FOREIGN_KEY_CHECKS = 0;

INSERT INTO admin (username, password, full_name, email, phone, created_at, updated_at)
VALUES ('admin1', 'c86602903b82fbe3653df60006af5abd:f9303f127c2442b326765c159f94240c0d7da6e1b12749551039cda30d5b3b90', 'Admin One', 'admin1@bank.com', '9876543210', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);




DROP TABLE IF EXISTS audit_log;
DROP TABLE IF EXISTS login_audit;
DROP TABLE IF EXISTS notification;
DROP TABLE IF EXISTS service_request;
DROP TABLE IF EXISTS cheque_request;
DROP TABLE IF EXISTS account_statement;
DROP TABLE IF EXISTS loan_payment;
DROP TABLE IF EXISTS transactions;
DROP TABLE IF EXISTS beneficiary;
DROP TABLE IF EXISTS fixed_deposit;
DROP TABLE IF EXISTS recurring_deposit;
DROP TABLE IF EXISTS card;
DROP TABLE IF EXISTS loan;
DROP TABLE IF EXISTS account;
DROP TABLE IF EXISTS customer;
DROP TABLE IF EXISTS kyc_document;
DROP TABLE IF EXISTS account_type;
DROP TABLE IF EXISTS loan_type;
DROP TABLE IF EXISTS transaction_type;
DROP TABLE IF EXISTS staff;
DROP TABLE IF EXISTS branch;
DROP TABLE IF EXISTS admin;