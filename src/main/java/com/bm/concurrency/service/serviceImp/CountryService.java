package com.bm.concurrency.service.serviceImp;

import com.bm.concurrency.DTOs.CountriesInfoModel;
import com.bm.concurrency.DTOs.CountryListResponse;
import com.bm.concurrency.constants.Flags;
import com.bm.concurrency.constants.enums.CountriesCode;
import com.bm.concurrency.constants.enums.Currencies;
import com.bm.concurrency.service.ICountryService;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class CountryService implements ICountryService {
    private List<CountriesInfoModel> countriesInfoList = List.of(
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
    public CountryListResponse getCountriesInfo() {
        return new CountryListResponse(countriesInfoList);
    }
}
