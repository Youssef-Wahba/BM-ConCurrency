package com.bm.concurrency.service.serviceImp;

import com.bm.concurrency.exception.ResourceNotFoundException;
import com.bm.concurrency.utils.ExchangeRateClient;
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
    private final  CurrencyValidator validator;
    public  CurrencyListResponse currencyList = getCurrencyList();

    public CurrencyListResponse getCurrencyList() { return new CurrencyListResponse(); }

    public ConversionResponse convert(Integer source, Integer target, double amount) {

        validator.validateCurrencyId(source,getCurrencyList());
        validator.validateCurrencyId(target,getCurrencyList());

        String sourceCurrencyCode = currencyList.getCurrency_list().get(source - 1).getCurrencyCode();
        String targetCurrencyCode = currencyList.getCurrency_list().get(target - 1).getCurrencyCode();

        Map<String, Object> conversionJson = exchangeRateClient.convert(sourceCurrencyCode, targetCurrencyCode, amount);
        return new ConversionResponse((double) conversionJson.get("conversion_result"));
    }

    public CompareResponse compare(int baseCurrencyId, List<Integer> targetCurrencyIds, double amount, ExchangeRateResponse exchangeRateResponse) {
        CurrencyListResponse countriesInfoList = getCurrencyList();

        validator.validateCurrenciesIds(baseCurrencyId, targetCurrencyIds, countriesInfoList);

        CurrencyDTO baseCurrencyInfo = getBaseCurrencyInfo(baseCurrencyId, countriesInfoList);
        Map<String, Double> conversionRates = exchangeRateResponse.getConversion_rates();

        validator.validateCurrencyCode(conversionRates, baseCurrencyInfo.getCurrencyCode());

        List<Double> convertedAmounts = getConvertedAmounts(countriesInfoList, conversionRates, amount, targetCurrencyIds);

        return new CompareResponse(convertedAmounts);
    }


    private CurrencyDTO getBaseCurrencyInfo(int baseCurrencyId, CurrencyListResponse countriesInfoList) {
        return countriesInfoList.getCurrency_list().get(baseCurrencyId - 1);
    }


    public List<Double> getConvertedAmounts(CurrencyListResponse countriesInfoList, Map<String, Double> conversionRates, double amount, List<Integer> targetCurrencyIds) {
        List<Double> convertedAmounts = new ArrayList<>();

        for (Integer targetCurrencyId : targetCurrencyIds) {
            CurrencyDTO targetCurrencyInfo = countriesInfoList.getCurrency_list().get(targetCurrencyId - 1);

            if (targetCurrencyInfo != null) {
                validator.validateCurrencyCode(conversionRates, targetCurrencyInfo.getCurrencyCode());

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
