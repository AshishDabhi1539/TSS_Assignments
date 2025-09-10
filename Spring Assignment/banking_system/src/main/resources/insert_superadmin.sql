-- Manual SuperAdmin insertion script
-- Run this directly in your MySQL database to insert SuperAdmin

DELETE FROM users WHERE email = 'admin@system.com';

INSERT INTO users (first_name, last_name, email, phone, address, status, role, password, enabled, soft_deleted, locked, created_at, updated_at, version) VALUES 
('Super', 'Admin', 'admin@system.com', '1234567890', 'HQ Office', 'VERIFIED', 'SUPERADMIN', '$2a$10$N.zmdr9k7uOCQb07YxWpNOy6y0.L1n/vyVs5Z5.JfbkqP.fzUOBdS', true, false, false, NOW(), NOW(), 0);

-- Verify insertion
SELECT id, first_name, last_name, email, role, status, enabled, locked FROM users WHERE email = 'admin@system.com';
