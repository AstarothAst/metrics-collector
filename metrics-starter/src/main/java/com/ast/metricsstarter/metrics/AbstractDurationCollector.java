package com.ast.metricsstarter.metrics;

import io.micrometer.core.instrument.MeterRegistry;
import io.micrometer.core.instrument.Timer;

import java.time.Duration;
import java.time.Instant;
import java.time.temporal.Temporal;

public abstract class AbstractDurationCollector implements MetricCollector {

    private final MeterRegistry registry;
    private Temporal before;

    public AbstractDurationCollector(MeterRegistry registry) {
        this.registry = registry;
    }

    public void registerMetric() {
        Timer.builder(getMetricName())
                .description(getDescription())
                .register(registry);
        before = Instant.now();
    }

    public void fillMetric(Object targetResult) {
        Instant now = Instant.now();
        Duration between = Duration.between(before, now);
        registry.get(getMetricName()).timer().record(between);
    }
}
