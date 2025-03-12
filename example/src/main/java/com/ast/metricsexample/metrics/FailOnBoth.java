package com.ast.metricsexample.metrics;

import com.ast.metricsstarter.metrics.MetricCollector;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope(BeanDefinition.SCOPE_PROTOTYPE)
public class FailOnBoth implements MetricCollector {

    @Override
    public String getMetricName() {
        return "failed-on-both";
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
