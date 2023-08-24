package com.bm.concurrency.controller;

import com.bm.concurrency.model.CountriesInfoModel;
import com.bm.concurrency.service.CountriesInfoService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/countries")
@RequiredArgsConstructor
public class CountriesInfoController {


    private final CountriesInfoService countriesInfoService;

    @GetMapping("/")
    public List<CountriesInfoModel> getCountries(){
        return countriesInfoService.getCountriesInfo();
    }
}
