package com.bm.concurrency.DTOs;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Map;

@Data
@NoArgsConstructor
public class ExchangeRateResponse {
    private Map<String, Double> conversion_rates;
}