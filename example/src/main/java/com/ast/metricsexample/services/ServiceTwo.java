package com.ast.metricsexample.services;

import com.ast.metricsexample.metrics.CallCounter3;
import com.ast.metricsstarter.metrics.Metrics;
import org.springframework.stereotype.Service;

@Service
public class ServiceTwo {

    @Metrics(CallCounter3.class)
    void doit(){
        //nothing to do
    }
}
