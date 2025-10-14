package com.tss.banking.entity.enums;

public enum FeeType {
    FLAT("Flat Fee"),
    PERCENTAGE("Percentage Fee"),
    TIERED("Tiered Fee"),
    MINIMUM("Minimum Fee"),
    MAXIMUM("Maximum Fee");
    
    private final String displayName;
    
    FeeType(String displayName) {
        this.displayName = displayName;
    }
    
    public String getDisplayName() {
        return displayName;
    }
}
