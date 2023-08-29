package com.bm.concurrency.payload.DTOs;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ConvertDTO {
    @NotNull(message = "Base currency Id is mandatory")
    private Integer source;
    @NotNull(message = "Target currency Id is mandatory")
    private Integer target;

    @NotNull(message = "Amount is mandatory")
    @Min(value = 0, message = "Amount must be greater than 0")
    private double amount;
}
