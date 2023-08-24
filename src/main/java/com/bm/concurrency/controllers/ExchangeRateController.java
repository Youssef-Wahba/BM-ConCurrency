package com.bm.concurrency.controllers;

import com.bm.concurrency.services.ExchangeRateService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<Map<String, Double>> exchangeRate(
            @RequestParam String baseCurrency,
            @RequestParam List<String> targetCurrencies) {

        Map<String, Double> exchangeRates = exchangeRateService.getFilteredExchangeRates(baseCurrency, targetCurrencies);

        if (exchangeRates.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        return ResponseEntity.ok(exchangeRates);
    }
}
