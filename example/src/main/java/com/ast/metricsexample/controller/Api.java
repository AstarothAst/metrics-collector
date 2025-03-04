package com.ast.metricsexample.controller;

import com.ast.metricsexample.metrics.MethodCallCounterMetric;
import com.ast.metricsexample.metrics.MethodCallDurationMetric;
import com.ast.metricsstarter.metrics.Metrics;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class Api {

    @GetMapping("/ping")
    @Metrics({MethodCallCounterMetric.class, MethodCallDurationMetric.class})
    public String ping() {
        return "pong";
    }
}
