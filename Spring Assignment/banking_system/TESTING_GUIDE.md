# Banking System API Testing Guide

This comprehensive guide provides all HTTP methods, request bodies, and authentication requirements for testing your banking system from start to end.

## Base URL
```
http://localhost:8080
```

## Testing Flow (Recommended Order)

### 1. SUPERADMIN INITIALIZATION

#### Initialize SuperAdmin (First Time Setup)
```http
POST /api/superadmin/init
Content-Type: application/json

{
  "firstName": "Super",
  "lastName": "Admin",
  "email": "superadmin@bank.com",
  "password": "superadmin123",
  "phoneNumber": "9999999999"
}
```
**Response**: SuperAdmin user created with token
**Save Token**: `SUPERADMIN_TOKEN`

### 2. BANK MANAGEMENT (SuperAdmin Only)

#### Create Bank
```http
POST /api/superadmin/banks
Authorization: Bearer {SUPERADMIN_TOKEN}
Content-Type: application/json

{
  "name": "State Bank of India",
  "address": "Mumbai, Maharashtra",
  "code": "SBI001",
  "currency": "INR",
  "country": "India"
}
```
**Response**: Bank created with ID (save as `BANK_ID`)

#### Get All Banks
```http
GET /api/superadmin/banks
Authorization: Bearer {SUPERADMIN_TOKEN}
```

### 3. BRANCH MANAGEMENT (SuperAdmin Only)

#### Create Branch
```http
POST /api/superadmin/branches
Authorization: Bearer {SUPERADMIN_TOKEN}
Content-Type: application/json

{
  "name": "Main Branch",
  "address": "Connaught Place, New Delhi",
  "contactNumber": "011-12345678",
  "bankId": {BANK_ID}
}
```
**Response**: Branch created with ID (save as `BRANCH_ID`)

#### Create Another Branch
```http
POST /api/superadmin/branches
Authorization: Bearer {SUPERADMIN_TOKEN}
Content-Type: application/json

{
  "name": "Secondary Branch",
  "address": "Karol Bagh, New Delhi",
  "contactNumber": "011-87654321",
  "bankId": {BANK_ID}
}
```

#### Get Branches by Bank
```http
GET /api/superadmin/banks/{BANK_ID}/branches
Authorization: Bearer {SUPERADMIN_TOKEN}
```

### 4. ADMIN USER CREATION (SuperAdmin Only)

#### Create Admin User
```http
POST /api/superadmin/admins
Authorization: Bearer {SUPERADMIN_TOKEN}
Content-Type: application/json

{
  "firstName": "Bank",
  "lastName": "Admin",
  "email": "admin@bank.com",
  "password": "admin123",
  "phoneNumber": "8888888888"
}
```
**Response**: Admin user created with ID (save as `ADMIN_ID`)

#### Assign Admin to Branch
```http
POST /api/superadmin/branches/{BRANCH_ID}/assign-admin
Authorization: Bearer {SUPERADMIN_TOKEN}
Content-Type: application/json

{
  "adminId": {ADMIN_ID}
}
```

### 5. CUSTOMER REGISTRATION (Public)

#### Register Customer
```http
POST /api/auth/register
Content-Type: application/json

{
  "firstName": "John",
  "lastName": "Doe",
  "email": "john.doe@example.com",
  "password": "password123",
  "phoneNumber": "7777777777"
}
```
**Response**: Customer registered with PENDING status (save as `CUSTOMER_ID`)

### 6. ADMIN LOGIN & CUSTOMER APPROVAL

#### Admin Login
```http
POST /api/auth/login
Content-Type: application/json

{
  "email": "admin@bank.com",
  "password": "admin123"
}
```
**Response**: JWT token (save as `ADMIN_TOKEN`)

#### Get Pending Users
```http
GET /api/admin/users/pending
Authorization: Bearer {ADMIN_TOKEN}
```

#### Approve Customer
```http
PUT /api/admin/users/{CUSTOMER_ID}/approve
Authorization: Bearer {ADMIN_TOKEN}
```

### 7. CUSTOMER LOGIN & ACCOUNT CREATION

#### Customer Login
```http
POST /api/auth/login
Content-Type: application/json

{
  "email": "john.doe@example.com",
  "password": "password123"
}
```
**Response**: JWT token (save as `CUSTOMER_TOKEN`)

#### Customer Profile
```http
GET /api/customers/profile
Authorization: Bearer {CUSTOMER_TOKEN}
```

#### Create Customer Account (Self-Service)
```http
POST /api/customers/accounts
Authorization: Bearer {CUSTOMER_TOKEN}
Content-Type: application/json

{
  "accountType": "SAVINGS",
  "initialBalance": 10000.0,
  "branchId": {BRANCH_ID}
}
```
**Response**: Account created (save as `ACCOUNT_ID`)

#### Get Customer Accounts
```http
GET /api/customers/accounts
Authorization: Bearer {CUSTOMER_TOKEN}
```

### 8. KYC DOCUMENT UPLOAD (Customer Self-Service)

#### Upload KYC Document with File
```http
POST /api/customers/kyc/upload
Authorization: Bearer {CUSTOMER_TOKEN}
Content-Type: multipart/form-data

Form Data:
- documentType: "AADHAAR"
- documentNumber: "1234-5678-9012"
- issuedBy: "Government of India"
- file: [Select PDF/Image file]
```

#### Get Customer KYC Documents
```http
GET /api/customers/kyc
Authorization: Bearer {CUSTOMER_TOKEN}
```

### 9. KYC VERIFICATION (Admin)

#### Get Pending KYC Documents
```http
GET /api/kyc-documents/status?status=PENDING
Authorization: Bearer {ADMIN_TOKEN}
```

#### Verify KYC Document
```http
PUT /api/kyc-documents/{KYC_ID}/verify?status=APPROVED
Authorization: Bearer {ADMIN_TOKEN}
```

### 10. TRANSACTION MANAGEMENT

#### Create Transaction (Customer)
```http
POST /api/customers/transactions
Authorization: Bearer {CUSTOMER_TOKEN}
Content-Type: application/json

{
  "fromAccountId": {ACCOUNT_ID},
  "toAccountId": null,
  "amount": 500.0,
  "transactionType": "DEBIT",
  "description": "ATM Withdrawal"
}
```

#### Get Transaction History
```http
GET /api/customers/transactions?page=0&size=10
Authorization: Bearer {CUSTOMER_TOKEN}
```

#### Get Account Transactions
```http
GET /api/customers/accounts/{ACCOUNT_ID}/transactions?page=0&size=10
Authorization: Bearer {CUSTOMER_TOKEN}
```

### 11. BENEFICIARY MANAGEMENT

#### Add Beneficiary
```http
POST /api/beneficiaries
Authorization: Bearer {CUSTOMER_TOKEN}
Content-Type: application/json

{
  "ownerId": {CUSTOMER_ID},
  "beneficiaryAccountId": {OTHER_ACCOUNT_ID},
  "alias": "Friend Account",
  "verificationStatus": "PENDING"
}
```

#### Get Customer Beneficiaries
```http
GET /api/beneficiaries/owner/{CUSTOMER_ID}
Authorization: Bearer {CUSTOMER_TOKEN}
```

### 12. ADDRESS MANAGEMENT

#### Create Address
```http
POST /api/addresses
Authorization: Bearer {CUSTOMER_TOKEN}
Content-Type: application/json

{
  "street": "123 Main Street",
  "city": "New Delhi",
  "state": "Delhi",
  "zipCode": "110001",
  "country": "India",
  "addressType": "HOME"
}
```

#### Get All Addresses
```http
GET /api/addresses
Authorization: Bearer {CUSTOMER_TOKEN}
```

### 13. ADMIN ACCOUNT MANAGEMENT

#### Admin Create Account (for any customer)
```http
POST /api/accounts/admin
Authorization: Bearer {ADMIN_TOKEN}
Content-Type: application/json

{
  "customerId": {CUSTOMER_ID},
  "accountType": "CURRENT",
  "initialBalance": 25000.0,
  "branchId": {BRANCH_ID}
}
```

#### Get Account by ID
```http
GET /api/accounts/{ACCOUNT_ID}
Authorization: Bearer {ADMIN_TOKEN}
```

### 14. FEE RULE MANAGEMENT (Admin)

#### Create Fee Rule
```http
POST /api/fee-rules
Authorization: Bearer {ADMIN_TOKEN}
Content-Type: application/json

{
  "transactionType": "TRANSFER",
  "feeType": "FLAT",
  "feeAmount": 10.0,
  "minAmount": 1000.0,
  "maxAmount": 100000.0,
  "isActive": true
}
```

#### Get Active Fee Rules
```http
GET /api/fee-rules/active
Authorization: Bearer {ADMIN_TOKEN}
```

### 15. INTEREST RATE CONFIG (Admin)

#### Create Interest Rate Config
```http
POST /api/interest-rate-configs
Authorization: Bearer {ADMIN_TOKEN}
Content-Type: application/json

{
  "accountType": "SAVINGS",
  "interestRate": 4.5,
  "minBalance": 1000.0,
  "isActive": true
}
```

#### Get Interest Configs by Account Type
```http
GET /api/interest-rate-configs/account-type?accountType=SAVINGS
Authorization: Bearer {ADMIN_TOKEN}
```

### 16. NOTIFICATION MANAGEMENT

#### Create Notification
```http
POST /api/notifications
Authorization: Bearer {ADMIN_TOKEN}
Content-Type: application/json

{
  "recipientId": {CUSTOMER_ID},
  "title": "Account Created",
  "message": "Your savings account has been successfully created",
  "type": "ACCOUNT_UPDATE"
}
```

#### Get Customer Notifications
```http
GET /api/notifications/recipient/{CUSTOMER_ID}
Authorization: Bearer {CUSTOMER_TOKEN}
```

## Authentication Tokens Summary

| Role | Login Endpoint | Token Variable | Access Level |
|------|----------------|----------------|--------------|
| SuperAdmin | `/api/superadmin/init` | `SUPERADMIN_TOKEN` | Full system access |
| Admin | `/api/auth/login` | `ADMIN_TOKEN` | Bank/branch management |
| Customer | `/api/auth/login` | `CUSTOMER_TOKEN` | Own data access |

## Important Notes

1. **Token Usage**: Always include `Authorization: Bearer {TOKEN}` header for protected endpoints
2. **File Upload**: Use `Content-Type: multipart/form-data` for KYC document uploads
3. **Pagination**: Most list endpoints support `?page=0&size=10` parameters
4. **Environment**: Ensure Cloudinary credentials are set for file uploads
5. **Database**: Make sure MySQL is running and configured properly

## Error Handling

Common HTTP status codes:
- `200`: Success
- `201`: Created
- `400`: Bad Request (validation errors)
- `401`: Unauthorized (invalid/missing token)
- `403`: Forbidden (insufficient permissions)
- `404`: Not Found
- `500`: Internal Server Error

## Testing Tools Recommended

- **Postman**: For GUI-based testing
- **cURL**: For command-line testing
- **Insomnia**: Alternative to Postman
- **Thunder Client**: VS Code extension

This guide covers all major endpoints and flows in your banking system. Test in the order provided for the best results!
