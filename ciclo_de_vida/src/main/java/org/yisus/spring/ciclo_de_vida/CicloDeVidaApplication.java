package org.yisus.spring.ciclo_de_vida;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class CicloDeVidaApplication {

	private static Logger log = LoggerFactory.getLogger(CicloDeVidaApplication.class);
	public static void main(String[] args) {

		SpringApplication.run(CicloDeVidaApplication.class, args);
		ConfigurableApplicationContext context = SpringApplication.run(CicloDeVidaApplication.class, args);
		context.getBean(LyfeCycleBean.class);
	}

}
