package org.yisus.spring.spring_framework_core;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.yisus.spring.spring_framework_core.constructor.PlacaMadre;

@Component
public class PC {
    private Integer peso;
    private String monitor;
    private String fuenteAlimentacion;
    private PlacaMadre placaMadre;

    public Integer getPeso() {
        return peso;
    }

    @Value("100")
    public void setPeso(Integer peso) {
        this.peso = peso;
    }

    public String getMonitor() {
        return monitor;
    }

    @Value("ASUS NEBULA")
    public void setMonitor(String monitor) {
        this.monitor = monitor;
    }

    public PlacaMadre getPlacaMadre() {
        return placaMadre;
    }

    @Autowired
    public void setPlacaMadre(PlacaMadre placaMadre) {
        this.placaMadre = placaMadre;
    }

    public String getFuenteAlimentacion() {
        return fuenteAlimentacion;
    }

    @Value("Generica")
    public void setFuenteAlimentacion(String fuenteAlimentacion) {
        this.fuenteAlimentacion = fuenteAlimentacion;
    }

    @Override
    public String toString() {
        return "PC{" +
                "peso=" + peso +
                ", monitor='" + monitor + '\'' +
                ", fuenteAlimentacion='" + fuenteAlimentacion + '\'' +
                ", placaMadre=" + placaMadre +
                '}';
    }
}
