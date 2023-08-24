package com.bm.concurrency.controllers;

import com.bm.concurrency.models.constants.enums.CountriesCode;
import com.bm.concurrency.models.entities.CountriesInfoModel;
import com.bm.concurrency.models.entities.RequestDto;
import com.bm.concurrency.services.ExchangeRateService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/exchange")
public class ExchangeRateController {


    private final ExchangeRateService exchangeRateService;

    public ExchangeRateController(ExchangeRateService exchangeRateService) {
        this.exchangeRateService = exchangeRateService;
    }
    @PostMapping("/compare")
    public ResponseEntity<Map<Integer, Double>> convertCurrency(@RequestBody RequestDto request) {

        Map<Integer, Double> convertedAmounts = exchangeRateService.getConvertedAmounts(request.getBaseCurrencyId(), request.getTargetCurrencyIds(), request.getAmount());

        if (convertedAmounts == null) {
            return ResponseEntity.badRequest().build();
        }

        return ResponseEntity.ok(convertedAmounts);
    }

}
