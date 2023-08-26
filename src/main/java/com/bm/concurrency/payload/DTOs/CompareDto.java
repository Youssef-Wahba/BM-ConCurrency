package com.bm.concurrency.payload.DTOs;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CompareDto {
      private Integer baseCurrencyId;
      private  List<Integer> targetCurrencyIds;
      private double amount;
}