package com.bm.concurrency.services;

import com.bm.concurrency.exchangeRate.ExchangeRateClient;
import com.bm.concurrency.models.constants.Flags;
import com.bm.concurrency.models.constants.enums.CountriesCode;
import com.bm.concurrency.models.constants.enums.Currencies;
import com.bm.concurrency.models.entities.CountriesInfoModel;
import com.bm.concurrency.models.responseModel.ExchangeRateResponse;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
public class ExchangeRateService {

    private final ExchangeRateClient exchangeRateClient;

    public ExchangeRateService(ExchangeRateClient exchangeRateClient) {
        this.exchangeRateClient = exchangeRateClient;

    }

    public Map<Integer, CountriesInfoModel> getCountriesInfo() {
        List<CountriesInfoModel> countriesInfoList = List.of(
                new CountriesInfoModel(1, CountriesCode.USA, Currencies.USD, Flags.USA_FLAG),
                new CountriesInfoModel(2, CountriesCode.EUR, Currencies.EUR, Flags.EUR_FLAG),
                new CountriesInfoModel(3, CountriesCode.UK, Currencies.GBP, Flags.UK_FLAG),
                new CountriesInfoModel(4, CountriesCode.UAE, Currencies.AED, Flags.UAE_FLAG),
                new CountriesInfoModel(5, CountriesCode.BH, Currencies.BHD, Flags.BH_FLAG),
                new CountriesInfoModel(6, CountriesCode.JP, Currencies.JPY, Flags.JP_FLAG),
                new CountriesInfoModel(7, CountriesCode.KW, Currencies.KWD, Flags.KW_FLAG),
                new CountriesInfoModel(8, CountriesCode.OM, Currencies.OMR, Flags.OM_FLAG),
                new CountriesInfoModel(9, CountriesCode.QA, Currencies.QAR, Flags.QA_FLAG),
                new CountriesInfoModel(10, CountriesCode.KSA, Currencies.SAR, Flags.KSA_FLAG),
                new CountriesInfoModel(11, CountriesCode.EG, Currencies.EGP, Flags.EG_FLAG)
        );
        return countriesInfoList.stream().collect(Collectors.toMap(CountriesInfoModel::getId, Function.identity()));
    }

    public Map<Integer, Double> getConvertedAmounts(int baseCurrencyId, List<Integer> targetCurrencyIds, double amount) {
        Map<Integer, CountriesInfoModel> countriesInfoMap = getCountriesInfo();
        CountriesInfoModel baseCurrencyInfo = countriesInfoMap.get(baseCurrencyId);

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
            CountriesInfoModel targetCurrencyInfo = countriesInfoMap.get(targetCurrencyId);

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
