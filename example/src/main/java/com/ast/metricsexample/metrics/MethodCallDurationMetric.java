package com.ast.metricsexample.metrics;

import com.ast.metricsstarter.metrics.AbstractDurationCollector;
import io.micrometer.core.instrument.MeterRegistry;
import org.springframework.stereotype.Component;

public class MethodCallDurationMetric extends AbstractDurationCollector {

    public MethodCallDurationMetric(MeterRegistry registry) {
        super(registry);
    }

    @Override
    public String getMetricName() {
        return "method-call-duration";
    }

    @Override
    public String getDescription() {
        return "Просто считаем сколько метод исполнялся";
    }
}
