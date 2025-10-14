package com.tss.banking.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
<<<<<<< HEAD
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
=======
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
>>>>>>> 71789bece0117f6fd0443d9de29f6cd341d4deba
@AllArgsConstructor
public class BranchResponseDto {
    private Long id;
    private String name;
    private String code;
    private String ifsc;
    private String address;
    private String contactNumber;
<<<<<<< HEAD
    private Long bankId;
=======
    private String email;
    private boolean isActive;
    
    // Bank information
    private Long bankId;
    private String bankName;
    private String bankCode;
>>>>>>> 71789bece0117f6fd0443d9de29f6cd341d4deba
}