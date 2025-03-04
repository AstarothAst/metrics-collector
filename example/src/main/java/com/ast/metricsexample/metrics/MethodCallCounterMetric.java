package com.ast.metricsexample.metrics;

import com.ast.metricsstarter.metrics.AbstractCountCollector;
import io.micrometer.core.instrument.MeterRegistry;
import org.springframework.stereotype.Component;

public class MethodCallCounterMetric extends AbstractCountCollector {

    public MethodCallCounterMetric(MeterRegistry registry) {
        super(registry);
    }

    @Override
    public String getMetricName() {
        return "method-call-counter";
    }

    @Override
    public String getDescription() {
        return "Просто считаем сколько раз вызван метод";
    }
}
