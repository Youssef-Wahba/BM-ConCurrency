package com.bm.concurrency.service;

import com.bm.concurrency.model.CountriesInfoModel;
import com.bm.concurrency.utilities.constants.Flags;
import com.bm.concurrency.utilities.enums.CountriesCode;
import com.bm.concurrency.utilities.enums.Currencies;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CountriesInfoService {
    public List<CountriesInfoModel> getCountriesInfo() {
        return List.of(
                new CountriesInfoModel(CountriesCode.USA, Currencies.USD, Flags.USA_FLAG),
                new CountriesInfoModel(CountriesCode.EUR, Currencies.EUR, Flags.EUR_FLAG),
                new CountriesInfoModel(CountriesCode.UK, Currencies.GBP, Flags.UK_FLAG),
                new CountriesInfoModel(CountriesCode.UAE, Currencies.AED, Flags.UAE_FLAG),
                new CountriesInfoModel(CountriesCode.BH, Currencies.BHD, Flags.BH_FLAG),
                new CountriesInfoModel(CountriesCode.JP, Currencies.JPY, Flags.JP_FLAG),
                new CountriesInfoModel(CountriesCode.KW, Currencies.KWD, Flags.KW_FLAG),
                new CountriesInfoModel(CountriesCode.OM, Currencies.OMR, Flags.OM_FLAG),
                new CountriesInfoModel(CountriesCode.QA, Currencies.QAR, Flags.QA_FLAG),
                new CountriesInfoModel(CountriesCode.KSA, Currencies.SAR, Flags.KSA_FLAG),
                new CountriesInfoModel(CountriesCode.EG, Currencies.EGP, Flags.EG_FLAG)
        );
    }
}
