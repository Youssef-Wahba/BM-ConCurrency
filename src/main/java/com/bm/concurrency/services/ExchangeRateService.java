package com.bm.concurrency.services;

import com.bm.concurrency.exchangeRate.ExchangeRateClient;
import com.bm.concurrency.models.responseModel.ExchangeRateResponse;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ExchangeRateService {

    private final ExchangeRateClient exchangeRateClient;

    public ExchangeRateService(ExchangeRateClient exchangeRateClient) {
        this.exchangeRateClient = exchangeRateClient;
    }

    public Map<String, Double> getFilteredExchangeRates(String baseCurrency, List<String> targetCurrencies) {
        ExchangeRateResponse response = exchangeRateClient.getExchangeRates(baseCurrency);
        Map<String, Double> conversionRates = response.getConversion_rates();

        if (!conversionRates.containsKey(baseCurrency)) {
            //exception handling "base currency not found"
        }

        Map<String, Double> filteredExchangeRates = new HashMap<>();
        for (String targetCurrency : targetCurrencies) {
            if (conversionRates.containsKey(targetCurrency)) {
                double targetRate = conversionRates.get(targetCurrency);
                filteredExchangeRates.put(targetCurrency, targetRate);
            } else {
                //exception handling "Target currency not found"
            }
        }

        return filteredExchangeRates;
    }
    }
