package com.tss.banking.dto.request;

import java.math.BigDecimal;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

public class BeneficiaryTransferRequestDto {

    @NotNull(message = "Beneficiary ID is required")
    @Positive(message = "Beneficiary ID must be positive")
    private Long beneficiaryId;

    @NotNull(message = "Amount is required")
    @DecimalMin(value = "1.0", message = "Amount must be at least ₹1")
    private BigDecimal amount;

    @NotBlank(message = "OTP is required")
    @Pattern(regexp = "^\\d{4,8}$", message = "OTP must be 4-8 digits")
    private String otp;

    @Size(max = 200, message = "Remarks cannot exceed 200 characters")
    private String remarks;

    // Constructors
    public BeneficiaryTransferRequestDto() {}

    public BeneficiaryTransferRequestDto(Long beneficiaryId, BigDecimal amount, String otp, String remarks) {
        this.beneficiaryId = beneficiaryId;
        this.amount = amount;
        this.otp = otp;
        this.remarks = remarks;
    }

    // Getters and Setters
    public Long getBeneficiaryId() {
        return beneficiaryId;
    }

    public void setBeneficiaryId(Long beneficiaryId) {
        this.beneficiaryId = beneficiaryId;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public String getOtp() {
        return otp;
    }

    public void setOtp(String otp) {
        this.otp = otp;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    @Override
    public String toString() {
        return "BeneficiaryTransferRequestDto{" +
                "beneficiaryId=" + beneficiaryId +
                ", amount=" + amount +
                ", otp='***'" + // Hide OTP in logs for security
                ", remarks='" + remarks + '\'' +
                '}';
    }
}
