package org.yisus.spring.ciclo_de_vida;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.BeanNameAware;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component(value = "myBean")
public class LifeCycleBean implements BeanNameAware {
    private SpringBean springBean;
    private static Logger log = LoggerFactory.getLogger(LifeCycleBean.class.getName());


    @Autowired
    public LifeCycleBean(SpringBean springBean) {
        this.springBean = springBean;
    }


    @PostConstruct
    public void init(){
        log.info("LyfeCycleBean init");
        log.info("Verficando dependencia: {}",springBean);
    }

    @Override
    public void setBeanName(String name) {
        log.info("Evento recibido, el nombre del bean es: {}", name);
        log.info("Verficando dependencia cuando se ejecutan las Aware: {}",springBean);

    }

    public SpringBean getSpringBean() {
        return springBean;
    }

    public void setSpringBean(SpringBean springBean) {
        this.springBean = springBean;
    }

    @PreDestroy
    public void destroy(){
        log.info("LyfeCycleBean destroy");
        log.info("Verficando dependencia: {}",springBean);
    }

}
