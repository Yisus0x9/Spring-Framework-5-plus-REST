package org.yisus.spring.spring_framework_core.profiles;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Service
@Profile("prod")
public class ProdEnviromentService implements EnviromentService{
    @Override
    public String getEnviromentService() {
        return "Production Enviroment";
    }
}
