package com.ast.metricsstarter.metrics;

import lombok.Getter;

import static java.util.Objects.nonNull;

@Getter
public class MetricsStarterException extends RuntimeException {

    public MetricsStarterException(Exception e) {
        if (nonNull(e)) {
            this.setStackTrace(e.getStackTrace());
        }
    }
}
