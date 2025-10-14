package com.tss.banking.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

public class BeneficiaryApprovalRequestDto {

    @NotNull(message = "Beneficiary ID is required")
    @Positive(message = "Beneficiary ID must be positive")
    private Long beneficiaryId;

    @NotBlank(message = "Approval status is required")
    @Pattern(regexp = "^(APPROVED|REJECTED)$", message = "Approval status must be either APPROVED or REJECTED")
    private String approvalStatus;

    @Size(max = 500, message = "Approval remarks cannot exceed 500 characters")
    private String approvalRemarks;

    // Constructors
    public BeneficiaryApprovalRequestDto() {}

    public BeneficiaryApprovalRequestDto(Long beneficiaryId, String approvalStatus, String approvalRemarks) {
        this.beneficiaryId = beneficiaryId;
        this.approvalStatus = approvalStatus;
        this.approvalRemarks = approvalRemarks;
    }

    // Getters and Setters
    public Long getBeneficiaryId() {
        return beneficiaryId;
    }

    public void setBeneficiaryId(Long beneficiaryId) {
        this.beneficiaryId = beneficiaryId;
    }

    public String getApprovalStatus() {
        return approvalStatus;
    }

    public void setApprovalStatus(String approvalStatus) {
        this.approvalStatus = approvalStatus;
    }

    public String getApprovalRemarks() {
        return approvalRemarks;
    }

    public void setApprovalRemarks(String approvalRemarks) {
        this.approvalRemarks = approvalRemarks;
    }

    @Override
    public String toString() {
        return "BeneficiaryApprovalRequestDto{" +
                "beneficiaryId=" + beneficiaryId +
                ", approvalStatus='" + approvalStatus + '\'' +
                ", approvalRemarks='" + approvalRemarks + '\'' +
                '}';
    }
}
