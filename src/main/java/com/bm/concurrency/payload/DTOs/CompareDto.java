package com.bm.concurrency.payload.DTOs;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CompareDto {
      @NotEmpty(message = "Base currency id cannot be empty")
      @NotNull(message = "Id cannot be null")
      @Min(value = 1, message = "Id must be greater than or equal to 1")
      private Integer baseCurrencyId;

      @NotEmpty(message = "Target currency ids list cannot be empty")
      @NotNull(message = "Target currency ids list cannot be null")
      @Size(min = 1, message = "Target currency ids list must have at least one element")
      private  List<@NotNull(message = "Id cannot be null") @Min(value = 1, message = "Id must be greater than or equal to 1") Integer> targetCurrencyIds;

      @NotNull(message = "Amount cannot be null")
      @NotEmpty(message = "Amount cannot be empty")
      @Min(value = 0, message = "Amount must be greater than or equal to 1")
      private double amount;
}