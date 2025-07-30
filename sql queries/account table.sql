use tss_students_db;

CREATE TABLE accounts (
    id INT PRIMARY KEY,
    name VARCHAR(100),
    balance DECIMAL(15, 2)
);

INSERT INTO accounts (id, name, balance) VALUES
(1, 'Ashish', 100000.00),
(2, 'Tushar', 50000.00);