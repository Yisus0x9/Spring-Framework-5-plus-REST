package org.yisus.spring.spring_framework_core.constructor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class PlacaMadre {
    private String marca;
    private String refrigeracion;
    private Integer modelo;
    private Procesador procesador;

    @Autowired
    public PlacaMadre(@Value("Asus") String marca, @Value("liquida") String refrigeracion, @Value("2023") Integer modelo, Procesador procesador) {
        this.marca = marca;
        this.refrigeracion = refrigeracion;
        this.modelo = modelo;
        this.procesador = procesador;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getRefrigeracion() {
        return refrigeracion;
    }

    public void setRefrigeracion(String refrigeracion) {
        this.refrigeracion = refrigeracion;
    }

    public Procesador getProcesador() {
        return procesador;
    }

    public void setProcesador(Procesador procesador) {
        this.procesador = procesador;
    }

    public Integer getModelo() {
        return modelo;
    }

    public void setModelo(Integer modelo) {
        this.modelo = modelo;
    }

    @Override
    public String toString() {
        return "PlacaMadre{" +
                "marca='" + marca + '\'' +
                ", refrigeracion='" + refrigeracion + '\'' +
                ", modelo=" + modelo +
                ", procesador=" + procesador +
                '}';
    }
}
