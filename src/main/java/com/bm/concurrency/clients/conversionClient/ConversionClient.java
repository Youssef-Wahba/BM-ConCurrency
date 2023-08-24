package com.bm.concurrency.clients.conversionClient;

import com.bm.concurrency.models.ConversionModel;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "Convert", url = "https://v6.exchangerate-api.com/v6/ecf10bab01b34bf0de9636e1")
public interface ConversionClient {
    @GetMapping("/pair/{source}/{target}/{amount}")
    ConversionModel convert(

            @PathVariable("source") String source,
            @PathVariable("target") String target,
            @PathVariable("amount") double amount

                            );


}