package com.ast.metricsexample.controller;

import com.ast.metricsexample.metrics.CallCounter1;
import com.ast.metricsexample.metrics.CallDuration1;
import com.ast.metricsstarter.metrics.Metrics;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

public interface Api {

    @GetMapping("/ping")
    //@Metrics({CallCounter1.class, CallDuration1.class})
    String ping();

    //@GetMapping("/error1")
        //@Metrics(FailOnRegister.class)
    UUID error1();

    //@GetMapping("/error2")
        //@Metrics(FailOnRegister.class)
    UUID error2();

    //@GetMapping("/error3")
        //@Metrics(FailOnRegister.class)
    UUID error3();
}
