package org.yisus.spring.users.config;

import com.github.javafaker.Faker;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Locale;

@Configuration
public class FakerBeanConfig {

    @Bean
    public Faker getfaker() {
        return new Faker(new Locale("es", "MX")); // Configura Faker para usar el idioma español de Mexico
    }
}
