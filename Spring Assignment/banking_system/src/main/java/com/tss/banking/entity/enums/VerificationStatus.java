package com.tss.banking.entity.enums;

public enum VerificationStatus {
    PENDING("Pending Verification"),
    VERIFIED("Verified"),
    REJECTED("Rejected"),
    EXPIRED("Expired"),
    UNDER_REVIEW("Under Review");
    
    private final String displayName;
    
    VerificationStatus(String displayName) {
        this.displayName = displayName;
    }
    
    public String getDisplayName() {
        return displayName;
    }
}
