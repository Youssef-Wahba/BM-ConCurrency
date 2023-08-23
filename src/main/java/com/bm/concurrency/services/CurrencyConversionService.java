package com.bm.concurrency.services;

import com.bm.concurrency.model.constants.Constants;
import com.bm.concurrency.model.responseModel.CurrencyConversionResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;


@Service
public class CurrencyConversionService {

    @Autowired
    private WebClient.Builder webClientBuilder;

    public Mono<Double> convertCurrency(String fromCurrency, String toCurrency, double amount) {
        String apiUrl = String.format("%s/%s/%s/%s", Constants.EXCHANGE_RATE_API_BASE_URL, fromCurrency, toCurrency, amount);

        return webClientBuilder.build()
                .get()
                .uri(apiUrl)
                .retrieve()
                .bodyToMono(CurrencyConversionResponse.class)
                .map(CurrencyConversionResponse::getConversionResult);
    }
}
