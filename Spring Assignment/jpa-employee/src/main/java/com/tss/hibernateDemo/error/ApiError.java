package com.tss.hibernateDemo.error;

import java.time.LocalDateTime;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ApiError {

	private LocalDateTime timestamp;
    private int status;
    private String error;
    private String message;
    
}
