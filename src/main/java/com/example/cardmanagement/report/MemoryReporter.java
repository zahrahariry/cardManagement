package com.example.cardmanagement.report;

import org.springframework.stereotype.Component;
import org.springframework.scheduling.annotation.Scheduled;


@Component
public class MemoryReporter {

    @Scheduled(cron = "0 0 * * *")
    public void reportMemoryUsed() {
    }
}
