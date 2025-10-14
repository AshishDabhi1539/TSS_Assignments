package com.tss.banking.dto.response;

<<<<<<< HEAD
import java.math.BigDecimal;
import java.time.LocalDateTime;

public class BeneficiaryResponseDto {

    private Long id;
    private String alias;
    private String remarks;
    private String status;
    private String approvalStatus;
    private LocalDateTime createdAt;
    private LocalDateTime activatedAt;
    private LocalDateTime lastUsedAt;
    private LocalDateTime lastActivityAt;
    private LocalDateTime coolingPeriodEndsAt;
    private BigDecimal totalTransferredToday;
    private BigDecimal totalTransferredThisMonth;
    private String approvalRemarks;

    // Beneficiary account details
    private String accountNumber;
    private String accountHolderName;
    private String ifscCode;
    private String branchName;

    // Owner details
    private String ownerEmail;
    private String ownerName;

    // Approver details
    private String approvedBy;
    private LocalDateTime approvedAt;

    // Constructors
    public BeneficiaryResponseDto() {}

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getApprovalStatus() {
        return approvalStatus;
    }

    public void setApprovalStatus(String approvalStatus) {
        this.approvalStatus = approvalStatus;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getActivatedAt() {
        return activatedAt;
    }

    public void setActivatedAt(LocalDateTime activatedAt) {
        this.activatedAt = activatedAt;
    }

    public LocalDateTime getLastUsedAt() {
        return lastUsedAt;
    }

    public void setLastUsedAt(LocalDateTime lastUsedAt) {
        this.lastUsedAt = lastUsedAt;
    }

    public LocalDateTime getLastActivityAt() {
        return lastActivityAt;
    }

    public void setLastActivityAt(LocalDateTime lastActivityAt) {
        this.lastActivityAt = lastActivityAt;
    }

    public LocalDateTime getCoolingPeriodEndsAt() {
        return coolingPeriodEndsAt;
    }

    public void setCoolingPeriodEndsAt(LocalDateTime coolingPeriodEndsAt) {
        this.coolingPeriodEndsAt = coolingPeriodEndsAt;
    }

    public BigDecimal getTotalTransferredToday() {
        return totalTransferredToday;
    }

    public void setTotalTransferredToday(BigDecimal totalTransferredToday) {
        this.totalTransferredToday = totalTransferredToday;
    }

    public BigDecimal getTotalTransferredThisMonth() {
        return totalTransferredThisMonth;
    }

    public void setTotalTransferredThisMonth(BigDecimal totalTransferredThisMonth) {
        this.totalTransferredThisMonth = totalTransferredThisMonth;
    }

    public String getApprovalRemarks() {
        return approvalRemarks;
    }

    public void setApprovalRemarks(String approvalRemarks) {
        this.approvalRemarks = approvalRemarks;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public String getAccountHolderName() {
        return accountHolderName;
    }

    public void setAccountHolderName(String accountHolderName) {
        this.accountHolderName = accountHolderName;
    }

    public String getIfscCode() {
        return ifscCode;
    }

    public void setIfscCode(String ifscCode) {
        this.ifscCode = ifscCode;
    }

    public String getBranchName() {
        return branchName;
    }

    public void setBranchName(String branchName) {
        this.branchName = branchName;
    }

    public String getOwnerEmail() {
        return ownerEmail;
    }

    public void setOwnerEmail(String ownerEmail) {
        this.ownerEmail = ownerEmail;
    }

    public String getOwnerName() {
        return ownerName;
    }

    public void setOwnerName(String ownerName) {
        this.ownerName = ownerName;
    }

    public String getApprovedBy() {
        return approvedBy;
    }

    public void setApprovedBy(String approvedBy) {
        this.approvedBy = approvedBy;
    }

    public LocalDateTime getApprovedAt() {
        return approvedAt;
    }

    public void setApprovedAt(LocalDateTime approvedAt) {
        this.approvedAt = approvedAt;
    }

    @Override
    public String toString() {
        return "BeneficiaryResponseDto{" +
                "id=" + id +
                ", alias='" + alias + '\'' +
                ", status='" + status + '\'' +
                ", approvalStatus='" + approvalStatus + '\'' +
                ", accountNumber='" + accountNumber + '\'' +
                ", accountHolderName='" + accountHolderName + '\'' +
                ", ifscCode='" + ifscCode + '\'' +
                ", createdAt=" + createdAt +
                ", activatedAt=" + activatedAt +
                '}';
    }
=======
import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BeneficiaryResponseDto {
    private Long id;
    private String alias;
    private String beneficiaryAccountNumber;
    private String accountHolderName;
    private String bankName;
    private String branchName;
    private String ifsc;
    private String verificationStatus;
    private LocalDateTime createdAt;
    private boolean isActive;
>>>>>>> 71789bece0117f6fd0443d9de29f6cd341d4deba
}
