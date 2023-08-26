package com.bm.concurrency.payload.DTOs;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ConvertDTO {
    @NotBlank(message = "Source currency is mandatory")
    @Pattern(regexp = "^[A-Z]{3}$", message = "Invalid currency code")
    private String source;

    @NotBlank(message = "Target currency is mandatory")
    @Pattern(regexp = "^[A-Z]{3}$", message = "Invalid currency code")
    private String target;

    @NotEmpty(message = "Amount cannot be empty")
    @NotNull(message = "Amount cannot be null")
    @Min(value = 0, message = "Amount must be greater than or equal to 1")
    private double amount;
}
