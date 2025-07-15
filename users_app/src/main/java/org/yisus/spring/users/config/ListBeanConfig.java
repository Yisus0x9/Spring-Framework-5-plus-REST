package org.yisus.spring.users.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;



@Configuration
public class ListBeanConfig {

    @Bean("list")
    public ArrayList getList() {
        return new ArrayList<>();
    }
}
