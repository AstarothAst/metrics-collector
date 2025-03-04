package com.ast.metricsexample.metrics;

import com.ast.metricsstarter.metrics.AbstractDurationCollector;
import io.micrometer.core.instrument.MeterRegistry;

public class CallDuration2 extends AbstractDurationCollector {

    public CallDuration2(MeterRegistry registry) {
        super(registry);
    }

    @Override
    public String getMetricName() {
        return "method-call-duration-2";
    }

    @Override
    public String getDescription() {
        return "Просто считаем сколько метод исполнялся 2";
    }
}
