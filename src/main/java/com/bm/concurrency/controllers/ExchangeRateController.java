package com.bm.concurrency.controllers;

import com.bm.concurrency.models.entities.CountriesInfoModel;
import com.bm.concurrency.models.entities.RequestDto;
import com.bm.concurrency.services.ExchangeRateService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("v3/currency")
public class ExchangeRateController {


    private final ExchangeRateService exchangeRateService;

    public ExchangeRateController(ExchangeRateService exchangeRateService) {
        this.exchangeRateService = exchangeRateService;
    }


    @PostMapping("/compare")
    public ResponseEntity<List<Double>> convertCurrency(@RequestBody RequestDto request) {
        List<Double> convertedAmounts = exchangeRateService.getConvertedAmounts(request.getBaseCurrencyId(), request.getTargetCurrencyIds(), request.getAmount());

        if (convertedAmounts == null) {
            return ResponseEntity.badRequest().build();
        }

        return ResponseEntity.ok(convertedAmounts);
    }

    @GetMapping("/convert/{source}/{target}/{amount}")
    public ResponseEntity<Map<String,Double>> convert(

            @PathVariable("source") String source,
            @PathVariable("target")   String target,
            @PathVariable("amount")  double amount )   {


        return ResponseEntity.ok(exchangeRateService.conversion(source, target,amount));
    }
    @GetMapping("/countries-list")
    public Map<Integer, CountriesInfoModel> getCountries(){
        return exchangeRateService.getCountriesInfo();
    }


}
