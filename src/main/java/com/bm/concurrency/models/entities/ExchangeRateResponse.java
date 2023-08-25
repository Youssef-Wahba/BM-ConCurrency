package com.bm.concurrency.models.entities;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Map;

@Getter
@Setter
public class ExchangeRateResponse  implements Serializable {
    private Map<String, Double> conversion_rates;
}