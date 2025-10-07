package com.tss.banking.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserSummaryDto {
    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private String phone;
    private String status;
    private String role;
    private Boolean emailVerified;
    private Boolean enabled;
    private LocalDateTime createdAt;
    private String approvedByName;
    private LocalDateTime approvedAt;
}
