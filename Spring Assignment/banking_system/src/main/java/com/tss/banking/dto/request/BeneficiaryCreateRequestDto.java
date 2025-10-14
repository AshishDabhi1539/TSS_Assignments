package com.tss.banking.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public class BeneficiaryCreateRequestDto {

    @NotBlank(message = "Account number is required")
    @Pattern(regexp = "^[A-Z]{3}\\d{9}$", message = "Account number must follow format: ACC123456789")
    private String accountNumber;

    @NotBlank(message = "Alias is required")
    @Size(min = 2, max = 50, message = "Alias must be between 2 and 50 characters")
    private String alias;

    @Size(max = 200, message = "Remarks cannot exceed 200 characters")
    private String remarks;

    // Constructors
    public BeneficiaryCreateRequestDto() {}

    public BeneficiaryCreateRequestDto(String accountNumber, String alias, String remarks) {
        this.accountNumber = accountNumber;
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
        return "BeneficiaryCreateRequestDto{" +
                "accountNumber='" + accountNumber + '\'' +
                ", alias='" + alias + '\'' +
                ", remarks='" + remarks + '\'' +
                '}';
    }
}
