package com.ast.metricsexample.metrics;

import com.ast.metricsstarter.metrics.MetricCollector;
import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.MeterRegistry;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope(BeanDefinition.SCOPE_PROTOTYPE)
public class BusinessMetric implements MetricCollector {

    private final MeterRegistry registry;

    public BusinessMetric(MeterRegistry registry) {
        this.registry = registry;
    }

    @Override
    public String getMetricName() {
        return "business-1";
    }

    @Override
    public String getDescription() {
        return "Собираем бизнес-метрику";
    }

    @Override
    public void registerMetric() {
        Counter.builder(getMetricName())
                .description(getDescription())
                .register(registry);
    }

    @Override
    public void fillMetric(Object targetResult) {
        int length = ((String) targetResult).length();
        Counter counter = registry.get(getMetricName()).counter();
        counter.increment(length);
    }
}
