package com.ast.metricsstarter.metrics;

public class MetricError extends MetricsStarterException {
    public MetricError(Exception e) {
        super(e);
    }

    public MetricError(String error) {
        super(error);
    }

    public MetricError() {
        super();
    }
}
