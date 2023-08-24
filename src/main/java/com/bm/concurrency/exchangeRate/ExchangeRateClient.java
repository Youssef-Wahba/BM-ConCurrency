package com.bm.concurrency.exchangeRate;

import com.bm.concurrency.models.constants.Constants;
import com.bm.concurrency.models.responseModel.ExchangeRateResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "ExchangeRateClient", url = Constants.EXCHANGE_RATE_API_BASE_URL)
public interface ExchangeRateClient {

    @GetMapping("/latest/{baseCurrency}")
    ExchangeRateResponse getExchangeRates(@PathVariable("baseCurrency") String baseCurrency);
}
