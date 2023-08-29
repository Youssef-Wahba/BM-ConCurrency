package com.bm.concurrency.controllers;

import com.bm.concurrency.payload.DTOs.ConvertDTO;
import com.bm.concurrency.payload.response.CompareResponse;
import com.bm.concurrency.payload.response.ConversionResponse;
import com.bm.concurrency.payload.response.CurrencyListResponse;
import com.bm.concurrency.payload.DTOs.CompareDto;
import com.bm.concurrency.payload.response.ExchangeRateResponse;
import com.bm.concurrency.service.ICurrencyService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*",maxAge = 3600)
@RequiredArgsConstructor
@Tag(name = "Currency controller" ,description = "Version 1 of currency controller")
@Validated
@RestController
@RequestMapping("api/v1/currency")
public class CurrencyController {
    private final ICurrencyService concurrencyService;
    @Operation(summary = "Get All Currency", description = "Returns list of currencies with their IDs, code and flag URLs")
    @GetMapping
    public ResponseEntity<CurrencyListResponse> getAllCurrencies(){
        return ResponseEntity.ok(concurrencyService.getCurrencyList());
    }

    @Operation(summary = "Compare Currency", description = "Compares base currency with more than target currency by providing id of base currency and list of target currency and amount")
    @PostMapping("/compare")
    public ResponseEntity<CompareResponse> compareCurrencies(@Valid @RequestBody CompareDto request) {
        ExchangeRateResponse exchangeRateResponse = concurrencyService.getAllCurrencyRates(request.getBaseCurrencyId());
        CompareResponse response = concurrencyService.compare(request.getBaseCurrencyId(), request.getTargetCurrencyIds(), request.getAmount(),exchangeRateResponse);
        return ResponseEntity.ok(response);
    }

    @Operation(summary = "Convert Currency", description = "Convert from base currency to target currency with specific amount")
    @GetMapping("/convert/{source}/{target}/{amount}")
    public ResponseEntity<ConversionResponse> convertCurrencies(@Valid ConvertDTO convertDTO)   {
        return ResponseEntity.ok(concurrencyService.convert(convertDTO.getSource(), convertDTO.getTarget(),convertDTO.getAmount()));
    }
}
