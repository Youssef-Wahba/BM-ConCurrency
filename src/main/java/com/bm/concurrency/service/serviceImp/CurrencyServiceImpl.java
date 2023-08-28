package com.bm.concurrency.service.serviceImp;

import com.bm.concurrency.exception.CurrencyApiException;
import com.bm.concurrency.exception.ResourceNotFoundException;
import com.bm.concurrency.client.ExchangeRateClient;
import com.bm.concurrency.payload.DTOs.CurrencyDTO;
import com.bm.concurrency.payload.response.CompareResponse;
import com.bm.concurrency.payload.response.ConversionResponse;
import com.bm.concurrency.payload.response.CurrencyListResponse;
import com.bm.concurrency.payload.response.ExchangeRateResponse;
import com.bm.concurrency.service.ICurrencyService;
import com.bm.concurrency.validation.CurrencyValidator;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Slf4j
@Service
@RequiredArgsConstructor
public class CurrencyServiceImpl implements ICurrencyService {
    private final ExchangeRateClient exchangeRateClient;
    CurrencyListResponse currencyList = getCurrencyList();

    public CurrencyListResponse getCurrencyList() { return new CurrencyListResponse(); }

    public ConversionResponse convert(Integer source, Integer target, double amount) {
        if (source < 1 || source > currencyList.getCurrency_list().size())
            throw new ResourceNotFoundException("Currency","id",Integer.toString(source));
        if (target < 1 ||  target > currencyList.getCurrency_list().size())
            throw new ResourceNotFoundException("Currency","id",Integer.toString(source));
        String sourceCurrencyCode = currencyList.getCurrency_list().get(source - 1).getCurrencyCode();
        String targetCurrencyCode = currencyList.getCurrency_list().get(target - 1).getCurrencyCode();
        Map<String, Object> conversionJson = exchangeRateClient.convert(sourceCurrencyCode, targetCurrencyCode, amount);
        return new ConversionResponse((double) conversionJson.get("conversion_result"));
    }

    public CompareResponse compareConvertedAmounts(int baseCurrencyId, List<Integer> targetCurrencyIds, double amount) {
        CurrencyListResponse countriesInfoList = getCurrencyList();

        CurrencyValidator.validateCurrenciesIds(baseCurrencyId, targetCurrencyIds, countriesInfoList);

        CurrencyDTO baseCurrencyInfo = getBaseCurrencyInfo(baseCurrencyId, countriesInfoList);
        Map<String, Double> conversionRates = getConversionRates(baseCurrencyInfo.getCurrencyCode());

        CurrencyValidator.validateCurrencyCode(conversionRates, baseCurrencyInfo.getCurrencyCode());

        List<Double> convertedAmounts = getConvertedAmounts(countriesInfoList, conversionRates, amount, targetCurrencyIds);

        return new CompareResponse(convertedAmounts);
    }


    private CurrencyDTO getBaseCurrencyInfo(int baseCurrencyId, CurrencyListResponse countriesInfoList) {
        return countriesInfoList.getCurrency_list().get(baseCurrencyId - 1);
    }

    private Map<String, Double> getConversionRates(String currencyCode) {
        ExchangeRateResponse response = exchangeRateClient.getExchangeRates(currencyCode);
        return response.getConversion_rates();
    }

    private List<Double> getConvertedAmounts(CurrencyListResponse countriesInfoList, Map<String, Double> conversionRates, double amount, List<Integer> targetCurrencyIds) {
        List<Double> convertedAmounts = new ArrayList<>();

        for (Integer targetCurrencyId : targetCurrencyIds) {
            CurrencyDTO targetCurrencyInfo = countriesInfoList.getCurrency_list().get(targetCurrencyId - 1);

            if (targetCurrencyInfo != null) {
                CurrencyValidator.validateCurrencyCode(conversionRates, targetCurrencyInfo.getCurrencyCode());

                double exchangeRate = conversionRates.get(targetCurrencyInfo.getCurrencyCode());
                double convertedAmount = amount * exchangeRate;
                convertedAmounts.add(convertedAmount);
            }
        }

        return convertedAmounts;
    }


    @Cacheable(cacheNames = "currency", key = "#baseCurrencyId")
    public ExchangeRateResponse getAllCurrencyRates(Integer baseCurrencyId) {
        CurrencyListResponse countriesInfoList = getCurrencyList();
        if (baseCurrencyId < 1 ||  baseCurrencyId > countriesInfoList.getCurrency_list().size())
            throw new ResourceNotFoundException("Currency","id",Integer.toString(baseCurrencyId));
        String baseCurrencyCode = countriesInfoList.getCurrency_list().get(baseCurrencyId - 1).getCurrencyCode();
        return exchangeRateClient.getExchangeRates(baseCurrencyCode);
    }

    @CacheEvict(cacheNames = "currency", allEntries = true)
    public void clearCache() {
        log.info("CACHE CLEANED AT : " + new Date());
    }
}
