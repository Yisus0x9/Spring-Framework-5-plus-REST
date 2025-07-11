package org.yisus.spring.ciclo_de_vida;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.BeanNameAware;
import org.springframework.stereotype.Component;

@Component(value = "myBean")
public class LyfeCycleBean implements BeanNameAware {
    private static Logger log = LoggerFactory.getLogger(LyfeCycleBean.class.getName());


    @Override
    public void setBeanName(String name) {
        log.info("Evento recibido, el nombre del bean es: {}", name);
    }


}
