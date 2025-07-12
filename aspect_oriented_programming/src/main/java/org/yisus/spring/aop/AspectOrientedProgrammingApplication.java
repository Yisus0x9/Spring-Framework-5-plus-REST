package org.yisus.spring.aop;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class AspectOrientedProgrammingApplication {

	public static void main(String[] args) {
		ConfigurableApplicationContext context = SpringApplication.run(AspectOrientedProgrammingApplication.class, args);
		TargetObject targetObject = context.getBean(TargetObject.class);
		targetObject.helloWorld("Hello World");
		targetObject.foo("foo");
	}
}
