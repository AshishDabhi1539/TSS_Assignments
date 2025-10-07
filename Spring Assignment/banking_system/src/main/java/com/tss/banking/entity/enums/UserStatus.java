package com.tss.banking.entity.enums;

public enum UserStatus {
    PENDING,           // User registered but email not verified
    EMAIL_VERIFIED,    // Email verified but admin approval pending
    VERIFIED,          // Admin approved and user can login
    INACTIVE           // User deactivated or rejected
}


