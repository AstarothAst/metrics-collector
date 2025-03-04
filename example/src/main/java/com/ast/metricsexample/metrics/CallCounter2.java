package com.ast.metricsexample.metrics;

import com.ast.metricsstarter.metrics.AbstractCountCollector;
import io.micrometer.core.instrument.MeterRegistry;

public class CallCounter2 extends AbstractCountCollector {

    public CallCounter2(MeterRegistry registry) {
        super(registry);
    }

    @Override
    public String getMetricName() {
        return "method-call-counter-2";
    }

    @Override
    public String getDescription() {
        return "Просто считаем сколько раз вызван метод 2";
    }
}
