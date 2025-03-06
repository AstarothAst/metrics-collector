package com.ast.metricsexample.services;

import com.ast.metricsexample.metrics.FailOnBoth;
import com.ast.metricsexample.metrics.FailOnFill;
import com.ast.metricsexample.metrics.FailOnRegister;
import com.ast.metricsstarter.metrics.Metrics;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class ServiceThree {

    @Metrics(FailOnRegister.class)
    public UUID doIt1(){
        System.out.println("Все сработало - FailOnRegister");
        return UUID.randomUUID();
    }

    @Metrics(FailOnFill.class)
    public UUID doIt2(){
        System.out.println("Все сработало - FailOnFill");
        return UUID.randomUUID();
    }

    @Metrics(FailOnBoth.class)
    public UUID doIt3(){
        System.out.println("Все сработало - FailOnBoth");
        return UUID.randomUUID();
    }
}
