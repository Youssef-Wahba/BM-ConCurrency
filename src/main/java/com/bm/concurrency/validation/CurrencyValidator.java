package com.bm.concurrency.validation;

import com.bm.concurrency.exception.CurrencyApiException;
import com.bm.concurrency.exception.ResourceNotFoundException;
import com.bm.concurrency.payload.response.CurrencyListResponse;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import java.util.List;
import java.util.Map;

@Component
public class CurrencyValidator {

    public void validateCurrencyId(int currencyId, CurrencyListResponse countriesInfoList) {
        if (currencyId < 1 || currencyId > countriesInfoList.getCurrency_list().size()) {
            throw new ResourceNotFoundException("Currency", "id", Integer.toString(currencyId));
        }
    }

    public void validateCurrencyCode(Map<String, Double> conversionRates, String currencyCode) {
        if (!conversionRates.containsKey(currencyCode)) {
            throw new CurrencyApiException(HttpStatus.BAD_REQUEST, "invalid currency code");
        }
    }

    public void validateCurrenciesIds(int baseCurrencyId, List<Integer> targetCurrencyIds, CurrencyListResponse countriesInfoList) {
        validateCurrencyId(baseCurrencyId, countriesInfoList);

        for (Integer targetCurrencyId : targetCurrencyIds) {
            validateCurrencyId(targetCurrencyId, countriesInfoList);
        }
    }
}
