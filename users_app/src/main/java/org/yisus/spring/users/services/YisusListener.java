package org.yisus.spring.users.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class YisusListener {
    private static final Logger log = LoggerFactory.getLogger(YisusListener.class);

    @KafkaListener(topics = {"yisus09"})
    public void listener(String message){
        log.info("message received: {}",message);
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        log.info("Mensaje procesado");

    }
}
