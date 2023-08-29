package com.bm.concurrency.utils;

import com.bm.concurrency.service.ICurrencyService;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CacheClean {
    private final ICurrencyService currencyService;
    @Scheduled(cron = "0 0 * * * *") // schedule every hour to clean cache
    public void cleanupCache() {
        currencyService.clearCache();
    }
}
