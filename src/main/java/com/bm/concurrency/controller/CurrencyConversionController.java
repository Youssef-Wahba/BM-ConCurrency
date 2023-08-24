package com.bm.concurrency.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.crypto.Mac;
import java.util.Map;

@RestController
public class CurrencyConversionController {
    @GetMapping
    public String getHello(){
        return "Hello";
    }

}
