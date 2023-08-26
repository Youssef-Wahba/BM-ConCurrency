package com.bm.concurrency.constants.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum Currency {
    USD(1,"USD","https://flagcdn.com/h60/us.png"),
    EUR(2,"EUR","https://flagcdn.com/h60/eu.png"),
    GBP(3,"GBP","https://flagcdn.com/h60/gb.png"),
    AED(4,"AED","https://flagcdn.com/h60/ae.png"),
    BHD(5,"BHD","https://flagcdn.com/h60/bh.png"),
    JPY(6,"JPY","https://flagcdn.com/h60/jp.png"),
    KWD(7,"KWD","https://flagcdn.com/h60/kw.png"),
    OMR(8,"OMR","https://flagcdn.com/h60/om.png"),
    QAR(9,"QAR","https://flagcdn.com/h60/qa.png"),
    SAR(10,"SAR","https://flagcdn.com/h60/sa.png"),
    EGP(11,"EGP","https://flagcdn.com/h60/eg.png");

    private final int id;
    private final String currencyCode;
    private final String flagUrl;
}
