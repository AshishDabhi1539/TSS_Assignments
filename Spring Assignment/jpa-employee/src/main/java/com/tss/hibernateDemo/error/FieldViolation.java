package com.tss.hibernateDemo.error;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FieldViolation {
    private String field;
    private Object rejectedValue;
    private String message;
}