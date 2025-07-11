package org.yisus.spring.ciclo_de_vida;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.BeanNameAware;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component(value = "myBean")
@Scope("singleton")
public class LifeCycleBean implements BeanNameAware, InitializingBean, DisposableBean {


    private SpringBean springBean;
    private static Logger log = LoggerFactory.getLogger(LifeCycleBean.class.getName());

    @Override
    public void afterPropertiesSet() throws Exception {
        log.info("afterPropertiesSet");
        log.info("Verficando dependencia: {}",springBean);
    }

    @Autowired
    public LifeCycleBean(SpringBean springBean) {
        this.springBean = springBean;
    }


    @PostConstruct
    public void init(){
        log.info("LifeCycleBean init");
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
    public void preDestroy(){
        log.info("LifeCycleBean Pre-destroy");
        log.info("Verficando dependencia: {}",springBean);
    }

    @Override
    public void destroy() throws Exception {
        log.info("LifeCycleBean destroy");
        log.info("Verficando dependencia: {}",springBean);
    }
}
