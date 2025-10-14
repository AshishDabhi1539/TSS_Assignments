package com.tss.banking.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;

public class BeneficiaryActivationRequestDto {

    @NotNull(message = "Beneficiary ID is required")
    @Positive(message = "Beneficiary ID must be positive")
    private Long beneficiaryId;

    @NotBlank(message = "OTP is required")
    @Pattern(regexp = "^\\d{4,8}$", message = "OTP must be 4-8 digits")
    private String otp;

    // Constructors
    public BeneficiaryActivationRequestDto() {}

    public BeneficiaryActivationRequestDto(Long beneficiaryId, String otp) {
        this.beneficiaryId = beneficiaryId;
        this.otp = otp;
    }

    // Getters and Setters
    public Long getBeneficiaryId() {
        return beneficiaryId;
    }

    public void setBeneficiaryId(Long beneficiaryId) {
        this.beneficiaryId = beneficiaryId;
    }

    public String getOtp() {
        return otp;
    }

    public void setOtp(String otp) {
        this.otp = otp;
    }

    @Override
    public String toString() {
        return "BeneficiaryActivationRequestDto{" +
                "beneficiaryId=" + beneficiaryId +
                ", otp='***'" + // Hide OTP in logs for security
                '}';
    }
}
