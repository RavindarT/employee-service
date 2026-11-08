package com.example.employeeservice;
import jakarta.validation.constraints.*;
public record EmployeeRequest(
    @NotBlank @Size(max = 255) String name,
    @NotNull @Min(0) @Max(125) Integer age,
    @NotBlank @Size(max = 255) String location
) {}
