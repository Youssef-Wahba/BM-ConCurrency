package com.bm.concurrency.payload.DTOs;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CompareDto {
      @NotNull(message = "Base currency Id is mandatory")
      @Min(value = 1, message = "Id must be greater than or equal to 1")
      private Integer baseCurrencyId;
      @NotNull(message = "Target currency ids list is mandatory")
      @Size(min = 1, message = "Target currency ids list must have at least one element")
      private  List<@NotNull(message = "Id cannot be null") @Min(value = 1, message = "Id must be greater than or equal to 1") Integer> targetCurrencyIds;
      @NotBlank(message = "Amount is mandatory")
      @Min(value = 0, message = "Amount must be greater than or equal to 1")
      private double amount;
}