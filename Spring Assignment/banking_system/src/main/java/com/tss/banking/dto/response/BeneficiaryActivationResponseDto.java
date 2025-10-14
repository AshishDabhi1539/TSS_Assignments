package com.tss.banking.dto.response;

public class BeneficiaryActivationResponseDto {

    private boolean success;
    private String message;
    private BeneficiaryResponseDto beneficiary;

    // Constructors
    public BeneficiaryActivationResponseDto() {}

    public BeneficiaryActivationResponseDto(boolean success, String message, BeneficiaryResponseDto beneficiary) {
        this.success = success;
        this.message = message;
        this.beneficiary = beneficiary;
    }

    // Getters and Setters
    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public BeneficiaryResponseDto getBeneficiary() {
        return beneficiary;
    }

    public void setBeneficiary(BeneficiaryResponseDto beneficiary) {
        this.beneficiary = beneficiary;
    }

    @Override
    public String toString() {
        return "BeneficiaryActivationResponseDto{" +
                "success=" + success +
                ", message='" + message + '\'' +
                ", beneficiary=" + beneficiary +
                '}';
    }
}
