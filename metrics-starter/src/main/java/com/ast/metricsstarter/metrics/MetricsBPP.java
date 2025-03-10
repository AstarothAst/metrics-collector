package com.ast.metricsstarter.metrics;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Lazy;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.stereotype.Component;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Supplier;
import java.util.stream.Collectors;

import static java.util.Collections.emptyList;

@Component
public class MetricsBPP implements BeanPostProcessor {

    private final BeanDefinitionRegistry registry;
    private final MetricCollectorsBeanProvider metricCollectorsBeanProvider;

    /**
     * Имя бина -> бин, содержащий методы, помеченные аннотацией {@link Metrics}
     */
    private final Map<String, Object> beansWithMetricAnnotationMap = new HashMap<>();

    @Lazy
    public MetricsBPP(ApplicationContext context,
                      MetricCollectorsBeanProvider metricCollectorsBeanProvider) {
        this.registry = (BeanDefinitionRegistry) context.getAutowireCapableBeanFactory();
        this.metricCollectorsBeanProvider = metricCollectorsBeanProvider;
    }

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        Method[] methods = bean.getClass().getDeclaredMethods();
        boolean needCollectMetrics = Arrays.stream(methods).anyMatch(this::isMetricAnnotationPresent);
        if (needCollectMetrics) {
            beansWithMetricAnnotationMap.put(beanName, bean);
        }
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        if (beansWithMetricAnnotationMap.containsKey(beanName)) {
            return createProxy(beanName);
        } else {
            return bean;
        }
    }

    private Object createProxy(String beanName) {
        Object bean = beansWithMetricAnnotationMap.get(beanName);

        if (bean.getClass().getInterfaces().length == 0) {
            return createCgLibProxy(bean);
        } else {
            return createDynamicProxy(bean, beanName);
        }
    }

    private boolean isMetricAnnotationPresent(Method method) {
        return Optional.ofNullable(AnnotationUtils.findAnnotation(method, Metrics.class)).isPresent();
    }

    private Map<String, List<Class<? extends MetricCollector>>> fillMetricInformation(Object bean) {
        return Arrays.stream(bean.getClass().getDeclaredMethods())
                .filter(this::isMetricAnnotationPresent)
                .collect(Collectors.toMap(
                        Method::getName,
                        method -> Optional.ofNullable(AnnotationUtils.findAnnotation(method, Metrics.class))
                                .map(Metrics::value)
                                .map(List::of)
                                .orElse(emptyList()),
                        (metric1, metric2) -> metric1 // при перегрузке методов могут быть дубликаты
                ));
    }

    private Object createCgLibProxy(Object bean) {
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(bean.getClass());

        enhancer.setCallback((MethodInterceptor) (obj, method, args, proxy) -> {
            List<MetricCollector> metricCollectors = getMetricCollectors(bean, method);
            Object targetResult;
            boolean noErrorFlag = true;

            try {
                callRegisterMetric(metricCollectors);
            } catch (MetricError e) {
                noErrorFlag = false;
            }

            targetResult = proxy.invokeSuper(obj, args);

            if (noErrorFlag) {
                callFillMetric(metricCollectors, targetResult);
            }
            return targetResult;
        });


        Constructor<?>[] declaredConstructors = bean.getClass().getDeclaredConstructors();

        int i = 0;

        return enhancer.create();
    }

    private Object createDynamicProxy(Object bean, String beanName) {
        InvocationHandler handler = (proxy, method, args) -> {
            List<MetricCollector> metricCollectors = getMetricCollectors(bean, method);
            Object targetResult;
            boolean noErrorFlag = true;

            try {
                callRegisterMetric(metricCollectors);
            } catch (MetricError e) {
                noErrorFlag = false;
            }

            targetResult = method.invoke(bean, args);

            if (noErrorFlag) {
                callFillMetric(metricCollectors, targetResult);
            }
            return targetResult;
        };

        Class<?>[] interfaces = getOriginalClassInterfaces(beanName);
        return Proxy.newProxyInstance(bean.getClass().getClassLoader(), interfaces, handler);
    }

    private Class<?>[] getOriginalClassInterfaces(String beanName) {
        try {
            BeanDefinition beanDefinition = registry.getBeanDefinition(beanName);
            Class<?> beanOriginalClass = Class.forName(beanDefinition.getBeanClassName());
            return beanOriginalClass.getInterfaces();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    private List<MetricCollector> getMetricCollectors(Object bean, Method method) {
        return fillMetricInformation(bean).get(method.getName()).stream()
                .map(metricCollectorsBeanProvider::createCollector)
                .toList();
    }

    private List<MetricCollector> callRegisterMetric(List<MetricCollector> metricCollectors) {
        try {
            metricCollectors.forEach(MetricCollector::registerMetric);
            return metricCollectors;
        } catch (Exception e) {
            throw new MetricError();
        }
    }

    private void callFillMetric(List<MetricCollector> metricCollectors, Object targetResult) {
        try {
            metricCollectors.forEach(metricCollector -> {
                metricCollector.fillMetric(targetResult);
                metricCollectorsBeanProvider.destroyBean(metricCollector);
            });
        } catch (Exception e) {
            throw new MetricError();
        }
    }
}
