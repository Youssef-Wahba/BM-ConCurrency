package com.bm.concurrency.service;

import com.bm.concurrency.DTOs.ConversionResponse;
import com.bm.concurrency.DTOs.CountriesInfoModel;
import com.bm.concurrency.DTOs.CountryListResponse;

import java.util.List;
import java.util.Map;

public interface IConcurrencyService {
    ConversionResponse conversion(String source, String target, double amount);
    Map<Integer, Double> getConvertedAmounts(int baseCurrencyId, List<Integer> targetCurrencyIds, double amount);
}
