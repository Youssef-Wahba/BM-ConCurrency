package com.bm.concurrency.models.entities;


import com.bm.concurrency.models.constants.enums.CountriesCode;
import com.bm.concurrency.models.constants.enums.Currencies;
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