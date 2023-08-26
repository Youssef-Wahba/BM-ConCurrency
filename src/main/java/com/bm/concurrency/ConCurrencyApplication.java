package com.bm.concurrency;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.core.env.Environment;

@SpringBootApplication
@EnableFeignClients
public class ConCurrencyApplication {
    public static void main(String[] args) {
        SpringApplication.run(ConCurrencyApplication.class, args);
    }
}
