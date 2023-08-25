package com.bm.concurrency.client;

import com.bm.concurrency.DTOs.ExchangeRateResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Map;
@FeignClient(name = "ExchangeRateClient", url ="${EXCHANGE_RATE_API_BASE_URL}" )
public interface ExchangeRateClient {
    @GetMapping("/latest/{baseCurrency}")
    ExchangeRateResponse getExchangeRates(@PathVariable("baseCurrency") String baseCurrency);

    @GetMapping("/pair/{source}/{target}/{amount}")
    Map<String, Object> convert(
            @PathVariable("source") String source,
            @PathVariable("target") String target,
            @PathVariable("amount") double amount
    );
}
