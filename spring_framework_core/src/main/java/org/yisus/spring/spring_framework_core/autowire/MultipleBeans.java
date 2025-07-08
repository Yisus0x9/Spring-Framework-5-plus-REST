package org.yisus.spring.spring_framework_core.autowire;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.yisus.spring.spring_framework_core.SpringFrameworkCoreApplication;
import org.yisus.spring.spring_framework_core.profiles.EnviromentService;
import org.yisus.spring.spring_framework_core.scopes.BeanPrototype;
import org.yisus.spring.spring_framework_core.scopes.BeanSingleton;

import java.util.List;

@Component
public class MultipleBeans {
    private List<EnviromentService> enviromentServices;
    private static final Logger log = LoggerFactory.getLogger(MultipleBeans.class);

    @Autowired
    public MultipleBeans(List<EnviromentService> enviromentServices) {
        this.enviromentServices = enviromentServices;
    }

    public void printEnviroments(){
        enviromentServices.forEach(enviromentService -> log.info(enviromentService.getEnviromentService()));
    }

    public List<EnviromentService> getEnviromentServices() {
        return enviromentServices;
    }

    public void setEnviromentServices(List<EnviromentService> enviromentServices) {
        this.enviromentServices = enviromentServices;
    }
}
