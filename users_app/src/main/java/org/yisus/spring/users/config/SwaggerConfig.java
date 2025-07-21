package org.yisus.spring.users.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI api() {
        return new OpenAPI().info(info());
    }

    @Bean
    public GroupedOpenApi publicApi() {
        return GroupedOpenApi.builder()
                .group("public")
                .pathsToExclude("/yisus/**") // excluye todas las rutas que empiecen con /api/yisus
                .build();
    }

    private Info info() {
        return new Info().title("Users API")
                .version("1.0.0")
                .description("API REST creada para aprender Spring Boot")
                .license(new License().name("Apache 2.0").url("http://springdoc.org"))
                .contact(new Contact().name("Jesus Peñarrieta Villa").url("https://github.com/Yisus0x9").email("jesuspenarrieta03@gmail.com"));
    }

}
