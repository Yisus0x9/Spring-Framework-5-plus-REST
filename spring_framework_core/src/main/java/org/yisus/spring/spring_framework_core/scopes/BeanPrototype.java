package org.yisus.spring.spring_framework_core.scopes;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope("prototype")
public class BeanPrototype {
    private String name;

    public BeanPrototype( @Value("prototype")String name) {
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
