package com.bm.concurrency.model;
import com.bm.concurrency.utilities.enums.CountriesCode;
import com.bm.concurrency.utilities.enums.Currencies;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CountriesInfoModel {
    public CountriesCode countryNameCode; //EG
    public Currencies currency; //EGP
    public String countryFlag;
}
