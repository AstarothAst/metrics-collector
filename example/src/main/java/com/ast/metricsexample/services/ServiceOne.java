package com.ast.metricsexample.services;

import com.ast.metricsexample.metrics.BusinessMetric;
import com.ast.metricsstarter.metrics.Metrics;

public interface ServiceOne {

    void doit1();

    @Metrics(BusinessMetric.class)
    String doit2();
}
