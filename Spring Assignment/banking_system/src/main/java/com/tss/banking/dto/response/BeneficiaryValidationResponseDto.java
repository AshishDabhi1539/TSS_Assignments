package com.tss.banking.dto.response;

import java.util.List;

public class BeneficiaryValidationResponseDto {

    private boolean valid;
    private String message;
    private List<String> errors;
    private List<String> warnings;
    
    // Validated data
    private String accountNumber;
    private String ifscCode;
    private String accountHolderName;
    private String alias;
    private String transferType; // INTRABANK or INTERBANK

    // Constructors
    public BeneficiaryValidationResponseDto() {}

    public BeneficiaryValidationResponseDto(boolean valid, String message, List<String> errors, List<String> warnings) {
        this.valid = valid;
        this.message = message;
        this.errors = errors;
        this.warnings = warnings;
    }

    // Getters and Setters
    public boolean isValid() {
        return valid;
    }

    public void setValid(boolean valid) {
        this.valid = valid;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<String> getErrors() {
        return errors;
    }

    public void setErrors(List<String> errors) {
        this.errors = errors;
    }

    public List<String> getWarnings() {
        return warnings;
    }

    public void setWarnings(List<String> warnings) {
        this.warnings = warnings;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public String getIfscCode() {
        return ifscCode;
    }

    public void setIfscCode(String ifscCode) {
        this.ifscCode = ifscCode;
    }

    public String getAccountHolderName() {
        return accountHolderName;
    }

    public void setAccountHolderName(String accountHolderName) {
        this.accountHolderName = accountHolderName;
    }

    public String getAlias() {
        return alias;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }

    public String getTransferType() {
        return transferType;
    }

    public void setTransferType(String transferType) {
        this.transferType = transferType;
    }

    @Override
    public String toString() {
        return "BeneficiaryValidationResponseDto{" +
                "valid=" + valid +
                ", message='" + message + '\'' +
                ", errors=" + errors +
                ", warnings=" + warnings +
                ", accountNumber='" + accountNumber + '\'' +
                ", ifscCode='" + ifscCode + '\'' +
                ", accountHolderName='" + accountHolderName + '\'' +
                ", alias='" + alias + '\'' +
                ", transferType='" + transferType + '\'' +
                '}';
    }
}
