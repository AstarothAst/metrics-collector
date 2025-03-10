package com.ast.metricsexample.metrics;

import com.ast.metricsstarter.metrics.AbstractErrorCountCollector;
import io.micrometer.core.instrument.MeterRegistry;

public class ErrorCount extends AbstractErrorCountCollector {

    public ErrorCount(MeterRegistry registry) {
        super(registry);
    }

    @Override
    public String getMetricName() {
        return "error-call-counter";
    }

    @Override
    public String getDescription() {
        return "Просто считаем сколько раз возникла ошибка";
    }
}
