package com.bm.concurrency.controller;

import com.bm.concurrency.services.CurrencyConversionService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
public class CurrencyConversionController {
    private final CurrencyConversionService conversionService;

    public CurrencyConversionController(CurrencyConversionService conversionService) {
        this.conversionService = conversionService;
    }

    @GetMapping("/convert")
    public Mono<Double> convert(
            @RequestParam String fromCurrency,
            @RequestParam String toCurrency,
            @RequestParam double amount) {
        return conversionService.convertCurrency(fromCurrency, toCurrency, amount);
    }
}
