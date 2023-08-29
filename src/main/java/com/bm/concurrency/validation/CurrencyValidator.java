package com.bm.concurrency.validation;

import com.bm.concurrency.exception.CurrencyApiException;
import com.bm.concurrency.exception.ResourceNotFoundException;
import com.bm.concurrency.payload.response.CurrencyListResponse;
import org.springframework.http.HttpStatus;

import java.util.List;
import java.util.Map;

public class CurrencyValidator {

    public static void validateCurrencyId(int currencyId, CurrencyListResponse countriesInfoList) {
        if (currencyId < 1 || currencyId > countriesInfoList.getCurrency_list().size()) {
            throw new ResourceNotFoundException("Currency", "id", Integer.toString(currencyId));
        }
    }

    public static void validateCurrencyCode(Map<String, Double> conversionRates, String currencyCode) {
        if (!conversionRates.containsKey(currencyCode)) {
            throw new CurrencyApiException(HttpStatus.BAD_REQUEST, "invalid currency code");
        }
    }

    public static void validateCurrenciesIds(int baseCurrencyId, List<Integer> targetCurrencyIds, CurrencyListResponse countriesInfoList) {
        CurrencyValidator.validateCurrencyId(baseCurrencyId, countriesInfoList);

        for (Integer targetCurrencyId : targetCurrencyIds) {
            CurrencyValidator.validateCurrencyId(targetCurrencyId, countriesInfoList);
        }
    }
}
