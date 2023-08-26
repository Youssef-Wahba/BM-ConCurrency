package com.bm.concurrency.payload.response;

import com.bm.concurrency.constants.enums.Currency;
import com.bm.concurrency.payload.DTOs.CurrencyDTO;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class CurrencyListResponse {
    List<CurrencyDTO> currency_list = new ArrayList<>();
    public CurrencyListResponse(){
        currency_list.add(new CurrencyDTO(Currency.USD));
        currency_list.add(new CurrencyDTO(Currency.EUR));
        currency_list.add(new CurrencyDTO(Currency.GBP));
        currency_list.add(new CurrencyDTO(Currency.AED));
        currency_list.add(new CurrencyDTO(Currency.BHD));
        currency_list.add(new CurrencyDTO(Currency.JPY));
        currency_list.add(new CurrencyDTO(Currency.KWD));
        currency_list.add(new CurrencyDTO(Currency.OMR));
        currency_list.add(new CurrencyDTO(Currency.QAR));
        currency_list.add(new CurrencyDTO(Currency.SAR));
        currency_list.add(new CurrencyDTO(Currency.EGP));
    }
}
