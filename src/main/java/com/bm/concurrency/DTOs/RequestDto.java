package com.bm.concurrency.DTOs;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RequestDto {
   private int baseCurrencyId;
   private  List<Integer> targetCurrencyIds;
   private double amount;
}