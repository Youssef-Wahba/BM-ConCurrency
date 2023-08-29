package com.bm.concurrency.service;

import com.bm.concurrency.constants.enums.Currency;
import com.bm.concurrency.payload.DTOs.CompareDto;
import com.bm.concurrency.payload.DTOs.CurrencyDTO;
import com.bm.concurrency.payload.response.CompareResponse;
import com.bm.concurrency.payload.response.CurrencyListResponse;
import com.bm.concurrency.payload.response.ExchangeRateResponse;
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

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class CurrencyServiceTests {
    @Mock
    private ExchangeRateClient exchangeRateClient;
    @InjectMocks
    private CurrencyServiceImpl currencyService;
    @Mock
    private CurrencyValidator validator;
    @Mock
    private CurrencyListResponse currencyListResponse;
    @Mock
    private  ICurrencyService concurrencyService;


    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        currencyService = new CurrencyServiceImpl(exchangeRateClient, validator);
        currencyService.currencyList = currencyService.getCurrencyList();
    }

    @Test
    public void testGetCurrencyList() {
        currencyListResponse = currencyService.getCurrencyList();

        assertNotNull(currencyListResponse);

        // Assert that the currency_list field is not empty
        assertFalse(currencyListResponse.getCurrency_list().isEmpty());

        // Assert that currencyListResponse has exactly 11 elements
        assertEquals(11, currencyListResponse.getCurrency_list().size());

        // Mock expected currencies data
        List<CurrencyDTO> expectedCurrencies = new ArrayList<>();
        expectedCurrencies.add(new CurrencyDTO(com.bm.concurrency.constants.enums.Currency.USD));
        expectedCurrencies.add(new CurrencyDTO(com.bm.concurrency.constants.enums.Currency.EUR));
        expectedCurrencies.add(new CurrencyDTO(com.bm.concurrency.constants.enums.Currency.GBP));
        expectedCurrencies.add(new CurrencyDTO(com.bm.concurrency.constants.enums.Currency.AED));
        expectedCurrencies.add(new CurrencyDTO(com.bm.concurrency.constants.enums.Currency.BHD));
        expectedCurrencies.add(new CurrencyDTO(com.bm.concurrency.constants.enums.Currency.JPY));
        expectedCurrencies.add(new CurrencyDTO(com.bm.concurrency.constants.enums.Currency.KWD));
        expectedCurrencies.add(new CurrencyDTO(com.bm.concurrency.constants.enums.Currency.OMR));
        expectedCurrencies.add(new CurrencyDTO(com.bm.concurrency.constants.enums.Currency.QAR));
        expectedCurrencies.add(new CurrencyDTO(com.bm.concurrency.constants.enums.Currency.SAR));
        expectedCurrencies.add(new CurrencyDTO(Currency.EGP));

        // Assert that the returned currency_list matches the expectedCurrencies
        assertEquals(expectedCurrencies, currencyListResponse.getCurrency_list());

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
