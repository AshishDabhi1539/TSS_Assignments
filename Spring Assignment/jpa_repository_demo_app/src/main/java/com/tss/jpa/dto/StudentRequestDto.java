package com.tss.jpa.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class StudentRequestDto {

    @NotNull(message = "Roll number is required")
    @Min(value = 1, message = "Roll number must be greater than 0")
    private Integer rollNumber;

    @NotBlank(message = "First name is required")
    @Size(min = 2, max = 30, message = "First name must be between 2 and 30 characters")
    @Pattern(regexp = "^[A-Za-z]+$", message = "First name should contain only alphabets")
    private String firstName;

    @Size(max = 30, message = "Last name must not exceed 30 characters")
    @Pattern(regexp = "^[A-Za-z]*$", message = "Last name should contain only alphabets")
    private String lastName;

    @NotBlank(message = "Email is required")
    @Email(message = "Please provide a valid email address")
    private String email;

    @NotNull(message = "Age is required")
    @Min(value = 17, message = "Age must be at least 17")
    @Max(value = 50, message = "Age must not exceed 50")
    private Integer age;
}
