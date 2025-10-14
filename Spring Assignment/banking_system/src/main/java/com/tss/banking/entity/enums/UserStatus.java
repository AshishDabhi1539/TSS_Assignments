package com.tss.banking.entity.enums;

public enum UserStatus {
<<<<<<< HEAD
    PENDING,
    VERIFIED,
    INACTIVE
=======
    PENDING,           // User registered but email not verified
    EMAIL_VERIFIED,    // Email verified but admin approval pending
    VERIFIED,          // Admin approved and user can login
    INACTIVE           // User deactivated or rejected
>>>>>>> 71789bece0117f6fd0443d9de29f6cd341d4deba
}


