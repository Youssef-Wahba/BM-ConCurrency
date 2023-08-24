package com.bm.concurrency.controller;

import com.bm.concurrency.service.ConvertService;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    private ConvertService convertService ;

    @GetMapping
    public ResponseEntity<Map<String,Object>> convert(String source, String target, double amount )   {

        Map<String, Object> conversionJson = convertService.conversion(source, target,amount);

        if (conversionJson.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        return ResponseEntity.ok(conversionJson);
    }
}
