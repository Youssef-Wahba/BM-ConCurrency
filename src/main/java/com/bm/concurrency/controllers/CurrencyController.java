package com.bm.concurrency.controllers;

import com.bm.concurrency.payload.response.CompareResponse;
import com.bm.concurrency.payload.response.ConversionResponse;
import com.bm.concurrency.payload.response.CurrencyListResponse;
import com.bm.concurrency.payload.DTOs.CompareDto;
import com.bm.concurrency.service.IConcurrencyService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("api/v1/currency")
public class CurrencyController {
    private final IConcurrencyService concurrencyService;

    @GetMapping
    public ResponseEntity<CurrencyListResponse> getAllCurrencies(){
        return ResponseEntity.ok(concurrencyService.getCurrencyInfo());
    }
    @PostMapping("/compare")
    public ResponseEntity<CompareResponse> compareCurrencies(@RequestBody CompareDto request) {
        CompareResponse response = concurrencyService.getConvertedAmounts(request.getBaseCurrencyId(), request.getTargetCurrencyIds(), request.getAmount());
        return ResponseEntity.ok(response);
    }

    @GetMapping("/convert/{source}/{target}/{amount}")
    public ResponseEntity<ConversionResponse> convertCurrencies(
            @PathVariable("source") String source,
            @PathVariable("target")   String target,
            @PathVariable("amount")  double amount )   {
        return ResponseEntity.ok(concurrencyService.conversion(source, target,amount));
    }
}
