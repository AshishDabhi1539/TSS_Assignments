package com.tss.jpa.dto;

import java.util.List;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class StudentResponsePage {

    @NotNull(message = "Contents cannot be null")
    @Valid
    private List<StudentResponseDto> contents;

    @Min(value = 0, message = "Total elements cannot be negative")
    private int totalElements;

    @Min(value = 1, message = "Size must be at least 1")
    private int size;

    private boolean isLastPage;

    @Min(value = 0, message = "Total pages cannot be negative")
    private int totalPages;
}
