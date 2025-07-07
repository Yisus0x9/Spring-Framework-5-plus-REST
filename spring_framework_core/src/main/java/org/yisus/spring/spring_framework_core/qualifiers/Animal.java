package org.yisus.spring.spring_framework_core.qualifiers;

public abstract class Animal {
    private Integer edad;
    private String nombre;

    public Animal() {
    }
    public Animal(Integer edad, String nombre) {
        this.edad = edad;
        this.nombre = nombre;
    }
    public Integer getEdad() {
        return edad;
    }

    public void setEdad(Integer edad) {
        this.edad = edad;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
}
