package com.bm.concurrency.service;

import com.bm.concurrency.payload.response.CompareResponse;
import com.bm.concurrency.payload.response.ConversionResponse;
import com.bm.concurrency.payload.response.CurrencyListResponse;
import com.bm.concurrency.payload.response.ExchangeRateResponse;
import java.util.List;

public interface ICurrencyService {

    CurrencyListResponse getCurrencyList();
    ConversionResponse convert(Integer source, Integer target, double amount);
    CompareResponse compare(int baseCurrencyId, List<Integer> targetCurrencyIds, double amount, ExchangeRateResponse exchangeRateResponse);
    ExchangeRateResponse getAllCurrencyRates(Integer baseCurrency);
    void clearCache();
}
