package com.bm.concurrency.controllers;

import com.bm.concurrency.DTOs.CountryListResponse;
import com.bm.concurrency.service.ICountryService;
import com.bm.concurrency.service.serviceImp.CountryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
@RequiredArgsConstructor
@RestController
@RequestMapping("api/v1/countries")
public class CountryController {
    private final ICountryService countryService;
    @GetMapping
    public ResponseEntity<CountryListResponse> getAllCountries(){
        return ResponseEntity.ok(countryService.getCountriesInfo());
    }
}
