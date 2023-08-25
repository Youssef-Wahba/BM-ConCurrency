package com.bm.concurrency;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@EnableFeignClients
@EnableCaching
public class ConCurrencyApplication {
    public static void main(String[] args) {
        SpringApplication.run(ConCurrencyApplication.class, args);
    }
}
