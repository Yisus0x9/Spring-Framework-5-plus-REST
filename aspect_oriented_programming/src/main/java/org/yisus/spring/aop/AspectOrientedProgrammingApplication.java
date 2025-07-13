package org.yisus.spring.aop;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.yisus.spring.aop.example.anotations.Audit;
import org.yisus.spring.aop.example.controllers.ClientController;
import org.yisus.spring.aop.example.controllers.EmployeeController;
import org.yisus.spring.aop.example.services.AuditService;
import org.yisus.spring.aop.example.services.EmployeeService;

@SpringBootApplication
public class AspectOrientedProgrammingApplication {

	public static void main(String[] args) {
		ConfigurableApplicationContext context = SpringApplication.run(AspectOrientedProgrammingApplication.class, args);
		/*
		TargetObject targetObject = context.getBean(TargetObject.class);
		targetObject.helloWorld("Hello World");
		targetObject.foo("foo");
		 */


		ClientController clientController = context.getBean(ClientController.class);
		EmployeeController employeeController = context.getBean(EmployeeController.class);
		AuditService auditService = context.getBean(AuditService.class);

		clientController.createClient("Jose N","josen@gmail.com");
		clientController.createClient("Jesus N","jesus@gmail.com");

		employeeController.createEmployee("Jose", 30, 50000, "IT");
		employeeController.createEmployee("Paulina", 22, 3000, "RH");

		clientController.updateClient(1,"emailexample@gmail.com");
		employeeController.deleteEmployee(1);

		clientController.listClients();
		employeeController.listEmployees();

		auditService.printAuditLogs();
	}
}
