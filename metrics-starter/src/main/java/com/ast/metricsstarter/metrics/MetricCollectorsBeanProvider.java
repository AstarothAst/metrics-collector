package com.ast.metricsstarter.metrics;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.beans.factory.support.AbstractBeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.UUID;

import static java.util.Objects.isNull;

@Service
@RequiredArgsConstructor
public class MetricCollectorsBeanProvider {

    private final ApplicationContext context;
    private final BeanFactory beanFactory;

    public MetricCollector createCollector(Class<? extends MetricCollector> collectorClass) {
        BeanDefinitionRegistry registry = (BeanDefinitionRegistry) context;
        String beanName = collectorClass.getName();

        if (!registry.isBeanNameInUse(beanName)) {
            BeanDefinitionBuilder builder = BeanDefinitionBuilder.rootBeanDefinition(collectorClass);
            builder.setScope(BeanDefinition.SCOPE_PROTOTYPE);
            AbstractBeanDefinition beanDefinition = builder.getBeanDefinition();

            registry.registerBeanDefinition(beanName, beanDefinition);
        }

        return context.getBean(collectorClass);
    }

    public void destroyBean(Object bean) {
        ((DefaultListableBeanFactory) beanFactory).destroyBean(bean);
    }
}
