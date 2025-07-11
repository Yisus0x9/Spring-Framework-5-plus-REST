package org.yisus.spring.spring_framework_core.scopes;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope("singleton") //redundante por default todos los beans son singleton
public class BeanSingleton {
    private String name;

    public BeanSingleton( @Value("${bean.name.singleton}")String name) {
        this.name = name;
    }

    public String getBean(){
        return "mi bean es un bean " + name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
