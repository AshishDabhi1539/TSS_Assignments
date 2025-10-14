package com.tss.banking.dto.request;

<<<<<<< HEAD
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


=======
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class InterBankTransferRequestDto {
    
    @NotBlank(message = "Destination bank code is required")
    private String destinationBankCode;
    
    @NotBlank(message = "Destination account number is required")
    private String destinationAccountNumber;
    
    @NotBlank(message = "Destination account holder name is required")
    private String destinationAccountHolderName;
    
    @NotBlank(message = "Destination IFSC code is required")
    private String destinationIfscCode;
    
    @NotNull(message = "Amount is required")
    @DecimalMin(value = "0.01", message = "Amount must be greater than 0")
    private BigDecimal amount;
    
    @NotBlank(message = "Remarks are required")
    @Size(min = 5, max = 255, message = "Remarks must be between 5 and 255 characters")
    private String remarks;
}
>>>>>>> 71789bece0117f6fd0443d9de29f6cd341d4deba
