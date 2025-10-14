package com.tss.banking.dto.request;

<<<<<<< HEAD
public class TransferActionRequestDto {
    private String reason;
    public String getReason() { return reason; }
    public void setReason(String v){this.reason=v;}
}


=======
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TransferActionRequestDto {
    
    @NotBlank(message = "Reason is required")
    private String reason;
}
>>>>>>> 71789bece0117f6fd0443d9de29f6cd341d4deba
