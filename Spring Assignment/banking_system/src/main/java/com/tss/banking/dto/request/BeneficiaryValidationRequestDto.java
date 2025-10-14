package com.tss.banking.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public class BeneficiaryValidationRequestDto {

    @NotBlank(message = "Account number is required")
    @Pattern(regexp = "^[A-Z]{3}\\d{9}$", message = "Account number must follow format: ACC123456789")
    private String accountNumber;

    @NotBlank(message = "IFSC code is required")
    @Pattern(regexp = "^[A-Z]{3}\\d{7}[A-Z]{2}$", message = "IFSC code must follow format: FCB0010001HO")
    private String ifscCode;

    @NotBlank(message = "Account holder name is required")
    @Pattern(regexp = "^[a-zA-Z\\s]{2,50}$", message = "Account holder name must contain only letters and spaces (2-50 characters)")
    private String accountHolderName;

    @NotBlank(message = "Alias is required")
    @Size(min = 2, max = 50, message = "Alias must be between 2 and 50 characters")
    private String alias;

    @Size(max = 200, message = "Remarks cannot exceed 200 characters")
    private String remarks;

    // Constructors
    public BeneficiaryValidationRequestDto() {}

    public BeneficiaryValidationRequestDto(String accountNumber, String ifscCode, String accountHolderName, String alias, String remarks) {
        this.accountNumber = accountNumber;
        this.ifscCode = ifscCode;
        this.accountHolderName = accountHolderName;
        this.alias = alias;
        this.remarks = remarks;
    }

    // Getters and Setters
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

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    @Override
    public String toString() {
        return "BeneficiaryValidationRequestDto{" +
                "accountNumber='" + accountNumber + '\'' +
                ", ifscCode='" + ifscCode + '\'' +
                ", accountHolderName='" + accountHolderName + '\'' +
                ", alias='" + alias + '\'' +
                ", remarks='" + remarks + '\'' +
                '}';
    }
}
