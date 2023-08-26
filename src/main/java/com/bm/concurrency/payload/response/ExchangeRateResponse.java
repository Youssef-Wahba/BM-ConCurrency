package com.bm.concurrency.payload.response;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;

@Data
@NoArgsConstructor
public class ExchangeRateResponse {
    private Map<String, Double> conversion_rates;
}