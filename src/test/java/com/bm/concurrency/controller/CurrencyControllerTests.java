package com.bm.concurrency.controller;

import com.bm.concurrency.controllers.CurrencyController;
import com.bm.concurrency.payload.DTOs.CompareDto;
import com.bm.concurrency.payload.response.CompareResponse;
import com.bm.concurrency.payload.response.ConversionResponse;
import com.bm.concurrency.service.serviceImp.CurrencyServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import java.util.Arrays;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;


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

        when(currencyService.compareConvertedAmounts(eq(1), eq(Arrays.asList(2, 11)), eq(1.0)))
                .thenReturn(response);

        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/currency/compare")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"baseCurrencyId\": 1,\"targetCurrencyIds\": [2, 11],\"amount\": 1}"))
                .andExpect(status().isOk())
                .andReturn();

        CompareResponse actualResponse = new ObjectMapper().readValue(
                mvcResult.getResponse().getContentAsString(), CompareResponse.class);

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
}









