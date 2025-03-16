package com.ast.metricsstarter.metrics;

import io.micrometer.core.instrument.MeterRegistry;

public abstract class AbstractErrorCountCollector extends AbstractCountCollector {


    public AbstractErrorCountCollector(MeterRegistry registry) {
        super(registry);
    }

    @Override
    public void fillErrorMetric(Exception e) {
        fillMetric(e);
    }
}
