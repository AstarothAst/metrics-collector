package com.ast.metricsexample.services;

import com.ast.metricsexample.metrics.CallCounter2;
import com.ast.metricsexample.metrics.CallDuration2;
import com.ast.metricsstarter.metrics.Metrics;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
public class ServiceOneImpl implements ServiceOne {

    @Override
    @Metrics({CallCounter2.class, CallDuration2.class})
    public void doit1() {
        //nothing to do
    }

    @Override
    public String doit2() {
        Random rnd = new Random();
        int length = rnd.nextInt(10 - 1) + 1;
        return randomString(length);
    }

    private String randomString(int length) {
        int leftLimit = 97; // 'a'
        int rightLimit = 122; //  'z'

        Random random = new Random();
        return random.ints(leftLimit, rightLimit + 1)
                .limit(length)
                .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                .toString();
    }
}
