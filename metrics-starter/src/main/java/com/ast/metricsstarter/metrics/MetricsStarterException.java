package com.ast.metricsstarter.metrics;

import lombok.Getter;

import static java.util.Objects.nonNull;

@Getter
public class MetricsStarterException extends RuntimeException {

    public MetricsStarterException(String errorMessage) {
        super(errorMessage);
    }
}
