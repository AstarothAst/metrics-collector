package com.ast.metricsstarter.metrics;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class MetricCollectorsBeanProvider {

    private final ApplicationContext context;
    private final BeanFactory beanFactory;

    public MetricCollectorsBeanProvider(ApplicationContext context, BeanFactory beanFactory, List<MetricCollector> collectors) {
        this.context = context;
        this.beanFactory = beanFactory;

        checkNamesAndThrowIfNeed(collectors);
    }

    public MetricCollector createCollector(Class<? extends MetricCollector> collectorClass) {
        return context.getBean(collectorClass);
    }

    public void destroyBean(Object bean) {
        ((DefaultListableBeanFactory) beanFactory).destroyBean(bean);
    }

    private void checkNamesAndThrowIfNeed(List<MetricCollector> collectors) {
        List<String> duplicateMetrics = collectors.stream()
                .collect(Collectors.groupingBy(MetricCollector::getMetricName))
                .entrySet().stream()
                .filter(entry -> entry.getValue().size() > 1)
                .map(entry -> {
                    String metricName = entry.getKey();
                    String classes = entry.getValue().stream().map(collector -> collector.getClass().getName()).collect(Collectors.joining(", "));
                    String msg = "Collectors [%s] have the same metric name: %s";
                    return msg.formatted(classes, metricName);
                }).toList();

        if(!duplicateMetrics.isEmpty()){
            String errorMsg = String.join("; ", duplicateMetrics);
            log.error(errorMsg);
            throw new MetricsStarterException(errorMsg);
        }
    }
}
