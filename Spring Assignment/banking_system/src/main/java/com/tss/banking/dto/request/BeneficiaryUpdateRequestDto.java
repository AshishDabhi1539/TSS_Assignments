package com.tss.banking.dto.request;

import jakarta.validation.constraints.Size;

public class BeneficiaryUpdateRequestDto {

    @Size(min = 2, max = 50, message = "Alias must be between 2 and 50 characters")
    private String alias;

    @Size(max = 200, message = "Remarks cannot exceed 200 characters")
    private String remarks;

    // Constructors
    public BeneficiaryUpdateRequestDto() {}

    public BeneficiaryUpdateRequestDto(String alias, String remarks) {
        this.alias = alias;
        this.remarks = remarks;
    }

    // Getters and Setters
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
        return "BeneficiaryUpdateRequestDto{" +
                "alias='" + alias + '\'' +
                ", remarks='" + remarks + '\'' +
                '}';
    }
}
