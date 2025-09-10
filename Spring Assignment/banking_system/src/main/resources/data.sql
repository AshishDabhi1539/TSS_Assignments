-- Insert SuperAdmin user with static credentials: email=admin@system.com, password=admin123
-- Password hash for 'admin123': $2a$10$N.zmdr9k7uOCQb07YxWpNOy6y0.L1n/vyVs5Z5.JfbkqP.fzUOBdS
INSERT INTO users (first_name, last_name, email, phone, address, status, role, password, enabled, soft_deleted, locked, created_at, updated_at, version) VALUES 
('Super', 'Admin', 'admin@system.com', '1234567890', 'HQ Office', 'VERIFIED', 'SUPERADMIN', '$2a$10$N.zmdr9k7uOCQb07YxWpNOy6y0.L1n/vyVs5Z5.JfbkqP.fzUOBdS', true, false, false, NOW(), NOW(), 0);

-- Insert Banks
INSERT INTO banks (name, code, address, contact_number) VALUES 
('State Bank of India', 'SBI', 'Mumbai, Maharashtra', '1800-1234'),
('HDFC Bank', 'HDFC', 'Mumbai, Maharashtra', '1800-2266'),
('ICICI Bank', 'ICICI', 'Mumbai, Maharashtra', '1800-1080');

-- Insert Branches
INSERT INTO branches (name, code, address, contact_number, bank_id) VALUES 
('SBI Main Branch', 'SBI001', 'Fort, Mumbai', '022-1234567', 1),
('SBI Andheri Branch', 'SBI002', 'Andheri West, Mumbai', '022-1234568', 1),
('HDFC Bandra Branch', 'HDFC001', 'Bandra West, Mumbai', '022-2266001', 2),
('HDFC Powai Branch', 'HDFC002', 'Powai, Mumbai', '022-2266002', 2),
('ICICI Worli Branch', 'ICICI001', 'Worli, Mumbai', '022-1080001', 3),
('ICICI Malad Branch', 'ICICI002', 'Malad West, Mumbai', '022-1080002', 3);