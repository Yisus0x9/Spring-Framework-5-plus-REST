package org.yisus.spring.spring_framework_core;

import ch.qos.logback.core.encoder.JsonEscapeUtil;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.yisus.spring.spring_framework_core.atributo.Coche;
import org.yisus.spring.spring_framework_core.atributo.Motor;
import org.yisus.spring.spring_framework_core.constructor.PlacaMadre;

import java.sql.SQLOutput;

@SpringBootApplication
public class SpringFrameworkCoreApplication {

    public static void main(String[] args) {
        ApplicationContext context=SpringApplication.run(SpringFrameworkCoreApplication.class, args);


//        Motor motor = new Motor("Ford", 2020);
//        Coche coche = new Coche("Ford", 2020, motor);
        Coche coche=context.getBean(Coche.class);
        System.out.println(coche);
        PlacaMadre placaMadre=context.getBean(PlacaMadre.class);
        System.out.println(placaMadre);
        PC pc=context.getBean(PC.class);
        System.out.println(pc);
    }

}
