package com.ast.metricsstarter.metrics;

public interface MetricCollector {

    /**
     * Имя метрики
     */
    String getMetricName();

    /**
     * Описание метрики
     */
    String getDescription();

    /**
     * Регистрация метрики в регистре микромета
     */
    void registerMetric();

    /**
     * Заполнение метрики в регистре микрометра
     *
     * @param targetResult Результат выполнения целевого метода, который помечен аннотацией {@link Metrics}
     */
    void fillMetric(Object targetResult);

    default void fillError(Exception e) {
        fillErrorMetric(e);
    }

    /**
     * Заполнение метрики, связанной с ошибками
     *
     * @param e исключение приведшее к ошибке, после обработки должно быть проброшено выше
     */
    default void fillErrorMetric(Exception e) {
        //nothing to do
    }
}
