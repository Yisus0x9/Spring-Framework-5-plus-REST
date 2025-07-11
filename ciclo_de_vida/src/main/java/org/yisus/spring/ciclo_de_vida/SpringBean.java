package org.yisus.spring.ciclo_de_vida;

import org.springframework.beans.factory.BeanNameAware;
import org.springframework.stereotype.Component;

@Component
public class SpringBean implements BeanNameAware {
    private String name;


    @Override
    public void setBeanName(String name) {
        this.name=name;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "SpringBean{" +
                "name='" + name + '\'' +
                '}';
    }
}
