package com.bm.concurrency.models;

import com.bm.concurrency.utls.enums.CountriesCode;
import com.bm.concurrency.utls.enums.Currencies;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class CountriesInfoModel {
    private final Integer id;
    private final CountriesCode countryNameCode; //EG
    private final Currencies currency; //EGP
    private final String countryFlag;
}