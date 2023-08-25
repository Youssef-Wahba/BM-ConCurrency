package com.bm.concurrency.service.serviceImp;

import com.bm.concurrency.DTOs.ConversionResponse;
import com.bm.concurrency.DTOs.CountryListResponse;
import com.bm.concurrency.client.ExchangeRateClient;
import com.bm.concurrency.constants.Flags;
import com.bm.concurrency.constants.enums.CountriesCode;
import com.bm.concurrency.constants.enums.Currencies;
import com.bm.concurrency.DTOs.CountriesInfoModel;
import com.bm.concurrency.DTOs.ExchangeRateResponse;
import com.bm.concurrency.service.IConcurrencyService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ConcurrencyService implements IConcurrencyService {
    private final ExchangeRateClient exchangeRateClient;
    private final CountryService countryService;
    public ConversionResponse conversion(String source, String target, double amount ) {
        Map<String ,Object>  conversionJson = exchangeRateClient.convert(source,target,amount);
        return new ConversionResponse((double)conversionJson.get("conversion_result"));
    }

    public Map<Integer, Double> getConvertedAmounts(int baseCurrencyId, List<Integer> targetCurrencyIds, double amount) {
        CountryListResponse countriesInfoMap = countryService.getCountriesInfo();
        CountriesInfoModel baseCurrencyInfo = countriesInfoMap.getCountry_list().get(baseCurrencyId-1);
        if (baseCurrencyInfo == null) {
            return null; //exception handling
        }
        ExchangeRateResponse response = exchangeRateClient.getExchangeRates(baseCurrencyInfo.getCurrency().toString());
        Map<String, Double> conversionRates = response.getConversion_rates();
        if (!conversionRates.containsKey(baseCurrencyInfo.getCurrency().toString())) {
            return null; //exception handling
        }
        Map<Integer, Double> convertedAmounts = new HashMap<>();
        for (int targetCurrencyId : targetCurrencyIds) {
            CountriesInfoModel targetCurrencyInfo = countriesInfoMap.getCountry_list().get(targetCurrencyId-1);
            if (targetCurrencyInfo != null && conversionRates.containsKey(targetCurrencyInfo.getCurrency().toString())) {
                double exchangeRate = conversionRates.get(targetCurrencyInfo.getCurrency().toString());
                double convertedAmount = amount * exchangeRate;
                convertedAmounts.put(targetCurrencyId, convertedAmount);
            } else {
                return null;// exception handling
            }
        }
        return convertedAmounts;
    }
}
