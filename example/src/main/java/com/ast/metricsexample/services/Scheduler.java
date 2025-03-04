package com.ast.metricsexample.services;

import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

/**
 * Сервис, проксик для которого должен делаться через CGLIB
 */
@Service
@RequiredArgsConstructor
public class Scheduler {

    private final ServiceOne serviceOne;
    private final ServiceTwo serviceTwo;

    @Scheduled(fixedRate = 1000L)
    public void cron() {
        serviceOne.doit1();
        serviceOne.doit2();

        serviceTwo.doit();
    }
}
