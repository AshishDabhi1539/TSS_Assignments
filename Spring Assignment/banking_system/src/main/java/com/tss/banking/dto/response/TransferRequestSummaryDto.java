package com.tss.banking.dto.response;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class TransferRequestSummaryDto {
    private long transferId; private String sourceBankCode; private String destinationBankCode;
    private String sourceAccountNumber; private String destinationAccountNumber;
    private String destinationAccountHolderName; private String destinationIfscCode;
    private BigDecimal amount; private String transferReference; private String status; private String remarks;
    private LocalDateTime createdDate; private String destinationBankName;
    public long getTransferId(){return transferId;} public void setTransferId(long v){this.transferId=v;}
    public String getSourceBankCode(){return sourceBankCode;} public void setSourceBankCode(String v){this.sourceBankCode=v;}
    public String getDestinationBankCode(){return destinationBankCode;} public void setDestinationBankCode(String v){this.destinationBankCode=v;}
    public String getSourceAccountNumber(){return sourceAccountNumber;} public void setSourceAccountNumber(String v){this.sourceAccountNumber=v;}
    public String getDestinationAccountNumber(){return destinationAccountNumber;} public void setDestinationAccountNumber(String v){this.destinationAccountNumber=v;}
    public String getDestinationAccountHolderName(){return destinationAccountHolderName;} public void setDestinationAccountHolderName(String v){this.destinationAccountHolderName=v;}
    public String getDestinationIfscCode(){return destinationIfscCode;} public void setDestinationIfscCode(String v){this.destinationIfscCode=v;}
    public BigDecimal getAmount(){return amount;} public void setAmount(BigDecimal v){this.amount=v;}
    public String getTransferReference(){return transferReference;} public void setTransferReference(String v){this.transferReference=v;}
    public String getStatus(){return status;} public void setStatus(String v){this.status=v;}
    public String getRemarks(){return remarks;} public void setRemarks(String v){this.remarks=v;}
    public LocalDateTime getCreatedDate(){return createdDate;} public void setCreatedDate(LocalDateTime v){this.createdDate=v;}
    public String getDestinationBankName(){return destinationBankName;} public void setDestinationBankName(String v){this.destinationBankName=v;}
}


