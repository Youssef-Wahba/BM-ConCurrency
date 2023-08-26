package com.bm.concurrency.service;

import com.bm.concurrency.payload.response.CompareResponse;
import com.bm.concurrency.payload.response.ConversionResponse;
import com.bm.concurrency.payload.response.CurrencyListResponse;

import java.util.List;

public interface IConcurrencyService {

    CurrencyListResponse getCurrencyInfo();
    ConversionResponse conversion(String source, String target, double amount);
    CompareResponse getConvertedAmounts(int baseCurrencyId, List<Integer> targetCurrencyIds, double amount);
}
