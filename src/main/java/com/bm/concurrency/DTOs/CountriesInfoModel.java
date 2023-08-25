package com.bm.concurrency.DTOs;


import com.bm.concurrency.constants.enums.CountriesCode;
import com.bm.concurrency.constants.enums.Currencies;
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