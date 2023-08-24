package com.bm.concurrency.models;


import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.Map;

@RequiredArgsConstructor
@Data
public class ConversionModel {

//               "base_code": "EUR",
//               "target_code": "GBP",
//               "conversion_rate": 0.85452353,
//               "conversion_result": 8.5452353

//   private  final String baseCode;
//   private  final String targetCode;
//   private  final String ConversionRate;
//   private  final String ConversionResult;

   private Map<String,Object> conversionResponse;

}
