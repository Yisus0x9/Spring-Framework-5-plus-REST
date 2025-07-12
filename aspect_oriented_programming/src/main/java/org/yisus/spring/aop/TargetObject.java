package org.yisus.spring.aop;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class TargetObject {
    private static final Logger log = LoggerFactory.getLogger(TargetObject.class);

    protected void helloWorld(String message) {
        log.info("{}",message);
    }

    public void foo(String message) {
        log.info("foo: {}",message);
    }
}
