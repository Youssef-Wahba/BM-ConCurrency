package com.bm.concurrency.service;

import com.bm.concurrency.clients.conversionClient.Clients;

import com.bm.concurrency.models.CountriesInfoModel;
import com.bm.concurrency.utls.constants.Flags;
import com.bm.concurrency.utls.enums.CountriesCode;
import com.bm.concurrency.utls.enums.Currencies;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
@Service
@RequiredArgsConstructor
public class ConcurrencyService {

    private final Clients client;

    public Map<String,Double> conversion(String source, String target, double amount ) {

        Map<String ,Object>  conversionJson = client.convert(source,target,amount);
        Map<String  , Double>  mapResponse = new HashMap<>();
        mapResponse.put("conversion_result", (double)conversionJson.get("conversion_result"));
        return   mapResponse;
    }
    public List<CountriesInfoModel> getCountriesInfo() {
        return List.of(
                new CountriesInfoModel(1, CountriesCode.USA, Currencies.USD, Flags.USA_FLAG),
                new CountriesInfoModel(2,CountriesCode.EUR, Currencies.EUR, Flags.EUR_FLAG),
                new CountriesInfoModel(3,CountriesCode.UK, Currencies.GBP, Flags.UK_FLAG),
                new CountriesInfoModel(4,CountriesCode.UAE, Currencies.AED, Flags.UAE_FLAG),
                new CountriesInfoModel(5,CountriesCode.BH, Currencies.BHD, Flags.BH_FLAG),
                new CountriesInfoModel(6,CountriesCode.JP, Currencies.JPY, Flags.JP_FLAG),
                new CountriesInfoModel(7,CountriesCode.KW, Currencies.KWD, Flags.KW_FLAG),
                new CountriesInfoModel(8,CountriesCode.OM, Currencies.OMR, Flags.OM_FLAG),
                new CountriesInfoModel(9,CountriesCode.QA, Currencies.QAR, Flags.QA_FLAG),
                new CountriesInfoModel(10,CountriesCode.KSA, Currencies.SAR, Flags.KSA_FLAG),
                new CountriesInfoModel(11,CountriesCode.EG, Currencies.EGP, Flags.EG_FLAG)
        );
    }
}
