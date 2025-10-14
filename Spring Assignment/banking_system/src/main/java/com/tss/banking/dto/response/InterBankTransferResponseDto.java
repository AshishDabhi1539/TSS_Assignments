package com.tss.banking.dto.response;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class InterBankTransferResponseDto {
    private String transferReference;
    private BigDecimal amount;
    private String destinationBankCode;
    private String destinationAccountNumber;
    private String destinationAccountHolderName;
    private String destinationIfscCode;
    private String remarks;
    private String status;
    private LocalDateTime transferDate;
    private LocalDateTime createdDate;
    private BigDecimal newBalance;
    private BigDecimal newAvailableBalance;
    private BigDecimal previousBalance;
    private BigDecimal previousAvailableBalance;
    public String getTransferReference(){return transferReference;}
    public void setTransferReference(String v){this.transferReference=v;}
    public BigDecimal getAmount(){return amount;}
    public void setAmount(BigDecimal v){this.amount=v;}
    public String getDestinationBankCode(){return destinationBankCode;}
    public void setDestinationBankCode(String v){this.destinationBankCode=v;}
    public String getDestinationAccountNumber(){return destinationAccountNumber;}
    public void setDestinationAccountNumber(String v){this.destinationAccountNumber=v;}
    public String getDestinationAccountHolderName(){return destinationAccountHolderName;}
    public void setDestinationAccountHolderName(String v){this.destinationAccountHolderName=v;}
    public String getDestinationIfscCode(){return destinationIfscCode;}
    public void setDestinationIfscCode(String v){this.destinationIfscCode=v;}
    public String getRemarks(){return remarks;}
    public void setRemarks(String v){this.remarks=v;}
    public String getStatus(){return status;}
    public void setStatus(String v){this.status=v;}
    public LocalDateTime getTransferDate(){return transferDate;}
    public void setTransferDate(LocalDateTime v){this.transferDate=v;}
    public LocalDateTime getCreatedDate(){return createdDate;}
    public void setCreatedDate(LocalDateTime v){this.createdDate=v;}
    public BigDecimal getNewBalance(){return newBalance;}
    public void setNewBalance(BigDecimal v){this.newBalance=v;}
    public BigDecimal getNewAvailableBalance(){return newAvailableBalance;}
    public void setNewAvailableBalance(BigDecimal v){this.newAvailableBalance=v;}
    public BigDecimal getPreviousBalance(){return previousBalance;}
    public void setPreviousBalance(BigDecimal v){this.previousBalance=v;}
    public BigDecimal getPreviousAvailableBalance(){return previousAvailableBalance;}
    public void setPreviousAvailableBalance(BigDecimal v){this.previousAvailableBalance=v;}
}


