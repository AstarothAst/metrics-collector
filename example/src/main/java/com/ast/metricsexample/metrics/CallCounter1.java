package com.ast.metricsexample.metrics;

import com.ast.metricsstarter.metrics.AbstractCountCollector;
import io.micrometer.core.instrument.MeterRegistry;

public class CallCounter1 extends AbstractCountCollector {

    public CallCounter1(MeterRegistry registry) {
        super(registry);
    }

    @Override
    public String getMetricName() {
        return "method-call-counter-1";
    }

    @Override
    public String getDescription() {
        return "Просто считаем сколько раз вызван метод 1";
    }
}
