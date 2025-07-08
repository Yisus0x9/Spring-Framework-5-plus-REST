package org.yisus.spring.spring_framework_core.profiles;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

@Service
@Profile(value = {"dev","default"})
public class DevEnviromentService  implements EnviromentService {
    @Override
    public String getEnviromentService() {
        return "Dev Enviroment";
    }
}
