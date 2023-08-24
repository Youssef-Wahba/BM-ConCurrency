package com.bm.concurrency.service;


import com.bm.concurrency.clients.conversionClient.ConversionClient;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class ConvertService {
    private final  ConversionClient conversionClient;

    public Map<String,Double> conversion(String source, String target, double amount ) {

        Map<String ,Object>  conversionJson = conversionClient.convert(source,target,amount);
          Map<String  , Double>  mapResponse = new HashMap<>();
mapResponse.put("conversion_result", (double)conversionJson.get("conversion_result"));
        return   mapResponse;
    }

}
