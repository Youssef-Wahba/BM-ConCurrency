package com.bm.concurrency.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CurrencyConversionController {
    @GetMapping
    public String getHello(){
        return "Hello";
    }
}
