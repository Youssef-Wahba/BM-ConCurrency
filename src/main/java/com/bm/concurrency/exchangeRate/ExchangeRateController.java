package com.bm.concurrency.exchangeRate;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
public class ExchangeRateController {

    private final ExchangeRateService exchangeRateService;

    public ExchangeRateController(ExchangeRateService exchangeRateService) {
        this.exchangeRateService = exchangeRateService;
    }

    @GetMapping("/exchangeRate")
    public Map<String, Double> exchangeRate(
            @RequestParam String baseCurrency,
            @RequestParam List<String> targetCurrencies) {
        return exchangeRateService.getFilteredExchangeRates(baseCurrency, targetCurrencies);
    }
}
