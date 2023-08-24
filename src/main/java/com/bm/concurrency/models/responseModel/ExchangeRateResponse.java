package com.bm.concurrency.models.responseModel;

import lombok.Getter;
import lombok.Setter;

import java.util.Map;

@Getter
@Setter
public class ExchangeRateResponse {
    private Map<String, Double> conversion_rates;
}