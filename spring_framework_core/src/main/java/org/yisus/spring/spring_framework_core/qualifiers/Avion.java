package org.yisus.spring.spring_framework_core.qualifiers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

@Component
@Primary
public class Avion implements Volador {
    private static final Logger log= LoggerFactory.getLogger(Avion.class);
    @Override
    public void volar() {
        log.info("El avión está volando");
    }
}
