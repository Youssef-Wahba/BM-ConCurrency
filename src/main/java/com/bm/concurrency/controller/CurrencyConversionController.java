package com.bm.concurrency.controller;

import com.bm.concurrency.models.CountriesInfoModel;
import com.bm.concurrency.service.ConcurrencyService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/currency")
public class CurrencyConversionController {

  private  final   ConcurrencyService concurrencyService ;

    @GetMapping
    public String getHello(){
        return "Hello";
    }


    @GetMapping("/convert/{source}/{target}/{amount}")
    public ResponseEntity<Map<String,Double>> convert(

            @PathVariable("source") String source,
            @PathVariable("target")   String target,
            @PathVariable("amount")  double amount )   {


        return ResponseEntity.ok(concurrencyService.conversion(source, target,amount));
    }
    @GetMapping("/")
    public List<CountriesInfoModel> getCountries(){
        return concurrencyService.getCountriesInfo();
    }

}
