-- Make created_at columns nullable temporarily
ALTER TABLE branches MODIFY COLUMN created_at DATETIME NULL;
ALTER TABLE banks MODIFY COLUMN created_at DATETIME NULL;

-- Fix existing branch records with null created_at
UPDATE branches SET created_at = NOW(), updated_at = NOW() WHERE created_at IS NULL;

-- Fix existing bank records with null created_at  
UPDATE banks SET created_at = NOW(), updated_at = NOW() WHERE created_at IS NULL;

-- Fix existing branch_assignment records with null created_at
UPDATE branch_assignments SET created_at = NOW() WHERE created_at IS NULL;

-- Make created_at columns NOT NULL again after fixing data
ALTER TABLE branches MODIFY COLUMN created_at DATETIME NOT NULL;
ALTER TABLE banks MODIFY COLUMN created_at DATETIME NOT NULL;
