package org.yisus.spring.ciclo_de_vida;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.stereotype.Component;

@Component
public class YisusBeanPostProccesor implements BeanPostProcessor {
    private static final Logger logger = LoggerFactory.getLogger(YisusBeanPostProccesor.class);


    // Este método se ejecuta antes de que el bean sea inicializado
    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) {
        if(bean instanceof LifeCycleBean) {
            logger.warn("****PostProcessBeforeInitialization para el bean: {}", beanName);
            LifeCycleBean lf = (LifeCycleBean) bean;
            logger.warn("Verificando el estado de su dependencia  {}", lf.getSpringBean());
            if(lf.getSpringBean()!=null) lf.setSpringBean(null);
        }
        return bean; // Puedes modificar el bean si es necesario
    }

    // Este método se ejecuta después de que el bean ha sido inicializado
    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) {
        if(bean instanceof LifeCycleBean) {
            logger.warn("****PostProcessAfterInitialization para el bean: {}", beanName);
            LifeCycleBean lf = (LifeCycleBean) bean;
            logger.warn("Verificando el estado de su dependencia  {}", lf.getSpringBean());
        }
        return bean; // Puedes modificar el bean si es necesario
    }
}
