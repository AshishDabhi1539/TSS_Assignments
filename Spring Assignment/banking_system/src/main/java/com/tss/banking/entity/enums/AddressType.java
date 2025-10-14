package com.tss.banking.entity.enums;

public enum AddressType {
    // User address types
    PERMANENT("Permanent Address"),
    TEMPORARY("Temporary Address"), 
    MAILING("Mailing Address"),
    COMMUNICATION("Communication Address"),
    
    // Bank address types
    REGISTERED("Registered Office"),
    HEAD_OFFICE("Head Office"),
    CORPORATE("Corporate Office"),
    
    // Branch address types
    BRANCH("Branch Office"),
    OFFICE("Office Address"),
    
    // General types
    BILLING("Billing Address"),
    SHIPPING("Shipping Address");
    
    private final String displayName;
    
    AddressType(String displayName) {
        this.displayName = displayName;
    }
    
    public String getDisplayName() {
        return displayName;
    }
    
    // Helper methods for entity-specific address types
    public static AddressType[] getUserAddressTypes() {
        return new AddressType[]{PERMANENT, TEMPORARY, MAILING, COMMUNICATION};
    }
    
    public static AddressType[] getBankAddressTypes() {
        return new AddressType[]{REGISTERED, HEAD_OFFICE, CORPORATE};
    }
    
    public static AddressType[] getBranchAddressTypes() {
        return new AddressType[]{BRANCH, OFFICE};
    }
}
