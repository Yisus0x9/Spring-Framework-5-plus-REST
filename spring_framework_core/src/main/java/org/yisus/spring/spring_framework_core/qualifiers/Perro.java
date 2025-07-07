package org.yisus.spring.spring_framework_core.qualifiers;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class Perro extends Animal {


    public Perro(@Value("10") Integer edad,@Value("MAX") String nombre) {
        super(edad, nombre);
    }

    @Override
    public String toString() {
        return "Hola, soy un Perro y mi nombre es " + getNombre() + " y tengo " + getEdad() + " años.";
    }
}
