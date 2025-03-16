package com.ast.metricsstarter.config;

import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@Slf4j
@ComponentScan(value = "com.ast.metricsstarter")
public class MetricsStarterConfig {

    @PostConstruct
    public void init() {
        log.info("Custom metrics collection system - enabled");
    }
}
