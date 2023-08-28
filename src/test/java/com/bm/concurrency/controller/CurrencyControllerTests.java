package com.bm.concurrency.controller;

import com.bm.concurrency.controllers.CurrencyController;
import com.bm.concurrency.payload.DTOs.CompareDto;
import com.bm.concurrency.payload.DTOs.ConvertDTO;
import com.bm.concurrency.payload.response.CompareResponse;
import com.bm.concurrency.payload.response.ConversionResponse;
import com.bm.concurrency.service.CurrencyServiceTests;
import com.bm.concurrency.service.serviceImp.CurrencyServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jayway.jsonpath.JsonPath;
import org.hamcrest.Matchers;
import org.json.JSONObject;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.List;

import org.mockito.Mockito;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.hamcrest.Matchers.*;
import static org.hamcrest.MatcherAssert.assertThat;

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
        CompareDto requestDto = new CompareDto();
        requestDto.setBaseCurrencyId(1);
        requestDto.setTargetCurrencyIds(Arrays.asList(2, 11));
        requestDto.setAmount(1);

        CompareResponse response = new CompareResponse();
        response.setCompare_result(Arrays.asList(0.92517166, 30.83792973));

        when(currencyService.compareConvertedAmounts(eq(1), eq(Arrays.asList(2, 11)), eq(1.0)))
                .thenReturn(response);

        ObjectMapper objectMapper = new ObjectMapper();
        String requestJson = objectMapper.writeValueAsString(requestDto);

        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/currency/compare")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestJson))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();

        String responseJson = mvcResult.getResponse().getContentAsString();
        CompareResponse actualResponse = objectMapper.readValue(responseJson, CompareResponse.class);

        // Assertions
        assertEquals(2, actualResponse.getCompare_result().size());

        assertEquals(0.92517166, actualResponse.getCompare_result().get(0), 0.000001);
        assertEquals(30.83792973, actualResponse.getCompare_result().get(1), 0.000001);
    }

    @Test
    public void testConvertCurrencies() throws Exception {
        // Input values
        int sourceCurrencyId = 1;
        int targetCurrencyId = 11;
        double amount = 1.0;

        // Mocking behavior
        double expectedConversionResult = 30.83774513;
        when(currencyService.convert(eq(sourceCurrencyId), eq(targetCurrencyId), eq(amount)))
                .thenReturn(new ConversionResponse(expectedConversionResult));

        // Perform the request and verify the response
        mockMvc.perform(get("/api/v1/currency/convert/{source}/{target}/{amount}", sourceCurrencyId, targetCurrencyId, amount))
                .andExpect(status().isOk())
                .andExpect(content().json("{\"conversion_result\": 30.83774513}")); // Verify the exact JSON response

        // Verify interactions
        verify(currencyService).convert(eq(sourceCurrencyId), eq(targetCurrencyId), eq(amount));
    }
}









