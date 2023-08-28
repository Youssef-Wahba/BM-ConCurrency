package com.bm.concurrency.controller;

import com.bm.concurrency.controllers.CurrencyController;
import com.bm.concurrency.payload.response.CompareResponse;
import com.bm.concurrency.payload.response.ConversionResponse;
import com.bm.concurrency.payload.response.CurrencyListResponse;
import com.bm.concurrency.payload.response.ExchangeRateResponse;
import com.bm.concurrency.service.serviceImp.CurrencyServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.util.StreamUtils;


@WebMvcTest( controllers = CurrencyController.class)
@AutoConfigureMockMvc(addFilters = false)
@ExtendWith(MockitoExtension.class)
public class CurrencyControllerTests {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CurrencyServiceImpl currencyService;

    @Autowired
    private ObjectMapper objectMapper;


    @Test
    public void testCompareCurrencies() throws Exception {
        CompareResponse response = new CompareResponse();
        response.setCompare_result(Arrays.asList(0.92517166, 30.83792973));

        ExchangeRateResponse exchangeRateResponse = currencyService.getAllCurrencyRates(1);

        System.out.println("Mock ExchangeRateResponse content: " + objectMapper.writeValueAsString(exchangeRateResponse));

        when(currencyService.compare(eq(1), eq(Arrays.asList(2, 11)), eq(1.0), eq(exchangeRateResponse)))
                .thenReturn(response);

        String requestJson = "{\"baseCurrencyId\": 1,\"targetCurrencyIds\": [2, 11],\"amount\": 1}";

        System.out.println("Request JSON content: " + requestJson);

        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/currency/compare")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestJson))
                .andExpect(status().isOk())
                .andReturn();

        String responseContent = mvcResult.getResponse().getContentAsString();
        System.out.println("Response JSON content: " + responseContent);

        CompareResponse actualResponse = objectMapper.readValue(responseContent, CompareResponse.class);

        assertEquals(2, actualResponse.getCompare_result().size());
        assertEquals(0.92517166, actualResponse.getCompare_result().get(0), 0.000001);
        assertEquals(30.83792973, actualResponse.getCompare_result().get(1), 0.000001);
    }

    @Test
    public void testConvertCurrencies() throws Exception {
        int sourceCurrencyId = 1;
        int targetCurrencyId = 11;
        double amount = 1.0;
        double expectedConversionResult = 30.83774513;

        when(currencyService.convert(eq(sourceCurrencyId), eq(targetCurrencyId), eq(amount)))
                .thenReturn(new ConversionResponse(expectedConversionResult));

        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders
                        .get("/api/v1/currency/convert/{source}/{target}/{amount}", sourceCurrencyId, targetCurrencyId, amount))
                .andExpect(status().isOk())
                .andExpect(content().json("{\"conversion_result\": 30.83774513}"))
                .andReturn();

        verify(currencyService).convert(eq(sourceCurrencyId), eq(targetCurrencyId), eq(amount));
    }

    @Test
    public void testGetAllCurrencies() throws Exception {
        // Load the expected JSON from the file
        String expectedJson = loadResourceAsString("expected-currencies.json");

        // Mock the behavior of currencyService.getCurrencyList()
        when(currencyService.getCurrencyList()).thenReturn(new CurrencyListResponse());

        // Perform the GET request and verify the response
        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/currency")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().json(expectedJson));
    }

    private String loadResourceAsString(String resourcePath) throws IOException {
        ClassPathResource resource = new ClassPathResource(resourcePath);
        byte[] bytes = StreamUtils.copyToByteArray(resource.getInputStream());
        return new String(bytes, StandardCharsets.UTF_8);
    }



}









