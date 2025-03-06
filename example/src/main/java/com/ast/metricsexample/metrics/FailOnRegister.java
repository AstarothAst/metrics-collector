package com.ast.metricsexample.metrics;

import com.ast.metricsstarter.metrics.MetricCollector;

public class FailOnRegister implements MetricCollector {

    @Override
    public String getMetricName() {
        return "failed-on-register";
    }

    @Override
    public String getDescription() {
        return "Коллектор выбрасывает исключение на регистрации";
    }

    @Override
    public void registerMetric() {
        throw new RuntimeException(getMetricName() + " error");
    }

    @Override
    public void fillMetric(Object targetResult) {

    }
}
