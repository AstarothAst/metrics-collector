package com.ast.metricsexample.controller;

import com.ast.metricsexample.metrics.CallCounter1;
import com.ast.metricsexample.metrics.CallDuration1;
import com.ast.metricsexample.metrics.FailOnRegister;
import com.ast.metricsexample.services.ServiceThree;
import com.ast.metricsstarter.metrics.Metrics;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class Api {

    private final ServiceThree serviceThree;

    @Metrics({CallCounter1.class, CallDuration1.class})
    @GetMapping("/ping")
    public String ping() {
        return "pong";
    }

    @GetMapping("/error1")
    public UUID error1() {
        return serviceThree.doIt1();
    }

    @GetMapping("/error2")
    public UUID error2() {
        return serviceThree.doIt2();
    }

    @GetMapping("/error3")
    public UUID error3() {
        return serviceThree.doIt3();
    }

    @GetMapping("/error4")
    public UUID error4() {
        return serviceThree.doIt4();
    }
}
