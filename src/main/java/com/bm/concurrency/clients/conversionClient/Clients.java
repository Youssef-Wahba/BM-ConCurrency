package com.bm.concurrency.clients.conversionClient;

import com.bm.concurrency.utls.constants.Constants;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Map;

@FeignClient(name = "ConCurrency", url = Constants.EXCHANGE_RATE_API_BASE_URL)

public interface Clients {

    @GetMapping("/pair/{source}/{target}/{amount}")
    public Map<String, Object> convert(

            @PathVariable("source") String source,
            @PathVariable("target") String target,
            @PathVariable("amount") double amount
    );
}
