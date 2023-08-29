package com.bm.concurrency.service;

import com.bm.concurrency.utils.ExchangeRateClient;
import com.bm.concurrency.payload.response.ConversionResponse;
import com.bm.concurrency.service.serviceImp.CurrencyServiceImpl;
import com.bm.concurrency.validation.CurrencyValidator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import java.util.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class CurrencyServiceTests {
    @Mock
    private ExchangeRateClient exchangeRateClient;
    @InjectMocks
    private CurrencyServiceImpl currencyService;
    @Mock
    private CurrencyValidator validator;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        currencyService = new CurrencyServiceImpl(exchangeRateClient,validator);
        currencyService.currencyList = currencyService.getCurrencyList();
    }

    @Test
    public void testConvert() {
        int sourceCurrencyIndex = 1;
        int targetCurrencyIndex = 2;
        double amount = 100.0;
        String sourceCurrencyCode = "USD";
        String targetCurrencyCode = "EUR";
        when(exchangeRateClient.convert(sourceCurrencyCode, targetCurrencyCode, amount))
                .thenReturn(Collections.singletonMap("conversion_result", 92.50284)); // Mocked conversion result
        ConversionResponse conversionResponse = currencyService.convert(sourceCurrencyIndex, targetCurrencyIndex, amount);
        double expectedConversionResult = 92.50284;
        assertEquals(expectedConversionResult, conversionResponse.getConversion_result(), 0.01);
    }
}
