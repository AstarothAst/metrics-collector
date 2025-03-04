package com.ast.metricsexample.metrics;

import com.ast.metricsstarter.metrics.AbstractDurationCollector;
import io.micrometer.core.instrument.MeterRegistry;

public class CallDuration1 extends AbstractDurationCollector {

    public CallDuration1(MeterRegistry registry) {
        super(registry);
    }

    @Override
    public String getMetricName() {
        return "method-call-duration-1";
    }

    @Override
    public String getDescription() {
        return "Просто считаем сколько метод исполнялся 1";
    }
}
