package com.bm.concurrency.controller;

import com.bm.concurrency.service.ConvertService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RequiredArgsConstructor
@RestController
@RequestMapping("/convert")

public class Conversion {
    private final ConvertService convertService ;

    @GetMapping("/{source}/{target}/{amount}")
    public ResponseEntity<Map<String,Double>> convert(

          @PathVariable("source") String source,
          @PathVariable("target")   String target,
          @PathVariable("amount")  double amount )   {


        return ResponseEntity.ok(convertService.conversion(source, target,amount));
    }
}
