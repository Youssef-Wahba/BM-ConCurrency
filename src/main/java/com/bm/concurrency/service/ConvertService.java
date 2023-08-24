package com.bm.concurrency.service;


import com.bm.concurrency.clients.conversionClient.ConversionClient;
import com.bm.concurrency.models.ConversionModel;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
@RequiredArgsConstructor
@Data
public class ConvertService {
    private   ConversionClient conversionClient;


    public Map<String, Object> conversion(String source, String target, double amount ) {
        ConversionModel response = conversionClient.convert(source,target,amount);
        Map<String, Object> conversionResponse = response.getConversionResponse();
//
//        if (!conversionResponse.containsKey(baseCurrency)) {
//            //exception handling "base currency not found"
//        }
//
//        Map<String, Double> filteredExchangeRates = new HashMap<>();
//        for (String targetCurrency : targetCurrencies) {
//            if (conversionRates.containsKey(targetCurrency)) {
//                double targetRate = conversionRates.get(targetCurrency);
//                filteredExchangeRates.put(targetCurrency, targetRate);
//            } else {
//                //exception handling "Target currency not found"
//            }
//        }

        return conversionResponse;
    }

}
