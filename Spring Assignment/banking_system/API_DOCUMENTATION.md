# Banking System API Documentation

This document provides comprehensive information about all available APIs in the Banking System.

## Authentication

The system uses JWT-based authentication. All protected endpoints require a valid JWT token in the Authorization header:
```
Authorization: Bearer <jwt_token>
```

### Authentication Flow

1. **Register**: `POST /api/auth/register` - Register as a customer (role is automatically set to CUSTOMER)
2. **Login**: `POST /api/auth/login` - Login and receive JWT token
   - Status: PENDING (requires admin approval)

3. **Login**: `POST /api/auth/login` (Public)
   - Returns JWT token for authenticated requests

## API Endpoints

### Authentication APIs
- `POST /api/auth/register` - Customer registration
- `POST /api/auth/login` - User login

### SuperAdmin APIs (`/api/superadmin/`)
- `POST /initialize` - Create initial superadmin
- `POST /create-admin` - Create new admin users
- `POST /banks` - Create banks
- `GET /banks` - List all banks
- `POST /branches` - Create branches
- `GET /branches` - List all branches
- `GET /banks/{bankId}/branches` - Get branches by bank
- `POST /branches/{branchId}/assign-admin` - Assign admin to branch

### Admin APIs (`/api/admin/`)
- `GET /users/pending` - Get pending user approvals
- `PUT /users/{id}/approve` - Approve user
- `PUT /users/{id}/reject` - Reject user

### Customer APIs (Role: CUSTOMER)
- `GET /api/customers/profile` - Get customer profile
- `POST /api/customers/accounts` - Create account (self-service)
- `GET /api/customers/accounts` - Get customer accounts
- `POST /api/customers/transactions` - Create transaction
- `GET /api/customers/transactions` - Get transaction history (paginated)
- `GET /api/customers/accounts/{accountId}/transactions` - Get account transactions
- `GET /api/customers/accounts/{accountId}/statement` - Get account statement
- `POST /api/customers/kyc/upload` - Upload KYC document (with file)
- `GET /api/customers/kyc` - Get customer KYC documents

### Account Management (`/api/accounts/`)
- `POST /admin/` - Admin create account (requires ADMIN/SUPERADMIN)
- `GET /{id}` - Get account by ID

### Transaction Management (`/api/transactions/`)
- `POST /admin/` - Admin create transaction (requires ADMIN/SUPERADMIN)
- `GET /{id}` - Get transaction by ID

### Address Management (`/api/addresses/`)
- `POST /` - Create address
- `GET /{id}` - Get address by ID
- `GET /` - List all addresses
- `PUT /{id}` - Update address
- `DELETE /{id}` - Delete address

### Beneficiary Management (`/api/beneficiaries/`)
- `POST /` - Create beneficiary
- `GET /{id}` - Get beneficiary by ID
- `GET /` - List all beneficiaries (Admin only)
- `GET /owner/{ownerId}` - Get beneficiaries by owner
- `PUT /{id}` - Update beneficiary
- `DELETE /{id}` - Delete beneficiary

### Fee Rule Management (`/api/fee-rules/`)
- `POST /` - Create fee rule (requires ADMIN/SUPERADMIN)
- `GET /{id}` - Get fee rule by ID
- `GET /` - Get all fee rules
- `GET /active` - Get active fee rules
- `GET /transaction-type/{type}` - Get fee rules by transaction type
- `PUT /{id}` - Update fee rule
- `DELETE /{id}` - Delete fee rule

### KYC Document Management (`/api/kyc-documents/`)
- `POST /` - Create KYC document (requires ADMIN/SUPERADMIN/CUSTOMER)
- `POST /upload` - Upload KYC document with file (Cloudinary integration)
- `GET /{id}` - Get KYC document by ID
- `GET /` - Get all KYC documents (requires ADMIN/SUPERADMIN)
- `GET /customer/{customerId}` - Get KYC documents by customer
- `GET /status?status={status}` - Get KYC documents by status
- `PUT /{id}` - Update KYC document
- `PUT /{id}/verify?status={status}` - Verify KYC document (Admin only)
- `DELETE /{id}` - Delete KYC document (Admin only)

### Interest Rate Config (`/api/interest-rate-configs/`)
- `POST /` - Create interest rate config
- `GET /{id}` - Get config by ID
- `GET /` - List all configs
- `GET /active` - Get active configs
- `GET /account-type?accountType=` - Get configs by account type
- `PUT /{id}` - Update config
- `DELETE /{id}` - Delete config

### KYC Document Management (`/api/kyc-documents/`)
- `POST /` - Create KYC document
- `GET /{id}` - Get KYC document by ID
- `GET /` - List all KYC documents (Admin only)
- `GET /customer/{customerId}` - Get documents by customer
- `GET /status?status=` - Get documents by status
- `PUT /{id}` - Update KYC document
- `PUT /{id}/verify?status=` - Verify document (Admin only)
- `DELETE /{id}` - Delete KYC document

### Notification Management (`/api/notifications/`)
- `POST /` - Create notification (Admin only)
- `GET /{id}` - Get notification by ID
- `GET /` - List all notifications (Admin only)
- `GET /my-notifications` - Get user's notifications
- `GET /my-notifications/unread` - Get unread notifications
- `GET /user/{userId}` - Get notifications by user (Admin only)
- `GET /status?status=` - Get notifications by status
- `PUT /{id}/mark-read` - Mark as read
- `PUT /{id}` - Update notification
- `DELETE /{id}` - Delete notification

## Key Features Implemented

### 1. **Fixed Authentication Issue**
- ✅ Customers can now create accounts using their own tokens
- ✅ Proper role-based access control

### 2. **Complete Entity Coverage**
- ✅ All entities have full CRUD operations
- ✅ Proper relationships and validations
- ✅ Enhanced Address entity with validation constraints

### 3. **Security Enhancements**
- ✅ JWT-based authentication
- ✅ Role-based authorization
- ✅ Proper endpoint security configuration

### 4. **Address Management Solution**
- ✅ Complete Address entity with validation
- ✅ Full CRUD operations for addresses
- ✅ Proper relationship with User entity

### 5. **Comprehensive API Coverage**
- ✅ User management and approval workflow
- ✅ Account creation and management
- ✅ Transaction processing
- ✅ Beneficiary management
- ✅ Fee rule configuration
- ✅ Interest rate management
- ✅ KYC document handling
- ✅ Notification system

## Usage Examples

### 1. Complete Setup Flow
```bash
# 1. Initialize SuperAdmin
POST /api/superadmin/initialize

# 2. Login as SuperAdmin
POST /api/auth/login
{
  "email": "admin@system.com",
  "password": "admin123"
}

# 3. Create Bank
POST /api/superadmin/banks
{
  "name": "ABC Bank",
  "code": "ABC001",
  "address": "Main Street"
}

# 4. Create Branch
POST /api/superadmin/branches
{
  "name": "Main Branch",
  "code": "BR001",
  "address": "Downtown",
  "bankId": 1
}
```

### 2. Customer Registration & Account Creation
```bash
# 1. Register as Customer
POST /api/auth/register
{
  "firstName": "John",
  "lastName": "Doe",
  "email": "john.doe@example.com",
  "password": "password123",
  "phoneNumber": "1234567890"
}

# 2. Admin approves customer
PUT /api/admin/users/1/approve

# 3. Customer login
POST /api/auth/login
{
  "email": "john.doe@example.com",
  "password": "password123"
}

# 4. Customer creates account
POST /api/customers/accounts
{
  "accountType": "SAVINGS",
  "initialBalance": 5000.0,
  "branchId": 1
}
```

## Error Handling
- All APIs return proper HTTP status codes
- Comprehensive error messages
- Validation error details
- Authentication/Authorization error responses

## Swagger Documentation
Access interactive API documentation at: `/swagger-ui/index.html`
