package org.yisus.spring.spring_framework_core;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.yisus.spring.spring_framework_core.atributo.Coche;
import org.yisus.spring.spring_framework_core.constructor.PlacaMadre;
import org.yisus.spring.spring_framework_core.profiles.EnviromentService;
import org.yisus.spring.spring_framework_core.qualifiers.Animal;
import org.yisus.spring.spring_framework_core.qualifiers.Nido;
import org.yisus.spring.spring_framework_core.qualifiers.Perro;
import org.yisus.spring.spring_framework_core.qualifiers.Volador;
import org.yisus.spring.spring_framework_core.setter.PC;

@SpringBootApplication
public class SpringFrameworkCoreApplication {
    private static final Logger log= LoggerFactory.getLogger(SpringFrameworkCoreApplication.class);

    public static void main(String[] args) {
        ApplicationContext context=SpringApplication.run(SpringFrameworkCoreApplication.class, args);


//        Motor motor = new Motor("Ford", 2020);
//        Coche coche = new Coche("Ford", 2020, motor);
        Coche coche=context.getBean(Coche.class);
        log.info("{}",coche);
        PlacaMadre placaMadre=context.getBean(PlacaMadre.class);
        log.info("{}",placaMadre);
        PC pc=context.getBean(PC.class);
        log.info("{}",pc);

        Nido nido=context.getBean( Nido.class);
        nido.print();

        Volador volador=context.getBean(Volador.class);
        volador.volar();

        EnviromentService env= context.getBean(EnviromentService.class);
        log.info("{}",env.getEnviromentService());
    }

}
