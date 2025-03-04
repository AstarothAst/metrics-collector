package com.ast.metricsstarter.metrics;

import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.MeterRegistry;

public abstract class AbstractCountCollector implements MetricCollector {

    private final MeterRegistry registry;

    public AbstractCountCollector(MeterRegistry registry) {
        this.registry = registry;
    }

    public void registerMetric() {
        Counter.builder(getMetricName())
                .description(getDescription())
                .register(registry);
    }

    public void fillMetric(Object targetResult) {
        Counter counter = registry.get(getMetricName()).counter();
        counter.increment();
    }
}
