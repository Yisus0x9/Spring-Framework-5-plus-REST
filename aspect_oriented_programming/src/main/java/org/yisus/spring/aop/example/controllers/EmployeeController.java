package org.yisus.spring.aop.example.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.yisus.spring.aop.example.services.EmployeeService;

@Component
public class EmployeeController {
    @Autowired
    private EmployeeService employeeService;
    private static Logger log = LoggerFactory.getLogger(EmployeeController.class);

    public void createEmployee(String name,Integer age, Integer salary,String area) {
        log.info("Se ha creado el empleado con exito: {}", employeeService.createEmploye(name, age, salary, area));
    }

    public void updateEmployeeSalary(Integer id, Integer newSalary) {
        log.info("Se ha actualizado el salario del empleado con exito: {}", employeeService.updateSalary(id, newSalary));
    }

    public void deleteEmployee(Integer id) {
        log.info("{}", employeeService.deleteEmploye(id));
    }

    public void listEmployees() {
        log.info("Lista de empleados: {}", employeeService.getEmployes().stream().toList());
    }

}

