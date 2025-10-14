# Banking System Environment Configuration Guide

## Required Environment Variables

### 1. Database Configuration (REQUIRED)
```
DB_USERNAME=root
DB_PASSWORD=your_mysql_password
```

### 2. JWT Configuration (REQUIRED)
```
JWT_SECRET=bXlTZWNyZXRLZXlGb3JCYW5raW5nU3lzdGVtVGhhdElzVmVyeVNlY3VyZUFuZExvbmc=
JWT_EXPIRATION=86400000
```

### 3. Server Configuration (OPTIONAL)
```
SERVER_PORT=8080
```

### 4. Cloudinary Configuration (OPTIONAL - Only if using file uploads)
```
CLOUDINARY_CLOUD_NAME=your_cloudinary_name
CLOUDINARY_API_KEY=your_api_key
CLOUDINARY_API_SECRET=your_api_secret
```

## Eclipse Run Configuration Setup

### Step 1: Right-click on your project → Run As → Run Configurations

### Step 2: Select "Spring Boot App" → New Configuration

### Step 3: Set Environment Variables
In the "Environment" tab, add these variables:

**Required Variables:**
- `DB_USERNAME` = `root` (or your MySQL username)
- `DB_PASSWORD` = `your_actual_mysql_password`

**Optional Variables (use defaults if not set):**
- `JWT_SECRET` = `bXlTZWNyZXRLZXlGb3JCYW5raW5nU3lzdGVtVGhhdElzVmVyeVNlY3VyZUFuZExvbmc=`
- `JWT_EXPIRATION` = `86400000`
- `SERVER_PORT` = `8080`

### Step 4: Main Class
- **Project**: banking_system
- **Main class**: `com.tss.banking.BankingSystemApplication`

## Database Setup

### 1. Create MySQL Database
```sql
CREATE DATABASE bank_management_system;
```

### 2. MySQL User Setup (if needed)
```sql
CREATE USER 'bankuser'@'localhost' IDENTIFIED BY 'bankpass123';
GRANT ALL PRIVILEGES ON bank_management_system.* TO 'bankuser'@'localhost';
FLUSH PRIVILEGES;
```

## What You DON'T Need to Set

The following have default values and will work without environment variables:
- ✅ JWT_SECRET (has default Base64 encoded value)
- ✅ JWT_EXPIRATION (defaults to 24 hours)
- ✅ SERVER_PORT (defaults to 8080)
- ✅ Cloudinary settings (optional, only for file uploads)

## Minimum Required Setup

**You ONLY need to set these 2 environment variables:**
1. `DB_USERNAME` - Your MySQL username
2. `DB_PASSWORD` - Your MySQL password

Everything else has sensible defaults!

## Testing the Setup

1. Start MySQL server
2. Create the database: `bank_management_system`
3. Set the 2 required environment variables in Eclipse
4. Run the application
5. Visit: `http://localhost:8080/swagger-ui.html`

## Sample Values for Testing

```
DB_USERNAME=root
DB_PASSWORD=root123
JWT_SECRET=bXlTZWNyZXRLZXlGb3JCYW5raW5nU3lzdGVtVGhhdElzVmVyeVNlY3VyZUFuZExvbmc=
JWT_EXPIRATION=86400000
SERVER_PORT=8080
```

## Troubleshooting

### Common Issues:
1. **Database Connection Failed**: Check DB_USERNAME and DB_PASSWORD
2. **Port Already in Use**: Change SERVER_PORT to 8081 or another port
3. **JWT Issues**: The default JWT_SECRET should work fine
4. **Tables Not Created**: Check if database exists and user has permissions
