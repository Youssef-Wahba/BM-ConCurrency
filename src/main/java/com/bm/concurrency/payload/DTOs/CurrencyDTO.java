package com.bm.concurrency.payload.DTOs;

import com.bm.concurrency.constants.enums.Currency;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class CurrencyDTO {
    private final Integer id;
    private final String currencyCode;
    private final String flagUrl;

    public CurrencyDTO(Currency currency){
        this.id = currency.getId();
        this.currencyCode = currency.getCurrencyCode();
        this.flagUrl = currency.getFlagUrl();
    }
}