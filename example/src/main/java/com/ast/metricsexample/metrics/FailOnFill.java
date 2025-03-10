package com.ast.metricsexample.metrics;

import com.ast.metricsstarter.metrics.MetricCollector;
import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.MeterRegistry;

public class FailOnFill implements MetricCollector {

    private final MeterRegistry registry;

    public FailOnFill(MeterRegistry registry) {
        this.registry = registry;
    }

    @Override
    public String getMetricName() {
        return "failed-on-fill";
    }

    @Override
    public String getDescription() {
        return "Коллектор выбрасывает исключение на заполнении";
    }

    @Override
    public void registerMetric() {
        Counter.builder(getMetricName())
                .description(getDescription())
                .register(registry);
    }

    @Override
    public void fillMetric(Object targetResult) {
        throw new RuntimeException(getMetricName() + " error");

    }
}
