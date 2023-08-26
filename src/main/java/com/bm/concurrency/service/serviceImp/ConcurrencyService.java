package com.bm.concurrency.service.serviceImp;

import com.bm.concurrency.exception.CurrencyApiException;
import com.bm.concurrency.exception.ResourceNotFoundException;
import com.bm.concurrency.client.ExchangeRateClient;
import com.bm.concurrency.payload.DTOs.CurrencyDTO;
import com.bm.concurrency.payload.response.CompareResponse;
import com.bm.concurrency.payload.response.ConversionResponse;
import com.bm.concurrency.payload.response.CurrencyListResponse;
import com.bm.concurrency.payload.response.ExchangeRateResponse;
import com.bm.concurrency.service.IConcurrencyService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class ConcurrencyService implements IConcurrencyService {
    private final ExchangeRateClient exchangeRateClient;

    public CurrencyListResponse getCurrencyInfo() {
        return new CurrencyListResponse();
    }

    public ConversionResponse conversion(String source, String target, double amount) {
        Map<String, Object> conversionJson = exchangeRateClient.convert(source, target, amount);
        return new ConversionResponse((double) conversionJson.get("conversion_result"));
    }

    public CompareResponse getConvertedAmounts(int baseCurrencyId, List<Integer> targetCurrencyIds, double amount) {
        CurrencyListResponse countriesInfoList = getCurrencyInfo();
        if (baseCurrencyId < 1 ||  baseCurrencyId > countriesInfoList.getCurrency_list().size())
            throw new ResourceNotFoundException("Currency","id",Integer.toString(baseCurrencyId));
        for (Integer targetCurrencyId : targetCurrencyIds) {
            if (targetCurrencyId < 1 ||  targetCurrencyId > countriesInfoList.getCurrency_list().size())
                throw new ResourceNotFoundException("Currency","id",Integer.toString(targetCurrencyId));
        }
        CurrencyDTO baseCurrencyInfo = countriesInfoList.getCurrency_list().get(baseCurrencyId - 1);
        ExchangeRateResponse response = exchangeRateClient.getExchangeRates(baseCurrencyInfo.getCurrencyCode());
        Map<String, Double> conversionRates = response.getConversion_rates();
        if (!conversionRates.containsKey(baseCurrencyInfo.getCurrencyCode()))
            throw new CurrencyApiException(HttpStatus.BAD_REQUEST,"invalid currency code");
        List<Double> convertedAmounts = new ArrayList<>();
        for (Integer targetCurrencyId : targetCurrencyIds) {
            CurrencyDTO targetCurrencyInfo = countriesInfoList.getCurrency_list().get(targetCurrencyId - 1);
            if (targetCurrencyInfo != null && conversionRates.containsKey(targetCurrencyInfo.getCurrencyCode())) {
                double exchangeRate = conversionRates.get(targetCurrencyInfo.getCurrencyCode());
                double convertedAmount = amount * exchangeRate;
                convertedAmounts.add(convertedAmount);
            } else
                throw new CurrencyApiException(HttpStatus.BAD_REQUEST,"invalid currency code");
        }
        return new CompareResponse(convertedAmounts);
    }
}
