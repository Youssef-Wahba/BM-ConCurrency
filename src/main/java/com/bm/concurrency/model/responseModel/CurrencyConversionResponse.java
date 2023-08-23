package com.bm.concurrency.model.responseModel;

import com.fasterxml.jackson.annotation.JsonProperty;

public class CurrencyConversionResponse {
    @JsonProperty("conversion_result")
    private double conversionResult;

    public double getConversionResult() {
        return conversionResult;
    }

    public void setConversionResult(double conversionResult) {
        this.conversionResult = conversionResult;
    }
}
