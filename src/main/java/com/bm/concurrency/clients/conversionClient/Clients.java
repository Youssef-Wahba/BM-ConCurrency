package com.bm.concurrency.clients.conversionClient;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Map;

@FeignClient(name = "convert", url = "https://v6.exchangerate-api.com/v6/ecf10bab01b34bf0de9636e1")

public interface Clients {

    @GetMapping("/pair/{source}/{target}/{amount}")
    public Map<String, Object> convert(

            @PathVariable("source") String source,
            @PathVariable("target") String target,
            @PathVariable("amount") double amount
    );
}
