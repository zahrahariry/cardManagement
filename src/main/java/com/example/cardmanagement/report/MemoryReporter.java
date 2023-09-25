package com.example.cardmanagement.report;

import org.springframework.stereotype.Component;
import org.springframework.scheduling.annotation.Scheduled;


@Component
public class MemoryReporter {

    @Scheduled(cron = "0 0 */8 * * *")
    public void reportMemoryUsed() {
        Runtime runtime = Runtime.getRuntime();
        Long memoryValue = runtime.totalMemory();
        System.out.println("memory that used is : "+ String.valueOf(memoryValue));
    }
}
