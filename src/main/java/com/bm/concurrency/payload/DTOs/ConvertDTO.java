package com.bm.concurrency.payload.DTOs;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ConvertDTO {
    @NotNull
    private Integer source;
    @NotNull
    private Integer target;

    @NotNull
    @Min(value = 0, message = "Amount must be greater than 0")
    private double amount;
}
