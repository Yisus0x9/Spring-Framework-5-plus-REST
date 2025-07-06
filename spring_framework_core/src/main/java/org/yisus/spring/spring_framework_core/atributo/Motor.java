package org.yisus.spring.spring_framework_core.atributo;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class Motor {
    @Value("Ford")
    private String marca;
    @Value("2020")
    private Integer modelo;


    public Motor() {}

    public Motor(String marca, Integer modelo) {
        this.marca = marca;
        this.modelo = modelo;
    }


    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public Integer getModelo() {
        return modelo;
    }

    public void setModelo(Integer modelo) {
        this.modelo = modelo;
    }

    @Override
    public String toString() {
        return "Motor{" +
                "Marca='" + marca + '\'' +
                ", modelo=" + modelo +
                '}';
    }
}
