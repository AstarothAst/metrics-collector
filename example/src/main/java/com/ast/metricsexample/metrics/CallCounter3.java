package com.ast.metricsexample.metrics;

import com.ast.metricsstarter.metrics.AbstractCountCollector;
import io.micrometer.core.instrument.MeterRegistry;

public class CallCounter3 extends AbstractCountCollector {

    public CallCounter3(MeterRegistry registry) {
        super(registry);
    }

    @Override
    public String getMetricName() {
        return "method-call-counter-3";
    }

    @Override
    public String getDescription() {
        return "Просто считаем сколько раз вызван метод 3";
    }
}
