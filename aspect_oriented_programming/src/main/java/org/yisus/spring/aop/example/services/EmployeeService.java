package org.yisus.spring.aop.example.services;

import org.springframework.stereotype.Service;
import org.yisus.spring.aop.example.anotations.Audit;
import org.yisus.spring.aop.example.models.Employee;

import java.util.ArrayList;
import java.util.List;

@Service
public class EmployeeService {
    private List<Employee> employees=new ArrayList<>();



    @Audit
    public Employee createEmploye(String name, Integer age, Integer salary, String area) {
        Employee employee =new Employee(employees.size(),name, age, salary, area);
        employees.add(employee);
        return employee;
    }

    @Audit
    public Employee updateSalary(Integer id, Integer newSalary) {
        Employee employeeFind =employees.stream().filter(employee -> employee.getSalary().equals(newSalary)).findFirst().orElse(null);
        if(employeeFind !=null) {
            employeeFind.setSalary(newSalary);}
        return employeeFind;
    }

    @Audit
    public String deleteEmploye(Integer id) {
        Employee employeeFind =employees.stream().filter(employee -> employee.getId().equals(id)).findFirst().orElse(null);
        if(employeeFind !=null) {
            employees.remove(employeeFind);
            return "Employe with id "+id+" deleted successfully";
        }
        return "Employe with id "+id+" not found";
    }

    public List<Employee> getEmployes() {
        return employees;
    }


    public void setEmployes(List<Employee> employees) {
        this.employees = employees;
    }
}
