package com.tss.banking.dto.response;

<<<<<<< HEAD
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
=======
import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
>>>>>>> 71789bece0117f6fd0443d9de29f6cd341d4deba
@AllArgsConstructor
public class BankResponseDto {
    private Long id;
    private String name;
    private String address;
<<<<<<< HEAD
=======
    private String code;
    private String currency;
    private String country;
    private LocalDateTime createdAt;
    private int totalBranches;
>>>>>>> 71789bece0117f6fd0443d9de29f6cd341d4deba
}