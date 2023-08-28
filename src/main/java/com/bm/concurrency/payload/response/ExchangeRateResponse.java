package com.bm.concurrency.payload.response;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Map;

@Data
@NoArgsConstructor
public class ExchangeRateResponse implements Serializable {
    private Map<String, Double> conversion_rates;
}