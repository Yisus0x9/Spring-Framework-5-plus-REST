package org.yisus.spring.spring_framework_core.qualifiers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component("pp") // Puedes usar múltiples nombres de bean
public class Pato extends Animal implements Volador{

    private static Logger log= LoggerFactory.getLogger(Pato.class);

    public Pato(@Value("1")Integer edad,@Value("Quackity") String nombre) {
        super(edad, nombre);
    }

    @Override
    public void volar() {
        log.info("El pato está volando");
    }

    @Override
    public String toString() {
        return "Hola, soy un Pato y mi nombre es " + getNombre() + " y tengo " + getEdad() + " años.";
    }
}
