package org.yisus.spring.spring_framework_core.constructor;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class Procesador {
    private String marca;
    private String serie;
    private Integer frecuencia;


    public Procesador(@Value("Intel") String marca,@Value("i7-10700K") String serie,@Value("3") Integer frecuencia) {
        this.marca = marca;
        this.serie = serie;
        this.frecuencia = frecuencia;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getSerie() {
        return serie;
    }

    public void setSerie(String serie) {
        this.serie = serie;
    }

    public Integer getFrecuencia() {
        return frecuencia;
    }

    public void setFrecuencia(Integer frecuencia) {
        this.frecuencia = frecuencia;
    }

    @Override
    public String toString() {
        return "Procesador{" +
                "marca='" + marca + '\'' +
                ", serie='" + serie + '\'' +
                ", frecuencia=" + frecuencia +
                '}';
    }
}
