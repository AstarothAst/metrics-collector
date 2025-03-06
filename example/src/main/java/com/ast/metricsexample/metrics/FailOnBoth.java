package com.ast.metricsexample.metrics;

import com.ast.metricsstarter.metrics.MetricCollector;

public class FailOnBoth implements MetricCollector {

    @Override
    public String getMetricName() {
        return "failed-on-register";
    }

    @Override
    public String getDescription() {
        return "Коллектор выбрасывает исключение везде";
    }

    @Override
    public void registerMetric() {
        throw new RuntimeException(getMetricName() + " error");
    }

    @Override
    public void fillMetric(Object targetResult) {
        throw new RuntimeException(getMetricName() + " error");
    }
}
