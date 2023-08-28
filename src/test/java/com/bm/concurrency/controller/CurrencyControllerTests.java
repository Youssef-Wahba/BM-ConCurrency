package com.bm.concurrency.controller;

import com.bm.concurrency.controllers.CurrencyController;
import com.bm.concurrency.payload.DTOs.CompareDto;
import com.bm.concurrency.payload.response.CompareResponse;
import com.bm.concurrency.service.CurrencyServiceTests;
import com.bm.concurrency.service.serviceImp.CurrencyServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.hamcrest.Matchers;
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
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


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
        // Input values
        CompareDto request = new CompareDto();
        request.setBaseCurrencyId(1);
        request.setTargetCurrencyIds(Arrays.asList(2, 11));
        request.setAmount(1);

        // Mocking behavior
        List<Double> expectedCompareResult = Arrays.asList(0.92517166, 30.83792973);
        when(currencyService.compareConvertedAmounts(eq(1), eq(Arrays.asList(2, 11)), eq(1.0)))
                .thenReturn(new CompareResponse(expectedCompareResult));

        // Call the endpoint using MockMvc
        mockMvc.perform(post("/api/v1/currency/compare")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.compare_result").isArray())
                .andExpect(jsonPath("$.compare_result", Matchers.hasSize(2)))
                .andExpect(jsonPath("$.compare_result", Matchers.contains(expectedCompareResult.get(0), expectedCompareResult.get(1))));

        // Verify interactions
        verify(currencyService).compareConvertedAmounts(eq(1), eq(Arrays.asList(2, 11)), eq(1.0));
    }
}







