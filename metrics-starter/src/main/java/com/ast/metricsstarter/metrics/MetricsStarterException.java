package com.ast.metricsstarter.metrics;

public class MetricsStarterException extends RuntimeException {

    public MetricsStarterException(Exception e) {
        super(e);
    }

    public MetricsStarterException(String error) {
        super(error);
    }
}
