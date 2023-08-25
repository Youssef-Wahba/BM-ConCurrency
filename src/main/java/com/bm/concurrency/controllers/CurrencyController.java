package com.bm.concurrency.controllers;

import com.bm.concurrency.DTOs.ConversionResponse;
import com.bm.concurrency.DTOs.RequestDto;
import com.bm.concurrency.service.IConcurrencyService;
import com.bm.concurrency.service.serviceImp.ConcurrencyService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.Map;

@RequiredArgsConstructor
@RestController
@RequestMapping("api/v1/currency")
public class CurrencyController {
    private final IConcurrencyService concurrencyService;

    @PostMapping("/compare")
    public ResponseEntity<Map<Integer, Double>> convertCurrency(@RequestBody RequestDto request) {

        Map<Integer, Double> convertedAmounts = concurrencyService.getConvertedAmounts(request.getBaseCurrencyId(), request.getTargetCurrencyIds(), request.getAmount());

        if (convertedAmounts == null) {
            return ResponseEntity.badRequest().build();
        }

        return ResponseEntity.ok(convertedAmounts);
    }

    @GetMapping("/convert/{source}/{target}/{amount}")
    public ResponseEntity<ConversionResponse> convert(
            @PathVariable("source") String source,
            @PathVariable("target")   String target,
            @PathVariable("amount")  double amount )   {
        return ResponseEntity.ok(concurrencyService.conversion(source, target,amount));
    }
}
