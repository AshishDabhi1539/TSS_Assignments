package com.tss.banking.dto.request;

import java.math.BigDecimal;

public class InterBankTransferRequestDto {
    private String destinationBankCode;
    private String destinationAccountNumber;
    private String destinationAccountHolderName;
    private String destinationIfscCode;
    private BigDecimal amount;
    private String remarks;
    public String getDestinationBankCode() { return destinationBankCode; }
    public void setDestinationBankCode(String v){this.destinationBankCode=v;}
    public String getDestinationAccountNumber() { return destinationAccountNumber; }
    public void setDestinationAccountNumber(String v){this.destinationAccountNumber=v;}
    public String getDestinationAccountHolderName() { return destinationAccountHolderName; }
    public void setDestinationAccountHolderName(String v){this.destinationAccountHolderName=v;}
    public String getDestinationIfscCode() { return destinationIfscCode; }
    public void setDestinationIfscCode(String v){this.destinationIfscCode=v;}
    public BigDecimal getAmount() { return amount; }
    public void setAmount(BigDecimal v){this.amount=v;}
    public String getRemarks() { return remarks; }
    public void setRemarks(String v){this.remarks=v;}
}


