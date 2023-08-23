package com.bm.concurrency.exchangeRate;

import lombok.Getter;
import lombok.Setter;

import java.util.Map;

@Getter
@Setter
public class ExchangeRateResponse {
    private Map<String, Double> conversion_rates;
}