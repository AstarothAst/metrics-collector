package com.ast.metricsexample.controller;

import com.ast.metricsexample.metrics.CallCounter1;
import com.ast.metricsexample.metrics.CallDuration1;
import com.ast.metricsexample.services.ServiceThree;
import com.ast.metricsstarter.metrics.Metrics;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class ApiImpl implements Api {

    private final ServiceThree serviceThree;

    @Override
    //@Metrics({CallCounter1.class, CallDuration1.class})
    public String ping() {
        return "pong";
    }

    @Override
    //@Metrics(FailOnRegister.class)
    public UUID error1() {
        return serviceThree.doIt1();
    }

    @Override
    //@Metrics(FailOnRegister.class)
    public UUID error2() {
        return serviceThree.doIt2();
    }

    @Override
    //@Metrics(FailOnRegister.class)
    public UUID error3() {
        return serviceThree.doIt3();
    }
}
