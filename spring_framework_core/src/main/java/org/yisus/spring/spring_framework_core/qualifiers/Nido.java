package org.yisus.spring.spring_framework_core.qualifiers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component(value = "nidooo")
public class Nido {

    @Autowired
    @Qualifier("pp")
    private Animal animal;
    private static final Logger log = LoggerFactory.getLogger(Nido.class);

    public void print(){
        log.info("En mi nido tengo a este animal: {{}}",animal);
    }
}
