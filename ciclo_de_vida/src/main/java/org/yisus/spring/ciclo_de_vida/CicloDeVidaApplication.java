package org.yisus.spring.ciclo_de_vida;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class CicloDeVidaApplication {

	private static Logger log = LoggerFactory.getLogger(CicloDeVidaApplication.class);

//	@Bean(initMethod = "init",destroyMethod = "destroy")
//	public ExplicitBean explicitBean(){
//		return new ExplicitBean();
//	}
	public static void main(String[] args) {

		ConfigurableApplicationContext context = SpringApplication.run(CicloDeVidaApplication.class, args);
		LifeCycleBean lifeCycleBean=context.getBean(LifeCycleBean.class);

	}

}
